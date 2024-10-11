package org.heigit.ors.api.responses.routing.geojson;

import java.util.Objects;

public class BasicData {
    private Double distance;
    private Double duration;

    public BasicData() {
    }

    public BasicData(Double distance, Double duration) {
        this.distance = distance;
        this.duration = duration;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BasicData basicData)) {
            return false;
        }
        return Objects.equals(distance, basicData.distance) && Objects.equals(duration, basicData.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, duration);
    }
}
