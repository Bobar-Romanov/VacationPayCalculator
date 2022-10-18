package com.neoflex.Calculator.models;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VacantionPay {
    private double salary;
    private int days;
    private LocalDate startDate;
    private double validation;


    public VacantionPay(double salary, int days, double validation){
        this.salary = salary;
        this.days = days;
        this.validation = validation;
    }

    public VacantionPay(double salary, int days, LocalDate startDate, double validation) {
        this.salary = salary;
        this.days = days;
        this.startDate = startDate;
        this.validation = validation;
    }

    public VacantionPay() {
    }

}

