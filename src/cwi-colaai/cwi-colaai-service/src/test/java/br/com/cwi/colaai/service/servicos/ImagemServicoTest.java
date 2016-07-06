
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.view_model.ImagemViewModel;
import java.io.InputStream;
import java.lang.reflect.Method;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Érico de Souza Loewe
 */
@RunWith(MockitoJUnitRunner.class)
public class ImagemServicoTest {
    
    @InjectMocks
    ImagemServico imagemServico;
    
    @Mock
    InputStream stream;
    
    @After
    public void tearDown() {
    }

    @Test
    public void renomearImagemDeveRetornarAImagemRenomeada() throws Exception {
        String nomeEsperado = "null_Imagem_Bonita.jpg";
        ImagemViewModel imagem = new ImagemViewModel("arquivo", "Imagem_Bonita.jpg", stream);
        ImagemViewModel imagemObtida;
        
        Method method = imagemServico.getClass().getDeclaredMethod("renomearImagem", ImagemViewModel.class);
        method.setAccessible(true);
        imagemObtida = (ImagemViewModel) method.invoke(imagemServico, imagem);
        
        assertEquals(nomeEsperado, imagemObtida.getNomeOriginal());
    }
    
    @Test(expected = NullPointerException.class)
    public void salvarNullDeveRetornarUmaExcecao() throws Exception {
        String esperado = null;
        String imagemObtida;
        
        imagemObtida = imagemServico.salvar(null);
        
        assertEquals(esperado, imagemObtida);
    }
    
}
