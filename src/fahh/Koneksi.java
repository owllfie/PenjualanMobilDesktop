/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fahh;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Koneksi {
    private final String url="jdbc:mysql://localhost/penjualan_mobil2";
    private final String username="root";
    private final String password="";
    private Connection con;
    public void connect(){
        try{
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Koneksi Berhasil");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public Connection getCon(){
        return con;
    }
}
