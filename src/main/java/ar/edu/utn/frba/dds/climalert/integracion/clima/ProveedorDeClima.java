package ar.edu.utn.frba.dds.climalert.integracion.clima;

import ar.edu.utn.frba.dds.climalert.domain.MedicionClima;

public interface ProveedorDeClima {

    MedicionClima obtenerClimaActual(String ubicacion);
}
