package ar.edu.utn.frba.dds.climalert.integracion.notificacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailPorConsola implements ServicioDeEmail {

    private static final Logger log = LoggerFactory.getLogger(EmailPorConsola.class);

    @Override
    public void enviar(Email email) {
        log.info("""

                ========== EMAIL ENVIADO ==========
                Para: {}
                Asunto: {}
                ----------------------------------------------
                {}
                ==============================================""",
                String.join(", ", email.destinatarios()), email.asunto(), email.cuerpo());
    }
}
