/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewatanahmakam.form;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import sewatanahmakam.SewaTanahMakam;
import sewatanahmakam.controller.IdJenazahController;
import sewatanahmakam.data.Identitasjenazah;

/**
 *
 * @author Candi-PC
 */
public class FormJenazah extends javax.swing.JInternalFrame implements NavigatorFormInterface{

    IdJenazahController jCont=new IdJenazahController(SewaTanahMakam.emf);
    Identitasjenazah jenazah=new Identitasjenazah();
    DefaultTableModel model;
    Image imageKTP;
    /**
     * Creates new form FormJenazah
     */
    public FormJenazah() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("No. Jenazah");
        model.addColumn("Nama");
        model.addColumn("Tempat Lahir");
        model.addColumn("Tanggal Lahir");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Alamat");
        model.addColumn("Tempat Wafat");
        model.addColumn("Tanggal Wafat");
        model.addColumn("Keterangan");
        tableJenazah.getTableHeader().setFont(new Font("Futura Bk BT", Font.ITALIC, 12));
        seleksiBarisTable();
    }
    

    private void setJDC(JDateChooser JDC){
        JDC.setLocale(Locale.forLanguageTag("in-ID")); //membuat locale indonesia
        JDC.setDateFormatString("EEEE, dd MMMM yyyy");
        JDC.setMaxSelectableDate(new Date());
        JDC.setDate(new Date());
    }
    
    public void renderTable(){
        TableCellRenderer tbr = new DefaultTableCellRenderer(){
            SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("In-ID"));
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column){
                if(value instanceof Date){
                    value = sdf.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        tableJenazah.getColumnModel().getColumn(3).setCellRenderer(tbr);
        tableJenazah.getColumnModel().getColumn(7).setCellRenderer(tbr);
    }
    
    private void tampilTableJenazah(){
        tableJenazah.setModel(jCont.showTable(model));
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=jCont.cari(model, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            tableJenazah.setModel(jCont.cari(model, cari));
        }
    }
    
    private void seleksiBarisTable(){
        tableJenazah.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableJenazah.getSelectedRow();       
                if(baris != -1){                        
                    txtNo.setText(tableJenazah.getValueAt(baris, 0).toString());
                    txtNama.setText(tableJenazah.getValueAt(baris, 1).toString());
                    txtTempatLahir.setText(tableJenazah.getValueAt(baris, 2).toString());
                    jdcTanggalLahir.setDate((Date)tableJenazah.getValueAt(baris, 3));
                    cmbKelamin.setSelectedItem(tableJenazah.getValueAt(baris, 4).toString());
                    txtAlamat.setText(tableJenazah.getValueAt(baris, 5).toString());
                    txtTempatWafat.setText(tableJenazah.getValueAt(baris, 6).toString());
                    jdcTanggalWafat.setDate((Date)tableJenazah.getValueAt(baris, 7));
                    txtKeterangan.setText(tableJenazah.getValueAt(baris, 8).toString());
                    tampilKTP();
                }
            }
        });
    }
    
    private void tampilKTP(){
        jenazah=jCont.findJenazah(txtNo.getText());
        if(jenazah.getFotoKTP()!=null){
            try{
                ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(jenazah.getFotoKTP()));
                ImageIcon icon=(ImageIcon) inputStream.readObject();
                imageKTP=icon.getImage();
                panelGambarKTP.setImage(imageKTP);
                inputStream.close();
                labelKTP.setText("Foto");
            }catch(ClassNotFoundException ex){
            }catch(IOException ex){}
        }else{
            panelGambarKTP.setImage(null);
            labelKTP.setText("Foto tidak ada");
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelData = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNo = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtTempatLahir = new javax.swing.JTextField();
        jdcTanggalLahir = new com.toedter.calendar.JDateChooser();
        cmbKelamin = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        txtTempatWafat = new javax.swing.JTextField();
        jdcTanggalWafat = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txtKeterangan = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableJenazah = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        panelKTP = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txtKTP = new javax.swing.JTextField();
        btCariKTP = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        labelKTP = new javax.swing.JLabel();
        panelGambarKTP = new sewatanahmakam.panelFoto.panelGambar();

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

        panelData.setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(30, 49, 92));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel1.setText("No Jenazah");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel2.setText("Nama Almarhum");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel3.setText("Tempat Lahir");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel4.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel4.setText("Tgl Lahir");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, 20));

        jLabel5.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel5.setText("Jenis Kelamin");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel6.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel6.setText("Alamat");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, -1, -1));

        jLabel7.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel7.setText("Tempat Wafat");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, -1, -1));

        jLabel8.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel8.setText("Tgl Wafat");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, -1, 28));

        txtNo.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtNo.setBorder(null);
        txtNo.setOpaque(false);
        jPanel1.add(txtNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 240, -1));

        txtNama.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtNama.setBorder(null);
        txtNama.setOpaque(false);
        jPanel1.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 240, -1));

        txtTempatLahir.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtTempatLahir.setBorder(null);
        txtTempatLahir.setOpaque(false);
        jPanel1.add(txtTempatLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 240, -1));

        jdcTanggalLahir.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jPanel1.add(jdcTanggalLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 240, 30));

        cmbKelamin.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        cmbKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-laki", "Perempuan" }));
        jPanel1.add(cmbKelamin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 240, -1));

        txtAlamat.setColumns(20);
        txtAlamat.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtAlamat.setRows(5);
        jScrollPane2.setViewportView(txtAlamat);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, -1, 98));

        txtTempatWafat.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtTempatWafat.setBorder(null);
        txtTempatWafat.setOpaque(false);
        jPanel1.add(txtTempatWafat, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 144, 246, 30));

        jdcTanggalWafat.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jPanel1.add(jdcTanggalWafat, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, 246, 30));

        jLabel9.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel9.setText("Keterangan");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, -1, -1));

        txtKeterangan.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtKeterangan.setBorder(null);
        txtKeterangan.setOpaque(false);
        jPanel1.add(txtKeterangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, 240, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 240, 10));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 240, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 240, 10));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 240, 10));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, 240, 10));

        tableJenazah.setBackground(new java.awt.Color(106, 117, 146));
        tableJenazah.setFont(new java.awt.Font("Futura Bk BT", 0, 11)); // NOI18N
        tableJenazah.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Jenazah", "Nama ", "Tempat Lahir", "Tanggal Lahir", "Jenis Kelamin", "Alamat", "Tempat Wafat", "Tanggal Wafat", "Keterangan"
            }
        ));
        jScrollPane1.setViewportView(tableJenazah);

        jPanel6.setBackground(new java.awt.Color(106, 117, 146));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cari Data");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

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
        jPanel6.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 240, -1));

        btCari.setBackground(new java.awt.Color(255, 255, 255));
        btCari.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        btCari.setText("Cari");
        btCari.setOpaque(false);
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel6.add(btCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 100, -1));
        jPanel6.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 240, 10));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        panelData.add(jPanel2, "card2");

        jTabbedPane1.addTab("Data Jenazah", panelData);

        panelKTP.setLayout(new java.awt.CardLayout());

        jPanel3.setBackground(new java.awt.Color(30, 49, 92));
        jPanel3.setFocusTraversalPolicyProvider(true);

        jPanel8.setBackground(new java.awt.Color(106, 117, 146));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtKTP.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtKTP.setForeground(new java.awt.Color(255, 255, 255));
        txtKTP.setText("Foto KTP");
        txtKTP.setBorder(null);
        txtKTP.setOpaque(false);
        txtKTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKTPActionPerformed(evt);
            }
        });
        jPanel8.add(txtKTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 240, -1));

        btCariKTP.setBackground(new java.awt.Color(255, 255, 255));
        btCariKTP.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        btCariKTP.setText("Cari Foto");
        btCariKTP.setOpaque(false);
        btCariKTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariKTPActionPerformed(evt);
            }
        });
        jPanel8.add(btCariKTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));
        jPanel8.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 240, 10));

        jPanel4.setBackground(new java.awt.Color(213, 217, 223));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelKTP.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        labelKTP.setText("Foto");
        jPanel4.add(labelKTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 390, -1, -1));

        panelGambarKTP.setOpaque(false);

        javax.swing.GroupLayout panelGambarKTPLayout = new javax.swing.GroupLayout(panelGambarKTP);
        panelGambarKTP.setLayout(panelGambarKTPLayout);
        panelGambarKTPLayout.setHorizontalGroup(
            panelGambarKTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        panelGambarKTPLayout.setVerticalGroup(
            panelGambarKTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        jPanel4.add(panelGambarKTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 450, 270));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        panelKTP.add(jPanel3, "card2");

        jTabbedPane1.addTab("Upload KTP", panelKTP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        cariTable(txtCari.getText());
    }//GEN-LAST:event_btCariActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtNo.setEnabled(false);
        txtNama.setEnabled(false);
        txtTempatLahir.setEnabled(false);
        jdcTanggalLahir.setEnabled(false);
        cmbKelamin.setEnabled(false);
        txtAlamat.setEnabled(false);
        txtTempatWafat.setEnabled(false);
        jdcTanggalWafat.setEnabled(false);
        txtKeterangan.setEnabled(false);
        txtCari.setEnabled(false);
        btCari.setEnabled(false);
        btCariKTP.setEnabled(false);
    }//GEN-LAST:event_formInternalFrameActivated

    private void txtKTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKTPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKTPActionPerformed

    private void btCariKTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariKTPActionPerformed
        JFileChooser chooser = new JFileChooser("..\\SewaTanahMakam\\foto\\fotoKTP");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("jpg|png|bmp", "jpg","png","bmp"));
        if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            File file=chooser.getSelectedFile();
            try{
                imageKTP=ImageIO.read(file);
                panelGambarKTP.setImage(imageKTP);
            }catch(IOException ex){}
            txtKTP.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_btCariKTPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari;
    private javax.swing.JButton btCariKTP;
    private javax.swing.JComboBox<String> cmbKelamin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcTanggalLahir;
    private com.toedter.calendar.JDateChooser jdcTanggalWafat;
    private javax.swing.JLabel labelKTP;
    private javax.swing.JPanel panelData;
    private sewatanahmakam.panelFoto.panelGambar panelGambarKTP;
    private javax.swing.JPanel panelKTP;
    private javax.swing.JTable tableJenazah;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtKTP;
    private javax.swing.JTextField txtKeterangan;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNo;
    private javax.swing.JTextField txtTempatLahir;
    private javax.swing.JTextField txtTempatWafat;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        txtNama.setEnabled(true);
        txtTempatLahir.setEnabled(true);
        jdcTanggalLahir.setEnabled(true);
        cmbKelamin.setEnabled(true);
        txtAlamat.setEnabled(true);
        txtTempatWafat.setEnabled(true);
        jdcTanggalWafat.setEnabled(true);
        txtCari.setEnabled(true);
        btCari.setEnabled(true);
        btCariKTP.setEnabled(true);
        bersih();
        renderTable();
    }

    @Override
    public void bersih() {
        txtNama.setText("");
        txtTempatLahir.setText("");
        cmbKelamin.setSelectedIndex(0);
        txtAlamat.setText("");
        txtTempatWafat.setText("");
        txtKeterangan.setText("");
        txtNo.setText(jCont.nomorOtomatis());
        setJDC(jdcTanggalLahir);
        setJDC(jdcTanggalWafat);
        txtCari.setText("");
        txtKTP.setText("");
        tampilTableJenazah();
        labelKTP.setText("Foto");
        panelGambarKTP.setImage(null);
        txtNama.requestFocus();
    }

    @Override
    public void simpan() {
        if("".equals(txtNama.getText())||"".equals(txtTempatLahir.getText())||"".equals(txtAlamat.getText())||
                "".equals(txtTempatWafat.getText())){
            JOptionPane.showMessageDialog(null, "Data belum lengkap!");
        }else{
            jenazah=jCont.findJenazah(txtNo.getText());
            Identitasjenazah jz=new Identitasjenazah();
            if(jenazah==null){
                jz.setNoJenazah(txtNo.getText());
                jz.setNamaJenazah(txtNama.getText());
                jz.setTempatLahir(txtTempatLahir.getText());
                jz.setTanggalLahir(jdcTanggalLahir.getDate());
                jz.setJenisKelamin(cmbKelamin.getSelectedItem().toString());
                jz.setAlamat(txtAlamat.getText());
                jz.setTempatWafat(txtTempatWafat.getText());
                jz.setTanggalWafat(jdcTanggalWafat.getDate());
                jz.setKeterangan("Terdaftar");
                if(!txtKTP.getText().equals("")){
                        ObjectOutputStream objectoutputstream=null;
                        ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
                        try{
                            objectoutputstream=new ObjectOutputStream(outputstream);
                            ImageIcon icon=new ImageIcon(imageKTP);
                            objectoutputstream.writeObject(icon);
                            objectoutputstream.flush();
                            objectoutputstream.close();
                        }catch(IOException ex){}
                        jz.setFotoKTP(outputstream.toByteArray());
                    }
                try{
                    jCont.save(jz);
                }catch(Exception e){}
                JOptionPane.showMessageDialog(null, "Data telah disimpan");
            }else{
                jz.setNoJenazah(txtNo.getText());
                jz.setNamaJenazah(txtNama.getText());
                jz.setTempatLahir(txtTempatLahir.getText());
                jz.setTanggalLahir(jdcTanggalLahir.getDate());
                jz.setJenisKelamin(cmbKelamin.getSelectedItem().toString());
                jz.setAlamat(txtAlamat.getText());
                jz.setTempatWafat(txtTempatWafat.getText());
                jz.setTanggalWafat(jdcTanggalWafat.getDate());
                jz.setKeterangan(txtKeterangan.getText());
                if(!txtKTP.getText().equals("")){
                        ObjectOutputStream objectoutputstream=null;
                        ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
                        try{
                            objectoutputstream=new ObjectOutputStream(outputstream);
                            ImageIcon icon=new ImageIcon(imageKTP);
                            objectoutputstream.writeObject(icon);
                            objectoutputstream.flush();
                        }catch(IOException ex){}
                        jz.setFotoKTP(outputstream.toByteArray());
                    }else{
                        ObjectOutputStream objectoutputstream=null;
                        ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
                        try{
                            objectoutputstream=new ObjectOutputStream(outputstream);
                            ImageIcon icon=new ImageIcon(imageKTP);
                            objectoutputstream.writeObject(icon);
                            objectoutputstream.flush();
                        }catch(IOException ex){}
                        jz.setFotoKTP(outputstream.toByteArray());
                    }
                try{
                    jCont.update(jz);
                }catch(Exception e){}
                JOptionPane.showMessageDialog(null, "Data telah diupdate");           
            }
            bersih();
        }
    }

    @Override
    public void hapus() {
        try{
            jCont.delete(txtNo.getText());
            JOptionPane.showMessageDialog(null, "Data telah dihapus");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Data tidak nisa dihapus karena "+ex.getMessage());
        }
        bersih();
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
