/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package screensframework;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;

/**
 *
 * @author ulrich
 */
public class ComboBoxClass {
    String query;
    ComboBox comboBox;
    DatabaseHelper db;
    
    public ComboBoxClass() throws SQLException {
        db = new DatabaseHelper();
    }
   
    
    public void loadComboBox(String query , ComboBox comboBox1) {
        this.comboBox = comboBox1;
        this.query = query;
        System.out.println(query);
           try {
            try {
                comboBox.getItems().clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        try {            
            db.setQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(ComboBoxClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(ComboBoxClass.class.getName()).log(Level.SEVERE, null, ex);
        }
            for (int i = 0; i < db.getRowCount(); i++) {
                
                System.out.println(db.getValueAt(i, 0));              
                String userName =  db.getValueAt(i, 0).toString();
                String user_id = db.getValueAt(i, 1).toString();
                this.comboBox.getItems().add(new ComboItem(userName, user_id )); 
            }
                this.comboBox.getSelectionModel().selectLast();
               // comboBox.setVisibleRowCount(6);
           }   catch (IllegalStateException | NumberFormatException s) {
                s.printStackTrace();
        } 
}
}