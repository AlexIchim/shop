package ro.msg.learning.shop.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.exception.LocationException;

import java.io.IOException;

@Service
public class DistanceMatrixGoogleApiService {
	private static final String API_KEY = "AIzaSyC5NjHmvBtMWujYwuoJS217B7MtF_6PdP8";

	private final RestTemplate restTemplate = new RestTemplate();

	public Double calculate(String source, String destination) throws IOException {
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=afeafafa" + source + "&destinations=" + destination + "&key=" + API_KEY;
		ResponseEntity<String> response = restTemplate.getForEntity(url , String.class);

		if (convertJsonToDistanceValue(response.getBody()) == Double.MAX_VALUE) {
			throw new LocationException(source + " | " + destination);
		} else {
			return convertJsonToDistanceValue(response.getBody());
		}
	}

	private Double convertJsonToDistanceValue(String json) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(json).get("rows");
		if (rootNode.isArray()) {
			for (final JsonNode jsonNode : rootNode) {
				if (jsonNode.get("elements").isArray()) {
					for (JsonNode jsonNode1 : jsonNode.get("elements")) {
						if (jsonNode1.get("distance") != null) {
							JsonNode distanceNode =  jsonNode1.get("distance");
							JsonNode valueNode = distanceNode.get("value");
							return valueNode.asDouble();
						} else {
							return Double.MAX_VALUE;
						}
					}
				}
			}
		}
		return Double.MAX_VALUE;
	}
}
