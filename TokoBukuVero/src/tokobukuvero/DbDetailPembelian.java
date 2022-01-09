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
public class DbDetailPembelian {

    String id, nama, harga, tanggal, pembelianId, bukuId, createdAt, updatedAt;
    KoneksiDB db = null;

    public DbDetailPembelian() {
        db = new KoneksiDB();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPembelianId() {
        return pembelianId;
    }

    public void setPembelianId(String pembelianId) {
        this.pembelianId = pembelianId;
    }

    public String getBukuId() {
        return bukuId;
    }

    public void setBukuId(String bukuId) {
        this.bukuId = bukuId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
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
        String query = "INSERT INTO t_detail_pembelian VALUES (null,'" + pembelianId + "', '" + bukuId + "' ,DEFAULT, DEFAULT)";
        db.executeQuery(query);
        db.tutupKoneksi(null);
    }

    public List tampil(String pembelianId) {
        List<DbDetailPembelian> data = new ArrayList<>();
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM t_detail_pembelian INNER JOIN m_buku  ON (t_detail_pembelian.dtpembelianBukuId = m_buku.bukuId) INNER JOIN t_pembelian  ON (t_detail_pembelian.dtPembelianPembelianId = t_pembelian.pembelianId) WHERE dtpembelianPembelianId='" + pembelianId + "'";
            System.out.println(query);
            rs = db.ambilData(query);

            while (rs.next()) {
                DbDetailPembelian debe = new DbDetailPembelian();
                debe.setNama(rs.getString("bukuNama"));
                debe.setHarga(rs.getString("bukuHarga"));
                debe.setTanggal(rs.getString("pembelianTanggal"));
                data.add(debe);
            }
            db.tutupKoneksi(rs);
        } catch (Exception x) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data, error : \n" + x);
        }
        return data;
    }
}
