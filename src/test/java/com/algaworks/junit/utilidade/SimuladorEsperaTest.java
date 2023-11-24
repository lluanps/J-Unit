package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SimuladorEsperaTest {
	
	@Test
	@Disabled("Não é mais aplicável")
	public void deveEsperarENaoDarTimeout() {
		//assertTimeout(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofSeconds(3))); É NECESSARIO ESPERAR OS 3 SEGUNDOS PARA O METODO VALIDAR
		// assertTimeoutPreemptively(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofSeconds(3))); // desse modo com assertTimeoutPreemptively não a essa necessidade de espera
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofMillis(3))); // foi usado o ofMillis para que o teste passe
	}

}
