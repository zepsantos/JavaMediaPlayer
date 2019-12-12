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
public class User {
    private String email,nome,password;
    
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
   public Boolean passwordValida(String pass){
       return this.password.equals(pass);
   }
    
   
   public String getNome(){
        return this.nome;
   }
}
