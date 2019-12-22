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
    private int categoria,id;
    
    
    public VideoContent(int id,String nome,int categoria, String path, Duration tamanho) {
        this.nome = nome;
        this.path = path;
        this.categoria = categoria;
        this.tamanho = tamanho;
        this.id = id;
    }

    public VideoContent(String name,int cat,String path,Duration tam){
        this.nome = name;
        this.path = path;
        this.categoria = cat;
        this.tamanho = tam;
        this.id = -1;
    
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
    
    public int getCategoria() {
        return this.categoria;
    }
    
    public int getID() {
        return this.id;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
}
