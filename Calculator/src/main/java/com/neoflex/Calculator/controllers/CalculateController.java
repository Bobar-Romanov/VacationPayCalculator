package com.neoflex.Calculator.controllers;


import ch.qos.logback.core.CoreConstants;
import com.neoflex.Calculator.forms.CalculateForm;

import com.neoflex.Calculator.forms.CalculateFormValidation;
import com.neoflex.Calculator.models.VacantionPay;
import com.neoflex.Calculator.services.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class CalculateController {

    @Autowired
    private CalculateService calculateService;

    @GetMapping("/calculate/{salary}/{days}")
    public VacantionPay calculateValidation(@PathVariable String salary, @PathVariable String days)
        {
            System.out.println("zxc");
            return calculateService.calculateVacantionPay(salary, days, "");
        }
    }
