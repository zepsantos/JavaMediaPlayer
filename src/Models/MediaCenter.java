/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DAO.UserDAO;
import DAO.ConteudoDAO;
import Views.MainForm;
import Views.backgroundUpdater;
import java.util.ArrayList;
import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ProgressBar;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.ID3v1;
import javafx.scene.media.*;
import javafx.util.Duration;
import javax.swing.JProgressBar;
/**
 *
 * @author josepgrs
 */
public class MediaCenter {
    private static MediaCenter inst = null;
    private User currentlyLoggedInUser;

    
    private ArrayList<Content> userMusicContentList;
    private ArrayList<VideoContent> userVideoContentList;
    private Content currentContent;
    private int index = 0;
    private int currentContentPos;
    private PlayerStatus playerStatus;
    private MediaPlayer currentPlayer;
    

    public MediaCenter() {
        
        this.userMusicContentList = new ArrayList<>();
        this.userVideoContentList = new ArrayList<>();
        this.playerStatus = PlayerStatus.STOP;
        currentlyLoggedInUser = null;
        new JFXPanel();
        
    }
    public ArrayList<Content> getUserContentList() {
        return new ArrayList<>(userMusicContentList);
    }
    
    public ArrayList<Content> getUserVideoContentList() {
        return new ArrayList<>(userVideoContentList);
    }
    
    public void addFile(String fName){
        Content content = null;
        ConteudoDAO ct = ConteudoDAO.getInstance();
        if(fName.endsWith(".mp3")){
            content = getTagAndCreateContent(fName);
        } else if(fName.endsWith(".avi")) { //TODO: ADICIONAR VIDEOS
            content = new VideoContent(fName,fName,Duration.ZERO);
        }  else { // CASO NAO ACABE EM NENHUMA DESTAS EXTENSOES
            
        }
           
        
        if(content != null) {
            if(content instanceof MusicContent) this.userMusicContentList.add((MusicContent) content);
            else this.userVideoContentList.add((VideoContent) content);
            ct.put(content.getNome(),content);
        }
       
 
    }
    
    
    private MusicContent getTagAndCreateContent(String path) {
        MusicContent content = null;
        
        try {
            File tmpFile = new File("Conteudo/" + path);
            MP3File tmp = new MP3File(tmpFile);
            if (tmp.hasID3v1Tag()) {
                ID3v1 id3v1Tag = tmp.getID3v1Tag();
                Media media = new Media(tmpFile.toURI().toString());
                MediaPlayer tmpMedia = new MediaPlayer(media);
                while(tmpMedia.getStatus() != MediaPlayer.Status.READY){
                    try {
                       Thread.sleep(10);
                    } catch(InterruptedException e) {   
                    }
                };
                content = new MusicContent(id3v1Tag.getTitle(), id3v1Tag.getArtist(),-1,path,media.getDuration());
            }
            }catch(TagException | IOException j){
                System.out.println(j.getMessage()); //TODO: QUANDO NAO TEM TAGS FAZER ALGUMA COISA
            }
        return content;
    }
    
    public boolean isPlaying() {
        return playerStatus == PlayerStatus.PLAYING;
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
        return this.playerStatus;
    }
    
    public Content getCurrentContent(){
        return currentContent;
    }
    
    public int getPlayListSize(){
        return this.userMusicContentList.size();
    }
    
    public void readPlaylist() {
        this.userMusicContentList = new ArrayList<>(ConteudoDAO.getInstance().values());
    }
    
    
        
    
    
 
    
    public void readANDinit(){
        readPlaylist();
    }
    
    private void reproduceMusic() {
        if(currentContent == null) {
            currentContent = userMusicContentList.get(index);
        if(currentContent == null) return;
            setMusicInPlayer(currentContent);
        } 
        if(currentPlayer != null) {
            currentPlayer.play();
            playerStatus = PlayerStatus.PLAYING;
        }
        
        
        
    }
    
    
    private void setMusicInPlayer(Content content) {
        File tmpFile = new File("Conteudo/" + content.getPath());
        setCurrentPlayer(new MediaPlayer(new Media(tmpFile.toURI().toString())));
        currentContent = content;
    }
    
    
    public double getMusicProgress() {
        double res = 0;
        try {
           //res = (Double) (currentPlayer.getCurrentTime().toMillis()/currentPlayer.getTotalDuration().toMillis())*100;
           res = currentPlayer.getCurrentTime().toMillis();
        } catch(NullPointerException e) {
            
        }
        return res;
    }
    
    private void setCurrentPlayer(final MediaPlayer player) {
        currentPlayer = player;
        currentPlayer.setOnEndOfMedia(() -> {
                
            if(++index < userMusicContentList.size()) {
                currentContent = userMusicContentList.get(index);
                File tmp = new File("Conteudo/"+ currentContent.getPath());
                Media media = new Media(tmp.toURI().toString());
                setCurrentPlayer(new MediaPlayer(media));
                currentPlayer.play();
            }
		
	});
        currentPlayer.totalDurationProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> ov, Duration t, Duration t1) {
                     
            }
            
        });
        
    }
    
   

    public void play(){
        reproduceMusic();
        this.playerStatus = PlayerStatus.PLAYING;
    }
    
    public void play(int musicIndex) {
        setMusicInPlayer(this.userMusicContentList.get(musicIndex));
        play();
    }
    
    
    
    
    public void pause(){
       //currentContentPos = player.getPosition();
        try {
            currentPlayer.pause();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        this.playerStatus = PlayerStatus.PAUSE;
    }
    
    public void stopPlayer() {
        if(currentPlayer != null)
        currentPlayer.stop();
        this.playerStatus = PlayerStatus.STOP;
    }
   
   
    
    public void skip_previous_song(){
        pause();
        if(this.userMusicContentList.size() == 0)return;
        
        if(index > 0 ){
            index--;
        }else{
            index = 0;
        }
        this.currentContent = this.userMusicContentList.get(index);
        if(this.currentContent != null){
            File tmpFile = new File("Conteudo/" + currentContent.getPath());
            setCurrentPlayer(new MediaPlayer(new Media(tmpFile.toURI().toString())));
        }
    
    }
    
    public void skip_next_song(){
        pause();
        
        if(this.userMusicContentList.size() == 0)return;
        
        if(this.userMusicContentList.size() > index + 1){
            index++;
        }else{
            index = 0;
        }
        this.currentContent = this.userMusicContentList.get(index);
        if(this.currentContent != null){
            File tmpFile = new File("Conteudo/" + currentContent.getPath());
            setCurrentPlayer(new MediaPlayer(new Media(tmpFile.toURI().toString())));
        }
        
        
    }
    
    public void skip_to_time(Duration duration) {
        currentPlayer.seek(duration);
    }
    
    
    
    public MediaPlayer getMediaPlayer() {
        return this.currentPlayer;
    }
    
}
