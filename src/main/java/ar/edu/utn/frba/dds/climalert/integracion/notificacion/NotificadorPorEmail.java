package ar.edu.utn.frba.dds.climalert.integracion.notificacion;

import ar.edu.utn.frba.dds.climalert.config.ClimalertProperties;
import ar.edu.utn.frba.dds.climalert.domain.Alerta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificadorPorEmail implements ObservadorDeAlerta {

    private final ServicioDeEmail servicioDeEmail;
    private final ClimalertProperties properties;

    public NotificadorPorEmail(ServicioDeEmail servicioDeEmail, ClimalertProperties properties) {
        this.servicioDeEmail = servicioDeEmail;
        this.properties = properties;
    }

    @Override
    public void serNotificadoDe(Alerta alerta) {
        List<String> destinatarios = properties.getAlerta().getDestinatarios();
        String asunto = "[CLIMALERT] Alerta climatica en " + alerta.getMedicion().getUbicacion();
        String cuerpo = """
                Se detectaron condiciones climaticas peligrosas.

                Motivo: %s

                Detalle del clima:
                %s
                """.formatted(alerta.getMotivo(), alerta.descripcionCompleta());

        this.servicioDeEmail.enviar(new Email(destinatarios, asunto, cuerpo));
    }
}
