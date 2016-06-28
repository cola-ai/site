/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.colaai.service.servicos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author alycio.neto
 */


@Service
public class ServicoImagem {
    public static String ROOT = "..\\cwi-colaai-web\\src\\main\\resources\\static\\media\\img";
    
    public String SalvarImagem(MultipartFile file, String path){
        try {
            Files.copy(file.getInputStream(), Paths.get(path, file.getOriginalFilename()));
                return file.getName();
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
