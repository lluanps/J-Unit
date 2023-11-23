package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SaudacaoUtilTest {
	
	@Test
	public void saudar() {
		String saudacao = SaudacaoUtil.saudar(9);
		System.out.println(saudacao);
		assertEquals("Bom dia", saudacao, "Falha no teste! Saudação incorrenta!");
	}
	
	@Test
	public void deveLancarException() {
		IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> SaudacaoUtil.saudar(-10));
		assertEquals("Hora inválida", illegalArgumentException.getMessage());
	}
	
	@Test
	public void naoDeveLancarException() {
		assertDoesNotThrow(() -> SaudacaoUtil.saudar(0));
	}

}
