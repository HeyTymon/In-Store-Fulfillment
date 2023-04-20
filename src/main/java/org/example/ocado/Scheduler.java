package org.example.ocado;

import lombok.Getter;
import lombok.ToString;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class Scheduler {

    List<Order> orderList;
    Store store;
    boolean assignByTime;
    List<Picker> pickersToAssignList;

    public Scheduler(List<Order> orderList, Store store, boolean assignByTime) {
        this.orderList = orderList;
        this.store = store;
        this.assignByTime = assignByTime;
        pickersToAssignList = new ArrayList<>();
    }

    //Algorithm:
    //1. Creating new list pickers.
    //2. Sorting the order list (by completion time or by order value).
    //3. Iterating by Order and Picker to assign the orders to pickers.
    //4. Checking the condition - if it is true, the order is assigned to picker.
    //5. Displaying output in the console.
    public void scheduleOrdersToPickers() {
        createPickersToAssignList();

        if (assignByTime) latestStartTime();
        else sortOrderByValue();

        for (Order o : orderList) {
            for (Picker p : pickersToAssignList) {
                if (timeAllocateCondition(o, p, store)) {
                    p.addNewOrder(new AssignedOrder(o.getOrderId(), p.getTimeWhenFree()), o.getPickingOrderTime());
                    break;
                }
            }
        }

        getOutput();
    }

    private static boolean timeAllocateCondition(Order o, Picker p, Store s) {
        LocalTime pickingTime = p.getTimeWhenFree().plus(o.getPickingOrderTime());
        return (s.endTime.isAfter(pickingTime) || s.endTime.equals(pickingTime)) &&
                (o.completeByTime.isAfter(pickingTime) || o.completeByTime.equals(pickingTime));
    }

    public List<Output> getOutput() {
        List<Output> outputData = new ArrayList<>();

        for (Picker p : pickersToAssignList) {
            for (AssignedOrder ao : p.getAssignedOrderList()) {
                outputData.add(new Output(p.getName(), ao.getOrderId(), String.valueOf(ao.getPickingOrderTime())));
            }
        }

        outputData = outputData.stream().sorted(Comparator.comparing(Output::getStartTime)).collect(Collectors.toList());

        outputData.forEach(System.out::println);

        return outputData;
    }

    public void createPickersToAssignList() {
        for (String s : store.getPickersNameList()) {
            pickersToAssignList.add(new Picker(s, store.getStartTime()));
        }
    }

    //Sorting methods

    public void latestStartTime() {
        orderList = orderList.stream().sorted(Comparator.comparing(Order::latestPickUp)).collect(Collectors.toList());
    }

    public void sortPickersByFreeTime() {
        pickersToAssignList = pickersToAssignList.stream().sorted(Comparator.comparing(Picker::getTimeWhenFree)).collect(Collectors.toList());
    }

    public void advanceSortByTime() {
        orderList = orderList.stream().sorted(Comparator.comparing(Order::getCompleteByTime).thenComparing(Order::getPickingOrderTime))
                .collect(Collectors.toList());
    }

    public void sortOrderByCompleteTime() {
        orderList = orderList.stream().sorted(Comparator.comparing(Order::getCompleteByTime)).collect(Collectors.toList());
    }

    public void sortOrderByValue() {
        orderList = orderList.stream()
                .sorted(Comparator.comparing(Order::getValueOfOrder).reversed().thenComparing(Order::getCompleteByTime))
                .collect(Collectors.toList());
    }
}
