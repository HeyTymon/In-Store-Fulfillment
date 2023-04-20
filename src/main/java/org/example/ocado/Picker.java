package org.example.ocado;

import lombok.Getter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Picker {
    public final String name;

    private final List<AssignedOrder> assignedOrderList;
    private LocalTime timeWhenFree;

    public Picker(String name, LocalTime timeWhenFree) {
        this.name = name;
        this.timeWhenFree = timeWhenFree;
        this.assignedOrderList = new ArrayList<>();
    }

    //This method is used in Scheduler scheduleOrdersToPickers method, and main purpose is adding new AssignedOrder to the assignedOrderList
    public void addNewOrder(AssignedOrder assignedOrder, Duration orderDuration) {
        assignedOrderList.add(assignedOrder);
        timeWhenFree = timeWhenFree.plus(orderDuration);
    }
}
