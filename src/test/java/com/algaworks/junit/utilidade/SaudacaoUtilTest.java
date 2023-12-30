package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Testes no utilitário de saudação")
public class SaudacaoUtilTest {
	
	@Test
	@DisplayName("Deve saudar com bom dia")
	public void deveRetornarBomDiaApartir5Horas() {
//		Int saudacao = 5;
		String saudacao = SaudacaoUtil.saudar(5);
		System.out.println(saudacao);
		assertEquals("Bom dia", saudacao, "Falha no teste! Saudação incorrenta!");
	}
	
	@Test
	@DisplayName("Deve saudar com boa tarde")
	public void deveRetornarBomTarde() {
		String saudacao = SaudacaoUtil.saudar(15);
		System.out.println(saudacao);
		assertEquals("Boa tarde", saudacao, "Falha no teste! Saudação incorrenta!");
	}
	
	@Test
	@DisplayName("Deve saudar com boa noite")
	public void deveRetornarBoaNoite() {
		String saudacao = SaudacaoUtil.saudar(2);
		System.out.println(saudacao);
		assertEquals("Boa noite", saudacao, "Falha no teste! Saudação incorrenta!");
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
	
	//Testes parametrizados
	@ParameterizedTest
	@ValueSource(ints = {5, 6, 7, 8, 9, 10, 11})//roda o teste para cada valor
	public void deveRetornarBomDiaQuandoSudar(int hora) {
		String saudacao = SaudacaoUtil.saudar(hora);
		assertEquals("Bom dia", saudacao);
	}

}
