package org.example.ocado;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataJson {
    ObjectMapper objectMapper = new ObjectMapper();
    File odersFile;
    File storeFile;

    public DataJson(String ordersPath, String storePath) {
        this.odersFile = new File(ordersPath);
        this.storeFile = new File(storePath);
    }

    //Methods used to extract data from json files. The data is used in Order and Store constructors
    public List<Order> readOrder() throws IOException {
        return objectMapper.readValue(odersFile, new TypeReference<List<Order>>() {
        });
    }

    public Store readStore() throws IOException {
        return objectMapper.readValue(storeFile, Store.class);
    }

}
