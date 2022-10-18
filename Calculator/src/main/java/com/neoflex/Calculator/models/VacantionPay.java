package com.neoflex.Calculator.models;


import java.time.LocalDate;

public class VacantionPay {
    private int salary;
    private int days;
    private LocalDate startDate;
    private int validation;
    private int port;

    public VacantionPay(int salary, int days, int validation){
        this.salary = salary;
        this.days = days;
        this.validation = validation;
    }

    public VacantionPay(int salary, int days, LocalDate startDate, int validation) {
        this.salary = salary;
        this.days = days;
        this.startDate = startDate;
        this.validation = validation;
    }

    public VacantionPay() {
    }

    public void setPort(int port) {
        this.port = port;
    }
}

