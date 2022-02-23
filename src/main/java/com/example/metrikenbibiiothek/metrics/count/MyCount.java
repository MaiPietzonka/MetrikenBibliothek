package com.example.metrikenbibiiothek.metrics.count;

import com.example.metrikenbibiiothek.StoreObject;
import com.example.metrikenbibiiothek.metrics.MeasureType;
import io.micrometer.core.instrument.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyCount<T> implements MeasureType {



    private Counter counter;
    private double incVal;

    private MeterRegistry meterRegistry;

    public MyCount(MeterRegistry _registry){
        System.out.println("im myCount Konstr???????????????????????");

       this.meterRegistry = _registry;
    }

    public MyCount(){
        this.incVal = 1.0;
    }
    public MyCount(double value){
        this.incVal = value;
    }



    @Override
    public void storeData(StoreObject object, Object[] c) {


        List<String> keyTags = getKey(object.getTag());
        List<String> valTags = getVal(object.getTag());


        myCount(object.getTableName(), keyTags, valTags);

    }

    private void myCount(String tablename, List<String> _key, List<String> _val){
        if(_key.size() == 1){
           meterRegistry.counter(tablename,_key.get(0),_val.get(0)).increment();
        }
        if(_key.size()==2){
            meterRegistry.counter(tablename,_key.get(0),_val.get(0),_key.get(1),_val.get(1)).increment();
        }

        if(_key.size()==3){
            meterRegistry.counter(tablename,_key.get(0),_val.get(0),_key.get(1),_val.get(1),_key.get(2),_val.get(2)).increment();
        }

    }



    private List<String> getKey(Map<String, String> map){
        List<String> keyList = new ArrayList<>();
        for (Map.Entry<String, String> entry: map.entrySet()){
            keyList.add(entry.getKey());
        }
        return keyList;
    }


    private List<String> getVal(Map<String, String> map){
        List<String> valList = new ArrayList<>();

        for (Map.Entry<String, String> entry: map.entrySet()){
            valList.add(entry.getValue());
        }

        return valList;
    }



}
