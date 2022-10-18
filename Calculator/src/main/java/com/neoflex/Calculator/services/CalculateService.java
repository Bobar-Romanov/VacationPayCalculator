package com.neoflex.Calculator.services;


import com.neoflex.Calculator.clients.DayOffFeignClient;
import com.neoflex.Calculator.models.VacantionPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
            int salary = Integer.parseInt(strSalary);
            int days = Integer.parseInt(strDays);
            int count = days;
            if(strStartDate.equals("")){
                VacantionPay vacantionPay = new VacantionPay(salary, days, count*days);
                vacantionPay.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
               return vacantionPay;
            }
            DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(strStartDate, dateformatter);
            LocalDate curDate = startDate;
            for(int i = 0; i < days; i++){
            if(dayOffFeignClient.getDayOff(curDate).equals("1") && !curDate.getDayOfWeek().toString().equals("SATURDAY")
                    && !curDate.getDayOfWeek().toString().equals("SUNDAY") ) {
                        count--;
                }
                curDate = curDate.plusDays(1);
            }
            System.out.println(salary * count);

            return new VacantionPay();
        }
}
