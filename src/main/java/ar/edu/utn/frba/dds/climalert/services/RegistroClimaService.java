package ar.edu.utn.frba.dds.climalert.services;

import ar.edu.utn.frba.dds.climalert.config.WeatherApiProperties;
import ar.edu.utn.frba.dds.climalert.domain.MedicionClima;
import ar.edu.utn.frba.dds.climalert.integracion.clima.ProveedorDeClima;
import ar.edu.utn.frba.dds.climalert.repository.RepositorioDeMediciones;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegistroClimaService {

    private static final Logger log = LoggerFactory.getLogger(RegistroClimaService.class);

    private final ProveedorDeClima proveedorDeClima;
    private final RepositorioDeMediciones repositorio;
    private final WeatherApiProperties weatherApiProperties;

    public RegistroClimaService(ProveedorDeClima proveedorDeClima,
                                RepositorioDeMediciones repositorio,
                                WeatherApiProperties weatherApiProperties) {
        this.proveedorDeClima = proveedorDeClima;
        this.repositorio = repositorio;
        this.weatherApiProperties = weatherApiProperties;
    }

    public void registrarClimaActual() {
        String ubicacion = weatherApiProperties.getLocation();
        try {
            MedicionClima medicion = proveedorDeClima.obtenerClimaActual(ubicacion);
            repositorio.guardar(medicion);
            log.info("Clima registrado para {}: {} C, humedad {}%",
                    ubicacion, medicion.getTemperaturaC(), medicion.getHumedad());
        } catch (Exception e) {
            log.warn("No se pudo obtener el clima de {}: {}", ubicacion, e.getMessage());
        }
    }
}
