/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokobukuvero;

import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author az
 */
public class DbBuku {

    String id, nama, harga, createdAt, updatedAt;
    KoneksiDB db = null;

    public DbBuku() {
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

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
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
        String query = "INSERT INTO m_buku VALUES (null,'" + nama + "','" + harga + "', DEFAULT, DEFAULT)";
        db.executeQuery(query);
        db.tutupKoneksi(null);
    }

    public void ubah() {
        String query = "UPDATE m_buku SET bukuNama='" + nama + "', bukuHarga='" + harga + "' WHERE bukuId='" + id + "'";
        db.executeQuery(query);
        db.tutupKoneksi(null);
    }

    public void hapus() {
        String sql = "DELETE FROM m_buku WHERE bukuId='" + id + "'";
        db.executeQuery(sql);
        db.tutupKoneksi(null);
    }

    public List tampil() {
        List<DbBuku> data = new ArrayList<>();
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM m_buku ORDER BY bukuId DESC";
            rs = db.ambilData(query);

            while (rs.next()) {
                DbBuku debe = new DbBuku();
                debe.setId(rs.getString("bukuId"));
                debe.setNama(rs.getString("bukuNama"));
                debe.setHarga(rs.getString("bukuHarga"));
                debe.setCreatedAt(rs.getString("bukuCreatedAt"));
                debe.setUpdatedAt(rs.getString("bukuUpdatedAt"));
                data.add(debe);
            }
            db.tutupKoneksi(rs);
        } catch (Exception x) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data, error : \n" + x);
        }
        return data;
    }

    public void cetak() {
        System.out.println("Cetak");
        try {
            InputStream sumber = getClass().getResourceAsStream("/tokobukuvero/reportBuku.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(sumber);
            Map params = new HashMap();
            params.put("test", "test");

            JasperPrint jp = JasperFillManager.fillReport(jr, params, KoneksiDB.ambilKoneksi());

            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setExtendedState(viewer.getExtendedState() | 0x6);
            viewer.setVisible(true);
            viewer.setTitle("Laporan Buku Seluruh");
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Tidak dapat menampilkan report " + ex);
        }
    }
}
