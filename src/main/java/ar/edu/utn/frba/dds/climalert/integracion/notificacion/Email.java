package ar.edu.utn.frba.dds.climalert.integracion.notificacion;

import java.util.List;

public record Email(List<String> destinatarios, String asunto, String cuerpo) {
}
