package com.example.metrikenbibiiothek.metrics.gauge;

import com.example.metrikenbibiiothek.StoreObject;
import com.example.metrikenbibiiothek.metrics.MeasureType;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class MyGaugeManuel<T> implements MeasureType {
    private static String table = "gaugeTable";
    //    private static int gauge = Metrics.gauge("gauge",100);
    private static final AtomicInteger myGauge = new AtomicInteger(0);
    private String direction;
    private MeterRegistry meterRegistry;

    public MyGaugeManuel(MeterRegistry meter){
        meterRegistry = meter;
    }


    @Override
    public void storeData(StoreObject object, Object... ds) {


        meterRegistry.gauge(object.getTableName(), myGauge);

        int incDec = object.getIncDec();
        if(incDec != 0){
            incrementDecrement(incDec);
        }

        System.out.println("Test bei Gauge:    " + myGauge );

    }

    private void incrementDecrement(int number){
        this.myGauge.addAndGet(number);
    }




}
