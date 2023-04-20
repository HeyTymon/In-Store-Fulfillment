package org.example.ocado;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@ToString
public class AssignedOrder {
    public final String orderId;
    public final LocalTime pickingOrderTime;

    public AssignedOrder(String orderId, LocalTime pickingOrderTime) {
        this.orderId = orderId;
        this.pickingOrderTime = pickingOrderTime;
    }
}
