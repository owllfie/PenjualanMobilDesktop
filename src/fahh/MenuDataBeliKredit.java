/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fahh;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class MenuDataBeliKredit extends javax.swing.JFrame {

    /**
     * Creates new form Beranda
     */
    private DefaultTableModel model=null;
    private PreparedStatement stat;
    private ResultSet rs;
    Koneksi k = new Koneksi();
    public MenuDataBeliKredit() {
        initComponents();
        k.connect();
        ShowTable();
        ComboMobil();
        ComboPembeli();
        ComboPaket();
    }
    
    class kredit extends MenuDataBeliCash{
        String kode, ktp, mobil, paket, tanggal; 
        int cicilan, tenor, total;
        public kredit(){
        this.kode=IDBeli.getText();
        String combo1=KTPPembeli.getSelectedItem().toString();
        this.ktp=combo1.split(":")[0];
        
        String combo2=KodeMobil.getSelectedItem().toString();
        String [] arr1=combo2.split(":");
        this.mobil=arr1[0];
        int harga=Integer.parseInt(arr1[4]);
        
        String combo3=PaketKredit.getSelectedItem().toString();
        String [] arr2=combo3.split(":");
        this.paket=arr2[0];
        double uangmuka=Double.parseDouble(arr2[1]);
        double tenor=Double.parseDouble(arr2[2]);
        double bunga=Double.parseDouble(arr2[3]);
        
        double dp=harga*uangmuka/100;
        double pinjaman=harga-dp;
        this.total=(int)(pinjaman-(pinjaman*bunga*tenor/12)/100);
        this.cicilan=(int)(this.total/tenor);
        
        this.tenor=(int)tenor;
        
        try{
            Date date = TanggalPembelian.getDate();
            DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
            this.tanggal=dateformat.format(date);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Tanggal Harus dimasukkan"+e.getMessage());
        }
        }
    }
        public void ShowTable(){
        model=new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("KTP");
        model.addColumn("Paket");
        model.addColumn("Mobil");
        model.addColumn("Tanggal");
        model.addColumn("Bayar");
        model.addColumn("Tenor");
        model.addColumn("Total");
        TabelKredit.setModel(model);
        
        try{
            this.stat=k.getCon().prepareStatement("select * from kredit");
            this.rs=this.stat.executeQuery();
            while(rs.next()){
                Object[] data={
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8)
                };
                model.addRow(data);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        IDBeli.setText("");
    }
        public void ComboMobil(){
    try{
            this.stat=k.getCon().prepareStatement("select * from mobil");
            this.rs=this.stat.executeQuery();
            while(rs.next()){
                    KodeMobil.addItem(rs.getString("kode_mobil")+":"
                    +rs.getString("merk")+":"
                    +rs.getString("type")+":"
                    +rs.getString("warna")+":"
                    +rs.getInt("harga")+":"
                    );
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
}
    public void ComboPembeli(){
    try{
            this.stat=k.getCon().prepareStatement("select * from pembeli");
            this.rs=this.stat.executeQuery();
            while(rs.next()){
                    KTPPembeli.addItem(rs.getString("ktp")+":"
                    +rs.getString("nama_pembeli")
                    );
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
}
    public void ComboPaket(){
    try{
            this.stat=k.getCon().prepareStatement("select * from paket");
            this.rs=this.stat.executeQuery();
            while(rs.next()){
                    PaketKredit.addItem(rs.getString("kode_paket")+":"
                    +rs.getInt("uang_muka")+":"
                    +rs.getInt("tenor")+":"
                    +rs.getInt("bunga_cicilan")
                    );
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        HapusButton = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        IDBeli = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelKredit = new javax.swing.JTable();
        KembaliButton = new javax.swing.JLabel();
        TambahButton = new javax.swing.JLabel();
        EditButton = new javax.swing.JLabel();
        PaketKredit = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        Bayar = new javax.swing.JTextField();
        KTPPembeli = new javax.swing.JComboBox<>();
        KodeMobil = new javax.swing.JComboBox<>();
        TanggalPembelian = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Transaksi Beli Kredit");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, -1, -1));

        HapusButton.setBackground(new java.awt.Color(51, 51, 255));
        HapusButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        HapusButton.setText("Hapus");
        HapusButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HapusButtonMouseClicked(evt);
            }
        });
        getContentPane().add(HapusButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 310, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("ID Beli:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 70, 20));

        IDBeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDBeliActionPerformed(evt);
            }
        });
        getContentPane().add(IDBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 570, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("KTP Pembeli:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 80, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Kode Mobil:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 70, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Paket Kredit:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 80, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Tanggal Pembelian:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 110, 20));

        TabelKredit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelKredit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelKreditMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelKredit);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 950, -1));

        KembaliButton.setBackground(new java.awt.Color(51, 51, 255));
        KembaliButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        KembaliButton.setText("Kembali");
        KembaliButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KembaliButtonMouseClicked(evt);
            }
        });
        getContentPane().add(KembaliButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        TambahButton.setBackground(new java.awt.Color(51, 51, 255));
        TambahButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TambahButton.setText("Tambah");
        TambahButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TambahButtonMouseClicked(evt);
            }
        });
        getContentPane().add(TambahButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, -1, -1));

        EditButton.setBackground(new java.awt.Color(51, 51, 255));
        EditButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EditButton.setText("Edit");
        EditButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditButtonMouseClicked(evt);
            }
        });
        getContentPane().add(EditButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, -1, -1));

        getContentPane().add(PaketKredit, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 570, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Bayar(RP):");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 110, 20));

        Bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BayarActionPerformed(evt);
            }
        });
        getContentPane().add(Bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 570, -1));

        getContentPane().add(KTPPembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 570, -1));

        getContentPane().add(KodeMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 570, -1));
        getContentPane().add(TanggalPembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 570, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IDBeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDBeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDBeliActionPerformed

    private void BayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BayarActionPerformed

    private void KembaliButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KembaliButtonMouseClicked
        // TODO add your handling code here:
        new Beranda().show();
        this.dispose();
    }//GEN-LAST:event_KembaliButtonMouseClicked

    private void TambahButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TambahButtonMouseClicked
        // TODO add your handling code here:
        try{
            kredit c= new kredit();
            this.stat=k.getCon().prepareStatement("insert into kredit values(?,?,?,?,?,?,?,?)");
            stat.setString(1, c.kode);
            stat.setString(2, c.ktp);
            stat.setString(3, c.mobil);
            stat.setString(4, c.paket);
            stat.setString(5, c.tanggal);
            stat.setInt(6, c.cicilan);
            stat.setInt(7, c.tenor);
            stat.setInt(8, c.total);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "cicilan perbulannya= RP."+c.cicilan+"\ntenornya sebanyak"+c.tenor+"\ntotal cicilannya sebanyak= RP. "+c.total);
            ShowTable();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_TambahButtonMouseClicked

    private void TabelKreditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelKreditMouseClicked
        // TODO add your handling code here:
        int row=TabelKredit.getSelectedRow();
        IDBeli.setText(model.getValueAt(row, 0).toString());
        
        String ktp=model.getValueAt(row, 1).toString();
        for(int i=0; i<KTPPembeli.getItemCount();i++){
            if(KTPPembeli.getItemAt(i).startsWith(ktp+":")){
                KTPPembeli.setSelectedIndex(i);
                break;
            }
        }
        String kodemobil=model.getValueAt(row, 2).toString();
        for(int i=0; i<KodeMobil.getItemCount();i++){
            if(KodeMobil.getItemAt(i).startsWith(kodemobil+":")){
                KodeMobil.setSelectedIndex(i);
                break;
            }
        }
        String kodepaket=model.getValueAt(row, 3).toString();
        for(int i=0; i<PaketKredit.getItemCount();i++){
            if(PaketKredit.getItemAt(i).startsWith(kodepaket+":")){
                PaketKredit.setSelectedIndex(i);
                break;
            }
        }
        try{
            String tanggal=model.getValueAt(row, 4).toString();
            SimpleDateFormat adf=new SimpleDateFormat("yyyy-MM-dd");
            Date date=adf.parse(tanggal);
            TanggalPembelian.setDate(date);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_TabelKreditMouseClicked

    private void EditButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditButtonMouseClicked
        // TODO add your handling code here:
        try{
            kredit c= new kredit();
            this.stat=k.getCon().prepareStatement("update kredit set ktp=?,"+"kode_mobil=?,kode_paket=?,tanggal_kredit=?,bayar_kredit=?,"+"tenor=?,totalcicil=? where kode_kredit=?");
            stat.setString(1, c.ktp);
            stat.setString(2, c.mobil);
            stat.setString(3, c.paket);
            stat.setString(4, c.tanggal);
            stat.setDouble(5, c.cicilan);
            stat.setDouble(6, c.tenor);
            stat.setDouble(7, c.total);
            stat.setString(8, c.kode);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "cicilan perbulannya= RP."+c.cicilan+"\ntenornya sebanyak"+c.tenor+"\ntotal cicilannya sebanyak= RP. "+c.total);
            ShowTable();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_EditButtonMouseClicked

    private void HapusButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HapusButtonMouseClicked
        // TODO add your handling code here:
        try{
            kredit c= new kredit();
            this.stat=k.getCon().prepareStatement("delete from kredit where kode_kredit=?");
            stat.setString(1, c.kode);
            stat.executeUpdate();
            ShowTable();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_HapusButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuDataBeliKredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuDataBeliKredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuDataBeliKredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuDataBeliKredit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuDataBeliKredit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Bayar;
    private javax.swing.JLabel EditButton;
    private javax.swing.JLabel HapusButton;
    private javax.swing.JTextField IDBeli;
    private javax.swing.JComboBox<String> KTPPembeli;
    private javax.swing.JLabel KembaliButton;
    private javax.swing.JComboBox<String> KodeMobil;
    private javax.swing.JComboBox<String> PaketKredit;
    private javax.swing.JTable TabelKredit;
    private javax.swing.JLabel TambahButton;
    private com.toedter.calendar.JDateChooser TanggalPembelian;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
