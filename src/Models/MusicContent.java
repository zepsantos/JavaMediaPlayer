/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.util.Duration;

/**
 *
 * @author Pedro Gomes
 */
public class MusicContent implements Content { //TODO ADICIONAR ALBUM
    
    private int id_conteudo;
    private String artista;
    private String nome;
    private int categoria;
    private String path;
    private Duration tamanho; //TODO: REVER ISTO

    public MusicContent(int id,String name,String artista,int cat,String path,Duration d){
        this.id_conteudo = id;
        this.nome = name;
        this.artista = artista;
        this.categoria = cat;
        this.tamanho = d;
        this.path = path;
  
    }
    
    public MusicContent(String name,String artista,int cat,String path,Duration d){
        this.id_conteudo = -1;
        this.nome = name;
        this.artista = artista;
        this.categoria = cat;
        this.tamanho = d;
        this.path = path;
  
    }
    
    
    public int getId_conteudo() {
        return id_conteudo;
    }

    public void setId_conteudo(int id_conteudo) {
        this.id_conteudo = id_conteudo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Duration getTamanho() {
        return tamanho;
    }

    public void setTamanho(Duration tamanho) {
        this.tamanho = tamanho;
    }
    
    
   
    
    
    
   
}
