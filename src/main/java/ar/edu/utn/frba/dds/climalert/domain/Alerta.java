package ar.edu.utn.frba.dds.climalert.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Alerta {

    private final MedicionClima medicion;
    private final String motivo;
    private final LocalDateTime fechaHora;

    public Alerta(MedicionClima medicion, String motivo) {
        this.medicion = medicion;
        this.motivo = motivo;
        this.fechaHora = LocalDateTime.now();
    }

    public String descripcionCompleta() {
        return medicion.descripcionCompleta();
    }
}
