/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokobukuvero;

/**
 *
 * @author Vero
 */
public class TokoBukuVero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama().setVisible(true);
//                new MasterBuku().setVisible(true);
//                new MasterPembeli().setVisible(true);
//                new TransaksiPembelian().setVisible(true);
//                new TambahPembelian().setVisible(true);

            }
        });
    }

}
