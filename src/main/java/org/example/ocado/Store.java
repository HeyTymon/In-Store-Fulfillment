package org.example.ocado;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalTime;
import java.util.List;

@Getter
@ToString
public class Store {

    public final List<String> pickersNameList;
    public final LocalTime startTime;
    public final LocalTime endTime;

    @JsonCreator
    public Store(@JsonProperty("pickers") List<String> pickers,
                 @JsonProperty("pickingStartTime") String pickingStartTime,
                 @JsonProperty("pickingEndTime") String pickingEndTime) {
        this.startTime = LocalTime.parse(pickingStartTime);
        this.endTime = LocalTime.parse(pickingEndTime);
        this.pickersNameList = pickers;
    }
}
