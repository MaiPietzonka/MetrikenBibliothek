package com.example.metrikenbibiiothek.repository;


import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

import com.influxdb.client.InfluxDBClient;
        import com.influxdb.client.InfluxDBClientFactory;
        import com.influxdb.client.domain.Authorization;
        import com.influxdb.client.domain.Bucket;
        import com.influxdb.client.domain.Permission;
        import com.influxdb.client.domain.PermissionResource;
        import com.influxdb.client.domain.BucketRetentionRules;


        // Quelle https://github.com/influxdata/influxdb-client-java/tree/master/client#management-api
public class BucketCreator {



//    private static char[] token = "A3JPRYiB1NkMg3ZTS6NeJ9zjT2GZU_Ffd0ZSgreq2FVyeNTSFA8vsQ4ThNl9y-Bu4xXEkOZDkUg_ALbvw8gsHg==".toCharArray();
    private static char[] token = "kNs2spnLQ0YRv9fY27PkbodGFTxTqt8n-u8ATpTXbiU0H3S67bZRVtJcTnG8OFL0xo_CRxpU-jEp_69QYd6ENA==".toCharArray();

    private AtomicBoolean createSuccessfull = new AtomicBoolean();
    public void createNewBucket(String name) {


        System.out.println("in createNewBucket");
        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8086", token);

        //
        // Create bucket "iot_bucket" with data retention set to 3,600 seconds
        //
        BucketRetentionRules retention = new BucketRetentionRules();
        retention.setEverySeconds(3600);
        Bucket bucket = new Bucket();
        try {
            bucket = influxDBClient.getBucketsApi().createBucket(name, retention, "cb4c73b49d72275d");  // Die OrgID findet man in der InfluxUI unter Member /About
        }catch (Exception e) {
            createSuccessfull.set(false);
            System.out.println(" Bucket existiert bereits");   // Noch in richtige ausgabe ändern
        }

//        if(createSuccessfull.get() == true){
//            System.out.println("Bucket wurde erstellt");
//            PermissionResource resource = new PermissionResource();
//            resource.setId(bucket.getId());
//            resource.setOrgID("cb4c73b49d72275d");
//            resource.setType(PermissionResource.TypeEnum.BUCKETS);
//
//            // Read permission
//            Permission read = new Permission();
//            read.setResource(resource);
//            read.setAction(Permission.ActionEnum.READ);
//
//            // Write permission
//            Permission write = new Permission();
//            write.setResource(resource);
//            write.setAction(Permission.ActionEnum.WRITE);
//
//            Authorization authorization = influxDBClient.getAuthorizationsApi()
//                    .createAuthorization("cb4c73b49d72275d", Arrays.asList(read, write));
//
//            //
//            // Created token that can be use for writes to bucket
//            //
//            String token = authorization.getToken();
//            System.out.println("Token: " + token);

//        }
//        System.out.println("close");
        influxDBClient.close();

    }



}