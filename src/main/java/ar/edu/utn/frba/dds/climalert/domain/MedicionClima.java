package ar.edu.utn.frba.dds.climalert.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MedicionClima {

    private static final double UMBRAL_TEMPERATURA = 35;
    private static final double UMBRAL_HUMEDAD = 60;

    private final String ubicacion;
    private final LocalDateTime fechaHora;
    private final double temperaturaC;
    private final double humedad;
    private final double sensacionTermicaC;
    private final double vientoKph;
    private final String condicion;
    private boolean evaluada = false;

    public MedicionClima(String ubicacion, LocalDateTime fechaHora, double temperaturaC, double humedad, double sensacionTermicaC, double vientoKph, String condicion) {
        this.ubicacion = ubicacion;
        this.fechaHora = fechaHora;
        this.temperaturaC = temperaturaC;
        this.humedad = humedad;
        this.sensacionTermicaC = sensacionTermicaC;
        this.vientoKph = vientoKph;
        this.condicion = condicion;
    }

    public void marcarEvaluada() {
        this.evaluada = true;
    }

    public boolean esCritica() {
        return temperaturaC > UMBRAL_TEMPERATURA && humedad > UMBRAL_HUMEDAD;
    }
    
    public String descripcionCompleta() {
        return """
                Ubicacion: %s
                Fecha/hora: %s
                Temperatura: %.1f C
                Sensacion termica: %.1f C
                Humedad: %.0f %%
                Viento: %.1f km/h
                Condicion: %s""".formatted(ubicacion, fechaHora, temperaturaC, sensacionTermicaC, humedad, vientoKph, condicion);
    }
}
