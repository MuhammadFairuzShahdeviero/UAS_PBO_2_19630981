/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokobukuvero;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Az
 */
public class KoneksiDB {

    public static Connection koneksi;
    public Statement st;
    public static String serverUrl = "jdbc:mysql://localhost:3306/tokobukuvero";
    public static String username = "root";
    public static String password = "";

    public static Connection ambilKoneksi() {
        if (koneksi == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                koneksi = DriverManager.getConnection(KoneksiDB.serverUrl, KoneksiDB.username, KoneksiDB.password);
            } catch (Exception x) {
                JOptionPane.showMessageDialog(null, "Koneksi gagal, Pesan error : \n" + x);
            }
        }
        return koneksi;
    }

    public void koneksi() {
        try {
            String serverUrl = "jdbc:mysql://localhost:3306/tokobukuvero";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(KoneksiDB.serverUrl, KoneksiDB.username, KoneksiDB.password);
            st = koneksi.createStatement();
        } catch (Exception x) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal, error : \n" + x);
        }
    }

    public void tutupKoneksi(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            st.close();
            koneksi.close();
        } catch (Exception x) {
            JOptionPane.showMessageDialog(null, "Tutup Koneksi Gagal, Pesan error \n" + x);
        }
    }

    public ResultSet ambilData(String sql) {
        ResultSet rs = null;
        try {
            koneksi();
            rs = st.executeQuery(sql);
        } catch (Exception x) {
            JOptionPane.showMessageDialog(null, "Ambil Data Gagal, Pesan error : \n" + x);
        }
        return rs;
    }

    public void executeQuery(String sql) {
        try {
            koneksi();
            st.executeUpdate(sql);
        } catch (Exception x) {
            JOptionPane.showMessageDialog(null, "Simpan Data Gagal, Pesan error : \n" + x);
        }
    }
}
