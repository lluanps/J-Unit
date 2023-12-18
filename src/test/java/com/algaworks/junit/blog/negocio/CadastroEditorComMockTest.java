package com.algaworks.junit.blog.negocio;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.algaworks.junit.blog.armazenamento.ArmazenamentoEditor;
import com.algaworks.junit.blog.modelo.Editor;

public class CadastroEditorComMockTest {
	
	CadastroEditor cadastroEditor;
	Editor editor;
	
	@BeforeEach
	void beforeEach() {
		editor = new Editor(null, "Luan", "luan@gmail.com", BigDecimal.TEN, true);
		
		ArmazenamentoEditor armazenamentoEditor = Mockito.mock(ArmazenamentoEditor.class);
		Mockito.when(armazenamentoEditor.salvar(editor))
				.thenReturn(new Editor(1L, "Luan", "luan@gmail.com", BigDecimal.TEN, true));
		
		GerenciadorEnvioEmail gerenciadorEnvioEmail = Mockito.mock(GerenciadorEnvioEmail.class);
		
		cadastroEditor = new CadastroEditor(armazenamentoEditor, gerenciadorEnvioEmail);
	}
	
	@Test
	public void deveCriarEditorQuandoForUmEditorValido() {
		Editor editorSalvo = cadastroEditor.criar(editor);
		assertEquals(1L, editorSalvo.getId());
	}

}
