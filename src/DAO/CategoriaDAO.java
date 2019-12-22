/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Categoria;
import Models.Content;
import Models.MediaCenter;
import Models.MusicContent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author josepgrs
 */
public class CategoriaDAO implements Map<String,Categoria> {
    private static CategoriaDAO inst = null;
    private final String urlDatabase = "jdbc:mysql://sambos.ddns.net/MediaCenter?user=dss&password=qweasd11"; 

    
    
    public static CategoriaDAO getInstance(){
        if(inst == null){
            return new CategoriaDAO();
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
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria get(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria put(String key, Categoria value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Categoria put(String key,Categoria value, Content content) {
        try (Connection con = DriverManager.getConnection(urlDatabase)) {
            //create the statement
            Statement st = con.createStatement();
            StringBuffer sql = null;
            if(content instanceof MusicContent)
            sql = new StringBuffer("INSERT INTO MusicGenreSelection (iduser,idmusic,idgenre) VALUES(");
            else 
            sql = new StringBuffer("INSERT INTO MovieGenreSelection (iduser,idmovie,idgenre) VALUES(");
            sql.append(MediaCenter.getInstance().getUser().getUserID());
            sql.append(",");
            sql.append(content.getID());
            sql.append(",");
            sql.append(value.getId());
            sql.append(");");
            System.out.println(sql.toString());
            st.executeUpdate(sql.toString());
            
        }
        catch (SQLException e) {
            // Erro ao estabelecer a ligação
            System.out.println(e.getMessage());
        }
        return value;
    }

    @Override
    public Categoria remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends String, ? extends Categoria> m) {
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
    public Collection<Categoria> values() {
        try (Connection conn = DriverManager.getConnection(urlDatabase)) {
            
            Collection<Categoria> col = new ArrayList<>(80);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM MusicGenre ");
            
            while (rs.next()) {
                col.add(new Categoria(rs.getInt(1),rs.getString(2)));
            }
           
            return col;
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
    }

    @Override
    public Set<Entry<String, Categoria>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   
    
}
