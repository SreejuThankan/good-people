package com.goodpeople.app.mapping;

import com.goodpeople.app.httprequester.HttpRequesterService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MappingServiceOpenStreetMapImpl implements MappingService {

    //    private static final String BASE_URL = "https://routing.openstreetmap.de/routed-bike/route/v1/driving/-0.0091,51.5486;0.8708,51.1483?overview=false&geometries=polyline";
    private static final String BASE_URL = "https://routing.openstreetmap.de/";

    private final PostCodeIoService postCodeIoService;
    private final HttpRequesterService httpRequesterService;


    @Override
    public RouteSummary getRouteSummaryBetween(String startPostCodeStr, String destPostCodeStr, RouteType routeType) {
        PostCodeDetails start = postCodeIoService.getDetailsForPostCode(startPostCodeStr);
        PostCodeDetails dest = postCodeIoService.getDetailsForPostCode(destPostCodeStr);
        String routeUrl = getRouteUrl(start, dest, routeType);
        String jsonResponse = httpRequesterService.get(routeUrl, null, String.class);

        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonObject firstRoute = jsonObject.getAsJsonArray("routes").get(0).getAsJsonObject();

        Distance distance = new Distance(firstRoute.getAsJsonPrimitive("distance").getAsDouble(), Metrics.KILOMETERS);
        Duration duration = Duration.ofSeconds(firstRoute.getAsJsonPrimitive("duration").getAsLong());

        return new RouteSummary(start, dest, distance, duration);
    }

    private String getRouteUrl(PostCodeDetails start, PostCodeDetails dest, RouteType routeType) {
        final String routeTypeStr;
        switch (routeType) {
            case CAR:
                routeTypeStr = "car";
                break;
            case WALK:
                routeTypeStr = "foot";
                break;
            case BIKE:
                routeTypeStr = "bike";
                break;

            default:
                throw new IllegalArgumentException("Unexpected value: " + routeType);
        }
        return String.format("%srouted-%s/route/v1/driving/%s,%s;%s,%s?overview=false&geometries=polyline",
                             BASE_URL, routeTypeStr, start.getLongitude(), start.getLatitude(), dest.getLongitude(), dest.getLatitude());
    }


    @Value
    private static class OpenStreetMapResponseEntity {

    }


//    {
//  "code":"Ok",
//  "waypoints":[
//    {
//      "hint":"kXhfl8V5X5cpAAAATAAAAIcBAAA9AAAA00jcQPg0S0ERoYJCPmw2QSkAAABMAAAAhwEAAD0AAAC0CAAAw9z__3yREgN03P__uJESAwMALwPklb1P",
//      "location":[
//        -0.009021,
//        51.54854
//      ],
//      "name":"Victory Parade"
//    },
//    {
//      "hint":"c3qxibd6sYneAAAASAAAAAAAAAAAAAAA4rzFQaFF0EEAAAAAAAAAAN4AAABIAAAAAAAAAAAAAAC0CAAA4kkNAPV1DAOQSQ0ADHYMAwAAzwzklb1P",
//      "location":[
//        0.870882,
//        51.148277
//      ],
//      "name":"Bank Street"
//    }
//  ],
//  "routes":[
//    {
//      "legs":[
//        {
//          "steps":[
//
//          ],
//          "weight":21547.7,
//          "distance":89369.9,
//          "summary":"",
//          "duration":21547.7
//        }
//      ],
//      "weight_name":"duration",
//      "weight":21547.7,
//      "distance":89369.9,
//      "duration":21547.7
//    }
//  ]
//}
}
