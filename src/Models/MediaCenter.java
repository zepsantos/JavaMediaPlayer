/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DAO.UserDAO;
import java.util.ArrayList;
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackListener;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.ID3v1;
/**
 *
 * @author josepgrs
 */
public class MediaCenter {
    private static MediaCenter inst = null;
    private User currentlyLoggedInUser;
    
    
    //private File currenteFilePlaying = null;
    private ArrayList<Conteudo> userContentList;
    private Conteudo currentlyContent;
    private int index = 0;
    private Thread musicThread;
    private Player player;
    private int currentMusicPos;
    private PlayerStatus musicPlaying;
    
    

    public MediaCenter() {
        this.userContentList = new ArrayList<>();
        this.musicPlaying = PlayerStatus.STOP;
        this.currentMusicPos = 0;
        currentlyLoggedInUser = null;
    }
    
    public void addFile(File file){
        
        //this.userContentList.add(file);
       
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
    
    public PlayerStatus getStatus(){
        return this.musicPlaying;
    }
    
    public String[] getCurrentMusic(){
        if(this.userContentList.size() == 0)return null;
        String string = this.userContentList.get(index).toString();
        String doc = string.substring(9);
        String[] parts = doc.split("-|\\.");
        
        return parts;
    }
    
    public int getPlayListSize(){
        return this.userContentList.size();
    }
    
    public void readPlaylist() {
       
        //Path p = Paths.get("Conteudo/");
        String[] pathnames;
        
        File f = new File("Conteudo/");
        pathnames = f.list();
        if(pathnames != null) {    
        for(String path : pathnames){
            //File tmp = new File("Conteudo/" + s));
            try {
                Conteudo tmp = getTagAndCreateContent(path);
                if(tmp != null)
                this.userContentList.add(tmp);
            } catch (TagException e) {
                //TODO: FAZER ALGUMA COISA QUANDO AS MUSICAS NAO TEM INFO, GUARDAR NOME FICHEIRO E REPRODUZIR
            }
      
            //this.userContentList.add(new File("Conteudo/"+s));
        }         
        }
    }
    
    private Conteudo getTagAndCreateContent(String path) throws TagException {
        Conteudo content = null;
        try {
                MP3File tmp = new MP3File(new File("Conteudo/" + path));
                if (tmp.hasID3v1Tag()) {
                    ID3v1 id3v1Tag = tmp.getID3v1Tag();
                    content = new Conteudo(0, id3v1Tag.getTitle(), id3v1Tag.getArtist(),"teste",path);
                }
            }catch (IOException e) {
                System.out.println(e.getMessage());
            } 
        return content;
    }
        
    
    
 
    
    public void readANDinit(){
        readPlaylist();
    }
    
    private void reproduceMusic() {
        currentlyContent = userContentList.get(index);
        if(currentlyContent == null) return;
        try {
                File tmp = new File("Conteudo/" + currentlyContent.getPath());
                System.out.println(tmp.length());
                FileInputStream source = new FileInputStream(tmp);
                player = new Player(source);
                startMusicPlaying();   
        }catch(JavaLayerException | IOException e) {
                System.out.println("1");
                e.printStackTrace();
        }
            
           
        musicPlaying = PlayerStatus.PLAYING;
    }
    
    
    private void startMusicPlaying() {
        Runnable tmpR = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            player.play();
                        }catch( JavaLayerException e) {
                            System.out.println("2");
                            e.printStackTrace();
                        }
                    }
               };
               musicThread = new Thread(tmpR);
               musicThread.start();
    }
    
    public void play(){
        reproduceMusic();
        this.musicPlaying = PlayerStatus.PLAYING;
    }
    
    
    public void pause(){
        currentMusicPos = player.getPosition();
        System.out.println(currentMusicPos);
        try {
            player.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        this.musicPlaying = PlayerStatus.PAUSE;
    }
   
   
    
    public void skip_previous_song(){
        pause();
        if(this.userContentList.size() == 0)return;
        
        if(index > 0 ){
            index--;
        }else{
            index = 0;
        }
        //this.currenteFilePlaying = this.userContentList.get(index);
    
    }
    
    public void skip_next_song(){
        pause();
        if(this.userContentList.size() == 0)return;
        
        if(this.userContentList.size() > index + 1){
            index++;
        }else{
            index = 0;
        }
        //this.currenteFilePlaying = this.userContentList.get(index);
    }
    
    
    
    
    
}
