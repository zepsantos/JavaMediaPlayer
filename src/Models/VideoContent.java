/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.util.Duration;

/**
 *
 * @author josepgrs
 */
public class VideoContent implements Content {
    private String path,nome;
    private Duration tamanho;

    public VideoContent(String nome, String path, Duration tamanho) {
        this.nome = nome;
        this.path = path;
        this.tamanho = tamanho;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public Duration getTamanho() {
        return this.tamanho;
    }
    
    
}
