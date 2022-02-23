package com.example.metrikenbibiiothek.metrics;


import com.example.metrikenbibiiothek.metrics.count.MyCount;
import com.example.metrikenbibiiothek.metrics.gauge.MyGauge;
import com.example.metrikenbibiiothek.metrics.gauge.MyGaugeManuel;
import com.example.metrikenbibiiothek.metrics.time.MyTime;
import com.example.metrikenbibiiothek.metrics.time.MyTimeManuel;


public class MeasureFactory {

    private static MeasureFactory factoryInstance = null;

    MeterRegisterService service = MeterRegisterService.getInstance();
    public MeasureFactory(){}

    public MeasureType getMeasure(MetricType measure){
        if(measure == null){
            return null;
        }

        if( measure == MetricType.COUNT){

            return new MyCount(service.getRegistry());
        }
        if( measure == MetricType.TIME){

            return new MyTime(service.getRegistry());
        }
        if( measure == MetricType.GAUGE){

            return new MyGauge(service.getRegistry());
        }
        if( measure == MetricType.TIME_MANUEL){

            return new MyTimeManuel(service.getRegistry());
        }

        if(measure == MetricType.GAUGE_MANUEL){

            return new MyGaugeManuel(service.getRegistry());
        }
        return null;
    }

    public static MeasureFactory getInstance()
    {
        if (factoryInstance == null)
            factoryInstance = new MeasureFactory();

        return factoryInstance;
    }

}
