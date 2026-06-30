package ar.edu.utn.frba.dds.climalert.scheduler;

import ar.edu.utn.frba.dds.climalert.services.MonitorClimaService;
import ar.edu.utn.frba.dds.climalert.services.RegistroClimaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimaScheduler {

    private final RegistroClimaService registroClimaService;
    private final MonitorClimaService monitorClimaService;

    public ClimaScheduler(RegistroClimaService registroClimaService, MonitorClimaService monitorClimaService) {
        this.registroClimaService = registroClimaService;
        this.monitorClimaService = monitorClimaService;
    }

    @Scheduled(fixedRateString = "${climalert.frecuencias.consulta-rate-ms}")
    public void obtenerYGuardarClima() {
        registroClimaService.registrarClimaActual();
    }

    @Scheduled(fixedRateString = "${climalert.frecuencias.eval-rate-ms}")
    public void evaluarAlertas() {
        monitorClimaService.evaluarUltimaMedicion();
    }
}
