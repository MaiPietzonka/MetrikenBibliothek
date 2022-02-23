package com.example.metrikenbibiiothek.metrics;

import com.example.metrikenbibiiothek.StoreObject;

public interface MeasureType<T> {

    void storeData(StoreObject object, T... c );


}
