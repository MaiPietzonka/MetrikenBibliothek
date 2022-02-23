package com.example.metrikenbibiiothek.repository;

import com.example.metrikenbibiiothek.metrics.MeterRegisterService;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.domain.Authorization;
import com.influxdb.client.domain.Permission;
import com.influxdb.client.domain.PermissionResource;
import com.influxdb.client.domain.Task;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Arrays;

public class TaskCreator {
    private static char[] token = "A3JPRYiB1NkMg3ZTS6NeJ9zjT2GZU_Ffd0ZSgreq2FVyeNTSFA8vsQ4ThNl9y-Bu4xXEkOZDkUg_ALbvw8gsHg==".toCharArray();
    private MeterRegisterService service = MeterRegisterService.getInstance();
    public void createTask(){
        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8086", token);

        Task task = new Task();

        String flux = "option task = {\n" +
                "     name: \"mean\",\n" +
                "     every: 1h,\n" +
                " }\n" +
                "\n"
                +
                " from(bucket:\"myFirstBucket\")\n" +
                "     |> range(start:-task.every)\n" +
                "     |> filter(fn:(r) => r._measurement ==\"blub3\")"+
                "data\n"+
                "|> aggregateWindow(every: 5m, fn:mean)"






        influxDBClient.close();












        // Create access token to "iot_bucket"
//        PermissionResource resource = new PermissionResource();
//        resource.setId("4a00b5b753740234");
//        resource.setOrgID("350fe8ff0c656a29");
//        resource.setType(PermissionResource.TypeEnum.TASKS);
//
//        // Read permission
//        Permission read = new Permission();
//        read.setResource(resource);
//        read.setAction(Permission.ActionEnum.READ);
//
//        // Write permission
//        Permission write = new Permission();
//        write.setResource(resource);
//        write.setAction(Permission.ActionEnum.WRITE);
//
//        Authorization authorization = influxDBClient.getAuthorizationsApi()
//                .createAuthorization("350fe8ff0c656a29", Arrays.asList(read, write));
    }
}
