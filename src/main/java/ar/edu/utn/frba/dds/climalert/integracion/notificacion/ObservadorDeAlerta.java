package ar.edu.utn.frba.dds.climalert.integracion.notificacion;

import ar.edu.utn.frba.dds.climalert.domain.Alerta;

public interface ObservadorDeAlerta {

    void serNotificadoDe(Alerta alerta);
}
