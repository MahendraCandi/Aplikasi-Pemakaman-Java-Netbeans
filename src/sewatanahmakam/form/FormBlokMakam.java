/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewatanahmakam.form;

import com.sun.glass.events.KeyEvent;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import sewatanahmakam.SewaTanahMakam;
import sewatanahmakam.controller.BlokMakamController;
import sewatanahmakam.data.Blokmakam;

/**
 *
 * @author Candi-PC
 */
public class FormBlokMakam extends javax.swing.JInternalFrame implements NavigatorFormInterface {

    BlokMakamController bmCont=new BlokMakamController(SewaTanahMakam.emf);
    Blokmakam bmakam =new Blokmakam();
    DefaultTableModel tblMakam;
    /**
     * Creates new form FormBlokMakam
     */
    public FormBlokMakam() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        tblMakam=new DefaultTableModel();
        tblMakam.addColumn("Blok Makam");
        tblMakam.addColumn("Nama Blok");
        tblMakam.addColumn("Kategori");
        tblMakam.addColumn("Lokasi");
        tblMakam.addColumn("Harga Sewa");
        tblMakam.addColumn("Keterangan");
        tampilTableMakam();
        seleksiBarisTable();
        tableBlok.getTableHeader().setFont(new Font("Futura Bk BT", Font.ITALIC, 12));
        
    }
    
    private void tampilTableMakam(){
        tableBlok.setModel(bmCont.showTable(tblMakam));
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=bmCont.cari(tblMakam, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            tableBlok.setModel(bmCont.cari(tblMakam, cari));
        }
    }
    
    private void seleksiBarisTable(){
        tableBlok.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableBlok.getSelectedRow();       
                if(baris != -1){                        
                    txtBlok.setText(tableBlok.getValueAt(baris, 0).toString());
                    cmbKategori.setSelectedItem(tableBlok.getValueAt(baris, 2).toString());
                    cmbNama.setSelectedItem(tableBlok.getValueAt(baris, 1).toString());
                    txtLokasi.setText(tableBlok.getValueAt(baris, 3).toString());
                    txtHarga.setText(tableBlok.getValueAt(baris, 4).toString());
                    txtKeterangan.setText(tableBlok.getValueAt(baris, 5).toString());
                }
            }
        });
    }
    
    public String[] islam(){
        String[] islam={"Al-Isya", "Al-Ashar", "Al-Dzuhur"};
        return islam;
    }
    
    public String[] kristen(){
        String[] kristen={"St. Micheal", "St. George"};
        return kristen;
    }
    
    public String[] chinese(){
        String[] chinese={"Zhong Jian", "Houmen"};
        return chinese;
    }
    
    private void comboBox(){
        final DefaultComboBoxModel Islam=new DefaultComboBoxModel(islam());
        final DefaultComboBoxModel Kristen=new DefaultComboBoxModel(kristen());
        final DefaultComboBoxModel Chinese=new DefaultComboBoxModel(chinese());
        cmbKategori.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if("Islam".equals(cmbKategori.getSelectedItem())){
                    cmbNama.setModel(Islam);
                }else if("Kristen".equals(cmbKategori.getSelectedItem())){
                    cmbNama.setModel(Kristen);
                }else{
                    cmbNama.setModel(Chinese);
                }
            }
        });
    }
    
    private void LokasidanHarga(){
        if("Al-Isya".equals(cmbNama.getSelectedItem())){
            txtLokasi.setText("Front");
            txtHarga.setText("900000");
        }else if("Al-Ashar".equals(cmbNama.getSelectedItem())||"St. Micheal".equals(cmbNama.getSelectedItem())||"Zhong Jian".equals(cmbNama.getSelectedItem())){
            txtLokasi.setText("Middle");
            txtHarga.setText("600000");
        }else{
            txtLokasi.setText("End");
            txtHarga.setText("300000");
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

        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        panelUtama = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBlok = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmbKategori = new javax.swing.JComboBox<>();
        txtHarga = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtKeterangan = new javax.swing.JTextField();
        cmbNama = new javax.swing.JComboBox<>();
        txtLokasi = new javax.swing.JTextField();
        txtBlok = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        txtJumdat = new javax.swing.JSpinner();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();

        jLabel8.setText("Blok Makam");

        jTextField3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        setBackground(new java.awt.Color(30, 49, 92));
        setOpaque(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        panelUtama.setBackground(new java.awt.Color(30, 49, 92));

        tableBlok.setBackground(new java.awt.Color(106, 117, 146));
        tableBlok.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        tableBlok.setForeground(new java.awt.Color(255, 255, 255));
        tableBlok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Blok", "Nama Blok", "Kategori", "Lokasi", "Harga Sewa", "Keterangan"
            }
        ));
        jScrollPane1.setViewportView(tableBlok);

        jPanel2.setBackground(new java.awt.Color(106, 117, 146));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cari Data");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtCari.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtCari.setForeground(new java.awt.Color(255, 255, 255));
        txtCari.setText("Cari");
        txtCari.setBorder(null);
        txtCari.setOpaque(false);
        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        jPanel2.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 240, -1));

        btCari.setBackground(new java.awt.Color(255, 255, 255));
        btCari.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        btCari.setText("Cari");
        btCari.setOpaque(false);
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel2.add(btCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 100, -1));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 240, 10));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel2.setText("Kategori");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel5.setText("Nama Blok");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel6.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel6.setText("Lokasi");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel7.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel7.setText("Harga Sewa");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel10.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel10.setText("Blok Makam");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cmbKategori.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        cmbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Islam", "Kristen", "Chinese" }));
        cmbKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKategoriActionPerformed(evt);
            }
        });
        jPanel1.add(cmbKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 240, -1));

        txtHarga.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtHarga.setBorder(null);
        txtHarga.setOpaque(false);
        txtHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHargaKeyTyped(evt);
            }
        });
        jPanel1.add(txtHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 240, -1));

        jLabel11.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel11.setText("Banyak data");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, -1, -1));

        txtKeterangan.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtKeterangan.setBorder(null);
        txtKeterangan.setOpaque(false);
        jPanel1.add(txtKeterangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 240, -1));

        cmbNama.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        cmbNama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNamaActionPerformed(evt);
            }
        });
        jPanel1.add(cmbNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 240, -1));

        txtLokasi.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtLokasi.setBorder(null);
        txtLokasi.setOpaque(false);
        jPanel1.add(txtLokasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 240, -1));

        txtBlok.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtBlok.setBorder(null);
        txtBlok.setOpaque(false);
        jPanel1.add(txtBlok, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 240, 20));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 60, 10));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 240, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 240, 10));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 240, 10));

        txtJumdat.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtJumdat.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        txtJumdat.setBorder(null);
        txtJumdat.setOpaque(false);
        jPanel1.add(txtJumdat, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 80, -1));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 240, 10));

        jLabel12.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel12.setText("Keterangan");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        javax.swing.GroupLayout panelUtamaLayout = new javax.swing.GroupLayout(panelUtama);
        panelUtama.setLayout(panelUtamaLayout);
        panelUtamaLayout.setHorizontalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 359, Short.MAX_VALUE))
        );
        panelUtamaLayout.setVerticalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtBlok.setEnabled(false);
        cmbKategori.setEnabled(false);
        cmbNama.setEnabled(false);
        txtLokasi.setEnabled(false);
        txtKeterangan.setEnabled(false);
        txtCari.setEnabled(false);
        btCari.setEnabled(false);
        txtJumdat.setEnabled(false);
    }//GEN-LAST:event_formInternalFrameActivated

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        cariTable(txtCari.getText());
    }//GEN-LAST:event_btCariActionPerformed

    private void cmbNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNamaActionPerformed
        LokasidanHarga();
    }//GEN-LAST:event_cmbNamaActionPerformed

    private void cmbKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKategoriActionPerformed
        cmbNamaActionPerformed(null);
    }//GEN-LAST:event_cmbKategoriActionPerformed

    private void txtHargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaKeyTyped
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACKSPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtHargaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari;
    private javax.swing.JComboBox<String> cmbKategori;
    private javax.swing.JComboBox<String> cmbNama;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel panelUtama;
    private javax.swing.JTable tableBlok;
    private javax.swing.JTextField txtBlok;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JSpinner txtJumdat;
    private javax.swing.JTextField txtKeterangan;
    private javax.swing.JTextField txtLokasi;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        cmbKategori.setEnabled(true);
        cmbNama.setEnabled(true);
        txtLokasi.setEnabled(true);
        txtHarga.setEnabled(true);
        txtCari.setEnabled(true);
        btCari.setEnabled(true);
        txtJumdat.setEnabled(true);
        comboBox();
        bersih();
    }

    @Override
    public void bersih() {
        cmbKategori.setSelectedIndex(0);
        cmbNama.setSelectedIndex(0);
        txtLokasi.setText("");
        txtHarga.setText("");
        txtCari.setText("");
        txtKeterangan.setText("");
        txtBlok.setText(bmCont.nomorOtomatis());
        txtJumdat.setValue(1);
        tampilTableMakam();
    }

    @Override
    public void simpan() {
        bmakam=bmCont.findBlokMakam(txtBlok.getText());
        Blokmakam bm=new Blokmakam();
        int jumdat=Integer.parseInt(txtJumdat.getValue().toString());
        if(bmakam==null){
            for(int i=1; i<=jumdat; i++){
                System.out.println(i);
                bm.setKodeBlok(txtBlok.getText());
                bm.setKategori(cmbKategori.getSelectedItem().toString());
                bm.setNamaBlok(cmbNama.getSelectedItem().toString());
                bm.setLokasi(txtLokasi.getText());
                bm.setHargaSewa(Double.parseDouble(txtHarga.getText()));
                bm.setKeterangan("Tersedia");
            try{
                bmCont.save(bm);
            }catch(Exception e){}
            txtBlok.setText(bmCont.nomorOtomatis());
            }
            
            
            JOptionPane.showMessageDialog(null, "Data telah disimpan");
        }else{
            bm.setKodeBlok(txtBlok.getText());
            bm.setKategori(cmbKategori.getSelectedItem().toString());
            bm.setNamaBlok(cmbNama.getSelectedItem().toString());
            bm.setLokasi(txtLokasi.getText());
            bm.setHargaSewa(Double.parseDouble(txtHarga.getText()));
            bm.setKeterangan(txtKeterangan.getText());
            try{
                bmCont.update(bm);
            }catch(Exception e){}
            JOptionPane.showMessageDialog(null, "Data telah diupdate");           
        }
        bersih();
    }

    @Override
    public void hapus() {
        try{
            bmCont.delete(txtBlok.getText());
            JOptionPane.showMessageDialog(null, "Data telah dihapus");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Data tidak nisa dihapus karena "+ex.getMessage());
        }
        bersih();
        tampilTableMakam();
    }

    @Override
    public void cari() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tampil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}