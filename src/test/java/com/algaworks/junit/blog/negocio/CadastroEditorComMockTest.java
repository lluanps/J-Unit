package com.algaworks.junit.blog.negocio;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.algaworks.junit.blog.armazenamento.ArmazenamentoEditor;
import com.algaworks.junit.blog.exception.RegraNegocioException;
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
	
	@Captor//injeta e cria um argumento captor a cada novo test
	ArgumentCaptor<Mensagem> mensagemArgumentCaptor;
	
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
	
	@Test
	void naoDeveEnviarEmailQuandoLancadoUmaException() {
		Mockito.when(armazenamentoEditor.salvar(editor))
				.thenThrow(new RuntimeException());

		assertAll("Não deve enviar email quando lancar exception do armazenamento ",
				() -> assertThrows(RuntimeException.class, () -> cadastroEditor.criar(editor)),
				() -> Mockito.verify(gerenciadorEnvioEmail, Mockito.never()).enviarEmail(Mockito.any()));
	}
	
	@Test
	void deveEnviarEmailComDestinoAoEditorQuandoOEditorForValidoAoCadastrar() {
		ArgumentCaptor<Mensagem> mensagemArgumentCaptor = ArgumentCaptor.forClass(Mensagem.class);
		
		Editor editorSalvo = cadastroEditor.criar(editor);
		
		Mockito.verify(gerenciadorEnvioEmail).enviarEmail(mensagemArgumentCaptor.capture());//pega o argumento que foi passado na execução do metodo criar

		Mensagem mensagem = mensagemArgumentCaptor.getValue();
		
		assertEquals(editorSalvo.getEmail(), mensagem.getDestinatario());
	}
	
	@Test
	void deveVerificarEmailQuandoUmEditorValidoEhCadastrado() {
		Editor editorSpy = Mockito.spy(editor);
		
		cadastroEditor.criar(editorSpy);
		
		Mockito.verify(editorSpy, Mockito.atLeast(1)).getEmail();
	}
	
	@Test
	void deveLancarExceptionQuandoEmailExistenteJaFoiCadastrado() {
		Mockito.when(armazenamentoEditor.encontrarPorEmail("luan@gmail.com"))
				.thenReturn(Optional.empty())
				.thenReturn(Optional.of(editor));
		
		Editor editorComEmailExistente = new Editor(null, "Luan", "luan@gmail.com", BigDecimal.TEN, true);
		
		cadastroEditor.criar(editor);
		
		assertThrows(RegraNegocioException.class, () -> cadastroEditor.criar(editorComEmailExistente));
	}
	
	@Test
	void deveEnviarEmailAposSalvarQuandoEditorForValido() {
		cadastroEditor.criar(editor);
		
		InOrder inOrder = Mockito.inOrder(armazenamentoEditor, gerenciadorEnvioEmail);
		
		inOrder.verify(armazenamentoEditor, Mockito.times(1)).salvar(editor);
		inOrder.verify(gerenciadorEnvioEmail, Mockito.times(1)).enviarEmail(Mockito.any(Mensagem.class));
	}

}
