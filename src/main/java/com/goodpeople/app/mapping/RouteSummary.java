package com.goodpeople.app.mapping;

import lombok.Value;
import org.springframework.data.geo.Distance;

import java.time.Duration;

@Value
public class RouteSummary {

    private final PostCodeDetails start;
    private final PostCodeDetails destination;
    private final Distance distance;
    private final Duration duration;

}
