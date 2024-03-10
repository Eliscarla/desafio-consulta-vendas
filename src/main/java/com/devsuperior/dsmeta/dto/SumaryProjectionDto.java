package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SumaryProjection;

public class SumaryProjectionDto {

    private String name;
    private Double amount;

    public SumaryProjectionDto() {
    }

    public SumaryProjectionDto(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    public SumaryProjectionDto(SumaryProjection projectionConsulta2) {
        amount = projectionConsulta2.getAmount();
        name = projectionConsulta2.getName();
    };

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
