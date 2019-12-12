/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DAO.UserDAO;
import java.util.ArrayList;
import java.io.File; 
import java.io.IOException;
import java.nio.file.DirectoryStream; 
import java.nio.file.Files; 
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.shape.Path;
  
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
    
    
    public void skip_next_song(){
        if(this.userContentList.size() == 0)return;
        
        if(this.userContentList.size() > index + 1){
            index++;
        }else{
            index = 0;
        }
        //this.currenteFilePlaying = this.userContentList.get(index);
    
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
    
    public String getEstado(){
        return this.estado;
    }
    
    public void readPlaylist(String dir){
       
        //Path p = Paths.get("C:\Users\Pedro Gomes\Desktop\DSSMediaCenter\Conteudo");
        
        File folder = new File(dir);
        String[] files = null;
        if(files != null){
            files = folder.list();
            return;
        }
        for(String a : files){
            System.out.println(a);
            this.userContentList.add(new File(a));
        }
        System.out.println("estou aqui");
    }
    
    
    
    
}
