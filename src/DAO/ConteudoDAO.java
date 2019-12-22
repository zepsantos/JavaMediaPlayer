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
        /*
        Content c = (Content) o;
        if(c instanceof MusicContent) return getMusicContent((MusicContent) c);
        if(c instanceof VideoContent) return getVideoContent((VideoContent) c);*/
        return null;
    }

    private MusicContent getMusicContent(MusicContent c ){
    
    try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mediacenter?user=root&password=1999pmqgslbop")){
            con.setAutoCommit(false);
            PreparedStatement sql;
           
            sql = con.prepareStatement("SELECT * FROM tconteudos WHERE nome=?");
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
    
    public int getOwner(Content c) {
        int tmp = -1;
        boolean music = c instanceof MusicContent;
        try (Connection con = DriverManager.getConnection(urlDatabase)) {
            Statement st = con.createStatement();
            String sql;
            if(music)
             sql = "SELECT * FROM MusicContent WHERE id_content="+String.valueOf(c.getID());
            else
             sql = "SELECT * FROM VideoContent WHERE id="+String.valueOf(c.getID());
 
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                if(music)
                tmp = rs.getInt(7);
                else 
                 tmp=rs.getInt(8);
            }
        }
        catch (SQLException e) {
            System.out.println("Erro ao obter o utilizador" + e.getMessage());
        }
        return tmp;
        
    }
    
    public int getMusicID(MusicContent mc) {
        int tmp = -1;
        try (Connection con = DriverManager.getConnection(urlDatabase)) {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM MusicContent WHERE nome='"+(String)mc.getNome()+"'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                tmp = rs.getInt(1);
            }
        }
        catch (SQLException e) {
            System.out.println("Erro ao obter o utilizador" + e.getMessage());
        }
        return tmp;
        
    }
    
    
    public Content putRepeatedContent(Content content) {
        boolean music = content instanceof MusicContent;
         try (Connection con = DriverManager.getConnection(urlDatabase)) {
            //create the statement
            Statement st = con.createStatement();
            StringBuffer sql;
            if(music)
            sql = new StringBuffer("INSERT INTO MusicUploaded (iduser,idmusic) VALUES(");
            else
                sql =  new StringBuffer("INSERT INTO VideoUploaded (iduser,idvideo) VALUES(");
            sql.append(MediaCenter.getInstance().getUser().getUserID());
            sql.append(",");
            sql.append(content.getID());
            sql.append("");
            sql.append(");");
            System.out.println(sql.toString());
            st.executeUpdate(sql.toString());
            
        }
        catch (SQLException e) {
            // Erro ao estabelecer a ligação
            System.out.println(e.getMessage());
            return null;
        }
        return content;
    }

    public int getMovieID(VideoContent mc) {
        int tmp = -1;
        try (Connection con = DriverManager.getConnection(urlDatabase)) {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM MovieContent WHERE nome='"+(String)mc.getNome()+"'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                tmp = rs.getInt(1);
            }
        }
        catch (SQLException e) {
            System.out.println("Erro ao obter o utilizador" + e.getMessage());
        }
        return tmp;
        
    }
    @Override
    public Content put(String k, Content c) {
        if(c instanceof MusicContent){
            if(getMusicID((MusicContent)c) != -1) return null;
            return putMusicContent(k,(MusicContent) c);
        }
        if(c instanceof VideoContent){
            if(getMovieID((VideoContent)c) != -1) return null;
            return putVideoContent(k,(VideoContent) c);
        }
        
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
            ResultSet rs = stm.executeQuery("SELECT * FROM MusicContent");
            
            while (rs.next()) {
                col.add(new MusicContent(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4), rs.getString(5),Duration.millis(rs.getLong(6))));
            }
            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }
    
    public Collection<Content> valuesFromUser() {
        try (Connection conn = DriverManager.getConnection(urlDatabase)) {
            
            Collection<Content> col = new ArrayList<>();
            StringBuffer sql = new StringBuffer("SELECT * FROM MusicContent WHERE user_id =? UNION SELECT id_content,nome,artista,categoria,path,tamanho,user_id from MusicContent mc INNER JOIN MusicUploaded mu on mc.id_content = mu.idmusic WHERE mu.iduser =?");
            PreparedStatement stm = conn.prepareStatement(sql.toString());
            int userid = MediaCenter.getInstance().getUser().getUserID();
            stm.setInt(1, userid);
            stm.setInt(2,userid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                col.add(new MusicContent(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4), rs.getString(5),Duration.millis(rs.getLong(6))));
            }
            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    
    }
    
  
    public Collection<VideoContent> MovieValuesFromUser() {
        try (Connection conn = DriverManager.getConnection(urlDatabase)) {
            
            Collection<VideoContent> col = new ArrayList<>();
            StringBuffer sql = new StringBuffer("SELECT * from VideoContent  Where user_id = ? " +
"UNION " +
"SELECT id,nome,categoria,tamanho,path,user_id from VideoContent mc INNER JOIN VideoUploaded mu on mc.id=mu.idmovie WHERE mu.iduser = ?;");
            PreparedStatement stm = conn.prepareStatement(sql.toString());
            int userid = MediaCenter.getInstance().getUser().getUserID();
            stm.setInt(1, userid);
            stm.setInt(2,userid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                col.add(new VideoContent(rs.getInt(1),rs.getString(2),rs.getInt(3), rs.getString(5),Duration.seconds(rs.getLong(4))));
            }
            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }
    
    public Collection<VideoContent> MovieValues() {
        try (Connection conn = DriverManager.getConnection(urlDatabase)) {
            
            Collection<VideoContent> col = new ArrayList<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM VideoContent ");
            
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
            
            String query = "UPDATE VideoContent SET categoria= ? WHERE id =?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt   (1, cat);
            preparedStmt.setInt(2,c.getID());
            preparedStmt.executeUpdate();    
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }
    
    
    
    private void changeMyCategoriaMusic(MusicContent c,int cat){
        try (Connection conn = DriverManager.getConnection(urlDatabase)) {
            
            String query = "UPDATE MusicContent SET categoria= ? WHERE id_content =?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, cat);
            preparedStmt.setInt(2,c.getID());
            preparedStmt.executeUpdate();
           
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }
    
    
    
    
    
    
    
    
}
