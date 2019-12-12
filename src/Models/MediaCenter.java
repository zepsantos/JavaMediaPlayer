/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DAO.UserDAO;
import java.awt.List;
import java.util.ArrayList;
import java.io.File; 
import java.io.IOException;
import java.nio.file.Files; 
import java.nio.file.Paths;
  
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 

/**
 *
 * @author josepgrs
 */
public class MediaCenter {
    private static MediaCenter inst = null;
    private User currentlyLoggedInUser = null;
    
    
    //private File currenteFilePlaying = null;
    private ArrayList<File> userContentList = new ArrayList();
    private int index = 0;
    
    private Clip clip;
    private long currente_frame;
    private String estado;
    
    

    public MediaCenter() {
        
    }
    
    public void setFile(String path){
        this.userContentList.add(new File(path));
    }
    
    
    public static MediaCenter getInstance() {
        if(inst == null ) inst = new MediaCenter();
        return inst;
    }
    
    
    public void login(String email, String password) {
        User tmp = UserDAO.getInstance().get(email);
        if(tmp == null) return;
        if(tmp.passwordValida(password)) {
            currentlyLoggedInUser = tmp;
        }
    }
    
    public boolean isAuthenticated() {
        return currentlyLoggedInUser != null;
    }
    
    public User getUser(){
        return this.currentlyLoggedInUser;
    }
    
    public String getEstado(){
        return this.estado;
    }
    
    public String[] getCurrentMusic(){
        String string = this.userContentList.get(index).toString();
        String doc = string.substring(9);
        String[] parts = doc.split("-|\\.");
        
        return parts;
    }
    
    
    public void readPlaylist(){
       
        //Path p = Paths.get("C:\Users\Pedro Gomes\Desktop\DSSMediaCenter\Conteudo");
        String[] pahnames;
        
        File f = new File("Conteudo\\\\");
            
        pahnames = f.list();
            
        for(String s : pahnames){
            this.userContentList.add(new File("Conteudo\\\\"+s));
        }       
    }
    
    public void init(){
        try{
            if(this.userContentList.get(index) != null){
            AudioInputStream audio = AudioSystem.getAudioInputStream(this.userContentList.get(index).getAbsoluteFile());
            clip = AudioSystem.getClip(); 
            clip.open(audio);
            estado = "stop";
            
            }
        }catch(Exception e){
            System.out.println("Ficheiro nao encontrado");
       
        }
    }
    
    public void readANDinit(){
        readPlaylist();
        init();
    }
    
    
    public void play(){
        if(clip.getFramePosition()== clip.getFrameLength()){
            clip.setFramePosition(0);
        }
        clip.start();
        this.estado = "play";
    }
    
    
    public void stop(){
        clip.stop();
        this.estado = "stop";
    }
   
   
    
    public void skip_previous_song(){
        stop();
        if(this.userContentList.size() == 0)return;
        
        if(index > 0 ){
            index--;
        }else{
            index = 0;
        }
        init();
        //this.currenteFilePlaying = this.userContentList.get(index);
    
    }
    
    public void skip_next_song(){
        stop();
        if(this.userContentList.size() == 0)return;
        
        if(this.userContentList.size() > index + 1){
            index++;
        }else{
            index = 0;
        }
        init();
        //this.currenteFilePlaying = this.userContentList.get(index);
    }
    
    
    
    
    
}
