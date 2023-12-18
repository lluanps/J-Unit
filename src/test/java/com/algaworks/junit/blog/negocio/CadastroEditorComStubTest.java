package com.algaworks.junit.blog.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algaworks.junit.blog.exception.RegraNegocioException;
import com.algaworks.junit.blog.modelo.Editor;

public class CadastroEditorComStubTest {
	
	static CadastroEditor cadastroEditor;
	static ArmazenamentoEditorFixoEmMemoria armazenamentoEditor;
	Editor editor;

	@BeforeEach
	void beforeEach() {
		editor = new Editor(null, "Luan", "luan@gmail", BigDecimal.TEN, true);
		
		armazenamentoEditor = new ArmazenamentoEditorFixoEmMemoria();
		cadastroEditor = new CadastroEditor(
				armazenamentoEditor,
				new GerenciadorEnvioEmail() {
			@Override
			void enviarEmail(Mensagem mensagem) {
				System.out.println("Enviando mensagem para: " + mensagem.getDestinatario());
			}
		});
	}
	
	@Test
	public void deveRetornarIdDeCadastroQuandoEditorForValido() {
		Editor editorSalvo = cadastroEditor.criar(editor);
		assertEquals(1L, editorSalvo.getId());
		assertTrue(armazenamentoEditor.chamouSalvar);
	}
	
	@Test
	public void deveLancarExceptionQuandoEditorForNull() {
		assertThrows(NullPointerException.class, () -> cadastroEditor.criar(null));
		assertFalse(armazenamentoEditor.chamouSalvar);
	}
	
	@Test
	public void deveLancarExceptionQuandoEmailJaExistente() {
		editor.setEmail("luan.existe@gmail.com");
		assertThrows(RegraNegocioException.class, ()-> cadastroEditor.criar(editor));
	}
	
	@Test
	public void naoDeveSalvarnQuandoEmailJaExistente() {
		editor.setEmail("luan.existe@gmail.com");
		
		try {
			cadastroEditor.criar(editor);
		} catch (RegraNegocioException e) {
			assertFalse(armazenamentoEditor.chamouSalvar);
		}
	}

}
