package org.example.ocado;

import lombok.Getter;

@Getter
public class Output {
    public final String picker;
    public final String orderId;
    public final String startTime;

    public Output(String picker, String orderId, String startTime) {
        this.picker = picker;
        this.orderId = orderId;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return picker + " " + orderId + " " + startTime;
    }
}
