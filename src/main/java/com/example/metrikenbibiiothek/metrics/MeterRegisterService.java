
package com.example.metrikenbibiiothek.metrics;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;

import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class MeterRegisterService {
    private static MeterRegisterService meterRegisterService = null;

    private String org = "org";
    private String bucket = "myFirstBucket";
    //private String token = "A3JPRYiB1NkMg3ZTS6NeJ9zjT2GZU_Ffd0ZSgreq2FVyeNTSFA8vsQ4ThNl9y-Bu4xXEkOZDkUg_ALbvw8gsHg==";  //zu Hause
    private String token = "kNs2spnLQ0YRv9fY27PkbodGFTxTqt8n-u8ATpTXbiU0H3S67bZRVtJcTnG8OFL0xo_CRxpU-jEp_69QYd6ENA==";  //im Büro
    private int step =5;

    private MeterRegistry meter;
    public MeterRegisterService(){
        meter = new InfluxMeterRegistry(config(), Clock.SYSTEM);

    }

    public MeterRegisterService( int _step){
        System.out.println("Konstruktor MeterRegisterService");
        step = _step;
        meter = new InfluxMeterRegistry(config(), Clock.SYSTEM);
    }



    private InfluxConfig config(){
        return new InfluxConfig() {
            @Override
            public String org() {
                return getOrg();
            }
            @Override
            public String bucket() {
                return getBucket();
            }
            @Override
            public String token() {
                return getToken(); // FIXME: This should be securely bound rather than hard-coded, of course.
            }
            @Override
            public String get(String k) {
                return null; // accept the rest of the defaults
            }
            @Override
            public Duration step() {
            return Duration.ofSeconds(getStep());
        }

        };
    }
    public MeterRegistry getRegistry(){
        return meter;
    }


    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getBucket() {
        return bucket;
    }

//    public void setBucket(String bucket) {
//        this.bucket = bucket;
//    }

    public String getToken() {
        return token;
    }

//    public void setToken(String token) {
//        this.token = token;
//    }

    public int getStep() {
        return step;
    }

//    public void setStep(int step) {
//        this.step = step;
//    }

    public void setMeter(MeterRegistry meter) {
        this.meter = meter;
    }

    public static MeterRegisterService getInstance(){

        if(meterRegisterService == null){
            meterRegisterService = new MeterRegisterService();
        }
        return meterRegisterService;
    }


}