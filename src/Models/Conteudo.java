/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Pedro Gomes
 */
public class Conteudo { //TODO ADICIONAR ALBUM
    
    private int id_conteudo;
    private String nome;
    private String artista;
    private String categoria;
    private String path;
    private int tamanho; //TODO: REVER ISTO


    public Conteudo(int id,String name,String artista,String cat,String path){
        this.id_conteudo = id;
        this.nome = name;
        this.artista = artista;
        this.categoria = cat;
        this.tamanho = 0;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
    
   
    
    
    
   
}
