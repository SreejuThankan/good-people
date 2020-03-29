package com.goodpeople.app.mapping;

public interface MappingService {


    RouteSummary getRouteSummaryBetween(String startPostCodeStr, String destPostCodeStr, RouteType routeType);
}
