package com.example.metrikenbibiiothek;

import com.example.metrikenbibiiothek.metrics.MetricType;
import com.example.metrikenbibiiothek.metrics.time.MyTime;
import com.example.metrikenbibiiothek.repository.BucketCreator;
import com.example.metrikenbibiiothek.repository.DbConnection;
import com.influxdb.client.domain.Bucket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@SpringBootApplication
public class MetrikenBibliothekApplication {



    public static void main(String[] args) {
        SpringApplication.run(MetrikenBibliothekApplication.class, args);


    }

    @RestController
    @RequestMapping("/count")
    public class Test {
        StoreData data = new StoreData();


        @GetMapping
        public String get() {
    //Application Property ändern
//            ReloadProperty load = new ReloadProperty();
//            try {
//                load.reload("step", "5s");
//            } catch (ConfigurationException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            // zum  aufrufen von storeData()


            //Zeit manuel messen
//            StoreObject object = new StoreObject.StoreBuilder().setMetricType("TimeManuel").setTable("time1").build();
//            data.storeData(object, "start");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            data.storeData(object, "stop");


            //count
//            ReloadProperty prop = new ReloadProperty("step", "5s");
            Map<String,String> map = new HashMap<>();
            map.put("Kunde","Mai");
            map.put("KundenID","200");

            Map<String,String> map1 = new HashMap<>();
            map1.put("Kunde","Hans");


            StoreObject ob = new StoreObject.StoreBuilder().setMetricType(MetricType.COUNT).setTable("blub3").setTag(map).build();
//            StoreObject ob1 = new StoreObject.StoreBuilder().setMetricType(MetricType.COUNT).setTable("counter").setTag(map1).build();
//            StoreObject ob = new StoreObject.StoreBuilder().setMetricType("Count").setTable("counter").setTag(map).build();
            data.storeData(ob);
//            data.storeData(ob1);
//            DbConnection db = new DbConnection();
//            db.sendData();
//            db.cumulativeCount("3","3", "cumul");
//            db.writeToDatabse();
//
            //gauge
//            MyGaugeManuel gauge = new MyGaugeManuel(service.getRegistry());
//            StoreObject.StoreBuilder ob = new StoreObject.StoreBuilder().setMetricType(new MyGaugeManuel(service.getRegistry())).setIncDec(4);
//            StoreObject ob2= ob.build();
//            StoreObject ob1 = ob.setIncDec(-2).build();
//            data.storeData(ob2);
//            data.storeData(ob1);
            //time
//            Map<String,String> map10 = new HashMap<>();
//            map10.put("key","val");
//            Supplier<Integer> i = () -> {try {
//                TimeUnit.MILLISECONDS.sleep(4000);
//            } catch (InterruptedException ignored) { }; return 0;};
////
//            StoreObject ob6 = new StoreObject.StoreBuilder().setMetricType(MetricType.TIME).setTable("timer1").setTag(map10).setSupplier(i).build();
//            data.storeData(ob6, System.nanoTime());
            BucketCreator creator = new BucketCreator();
            creator.createNewBucket("TEST");

            return " Gauge : " ;
//            return "Der Counter ist = " + MyCount.getCounter().count();
        }
    }

}
