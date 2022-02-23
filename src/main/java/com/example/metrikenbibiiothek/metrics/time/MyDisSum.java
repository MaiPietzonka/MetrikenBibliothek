package com.example.metrikenbibiiothek.metrics.time;

import com.example.metrikenbibiiothek.StoreObject;
import com.example.metrikenbibiiothek.metrics.MeasureType;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Metrics;

public class MyDisSum implements MeasureType {

    private DistributionSummary dis;

    @Override
    public void storeData(StoreObject object, Object[] c) {
        dis = DistributionSummary.builder(object.getTableName()).register(Metrics.globalRegistry);
        dis.record(5);
    }



}
