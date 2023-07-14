package com.bayzdelivery.dto;

import java.util.List;

public class TopDeliveryMenDto {
    private List<PersonResponse> personResponseList;

    private Double averageCommission;

    public List<PersonResponse> getPersonResponseList() {
        return personResponseList;
    }

    public void setPersonResponseList(List<PersonResponse> personResponseList) {
        this.personResponseList = personResponseList;
    }

    public Double getAverageCommission() {
        return averageCommission;
    }

    public void setAverageCommission(Double averageCommission) {
        this.averageCommission = averageCommission;
    }

}
