package hu.pannonuni.routerangers.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GeocodingController {

    // https://openrouteservice.org/dev/#/api-docs
    // https://github.com/VROOM-Project/vroom/blob/master/docs/API.md

    @Value("${api.token}")
    private String apiToken;

    private final String nominatimUrl = "https://nominatim.openstreetmap.org/search?format=json&q=";
    private final String apiUrl = "https://api.openrouteservice.org/v2/directions/";
    private final String optimizationUrl = "https://api.openrouteservice.org/optimization";

    private final RestTemplate restTemplate = new RestTemplate();

    // Endpoint for geocoding an address and returning a simplified result
    @GetMapping("/geocode")
    public ResponseEntity<Map<String, String>> geocode(@RequestParam String address) {
        String url = nominatimUrl + address.replace(" ", "+");
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);

        if (response != null && response.isArray() && response.size() > 0) {
            JsonNode location = response.get(0);

            // Extract the relevant fields like latitude, longitude, and display name
            String displayName = location.get("display_name").asText();
            String latitude = location.get("lat").asText();
            String longitude = location.get("lon").asText();

            // Prepare a simplified response with the extracted fields
            Map<String, String> result = new HashMap<>();
            result.put("address", displayName);
            result.put("latitude", latitude);
            result.put("longitude", longitude);

            return ResponseEntity.ok(result);
        }

        // Return a response indicating that the address could not be found
        return ResponseEntity.status(404).body(Map.of("error", "Address not found"));
    }

    // Endpoint for getting a route between two addresses
    @GetMapping("/route")
    public ResponseEntity<String> getRoute(@RequestParam String startAddress, @RequestParam String endAddress) {
        try {
            // 1. Geocode the start and end addresses to get their coordinates
            Map<String, String> startLocation = geocode(startAddress).getBody();
            Map<String, String> endLocation = geocode(endAddress).getBody();

            // Extract coordinates from the geocoding responses
            double startLat = Double.parseDouble(startLocation.get("latitude"));
            double startLon = Double.parseDouble(startLocation.get("longitude"));
            double endLat = Double.parseDouble(endLocation.get("latitude"));
            double endLon = Double.parseDouble(endLocation.get("longitude"));

            // 2. Construct the URL for route calculation
            String url = apiUrl + "driving-car?start=" + startLon + "," + startLat +
                    "&end=" + endLon + "," + endLat + "&api_key=" + apiToken;

            // Add the API token as a header (if needed by your API)
            restTemplate.getInterceptors().add((request, body, execution) -> {
                request.getHeaders().set("Authorization", apiToken);
                return execution.execute(request, body);
            });

            // 3. Make the request to get the route
            String response = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error while retrieving route: " + e.getMessage());
        }
    }

    @GetMapping("/optimize")
    public ResponseEntity<String> optimize() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // JSON
        String requestBody = "{\n" +
                "  \"jobs\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"service\": 300,\n" +
                "      \"delivery\": [1],\n" +
                "      \"location\": [2.03655, 48.61128],\n" +
                "      \"skills\": [1],\n" +
                "      \"time_windows\": [[32400, 36000]]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"service\": 300,\n" +
                "      \"delivery\": [1],\n" +
                "      \"location\": [2.39719, 49.07611],\n" +
                "      \"skills\": [1]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"vehicles\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"profile\": \"driving-car\",\n" +
                "      \"start\": [2.35044, 48.71764],\n" +
                "      \"end\": [2.35044, 48.71764],\n" +
                "      \"capacity\": [4],\n" +
                "      \"skills\": [1],\n" +
                "      \"time_window\": [28800, 43200]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        return restTemplate.exchange(optimizationUrl, HttpMethod.POST, entity, String.class);
    }
}
