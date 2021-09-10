package com.example.demo.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class OrdenService {
    public static Date creacion(){
        return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
