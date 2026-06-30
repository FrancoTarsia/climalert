package ar.edu.utn.frba.dds.climalert.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "climalert")
@Data
public class ClimalertProperties {

    private Frecuencias frecuencias = new Frecuencias();
    private Alerta alerta = new Alerta();

    @Data
    public static class Frecuencias {
        private long consultaRateMs;
        private long evalRateMs;
    }

    @Data
    public static class Alerta {
        private List<String> destinatarios;
    }
}
