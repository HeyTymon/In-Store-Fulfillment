package org.example.ocado;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchedulerTest {
        Scheduler scheduler;
        DataJson dataJson = new DataJson("src/main/resources/ordersTest.json", "src/main/resources/storeTest.json" );
    SchedulerTest() throws IOException {

       scheduler = new Scheduler(dataJson.readOrder(),dataJson.readStore(),true);
    }

    @Test
    void sortOrderByCompleteTime_test()
    {
        scheduler.sortOrderByCompleteTime();

        assertEquals("order-2",scheduler.orderList.get(0).getOrderId());
        assertEquals(LocalTime.of(9,15),scheduler.orderList.get(0).getCompleteByTime());
        assertEquals("order-3",scheduler.orderList.get(1).getOrderId());
        assertEquals(LocalTime.of(9,30),scheduler.orderList.get(1).getCompleteByTime());
        assertEquals("order-1",scheduler.orderList.get(2).getOrderId());
        assertEquals(LocalTime.of(10,0),scheduler.orderList.get(2).getCompleteByTime());
    }

    @Test
    void sortOrderByValue_test()
    {
        scheduler.sortOrderByValue();

        assertEquals("order-3",scheduler.orderList.get(0).getOrderId());
        assertEquals(3.00,scheduler.orderList.get(0).getValueOfOrder());
        assertEquals("order-2",scheduler.orderList.get(1).getOrderId());
        assertEquals(2.00,scheduler.orderList.get(1).getValueOfOrder());
        assertEquals("order-1",scheduler.orderList.get(2).getOrderId());
        assertEquals(1.00,scheduler.orderList.get(2).getValueOfOrder());
    }

    @Test
    void createPickersToAssignList_test()
    {
        scheduler.createPickersToAssignList();

        assertEquals(scheduler.store.pickersNameList.get(0),scheduler.pickersToAssignList.get(0).getName());
        assertEquals(scheduler.store.getStartTime(),scheduler.pickersToAssignList.get(0).getTimeWhenFree());
        assertEquals(scheduler.store.pickersNameList.get(1),scheduler.pickersToAssignList.get(1).getName());
        assertEquals(scheduler.store.getStartTime(),scheduler.pickersToAssignList.get(1).getTimeWhenFree());
    }

    @Test
    void getOutput_test()
    {
        scheduler.scheduleOrdersToPickers();

        assertEquals("P1 order-2 09:00",scheduler.getOutput().get(0).toString());
        assertEquals("P2 order-1 09:00",scheduler.getOutput().get(1).toString());
        assertEquals("P1 order-3 09:15",scheduler.getOutput().get(2).toString());

    }

    @Test
    void addNewOrder_test()
    {
        scheduler.createPickersToAssignList();

        scheduler.pickersToAssignList.get(0).addNewOrder(new AssignedOrder(scheduler.orderList.get(0).getOrderId(),
                scheduler.pickersToAssignList.get(0).getTimeWhenFree()),
                scheduler.orderList.get(0).getPickingOrderTime());

        assertEquals("order-1",scheduler.pickersToAssignList.get(0).getAssignedOrderList().get(0).getOrderId());
        assertEquals(LocalTime.of(9,0),scheduler.pickersToAssignList.get(0).getAssignedOrderList().get(0).getPickingOrderTime());
    }

}