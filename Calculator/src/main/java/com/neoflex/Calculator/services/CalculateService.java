package com.neoflex.Calculator.services;


import com.neoflex.Calculator.clients.DayOffFeignClient;
import com.neoflex.Calculator.models.VacantionPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.zip.DataFormatException;

@Service
public class CalculateService {

    private DayOffFeignClient dayOffFeignClient;

    @Autowired
    public CalculateService(DayOffFeignClient dayOffFeignClient) {
        this.dayOffFeignClient = dayOffFeignClient;
    }

    @Autowired
    private Environment environment;

    public VacantionPay calculateVacantionPay(String strSalary, String strDays, String strStartDate){
            double salary = 0;
            int days = 0;
            try {
                salary = Double.parseDouble(strSalary);
                days = Integer.parseInt(strDays);
            }catch (NumberFormatException e){
               return new VacantionPay();
            }
            int count = days;
            if(days <= 0 || salary <= 0){
                return new VacantionPay();
            }
            if(strStartDate == null){
                VacantionPay vacantionPay = new VacantionPay(salary, days, (salary/29.3)*count);
                return vacantionPay;
            }
            LocalDate startDate = null;
            try{
                DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                startDate = LocalDate.parse(strStartDate, dateformatter);
            }catch (DateTimeParseException e){
                return new VacantionPay();
            }
            LocalDate curDate = startDate;
            for(int i = 0; i < days; i++){
            if(dayOffFeignClient.getDayOff(curDate).equals("1") && !curDate.getDayOfWeek().toString().equals("SATURDAY")
                    && !curDate.getDayOfWeek().toString().equals("SUNDAY") ) {
                        count--;
                }
                curDate = curDate.plusDays(1);
            }
            System.out.println(salary * count);

            return new VacantionPay(salary, days, startDate,(salary/29.3)*count);
        }
}
