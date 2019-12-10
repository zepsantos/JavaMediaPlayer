/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.User;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.sql.*;

/**
 *
 * @author josepgrs
 */
public class UserDAO implements Map<String,User> {
    
    private static UserDAO inst = null;
    private final String urlDatabase = "jdbc:mysql://sambos.ddns.net/MediaCenter?user=dss&password=qweasd11";
    
    public UserDAO() {
       
    }
    
    public static UserDAO getInstance() {
        if(inst == null) inst = new UserDAO();
        return inst;
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
    public User get(Object key) {
        User tmp = null;
        try (Connection con = DriverManager.getConnection(urlDatabase)) {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM Users WHERE email='"+(String)key+"'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                tmp = new User(rs.getString(2),rs.getString(3));
            }
        }
        catch (SQLException e) {
            System.out.println("Erro ao obter o utilizador" + e.getMessage());
        }
        return tmp;
    }

    @Override
    public User put(String key, User value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends String, ? extends User> m) {
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
    public Collection<User> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<String, User>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
