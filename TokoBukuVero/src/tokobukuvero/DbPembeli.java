/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokobukuvero;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author az
 */
public class DbPembeli {

    String id, nama, createdAt, updatedAt;
    KoneksiDB db = null;

    public DbPembeli() {
        db = new KoneksiDB();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void tambah() {
        String query = "INSERT INTO m_pembeli VALUES (null,'" + nama + "', DEFAULT, DEFAULT)";
        db.executeQuery(query);
        db.tutupKoneksi(null);
    }

    public void ubah() {
        String query = "UPDATE m_pembeli SET pembeliNama='" + nama + "' WHERE pembeliId='" + id + "'";
        db.executeQuery(query);
        db.tutupKoneksi(null);
    }

    public void hapus() {
        String sql = "DELETE FROM m_pembeli WHERE pembeliId='" + id + "'";
        db.executeQuery(sql);
        db.tutupKoneksi(null);
    }

    public List tampil() {
        List<DbPembeli> data = new ArrayList<>();
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM m_pembeli ORDER BY pembeliId DESC";
            rs = db.ambilData(query);

            while (rs.next()) {
                DbPembeli debe = new DbPembeli();
                debe.setId(rs.getString("pembeliId"));
                debe.setNama(rs.getString("pembeliNama"));
                debe.setCreatedAt(rs.getString("pembeliCreatedAt"));
                debe.setUpdatedAt(rs.getString("pembeliUpdatedAt"));
                data.add(debe);
            }
            db.tutupKoneksi(rs);
        } catch (Exception x) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data, error : \n" + x);
        }
        return data;
    }
}
