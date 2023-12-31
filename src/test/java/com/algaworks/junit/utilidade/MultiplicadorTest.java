package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class MultiplicadorTest {
	
	@ParameterizedTest
	@EnumSource(value = Multiplicador.class, names = {"DOBRO", "TRIPLO"})
	void aplicarMultiplicador(Multiplicador multiplicador) {
		assertNotNull(multiplicador.aplicarMultiplicador(10.0));
	}
	
	@ParameterizedTest
	@EnumSource(value = Multiplicador.class)
	void aplicarMultiplicadorTodos(Multiplicador multiplicador) {
		assertNotNull(multiplicador.aplicarMultiplicador(10.0));
	}

}
