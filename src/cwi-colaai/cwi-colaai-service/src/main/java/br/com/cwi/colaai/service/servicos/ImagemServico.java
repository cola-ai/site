/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

import br.com.cwi.colaai.entity.view_model.ImagemViewModel;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;



@Service
public class ImagemServico {
    
    private static final Logger LOGGER = Logger.getLogger(ImagemServico.class.getName());
    public final String ROOT;
    public final String PASTA_IMAGENS;
    public final String PASTA_FINAL;
    public final String PREPEND;

    public ImagemServico() {
        ROOT = "..\\cwi-colaai-web\\src\\main\\resources\\static\\";
        PASTA_IMAGENS = "media\\img";
        PREPEND = "root";
        PASTA_FINAL = String.format("%s\\%s\\%s", ROOT, PASTA_IMAGENS, PREPEND);
    }

    public ImagemServico(String prepend) {
        ROOT = "..\\cwi-colaai-web\\src\\main\\resources\\static\\";
        PASTA_IMAGENS = "media\\img";
        PREPEND = prepend;
        PASTA_FINAL = String.format("%s\\%s\\%s", ROOT, PASTA_IMAGENS, PREPEND);
    }    
    
    public String salvar(ImagemViewModel imagem){
        String caminhoFinalImagem = "";
        try {
            caminhoFinalImagem = subirImagem(renomearImagem(imagem));
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return caminhoFinalImagem;
    }
    
    private ImagemViewModel renomearImagem(ImagemViewModel imagem) {
        return renomearImagem(imagem, 0);
    }
    
    private ImagemViewModel renomearImagem(ImagemViewModel imagem, Integer i) {
        String nomeDoArquivoFinal = i == 0 ? String.format("%s_%s", PREPEND, imagem.getNomeOriginal()) : String.format("%s_%d_%s", PREPEND, i, imagem.getNomeOriginal());
        
        
        if (Files.exists(Paths.get(String.format("%s\\%s", PASTA_FINAL, nomeDoArquivoFinal))))
        {
            return renomearImagem(imagem, ++i);
        }
        
        imagem.setNomeOriginal(nomeDoArquivoFinal);        
        return imagem;
    }
    
    private String subirImagem(ImagemViewModel imagem) throws IOException {
        criarPastaFinal();
        Files.copy(imagem.getStream(), Paths.get(PASTA_FINAL, imagem.getNomeOriginal()));

        return String.format("%s/%s", PASTA_IMAGENS.replace("\\", "/"), imagem.getNomeOriginal());
    }

    private void criarPastaFinal() {
        try {
            Files.createDirectory(Paths.get(PASTA_FINAL));
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}
