package com.example.metrikenbibiiothek.metrics.time;

import com.example.metrikenbibiiothek.StoreObject;
import com.example.metrikenbibiiothek.metrics.MeasureType;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MyTime<T> implements MeasureType {

    private Timer timer;
    private MeterRegistry meterRegistry;

    public MyTime(MeterRegistry meter ){
        this.meterRegistry = meter;
    }

    @Override
    public void storeData(StoreObject object, Object... c) {
        String keyTag = getKey(object.getTag());
        Map<String, String> map  = object.getTag();
        String valueTag = map.get(keyTag);
        timer = Timer.builder(object.getTableName()).tag(keyTag,valueTag).register(meterRegistry);
        timer.record(object.getSupplier());


    }
    private String getKey(Map<String,String> map){
        String key = "";
        for(Map.Entry<String,String> pair: map.entrySet()){
            key = pair.getKey();
        }
        return key;
    }



}