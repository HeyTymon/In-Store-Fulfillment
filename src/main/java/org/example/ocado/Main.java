package org.example.ocado;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //Validation is missing - not enough time
        String orderPath = args[0];
        String storePath = args[1];
        String howToAssign = args[2];

        DataJson dataJson = new DataJson(orderPath, storePath);

        Scheduler scheduler = new Scheduler(dataJson.readOrder(), dataJson.readStore(), Boolean.parseBoolean(howToAssign));

        scheduler.scheduleOrdersToPickers();
    }
}
