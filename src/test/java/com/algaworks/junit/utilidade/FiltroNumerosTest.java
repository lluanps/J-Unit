package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FiltroNumerosTest {
	
	//GIVEN, WHEN , THEN
	//DADO, QUANDO, ENTAO
	@Test
	public void Dado_uma_lista_de_numeros_Quando_fitrar_por_pares_Entao_deve_retornar_apenas_numeros_pares() {
		List<Integer> numeros = Arrays.asList(1, 2, 3, 4);
		List<Integer> numerosParesEsperados = Arrays.asList(2, 4);
		List<Integer> resultado = FiltroNumeros.numerosPares(numeros);

		assertIterableEquals(numerosParesEsperados, resultado);
		// outra forma de realziar  -> assertArrayEquals(numerosParesEsperados.toArray(new Object[] {}), resultado.toArray(new Object[] {}));
	}
	
	@Test
	public void Dado_uma_lista_de_numeros_Quando_fitrar_por_impares_Entao_deve_retornar_apenas_numeros_pares() {
		List<Integer> numeros = Arrays.asList(1, 2, 3, 4);
		List<Integer> numerosImparesEsperados = Arrays.asList(1, 3);
		List<Integer> resultado = FiltroNumeros.numerosImpares(numeros);

		assertIterableEquals(numerosImparesEsperados, resultado);
		// outra forma de realziar  -> assertArrayEquals(numerosParesEsperados.toArray(new Object[] {}), resultado.toArray(new Object[] {}));
	}

}
