package org.heigit.ors.api.controllers;

import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

import org.heigit.ors.api.APIEnums;
import org.heigit.ors.api.EndpointsProperties;
import org.heigit.ors.api.SystemMessageProperties;
import org.heigit.ors.api.errors.CommonResponseEntityExceptionHandler;
import org.heigit.ors.api.responses.routing.geojson.BasicData;
import org.heigit.ors.api.services.MatrixRoutingService;
import org.heigit.ors.api.services.RoutingService;
import org.heigit.ors.routing.RoutingErrorCodes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/matrix-directions")
public class MatrixRoutingAPI {
    static final CommonResponseEntityExceptionHandler errorHandler = new CommonResponseEntityExceptionHandler(RoutingErrorCodes.BASE);

    private final EndpointsProperties endpointsProperties;
    private final SystemMessageProperties systemMessageProperties;
    private final RoutingService routingService;
    private final MatrixRoutingService matrixRoutingService;

    public MatrixRoutingAPI(EndpointsProperties endpointsProperties, SystemMessageProperties systemMessageProperties, RoutingService routingService, MatrixRoutingService matrixRoutingService) {
        this.endpointsProperties = endpointsProperties;
        this.systemMessageProperties = systemMessageProperties;
        this.routingService = routingService;
        this.matrixRoutingService = matrixRoutingService;
    }

    @GetMapping(value = "/{profile}")
    public List<BasicData> getSimpleData(@Parameter(description = "Specifies the matrix profile.", required = true, example = "driving-car") @PathVariable APIEnums.Profile profile,
                                         @RequestParam String paths) {
        return matrixRoutingService.getSimpleData(profile, paths);
    }

}
