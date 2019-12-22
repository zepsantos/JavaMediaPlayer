/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author josepgrs
 */
public class Categoria {
    private int id;
    private String nome;

    public Categoria(int id , String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    
    
}
