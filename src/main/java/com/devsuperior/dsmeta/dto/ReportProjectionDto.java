package com.devsuperior.dsmeta.dto;
import com.devsuperior.dsmeta.projections.ReportProjection;

import java.time.LocalDate;

public class ReportProjectionDto {
    private Long id;
    private Double amount;
    private LocalDate date;
    private String name ;

    public ReportProjectionDto() {
    }

    public ReportProjectionDto(Long id, Double amount, LocalDate date) {
        id = id;
        amount = amount;
        date = date;
        name = name;
    }

    public ReportProjectionDto(ReportProjection projection) {
        id = projection.getId();
        amount = projection.getAmount();
        date = projection.getDate();
        name = projection.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
