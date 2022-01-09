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
public class DbPembelian {

    String id, pembeliId, nama, tanggal, createdAt, updatedAt;
    KoneksiDB db = null;

    public DbPembelian() {
        db = new KoneksiDB();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPembeliId() {
        return pembeliId;
    }

    public void setPembeliId(String pembeliId) {
        this.pembeliId = pembeliId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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
        String query = "INSERT INTO t_pembelian VALUES (null,'" + pembeliId + "', '" + tanggal + "' ,DEFAULT, DEFAULT)";
        db.executeQuery(query);
        db.tutupKoneksi(null);
    }

    public void hapus() {
        String sql = "DELETE FROM t_pembelian WHERE pembelianId='" + id + "'";
        db.executeQuery(sql);
        db.tutupKoneksi(null);
    }

    public List tampil() {
        List<DbPembelian> data = new ArrayList<>();
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM t_pembelian INNER JOIN m_pembeli ON (t_pembelian.pembelianPembeliId = m_pembeli.pembeliId);";
            rs = db.ambilData(query);

            while (rs.next()) {
                DbPembelian debe = new DbPembelian();
                debe.setId(rs.getString("pembelianId"));
                debe.setPembeliId(rs.getString("pembelianPembeliId"));
                debe.setNama(rs.getString("pembeliNama"));
                debe.setTanggal(rs.getString("pembelianTanggal"));
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

    public String ambilIdTerakhir() {
        String hasil = null;
        ResultSet res = null;
        try {
            String sql = "SELECT * FROM t_pembelian ORDER BY pembelianId ASC";
            res = db.ambilData(sql);
            res.last();
            hasil = res.getString("pembelianId");
        } catch (Exception x) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil ID, error \n" + x);
        }
        return hasil;
    }
}
