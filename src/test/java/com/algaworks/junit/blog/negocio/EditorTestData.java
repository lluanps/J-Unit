package com.algaworks.junit.blog.negocio;

import java.math.BigDecimal;

import com.algaworks.junit.blog.modelo.Editor;

public class EditorTestData {
	
	private EditorTestData() {
	}

	public static Editor.Builder umEditorNovo() {
		return Editor.builder()
				.comNome("Luan")
				.comEmail("luan@gmail.com")
				.comValorPagoPorPalavra(BigDecimal.TEN)
				.comPremium(true);
	}

	public static Editor.Builder umEditorExistente() {
		return umEditorNovo().comId(1L);
	}
	
	public static Editor.Builder umEditorInexistente() {
		return umEditorNovo().comId(99L);
	}
	
}
