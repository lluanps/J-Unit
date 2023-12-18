package com.algaworks.junit.blog.negocio;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.algaworks.junit.blog.armazenamento.ArmazenamentoEditor;
import com.algaworks.junit.blog.modelo.Editor;

@ExtendWith(MockitoExtension.class)
public class CadastroEditorComMockTest {
	
	Editor editor;
	
	@Mock
	ArmazenamentoEditor armazenamentoEditor;

	@Mock
	GerenciadorEnvioEmail gerenciadorEnvioEmail;
	
	//junta os mocks dentro cadastroEditor, criando uma instancia concreto com os mocks acimas, sendo passados como parametros
	@InjectMocks
	CadastroEditor cadastroEditor;
	
	@BeforeEach
	void beforeEach() {
		editor = new Editor(null, "Luan", "luan@gmail.com", BigDecimal.TEN, true);
		
		//Mockito.when(armazenamentoEditor.salvar(editor))
		Mockito.when(armazenamentoEditor.salvar(Mockito.any(Editor.class))) //Parâmetros dinâmicos
				//.thenReturn(new Editor(1L, "Luan", "luan@gmail.com", BigDecimal.TEN, true));
				.thenAnswer(invocacao -> {
					Editor editorPassado = invocacao.getArgument(0, Editor.class);
					editorPassado.setId(1L);
					return editorPassado;
				});
		
		cadastroEditor = new CadastroEditor(armazenamentoEditor, gerenciadorEnvioEmail);
	}
	
	@Test
	public void deveCriarEditorQuandoForUmEditorValido() {
		Editor editorSalvo = cadastroEditor.criar(editor);
		assertEquals(1L, editorSalvo.getId());
	}
	
	@Test
	void deveChamarMetodoSalvarDoArmazenamentoQuandoEditorForValido() {
		cadastroEditor.criar(editor);
		Mockito.verify(armazenamentoEditor, Mockito.times(1))
				.salvar(Mockito.eq(editor));
	}

}
