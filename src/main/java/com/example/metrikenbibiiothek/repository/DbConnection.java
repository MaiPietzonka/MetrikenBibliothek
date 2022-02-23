package com.example.metrikenbibiiothek.repository;

import com.example.metrikenbibiiothek.metrics.MeterRegisterService;
import com.influxdb.client.*;
import com.influxdb.client.domain.WritePrecision;

import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;


import java.time.Instant;
import java.util.List;



public class DbConnection {
//    String token = "A3JPRYiB1NkMg3ZTS6NeJ9zjT2GZU_Ffd0ZSgreq2FVyeNTSFA8vsQ4ThNl9y-Bu4xXEkOZDkUg_ALbvw8gsHg==";
private String token = "kNs2spnLQ0YRv9fY27PkbodGFTxTqt8n-u8ATpTXbiU0H3S67bZRVtJcTnG8OFL0xo_CRxpU-jEp_69QYd6ENA==";  //im Büro
    // String token MAC



    private char [] myToken = token.toCharArray();
    private InfluxDBClient influxdb;// = InfluxDBClientFactory.create("http://localhost:8086",  myToken, "org", "bucket");
    private WriteApiBlocking write;// = influxdb.getWriteApiBlocking();
    private String org;
    private String bucket;
    private String uri = "http://localhost:8086";
    private MeterRegisterService service = new MeterRegisterService();

    public DbConnection(){
        org = service.getOrg();
        bucket = service.getBucket();
        token = service.getToken();
        myToken = token.toCharArray();
        influxdb = InfluxDBClientFactory.create(uri,  myToken,org, bucket);
        write = influxdb.getWriteApiBlocking();

    }



    public void sendData(){
         try (WriteApi writeApi = influxdb.makeWriteApi()) {

            //
            // Write by Data Point
            //
            Point point = Point.measurement("test")
                    .addTag("location", "west")
                    .addField("value", 012353545D)
                    .time(Instant.now().toEpochMilli(), WritePrecision.MS);

            writeApi.writePoint(point);
        }
    }

    public String cumulativeCount(String start, String stop, String table){
        String flux = "from(bucket: \"bucket\") "+
                    "|> range(start: -3m, stop: now()) "+
                    "|> filter(fn: (r) => r[\"_measurement\"] == \"blub3\") "+
                    "|> aggregateWindow(every: 3s, fn: sum, createEmpty: false) "+
                    "|> cumulativeSum(columns: [\"_value\"])"
                ;
//
//        String flux =  String.format("from(bucket: \"bucket\")" +
//                "|> range(start: -5, stop:now())"+
//                "|> filter(fn: (r) => r[\"_measurement\"] == \"counter\")"+
//                " |> filter(fn: (r) => r[\"Kunde\"] == \"Mai\")"
//                ,service.getBucket());

        QueryApi queryApi = influxdb.getQueryApi();



        List<FluxTable> tables = queryApi.query(flux);

        for (FluxTable fluxTable : tables) {

            List<FluxRecord> records = fluxTable.getRecords();

            for (FluxRecord fluxRecord : records) {

                System.out.println(fluxRecord.getValueByKey("_value"));
            }

        } influxdb.close();
        return flux;

    }

    public void writeToDatabse(){
        QueryApi queryApi = influxdb.getQueryApi();

        String fluxQuery = "from(bucket: \"bucket\") "+
                "|> range(start: -30m, stop: now()) "+
                "|> filter(fn: (r) => r[\"_measurement\"] == \"counter\") "+
                "|> aggregateWindow(every: 30s, fn: sum, createEmpty: false) "+
                "|> cumulativeSum(columns: [\"_value\"])"+
                "|> yield(name: \"last\")"
                ;

        List<FluxTable> tables = queryApi.query(fluxQuery);

        if(tables.isEmpty()){
            System.out.println("Jo ist leer");
        }


        for (FluxTable fluxTable : tables) {                                // Sehr rechenintensiv
            List<FluxRecord> records = fluxTable.getRecords();
            for (FluxRecord fluxRecord : records) {
                try (WriteApi writeApi = influxdb.makeWriteApi()) {


                    Point point = Point.measurement("cumula1")
                            .addTag("Sum", "Value")
                            .addField("value", (Double) fluxRecord.getValueByKey("_value"))
                            .time(Instant.now().toEpochMilli(), WritePrecision.MS);

                    writeApi.writePoint(point);
                }
                    System.out.println(fluxRecord.getTime() + ": " + (Double) fluxRecord.getValueByKey("_value"));

            }
        }


        influxdb.close();
    }
}
