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
        /*try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mediacenter?user=root&password=1999pmqgslbop")){
            con.setAutoCommit(false);
            PreparedStatement sql;
            sql = con.prepareStatement("SELECT * FROM tconteudos WHERE nome=?");
            sql.setString(1,(String)o);
            ResultSet rs = sql.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id_conteudo");
                String nome = rs.getString("nome");
                String cat = rs.getString("categoria");
                int size = rs.getInt("tamanho");
                //System.out.println(id + " " + nome + " " + cat + " " + size);
                return new MusicContent(id,nome,cat,);
            }
      
        con.commit();
        }catch(SQLException e){
            System.out.println("Deu merda na excessao do get");
            //con.rollback();
        } */
        return null;
    }

    @Override
    public Content put(String k, Content c) {
        if(c instanceof MusicContent) return putMusicContent(k,(MusicContent) c);
        
       return null; 
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
            st.executeUpdate(sql.toString());
            
        }
        catch (SQLException e) {
            // Erro ao estabelecer a ligação
            e.printStackTrace();
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

    @Override
    public Set<Entry<String, Content>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
}
