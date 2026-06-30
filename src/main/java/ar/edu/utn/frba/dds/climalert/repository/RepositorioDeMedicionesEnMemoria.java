package ar.edu.utn.frba.dds.climalert.repository;

import ar.edu.utn.frba.dds.climalert.domain.MedicionClima;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositorioDeMedicionesEnMemoria implements RepositorioDeMediciones {

    private final List<MedicionClima> mediciones = new ArrayList<>();

    @Override
    public void guardar(MedicionClima medicion) {
        this.mediciones.add(medicion);
    }

    @Override
    public Optional<MedicionClima> ultimaNoEvaluada() {
        return this.mediciones.stream()
                .filter(medicion -> !medicion.isEvaluada())
                .reduce((anterior, siguiente) -> siguiente);
    }
}
