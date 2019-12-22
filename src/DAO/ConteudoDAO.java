/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.util.Map;
import Models.Content;
import Models.MediaCenter;
import Models.MusicContent;
import Models.VideoContent;
import java.util.Collection;
import java.util.Set;
import java.sql.*;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 *
 * @author Pedro Gomes
 */
public class ConteudoDAO implements Map<String,Content> {

    private static ConteudoDAO inst;
    private final String urlDatabase = "jdbc:mysql://sambos.ddns.net/MediaCenter?user=dss&password=qweasd11"; 
    
    private ConteudoDAO(){
    
    }
    
    
    public static ConteudoDAO getInstance(){
        if(inst == null){
            return new ConteudoDAO();
        }else return inst;
    
    }  

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsKey(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Content get(Object o) {
        Content c = (Content) o;
        if(c instanceof MusicContent) return getMusicContent((MusicContent) c);
        if(c instanceof VideoContent) return getVideoContent((VideoContent) c);
        return null;
    }

    private MusicContent getMusicContent(MusicContent c ){
    
    try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mediacenter?user=root&password=1999pmqgslbop")){
            con.setAutoCommit(false);
            PreparedStatement sql;
            sql = con.prepareStatement("SELECT * FROM MusicContent WHERE nome=?");
            sql.setString(1,c.getNome());
            ResultSet rs = sql.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id_content");
                String nome = rs.getString("nome");
                String art = rs.getString("artista");
                int cat = rs.getInt("categoria");
                String path = rs.getString("path");
                int size = rs.getInt("tamanho");
                //System.out.println(id + " " + nome + " " + cat + " " + size);
                return new MusicContent(id,nome,art,cat,path,Duration.ZERO);
            }
      
        con.commit();
        }catch(SQLException e){
            System.out.println("Deu merda na excessao do get");
            //con.rollback();
        } 
        return null;
    
    }
    
    private VideoContent getVideoContent(VideoContent c ){
    
    try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mediacenter?user=root&password=1999pmqgslbop")){
            con.setAutoCommit(false);
            PreparedStatement sql;
            sql = con.prepareStatement("SELECT * FROM VideoContent WHERE nome=?");
            sql.setString(1,c.getNome());
            ResultSet rs = sql.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int cat = rs.getInt("categoria");
                String path = rs.getString("path");
                int size = rs.getInt("tamanho");
                //System.out.println(id + " " + nome + " " + cat + " " + size);
                return new VideoContent(id,nome,cat,path,Duration.ZERO);
            }
      
        con.commit();
        }catch(SQLException e){
            System.out.println("Deu merda na excessao do get");
            //con.rollback();
        } 
        return null;
    
    }
    
    @Override
    public Content put(String k, Content c) {
        if(c instanceof MusicContent) return putMusicContent(k,(MusicContent) c);
        if(c instanceof VideoContent) return putVideoContent(k,(VideoContent) c);
        
       return null; 
    }
    
    public VideoContent putVideoContent(String k, VideoContent v) {
        try (Connection con = DriverManager.getConnection(urlDatabase)) {
            //create the statement
            Statement st = con.createStatement();
            StringBuffer sql = new StringBuffer("INSERT INTO VideoContent (nome,categoria,tamanho,path,user_id) VALUES('");
            sql.append(k);
            sql.append("','");
            sql.append(v.getCategoria());
            sql.append("','");
            sql.append(v.getTamanho().toSeconds());
            sql.append("','");
            sql.append(v.getPath());
            sql.append("',");
            sql.append(MediaCenter.getInstance().getUser().getUserID());
            sql.append(");");
            System.out.println(sql.toString());
            st.executeUpdate(sql.toString());
            
        }
        catch (SQLException e) {
            // Erro ao estabelecer a ligação
            System.out.println(e.getMessage());
        }
        return v;
    }
    
    public MusicContent putMusicContent(String k, MusicContent v) {
        try (Connection con = DriverManager.getConnection(urlDatabase)) {
            //create the statement
            Statement st = con.createStatement();
            StringBuffer sql = new StringBuffer("INSERT INTO MusicContent (nome,artista,categoria,path,tamanho,user_id) VALUES('");
            sql.append(k);
            sql.append("','");
            sql.append(v.getArtista());
            sql.append("','");
            sql.append(v.getCategoria());
            sql.append("','");
            sql.append(v.getPath());
            sql.append("',");
            sql.append(v.getTamanho().toMillis());
            sql.append(",");
            sql.append(MediaCenter.getInstance().getUser().getUserID());
            sql.append(");");
            System.out.println(sql.toString());
            st.executeUpdate(sql.toString());
            
        }
        catch (SQLException e) {
            // Erro ao estabelecer a ligação
            System.out.println(e.getMessage());
        }
        return v;
    }

    @Override
    public Content remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends String, ? extends Content> map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Content> values() {
        try (Connection conn = DriverManager.getConnection(urlDatabase)) {
            
            Collection<Content> col = new ArrayList<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM MusicContent WHERE user_id ="+ MediaCenter.getInstance().getUser().getUserID());
            
            while (rs.next()) {
                col.add(new MusicContent(rs.getString(2),rs.getString(3),rs.getInt(4), rs.getString(5),Duration.millis(rs.getLong(6))));
            }
            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }
    
  
    public Collection<VideoContent> MovieValues() {
        try (Connection conn = DriverManager.getConnection(urlDatabase)) {
            
            Collection<VideoContent> col = new ArrayList<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM VideoContent WHERE user_id ="+ MediaCenter.getInstance().getUser().getUserID());
            
            while (rs.next()) {
                col.add(new VideoContent(rs.getInt(1),rs.getString(2),rs.getInt(3), rs.getString(5),Duration.seconds(rs.getLong(4))));
            }
            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public Set<Entry<String, Content>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void changeMyCategoria(Content c, int cat){
        if(c instanceof MusicContent)changeMyCategoriaMusic((MusicContent) c,cat) ;
        if(c instanceof VideoContent)changeMyCategoriaVideo((VideoContent)c,cat); 
    
    }
    
    
    private void changeMyCategoriaVideo(VideoContent c,int cat){
        try (Connection conn = DriverManager.getConnection(urlDatabase)) {
            
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("UPDATE VideoContent SET categoria="+cat);           
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }
    
    
    
    private void changeMyCategoriaMusic(MusicContent c,int cat){
        try (Connection conn = DriverManager.getConnection(urlDatabase)) {
            
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("UPDATE MusicContent SET categoria="+cat+"WHERE id_content ="+c.getId_conteudo());           
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }
    
    
    
    
    
    
    
    
}
