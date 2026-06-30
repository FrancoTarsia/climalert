package ar.edu.utn.frba.dds.climalert.services;

import ar.edu.utn.frba.dds.climalert.domain.Alerta;
import ar.edu.utn.frba.dds.climalert.domain.MedicionClima;
import ar.edu.utn.frba.dds.climalert.integracion.notificacion.ObservadorDeAlerta;
import ar.edu.utn.frba.dds.climalert.repository.RepositorioDeMediciones;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonitorClimaService {

    private static final Logger log = LoggerFactory.getLogger(MonitorClimaService.class);
    private static final String MOTIVO_CRITICO = "Temperatura mayor a 35 C y humedad superior a 60%";

    private final RepositorioDeMediciones repositorio;
    private final List<ObservadorDeAlerta> observadores;

    public MonitorClimaService(RepositorioDeMediciones repositorio, List<ObservadorDeAlerta> observadores) {
        this.repositorio = repositorio;
        this.observadores = observadores;
    }

    public void evaluarUltimaMedicion() {
        Optional<MedicionClima> posibleMedicion = repositorio.ultimaNoEvaluada();
        if (posibleMedicion.isEmpty()) {
            return;
        }

        MedicionClima medicion = posibleMedicion.get();

        if (medicion.esCritica()) {
            log.info("Condiciones criticas detectadas en {}. Generando alerta.", medicion.getUbicacion());
            notificar(new Alerta(medicion, MOTIVO_CRITICO));
        }

        medicion.marcarEvaluada();
    }

    private void notificar(Alerta alerta) {
        this.observadores.forEach(observador -> observador.serNotificadoDe(alerta));
    }
}
