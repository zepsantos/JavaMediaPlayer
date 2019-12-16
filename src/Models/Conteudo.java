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
public class Conteudo {
    
    private int id_conteudo;
    private String artista;
    private String nome;
    private String categoria;
    private long tamanho;


    public Conteudo(String name,String art,String cat,long tam){
        this.id_conteudo = -1;
        this.nome = name;
        this.artista = art;
        this.categoria = cat;
        this.tamanho = tam;
  
    }
    
    
    public Conteudo(int id,String name,String art,String cat,long tam){
        this.id_conteudo = id;
        this.nome = name;
        this.artista = art;
        this.categoria = cat;
        this.tamanho = tam;
  
    }
    
    
    
    
    
   
}
