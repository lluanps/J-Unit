package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Pessoatest {
	
	@Test
	public void assercaoAgrupada() {
		Pessoa pessoa = new Pessoa("Luan", "Pinheiro");
		
//		assertEquals("Luan", pessoa.getNome());
//		assertEquals("Pinheiro", pessoa.getSobrenome());
		
		// não recomendado que utilize esse metodo para muitas asserções
		assertAll("Asserções de pessoa",
				() -> assertEquals("Luan", pessoa.getNome()),
				() -> assertEquals("Pinheiro", pessoa.getSobrenome()));
	}

}
