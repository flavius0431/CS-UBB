package com.example.practic.domain;

public class TrainStation extends Entity<Long>{

    private Long departureCityId;
    private Long destinationCityId;

    public TrainStation(Long departureCityId, Long destinationCityId) {
        this.departureCityId = departureCityId;
        this.destinationCityId = destinationCityId;
    }

    public Long getDepartureCityId() {
        return departureCityId;
    }

    public void setDepartureCityId(Long departureCityId) {
        this.departureCityId = departureCityId;
    }

    public Long getDestinationCityId() {
        return destinationCityId;
    }

    public void setDestinationCityId(Long destinationCityId) {
        this.destinationCityId = destinationCityId;
    }
}
