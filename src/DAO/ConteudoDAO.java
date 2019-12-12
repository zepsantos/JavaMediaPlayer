/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.util.Map;
import Models.Conteudo;
import java.util.Collection;
import java.util.Set;
import java.sql.*;

/**
 *
 * @author Pedro Gomes
 */
public class ConteudoDAO implements Map<String,Conteudo> {

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
    public Conteudo get(Object o) {
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mediacenter?user=root&password=1999pmqgslbop")){
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
                return new Conteudo(id,nome,cat,size);
            }
      
        con.commit();
        }catch(SQLException e){
            System.out.println("Deu merda na excessao do get");
            //con.rollback();
        }
        return null;
    }

    @Override
    public Conteudo put(String k, Conteudo v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Conteudo remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends String, ? extends Conteudo> map) {
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
    public Collection<Conteudo> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<String, Conteudo>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
}
