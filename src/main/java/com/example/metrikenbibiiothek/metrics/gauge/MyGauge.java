package com.example.metrikenbibiiothek.metrics.gauge;

import com.example.metrikenbibiiothek.StoreObject;
import com.example.metrikenbibiiothek.metrics.MeasureType;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;

public class MyGauge implements MeasureType {

    private MeterRegistry registry;
    public MyGauge(MeterRegistry reg){
        registry = reg;
    }

    @Override
    public void storeData(StoreObject object, Object... c) {
        String keyTag = getKey(object.getTag());
        Map<String, String> map  = object.getTag();
        String valueTag = map.get(keyTag);
//        Supplier<Number> doubleSupplier = ()-> Arrays.stream(c).count();
        Supplier<Number>  sup = object.getSupplier();
        Gauge g = Gauge.builder(object.getTableName(), sup).tag(keyTag, valueTag).register(registry);

    }

    private String getKey(Map<String,String> map){
        String key = "";
        for(Map.Entry<String,String> pair: map.entrySet()){
            key = pair.getKey();
        }
        return key;
    }



}