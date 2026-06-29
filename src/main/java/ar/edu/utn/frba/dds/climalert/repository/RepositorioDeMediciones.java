package ar.edu.utn.frba.dds.climalert.repository;

import ar.edu.utn.frba.dds.climalert.domain.MedicionClima;

import java.util.List;
import java.util.Optional;

public interface RepositorioDeMediciones {

    void guardar(MedicionClima medicion);

    Optional<MedicionClima> ultimaNoEvaluada();

    List<MedicionClima> todas();
}
