package com.goodpeople.app.mapping;

import com.goodpeople.app.httprequester.HttpRequesterService;
import com.goodpeople.app.medicine.Medicine;
import com.goodpeople.app.person.Person;
import com.goodpeople.app.person.PersonRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MappingServiceOpenStreetMapImpl implements MappingService {

    //    private static final String BASE_URL = "https://routing.openstreetmap.de/routed-bike/route/v1/driving/-0.0091,51.5486;0.8708,51.1483?overview=false&geometries=polyline";
    private static final String BASE_URL = "https://routing.openstreetmap.de/";

    private final PostCodeIoService postCodeIoService;
    private final HttpRequesterService httpRequesterService;
    private final PersonRepository personRepository;

    @Override
    public List<Person> get10QuickestDeliveryPeople(Person person, Medicine requiredMedicine) {
        String city = person.getAddress().getPostCodeDetails().getCity();
        List<Person> peopleInCityWithOwnedMedicine = personRepository.findPeopleInCityWithOwnedMedicine(city, requiredMedicine.getId());

        Function<Person, RouteSummary> routeSummaryFunc =
                deliveryPerson -> getRouteSummaryBetween(deliveryPerson.getPostcode(),
                                                         person.getPostcode(),
                                                         deliveryPerson.getRouteTypes().iterator().next());
        return peopleInCityWithOwnedMedicine.stream()
                                            .collect(Collectors.toMap(Function.identity(), routeSummaryFunc))
                                            .entrySet().stream()
                                            .sorted(Comparator.comparing(entry -> entry.getValue().getDuration()))
                                            .limit(10)
                                            .map(Map.Entry::getKey)
                                            .collect(Collectors.toList());
    }

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
}
