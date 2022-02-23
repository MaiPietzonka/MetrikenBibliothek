package com.example.metrikenbibiiothek;


import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;


public class ReloadProperty  {

    private String db = "management.metrics.export.influx.db";
    private String step = "management.metrics.export.influx.step";
    private String uri = "management.metrics.export.influx.uri";
    private String token = "management.metrics.export.influx.token";
    private Resource re = new ClassPathResource("/application.properties");


    public ReloadProperty(String _key, String _newVal){
        try {
            reload(_key, _newVal);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String fileName = "application.properties";

    public void getLoad(){
        InputStream inputStream = null;

        try {
            Properties prop = new Properties();

            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            prop.load(inputStream);
            System.out.println(step + prop.getProperty(this.step));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getPropertie(){

    }
    public void reload(String key, String newValue) throws ConfigurationException, IOException {
        // https://www.codeproject.com/Questions/67995/can-we-update-properties-file-at-runtime
        // muss aber noch zwei mal auf run drücken

        Properties props = new Properties();

        String filename = "./src/main/resources/application.properties";


            //alte File laden
            FileInputStream configStream = new FileInputStream(filename);
            props.load(configStream);
            configStream.close();

            //alte property verändern
            switch (key) {
                case "db":
                    props.setProperty(this.db, newValue);
                    break;
                case "step":
//                    System.out.println(props.getProperty(this.step));
//                    System.out.println("In Step  " + this.step + "    " + newValue);
                    props.setProperty(this.step, newValue);
//                    System.out.println(props.getProperty(this.step));
                    break;
                case "uri":
                    props.setProperty(this.uri, newValue);
                    break;
                case "token":
                    props.setProperty(this.token, newValue);
                    break;
                default:
                    System.out.println("Kein von den Cases /ReloadProperty");
                    break;

            }

            //file speichern
            FileOutputStream outputStream = new FileOutputStream(filename);
            props.store(outputStream, "Application.properties");
            outputStream.close();

            
    }

    public String getDb() {
        return db;
    }

    public String getStep() {
        return step;
    }

    public String getUri() {
        return uri;
    }

}
