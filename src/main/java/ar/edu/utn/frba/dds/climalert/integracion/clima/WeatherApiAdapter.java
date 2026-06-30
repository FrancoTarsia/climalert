package ar.edu.utn.frba.dds.climalert.integracion.clima;

import ar.edu.utn.frba.dds.climalert.config.WeatherApiProperties;
import ar.edu.utn.frba.dds.climalert.domain.MedicionClima;
import ar.edu.utn.frba.dds.climalert.integracion.clima.dto.WeatherApiResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@Component
public class WeatherApiAdapter implements ProveedorDeClima {

    private final RestTemplate restTemplate;
    private final WeatherApiProperties properties;

    public WeatherApiAdapter(RestTemplate restTemplate, WeatherApiProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public MedicionClima obtenerClimaActual(String ubicacion) {
        URI uri = UriComponentsBuilder
                .fromUriString(properties.getBaseUrl())
                .path("/current.json")
                .queryParam("key", properties.getApiKey())
                .queryParam("q", ubicacion)
                .build()
                .toUri();

        WeatherApiResponse respuesta = restTemplate.getForObject(uri, WeatherApiResponse.class);

        return mapearADominio(ubicacion, respuesta);
    }

    private MedicionClima mapearADominio(String ubicacion, WeatherApiResponse respuesta) {
        if (respuesta == null || respuesta.current() == null) {
            throw new IllegalStateException("WeatherAPI devolvio una respuesta vacia para: " + ubicacion);
        }
        WeatherApiResponse.Current actual = respuesta.current();
        return new MedicionClima(
                ubicacion,
                LocalDateTime.now(),
                actual.tempC(),
                actual.humidity(),
                actual.feelslikeC(),
                actual.windKph(),
                actual.condition() != null ? actual.condition().text() : "N/D");
    }
}
