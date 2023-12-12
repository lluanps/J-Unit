package com.algaworks.junit.blog.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algaworks.junit.blog.modelo.Editor;
import com.algaworks.junit.blog.modelo.Ganhos;
import com.algaworks.junit.blog.modelo.Post;
import com.algaworks.junit.blog.utilidade.ProcessadorTextoSimples;

class CalculadoraGanhosTest {

	@BeforeAll
	static void beforeAll() {
		System.out.println("Executa esse test antes de todos os testes");
	}
	
	@BeforeEach
	void beforeEach() {
		System.out.println("Antes de cada método");
	}
	
	@AfterAll
	void afterAll() {
		System.out.println("Depois de todos os métodos");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("Depois de cada test");
	}
	
	@Test
	public void deveCalcularGanhos() {
		CalculadoraGanhos calculadoraGanhos = new CalculadoraGanhos(new ProcessadorTextoSimples(), BigDecimal.TEN);
		
		Editor autor = new Editor(1L, "Luan", "luan@gmail.com",new BigDecimal(5), true);
		
		Post post = new Post(1L, "Ecossistema Java", "o ecossistema do java é muito maduro", autor, "ecossistema java-abc-123", null, false, false);
		
		Ganhos ganhos = calculadoraGanhos.calcular(post);
		
		assertEquals(new BigDecimal("45"), ganhos.getTotalGanho());
		assertEquals(7, ganhos.getQuantidadePalavras());
		assertEquals(autor.getValorPagoPorPalavra(), ganhos.getValorPagoPorPalavra());
	}
	
	@Test
	public void deveCalcularGanhosSemPremium() {
		CalculadoraGanhos calculadoraGanhos = new CalculadoraGanhos(new ProcessadorTextoSimples(), BigDecimal.TEN);
		
		Editor autor = new Editor(1L, "Luan", "luan@gmail.com",new BigDecimal(5), false);
		
		Post post = new Post(1L, "Ecossistema Java", "o ecossistema do java é muito maduro", autor, "ecossistema java-abc-123", null, false, false);
		
		Ganhos ganhos = calculadoraGanhos.calcular(post);
		
		assertEquals(new BigDecimal("35"), ganhos.getTotalGanho());
		assertEquals(7, ganhos.getQuantidadePalavras());
		assertEquals(autor.getValorPagoPorPalavra(), ganhos.getValorPagoPorPalavra());
	}
	
}
