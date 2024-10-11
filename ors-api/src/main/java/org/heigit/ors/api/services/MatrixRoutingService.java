package org.heigit.ors.api.services;

import java.util.Arrays;
import java.util.List;

import org.heigit.ors.api.APIEnums;
import org.heigit.ors.api.requests.routing.RouteRequest;
import org.heigit.ors.api.responses.routing.geojson.BasicData;
import org.heigit.ors.exceptions.StatusCodeException;
import org.heigit.ors.routing.RouteResult;
import org.springframework.stereotype.Service;

@Service
public class MatrixRoutingService {
    private final RoutingService routingService;

    public MatrixRoutingService(RoutingService routingService) {
        this.routingService = routingService;
    }

    public List<BasicData> getSimpleData(APIEnums.Profile profile, String paths) {
        List<List<Double>> coordinates = Arrays.stream(paths.split(";"))
                .map(coord -> Arrays.stream(coord.split(","))
                        .map(Double::parseDouble)
                        .toList())
                .toList();

        List<Double> userCoordinates = coordinates.get(0);
        List<List<Double>> sectionCoordinates = coordinates.subList(1, coordinates.size());
        return sectionCoordinates.stream()
                .map(sectionCoords -> {
                    RouteRequest request = new RouteRequest(List.of(userCoordinates, sectionCoords));
                    request.setProfile(profile);
                    RouteResult[] result;
                    try {
                        result = routingService.generateRouteFromRequest(request);
                    } catch (StatusCodeException e) {
                        throw new RuntimeException(e);
                    }
                    BasicData basicData = new BasicData();
                    basicData.setDistance(result[0].getSummary().getDistance());
                    basicData.setDuration(result[0].getSummary().getDuration());
                    return basicData;
                })
                .toList();
    }
}
