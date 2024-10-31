package hu.pannonuni.routerangers.service;

import hu.pannonuni.model.AddressCreateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeocodingService {

    @Value("${api.token}")
    private String apiToken;

    private final RestTemplate restTemplate;

    private static final String API_URL_FOR_COORDINATES = "https://api.openrouteservice.org/geocode/search";

    public Double[] fetchCoordinates(String address) {
        String url = String.format("%s?api_key=%s&text=%s", API_URL_FOR_COORDINATES, apiToken, address);
        Map response = restTemplate.getForObject(url, Map.class);

        // Ellenőrizd a válasz struktúráját
        if (response != null && response.containsKey("features")) {
            List<Map<String, Object>> features = (List<Map<String, Object>>) response.get("features");
            if (!features.isEmpty()) {
                Map<String, Object> firstFeature = features.get(0);
                List<Double> coordinates = (List<Double>) ((Map<String, Object>) firstFeature.get("geometry")).get("coordinates");
                return new Double[]{coordinates.get(1), coordinates.get(0)}; // [latitude, longitude]
            }
        }
        return null;
    }

    // Új metódus a koordináták lekérésére AddressCreateModel alapján
    public Double[] fetchCoordinatesForAddress(AddressCreateModel addressCreateModel) {
        String fullAddress = String.format("%s %s, %s, %s",
                addressCreateModel.getStreet(),
                addressCreateModel.getHouseNumber(),
                addressCreateModel.getCity(),
                addressCreateModel.getPostalCode());
        return fetchCoordinates(fullAddress);
    }
}
