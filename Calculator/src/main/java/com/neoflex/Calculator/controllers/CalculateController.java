package com.neoflex.Calculator.controllers;



import com.neoflex.Calculator.models.VacantionPay;
import com.neoflex.Calculator.services.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
    public class CalculateController {

    @Autowired
    private CalculateService calculateService;

    @GetMapping(value = "/calculate")
    public ResponseEntity<?> calculateValidation(@RequestParam String salary, @RequestParam String days, @RequestParam
            (required = false)String date)
        {
            return new ResponseEntity<>(calculateService.calculateVacantionPay(salary, days, date), HttpStatus.OK);
        }
    }

