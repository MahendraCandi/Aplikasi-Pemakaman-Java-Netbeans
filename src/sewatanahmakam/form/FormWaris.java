/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewatanahmakam.form;

import com.sun.glass.events.KeyEvent;
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
import java.util.Calendar;
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
import sewatanahmakam.controller.IdWarisController;
import sewatanahmakam.data.Identitaswaris;

/**
 *
 * @author Candi-PC
 */

public class FormWaris extends javax.swing.JInternalFrame implements NavigatorFormInterface{

    IdWarisController wCont=new IdWarisController(SewaTanahMakam.emf);
    Identitaswaris waris=new Identitaswaris();
    DefaultTableModel model;
    Image imageKTP, imageKK;
    /**
     * Creates new form FormWaris
     */
    public FormWaris() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("No. Waris");
        model.addColumn("Nama Waris");
        model.addColumn("Tanggal Lahir");
        model.addColumn("Jenis Kelamin");
        model.addColumn("No. Telepon");
        model.addColumn("Alamat");
        model.addColumn("Keterangan");
        tableWaris.getTableHeader().setFont(new Font("Futura Bk BT", Font.ITALIC, 12));
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
        tableWaris.getColumnModel().getColumn(2).setCellRenderer(tbr);
    }
    
    private void tampilTableWaris(){
        tableWaris.setModel(wCont.showTable(model));
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=wCont.cari(model, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            tableWaris.setModel(wCont.cari(model, cari));
        }
    }
    
    private void seleksiBarisTable(){
        tableWaris.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableWaris.getSelectedRow();       
                if(baris != -1){                        
                    txtNo.setText(tableWaris.getValueAt(baris, 0).toString());
                    txtNama.setText(tableWaris.getValueAt(baris, 1).toString());
                    jdcTanggal.setDate((Date)tableWaris.getValueAt(baris, 2));
                    cmbKelamin.setSelectedItem(tableWaris.getValueAt(baris, 3).toString());
                    txtTelp.setText(tableWaris.getValueAt(baris, 4).toString());
                    txtAlamat.setText(tableWaris.getValueAt(baris, 5).toString());
                    txtKeterangan.setText(tableWaris.getValueAt(baris, 6).toString());
                    tampilKTP();
                    tampilKK();
                }
            }
        });
    }
    
    private void tampilKTP(){
        waris=wCont.findWaris(txtNo.getText());
        if(waris.getFotoKTP()!=null){
            try{
                ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(waris.getFotoKTP()));
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
    
    private void tampilKK(){
        waris=wCont.findWaris(txtNo.getText());
        if(waris.getFotoKK()!=null){
            try{
                ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(waris.getFotoKK()));
                ImageIcon icon=(ImageIcon) inputStream.readObject();
                imageKK=icon.getImage();
                panelGambarKK.setImage(imageKK);
                inputStream.close();
                labelKK.setText("Foto");
            }catch(ClassNotFoundException ex){
            }catch(IOException ex){}
        }else{
            panelGambarKK.setImage(null);
            labelKK.setText("Foto tidak ada");
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

        jPanel7 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelData = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableWaris = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        cmbKelamin = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        txtTelp = new javax.swing.JTextField();
        txtKeterangan = new javax.swing.JTextField();
        txtNo = new javax.swing.JTextField();
        jdcTanggal = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btCari = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        panelKTP = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        labelKTP = new javax.swing.JLabel();
        panelGambarKTP = new sewatanahmakam.panelFoto.panelGambar();
        jPanel8 = new javax.swing.JPanel();
        txtKTP = new javax.swing.JTextField();
        btCariKTP = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        panelKK = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        txtKK = new javax.swing.JTextField();
        btCariKK = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        panelGambarKK = new sewatanahmakam.panelFoto.panelGambar2();
        labelKK = new javax.swing.JLabel();

        setTitle("Form Ahli Waris");
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
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel5.setBackground(new java.awt.Color(30, 49, 92));

        tableWaris.setBackground(new java.awt.Color(106, 117, 146));
        tableWaris.setFont(new java.awt.Font("Futura Bk BT", 0, 11)); // NOI18N
        tableWaris.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Waris", "Nama Waris", "Tanggal Lahir", "Jenis Kelamin", "No. Telpon", "Alamat", "Keterangan"
            }
        ));
        jScrollPane2.setViewportView(tableWaris);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbKelamin.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        cmbKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));
        cmbKelamin.setBorder(null);
        cmbKelamin.setOpaque(false);
        jPanel4.add(cmbKelamin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 240, -1));

        txtAlamat.setColumns(20);
        txtAlamat.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 250, 120));

        jLabel7.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel7.setText("Keterangan");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, -1, -1));

        txtTelp.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtTelp.setBorder(null);
        txtTelp.setOpaque(false);
        txtTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelpKeyTyped(evt);
            }
        });
        jPanel4.add(txtTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 240, -1));

        txtKeterangan.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtKeterangan.setBorder(null);
        txtKeterangan.setOpaque(false);
        txtKeterangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeteranganActionPerformed(evt);
            }
        });
        jPanel4.add(txtKeterangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, 240, -1));

        txtNo.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtNo.setBorder(null);
        txtNo.setOpaque(false);
        txtNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoActionPerformed(evt);
            }
        });
        jPanel4.add(txtNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 240, -1));

        jdcTanggal.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jdcTanggal.setOpaque(false);
        jPanel4.add(jdcTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 240, 28));

        jLabel1.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel1.setText("No. Waris");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        txtNama.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtNama.setBorder(null);
        txtNama.setOpaque(false);
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });
        jPanel4.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 240, -1));

        jLabel2.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel2.setText("Nama Waris");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel6.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel6.setText("Alamat");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel3.setText("Tanggal Lahir");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, 28));

        jLabel4.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel4.setText("Jenis Kelamin");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel5.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel5.setText("No. Telpon");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 240, -1));
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 240, 10));
        jPanel4.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 240, 10));
        jPanel4.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 240, 10));

        jPanel6.setBackground(new java.awt.Color(106, 117, 146));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Cari Data");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
        panelData.setLayout(panelDataLayout);
        panelDataLayout.setHorizontalGroup(
            panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelDataLayout.setVerticalGroup(
            panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Data Ahli Waris", panelData);

        panelKTP.setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(30, 49, 92));

        jPanel3.setBackground(new java.awt.Color(213, 217, 223));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelKTP.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        labelKTP.setText("Foto");
        jPanel3.add(labelKTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 390, -1, -1));

        panelGambarKTP.setOpaque(false);

        javax.swing.GroupLayout panelGambarKTPLayout = new javax.swing.GroupLayout(panelGambarKTP);
        panelGambarKTP.setLayout(panelGambarKTPLayout);
        panelGambarKTPLayout.setHorizontalGroup(
            panelGambarKTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        panelGambarKTPLayout.setVerticalGroup(
            panelGambarKTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        jPanel3.add(panelGambarKTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 410, 260));

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
        jPanel8.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 240, 10));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        panelKTP.add(jPanel2, "card2");

        jTabbedPane1.addTab("Upload KTP", panelKTP);

        panelKK.setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(30, 49, 92));

        jPanel9.setBackground(new java.awt.Color(106, 117, 146));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtKK.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtKK.setForeground(new java.awt.Color(255, 255, 255));
        txtKK.setText("Foto KK");
        txtKK.setBorder(null);
        txtKK.setOpaque(false);
        txtKK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKKActionPerformed(evt);
            }
        });
        jPanel9.add(txtKK, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 240, -1));

        btCariKK.setBackground(new java.awt.Color(255, 255, 255));
        btCariKK.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        btCariKK.setText("Cari Foto");
        btCariKK.setOpaque(false);
        btCariKK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariKKActionPerformed(evt);
            }
        });
        jPanel9.add(btCariKK, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));
        jPanel9.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 240, 10));

        jPanel10.setBackground(new java.awt.Color(213, 217, 223));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelGambarKK.setOpaque(false);

        javax.swing.GroupLayout panelGambarKKLayout = new javax.swing.GroupLayout(panelGambarKK);
        panelGambarKK.setLayout(panelGambarKKLayout);
        panelGambarKKLayout.setHorizontalGroup(
            panelGambarKKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        panelGambarKKLayout.setVerticalGroup(
            panelGambarKKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        jPanel10.add(panelGambarKK, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 610, 360));

        labelKK.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        labelKK.setText("Foto");
        jPanel10.add(labelKK, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 390, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        panelKK.add(jPanel1, "card2");

        jTabbedPane1.addTab("Upload KK", panelKK);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        getContentPane().add(jPanel7, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void txtNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoActionPerformed

    private void txtKeteranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeteranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeteranganActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        cariTable(txtCari.getText());
    }//GEN-LAST:event_btCariActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtNo.setEnabled(false);
        txtNama.setEnabled(false);
        jdcTanggal.setEnabled(false);
        cmbKelamin.setEnabled(false);
        txtTelp.setEnabled(false);
        txtAlamat.setEnabled(false);
        txtKeterangan.setEnabled(false);
        txtCari.setEnabled(false);
        btCari.setEnabled(false);
        txtKTP.setEnabled(false);
        btCariKTP.setEnabled(false);
        txtKK.setEnabled(false);
        btCariKK.setEnabled(false);
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

    private void txtKKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKKActionPerformed

    private void btCariKKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariKKActionPerformed
        JFileChooser chooser = new JFileChooser("..\\SewaTanahMakam\\foto\\fotoKK");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("jpg|png|bmp", "jpg","png","bmp"));
        if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
            File file=chooser.getSelectedFile();
            try{
                imageKK=ImageIO.read(file);
                panelGambarKK.setImage(imageKK);
            }catch(IOException ex){}
            txtKK.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_btCariKKActionPerformed

    private void txtTelpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelpKeyTyped
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACKSPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTelpKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari;
    private javax.swing.JButton btCariKK;
    private javax.swing.JButton btCariKTP;
    private javax.swing.JComboBox<String> cmbKelamin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
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
    private com.toedter.calendar.JDateChooser jdcTanggal;
    private javax.swing.JLabel labelKK;
    private javax.swing.JLabel labelKTP;
    private javax.swing.JPanel panelData;
    private sewatanahmakam.panelFoto.panelGambar2 panelGambarKK;
    private sewatanahmakam.panelFoto.panelGambar panelGambarKTP;
    private javax.swing.JPanel panelKK;
    private javax.swing.JPanel panelKTP;
    private javax.swing.JTable tableWaris;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtKK;
    private javax.swing.JTextField txtKTP;
    private javax.swing.JTextField txtKeterangan;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNo;
    private javax.swing.JTextField txtTelp;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        txtNama.setEnabled(true);
        jdcTanggal.setEnabled(true);
        cmbKelamin.setEnabled(true);
        txtTelp.setEnabled(true);
        txtAlamat.setEnabled(true);
        txtCari.setEnabled(true);
        btCari.setEnabled(true);
        txtKTP.setEnabled(true);
        btCariKTP.setEnabled(true);
        txtKK.setEnabled(true);
        btCariKK.setEnabled(true);
        bersih();
        renderTable();
    }

    @Override
    public void bersih() {
        txtNama.setText("");
        cmbKelamin.setSelectedIndex(0);
        txtTelp.setText("");
        txtAlamat.setText("");
        txtKeterangan.setText("");
        txtCari.setText("");
        txtKTP.setText("");
        txtKK.setText("");
        txtNo.setText(wCont.nomorOtomatis());
        setJDC(jdcTanggal);
        labelKTP.setText("Foto");
        labelKK.setText("Foto");
        panelGambarKTP.setImage(null);
        panelGambarKK.setImage(null);
        tampilTableWaris();
    }

    @Override
    public void simpan() {
        if("".equals(txtNama.getText())||"".equals(txtTelp.getText())||"".equals(txtAlamat.getText())){
            JOptionPane.showMessageDialog(null, "Data belum lengkap!");
        }else{
            waris=wCont.findWaris(txtNo.getText());
            Identitaswaris wr=new Identitaswaris();
            if(waris==null){
                wr.setNoWaris(txtNo.getText());
                wr.setNamaWaris(txtNama.getText());
                wr.setTanggalLahir(jdcTanggal.getDate());
                wr.setJenisKelamin(cmbKelamin.getSelectedItem().toString());
                wr.setNoTelp(txtTelp.getText());
                wr.setAlamat(txtAlamat.getText());
                wr.setKeterangan("Terdaftar");
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
                        wr.setFotoKTP(outputstream.toByteArray());
                    }
                if(!txtKK.getText().equals("")){
                        ObjectOutputStream objectoutputstream=null;
                        ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
                        try{
                            objectoutputstream=new ObjectOutputStream(outputstream);
                            ImageIcon icon=new ImageIcon(imageKK);
                            objectoutputstream.writeObject(icon);
                            objectoutputstream.flush();
                            objectoutputstream.close();
                        }catch(IOException ex){}
                        wr.setFotoKK(outputstream.toByteArray());
                    }
                try{
                    wCont.save(wr);
                }catch(Exception e){}
                JOptionPane.showMessageDialog(null, "Data telah disimpan");
            }else{
                wr.setNoWaris(txtNo.getText());
                wr.setNamaWaris(txtNama.getText());
                wr.setTanggalLahir(jdcTanggal.getDate());
                wr.setJenisKelamin(cmbKelamin.getSelectedItem().toString());
                wr.setNoTelp(txtTelp.getText());
                wr.setAlamat(txtAlamat.getText());
                wr.setKeterangan(txtKeterangan.getText());
                if(!txtKTP.getText().equals("")){
                        ObjectOutputStream objectoutputstream=null;
                        ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
                        try{
                            objectoutputstream=new ObjectOutputStream(outputstream);
                            ImageIcon icon=new ImageIcon(imageKTP);
                            objectoutputstream.writeObject(icon);
                            objectoutputstream.flush();
                        }catch(IOException ex){}
                        wr.setFotoKTP(outputstream.toByteArray());
                    }else{
                        ObjectOutputStream objectoutputstream=null;
                        ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
                        try{
                            objectoutputstream=new ObjectOutputStream(outputstream);
                            ImageIcon icon=new ImageIcon(imageKTP);
                            objectoutputstream.writeObject(icon);
                            objectoutputstream.flush();
                        }catch(IOException ex){}
                        wr.setFotoKTP(outputstream.toByteArray());
                    }
                if(!txtKK.getText().equals("")){
                        ObjectOutputStream objectoutputstream=null;
                        ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
                        try{
                            objectoutputstream=new ObjectOutputStream(outputstream);
                            ImageIcon icon=new ImageIcon(imageKK);
                            objectoutputstream.writeObject(icon);
                            objectoutputstream.flush();
                        }catch(IOException ex){}
                        wr.setFotoKK(outputstream.toByteArray());
                    }else{
                        ObjectOutputStream objectoutputstream=null;
                        ByteArrayOutputStream outputstream=new ByteArrayOutputStream();
                        try{
                            objectoutputstream=new ObjectOutputStream(outputstream);
                            ImageIcon icon=new ImageIcon(imageKK);
                            objectoutputstream.writeObject(icon);
                            objectoutputstream.flush();
                        }catch(IOException ex){}
                        wr.setFotoKK(outputstream.toByteArray());
                    }
                try{
                    wCont.update(wr);
                }catch(Exception e){}
                JOptionPane.showMessageDialog(null, "Data telah diupdate");           
            }
            bersih();
        }
    }

    @Override
    public void hapus() {
        try{
            wCont.delete(txtNo.getText());
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
