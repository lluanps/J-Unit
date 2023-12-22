package com.algaworks.junit.blog.negocio;

import java.math.BigDecimal;

import com.algaworks.junit.blog.modelo.Editor;

public class EditorTestData {
	
	private EditorTestData() {
	}

	public static Editor umEditorNovo() {
		return new Editor(null, "Luan", "luan@gmail.com", BigDecimal.TEN, true);
	}

	public static Editor umEditorExistente() {
		return new Editor(1L, "Luan", "luan@gmail.com", BigDecimal.TEN, true);
	}
	
}
