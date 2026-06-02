package com.rutasmart.api.student;

public record RouteSummary(
        String routeName,
        String stopName,
        String nextBusTime,
        String nextBusNote,
        String busStatus,
        String busDistance,
        String estimatedTime,
        String estimatedNote,
        String[] departures
) {
}
