/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.util.Duration;

/**
 *
 * @author josepgrs
 */
public interface Content {
    String getPath();
    String getNome();
    int getID();
    void setID(int id);
    Duration getTamanho();
    void setCategoria(int categoria);
}
