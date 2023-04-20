package org.example.ocado;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalTime;

@Getter
@ToString
public class Order {

    public final String orderId;
    public final double valueOfOrder;
    public final Duration pickingOrderTime;
    public final LocalTime completeByTime;

    @JsonCreator
    public Order(@JsonProperty("orderId") String orderId,
                 @JsonProperty("orderValue") String orderValue,
                 @JsonProperty("pickingTime") String pickingTime,
                 @JsonProperty("completeBy") String completeBy) {
        this.orderId = orderId;
        this.valueOfOrder = Double.valueOf(orderValue);
        this.pickingOrderTime = Duration.parse(pickingTime);
        this.completeByTime = LocalTime.parse(completeBy);
    }

    public LocalTime latestPickUp() {
        return completeByTime.minus(pickingOrderTime);
    }
}
