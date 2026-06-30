package ar.edu.utn.frba.dds.climalert.integration.clima.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiResponse(
        @JsonProperty("location") Location location,
        @JsonProperty("current") Current current) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Location(
            @JsonProperty("name") String name,
            @JsonProperty("localtime") String localtime) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Current(
            @JsonProperty("temp_c") double tempC,
            @JsonProperty("humidity") double humidity,
            @JsonProperty("feelslike_c") double feelslikeC,
            @JsonProperty("wind_kph") double windKph,
            @JsonProperty("condition") Condition condition) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Condition(
            @JsonProperty("text") String text) {
    }
}
