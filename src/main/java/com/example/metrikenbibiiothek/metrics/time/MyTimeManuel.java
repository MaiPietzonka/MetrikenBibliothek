package com.example.metrikenbibiiothek.metrics.time;

import com.example.metrikenbibiiothek.StoreObject;
import com.example.metrikenbibiiothek.metrics.MeasureType;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class MyTimeManuel implements MeasureType {


    private Timer.Sample sample;
    private MeterRegistry meterRegistry;

    public MyTimeManuel(MeterRegistry meter){
        this.meterRegistry = meter;
    }
    @Override
    public void storeData(StoreObject object, Object... c) {
        String value = Arrays.stream(c).iterator().next().toString();
        if(value.equals("start")){
            System.out.println("start");
            startTimer();
        }
        if(value.equals("stop")){
            System.out.println("stop");
            stopTimer(object.getTableName());
        }
    }

    private void startTimer(){
        sample = Timer.start(meterRegistry);
    }

    private void stopTimer(String table){
        sample.stop(meterRegistry.timer(table));
    }

    private void setTable(){

    }
}