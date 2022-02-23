package com.example.metrikenbibiiothek;

import org.springframework.stereotype.Component;


public class StoreData<T> {


    public void storeData(StoreObject so, T ... tmp){
        so.getMetricType().storeData(so, tmp);
    }

}
