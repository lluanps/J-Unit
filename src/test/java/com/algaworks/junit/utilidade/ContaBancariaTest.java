package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class ContaBancariaTest {

	@Test
	public void saque() {
		ContaBancaria contaBancaria = new ContaBancaria(new BigDecimal("100"));
		contaBancaria.saque(new BigDecimal("30"));
		assertEquals(new BigDecimal("70"), contaBancaria.saldo());
	}
	
	@Test
	public void saqueComValorNullFalha() {
		ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.TEN);
		assertThrows(IllegalArgumentException.class, () -> contaBancaria.saque(null));
	}
	
	@Test
	public void saqueComValorZeradoFalha() {
		ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.TEN);
		assertThrows(IllegalArgumentException.class, () -> contaBancaria.saque(BigDecimal.ZERO));
	}
	
	@Test
	public void saqueComValorMenorQueZeroFalha() {
		ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.TEN);
		assertThrows(IllegalArgumentException.class, () -> contaBancaria.saque(new BigDecimal("-5")));
	}
	
	@Test
	public void deposito() {
		ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.TEN);
		contaBancaria.deposito(BigDecimal.TEN);
		assertEquals(new BigDecimal("20"), contaBancaria.saldo());
	}
	
	@Test
	public void depositoComValorNullFalha() {
		ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.TEN);
		assertThrows(IllegalArgumentException.class, () -> contaBancaria.deposito(null));
	}
	
	@Test
	public void depositoComValorIgualAZeroFalha() {
		ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.TEN);
		assertThrows(IllegalArgumentException.class, () -> contaBancaria.deposito(BigDecimal.ZERO));
	}
	
	@Test
	public void depositoComValorMenorQueZeroFalha() {
		ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.TEN);
		assertThrows(IllegalArgumentException.class, () -> contaBancaria.deposito(new BigDecimal("-5")));
	}
	
	@Test
	public void saldo() {
		ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.TEN);
		assertEquals(new BigDecimal("10"), contaBancaria.saldo());
	}
	
	@Test
	public void deveRetornarSaldoCorretoAposSaqueEDeposito() {
		ContaBancaria contaBancaria = new ContaBancaria(new BigDecimal("100"));
		contaBancaria.saque(new BigDecimal("50"));
		contaBancaria.deposito(new BigDecimal("15"));
		assertEquals(new BigDecimal("65"), contaBancaria.saldo());
	}
	
}
