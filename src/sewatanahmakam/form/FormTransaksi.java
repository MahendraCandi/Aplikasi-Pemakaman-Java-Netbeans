/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewatanahmakam.form;

import com.sun.glass.events.KeyEvent;
import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import sewatanahmakam.SewaTanahMakam;
import sewatanahmakam.controller.BlokMakamController;
import sewatanahmakam.controller.DataArsipController;
import sewatanahmakam.controller.IdJenazahController;
import sewatanahmakam.controller.IdWarisController;
import sewatanahmakam.controller.TransaksiController;
import sewatanahmakam.data.*;

/**
 *
 * @author Candi-PC
 */
public class FormTransaksi extends javax.swing.JInternalFrame implements NavigatorFormInterface{

    IdJenazahController jCont=new IdJenazahController(SewaTanahMakam.emf);
    IdWarisController wCont=new IdWarisController(SewaTanahMakam.emf);
    BlokMakamController bCont=new BlokMakamController(SewaTanahMakam.emf);
    TransaksiController tCont=new TransaksiController(SewaTanahMakam.emf);
    DataArsipController aCont=new DataArsipController(SewaTanahMakam.emf);
    Blokmakam blok=new Blokmakam();
    Dataarsip arsip=new Dataarsip();
    Datatransaksi transaksi=new Datatransaksi();
    Identitasjenazah jenazah =new Identitasjenazah();
    Identitaswaris waris=new Identitaswaris();
    DefaultTableModel model;
    public String kdJen, kdWar, kdBlok;
    double nisan=0, rumput=0, keramik=0, total=0, ubay=0, ukem=0;
    String Kode;
    /**
     * Creates new form FormTransaksi
     */
    public FormTransaksi(String kode) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }            
        };
        
        model.addColumn("No. Arsip");
        model.addColumn("Tgl Buat");
        model.addColumn("Tgl Habis");
        model.addColumn("No. Jenazah");
        model.addColumn("Nama Jenazah");
        model.addColumn("No. Waris");
        model.addColumn("Nama Waris");
        model.addColumn("Kode Blok");
        model.addColumn("Nama Blok");
        model.addColumn("Lokasi");
        model.addColumn("Keterangan");
        tableArsip.getTableHeader().setFont(new Font("Futura Bk BT", Font.ITALIC, 12));
        tampilTableArsip();
        renderTable();
        Kode=kode;
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
        tableArsip.getColumnModel().getColumn(1).setCellRenderer(tbr);
        tableArsip.getColumnModel().getColumn(2).setCellRenderer(tbr);
    }
    
    private void tampilTableArsip(){
        tableArsip.setModel(aCont.showTable(model));
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=aCont.cari(model, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            tableArsip.setModel(aCont.cari(model, cari));
        }
    }
    
    public void pilihJenazah(){
        PopJenazah PP=new PopJenazah();
        PP.FT=this;
        jenazah=jCont.findJenazah(kdJen);
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("In-ID"));
        txtNoJenazah.setText(kdJen);
        txtNamaJenazah.setText(jenazah.getNamaJenazah());
        txtTempatLahir.setText(jenazah.getTempatLahir());
        txtTanggalLahir.setText(sdf.format(jenazah.getTanggalLahir()));
        txtKelaminJenazah.setText(jenazah.getJenisKelamin());
        txtalamat.setText(jenazah.getAlamat());       
        txtTempatWafat.setText(jenazah.getTempatWafat());       
        txtTanggalWafat.setText(sdf.format(jenazah.getTanggalWafat()));
        PP.dispose();
    }
    
    public void pilihWaris(){
        PopJenazah PP=new PopJenazah();
        PP.FT=this;
        waris=wCont.findWaris(kdWar);
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
        txtNoWaris.setText(kdWar);
        txtNamaWaris.setText(waris.getNamaWaris());
        txtTanggalLahirWaris.setText(sdf.format(waris.getTanggalLahir()));
        txtJenisKelaminWaris.setText(waris.getJenisKelamin());
        txtTelp.setText(waris.getNoTelp());       
        txtAlamatWaris.setText(waris.getAlamat());       
    }
    
    public void pilihBlok(){
        PopBlokMakam PP=new PopBlokMakam();
        PP.FT=this;
        blok=bCont.findBlokMakam(kdBlok);
        txtKodeBlok.setText(kdBlok);
        txtKategori.setText(blok.getKategori());
        txtNamaBlok.setText(blok.getNamaBlok());
        txtLokasi.setText(blok.getLokasi());
        txtHargaSewa.setText(String.valueOf(blok.getHargaSewa()));
        total=Double.parseDouble(txtHargaSewa.getText());
        txtTotalBiaya.setText(String.valueOf(total));
        tglBuatIzin();
        tglHabisIzin();
    }
    
    private void setTanggal(){
        //membuat format waktu jam, hari bulan dan tahun dengan locale indonesia
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
        Calendar cal=Calendar.getInstance();
        txtTanggal.setText(sdf.format(cal.getTime()));
    }
    
    private void tglBuatIzin(){
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
        Calendar cal=Calendar.getInstance();
        txtTanggalBuat.setText(sdf.format(cal.getTime()));
    }
    
    private void tglHabisIzin(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, 3);
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
        txtTanggalHabis.setText(sdf.format(cal.getTime()));
    }
    
    private void hargaNisan(){
        if(cbNisan.isSelected()==true){
            nisan=125000;
            total+=nisan;
        }else{
            nisan=125000;
            total-=nisan;
        }
        txtTotalBiaya.setText(String.valueOf(total));
    }
    
    private void hargaRumput(){
        if(cbRumput.isSelected()==true){
            rumput=400000;
            total+=rumput;
        }else{
            rumput=400000;
            total-=rumput;
        }
        txtTotalBiaya.setText(String.valueOf(total));
    }
    
    private void hargaKeramik(){
        if(cbKeramik.isSelected()==true){
            keramik=500000;
            total+=keramik;
        }else{
            keramik=500000;
            total-=keramik;
        }
        txtTotalBiaya.setText(String.valueOf(total));
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
        tabPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        panelAtas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNoArsip = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTanggal = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        panelUtama = new javax.swing.JPanel();
        panelArsip = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNamaJenazah = new javax.swing.JTextField();
        txtNoJenazah = new javax.swing.JTextField();
        txtTempatLahir = new javax.swing.JTextField();
        txtTanggalLahir = new javax.swing.JTextField();
        txtKelaminJenazah = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        txtTempatWafat = new javax.swing.JTextField();
        txtTanggalWafat = new javax.swing.JTextField();
        btCariJenazah = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtJenisKelaminWaris = new javax.swing.JTextField();
        txtTelp = new javax.swing.JTextField();
        txtTanggalLahirWaris = new javax.swing.JTextField();
        txtNamaWaris = new javax.swing.JTextField();
        txtNoWaris = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAlamatWaris = new javax.swing.JTextArea();
        btCariWaris = new javax.swing.JButton();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        btSelanjutnya = new javax.swing.JButton();
        panelTransaksi = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtHargaSewa = new javax.swing.JTextField();
        txtLokasi = new javax.swing.JTextField();
        txtNamaBlok = new javax.swing.JTextField();
        txtKategori = new javax.swing.JTextField();
        txtKodeBlok = new javax.swing.JTextField();
        btCariBlok = new javax.swing.JButton();
        cbNisan = new javax.swing.JCheckBox();
        cbRumput = new javax.swing.JCheckBox();
        cbKeramik = new javax.swing.JCheckBox();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator21 = new javax.swing.JSeparator();
        jSeparator22 = new javax.swing.JSeparator();
        jSeparator23 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        txtTanggalBuat = new javax.swing.JTextField();
        jSeparator24 = new javax.swing.JSeparator();
        jSeparator25 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        txtTanggalHabis = new javax.swing.JTextField();
        jSeparator26 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        btKembali = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtUkem = new javax.swing.JLabel();
        txtUbay = new javax.swing.JTextField();
        txtTotalBiaya = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        tabData = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btCari2 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableArsip = new javax.swing.JTable();

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

        jPanel5.setBackground(new java.awt.Color(30, 49, 92));

        panelAtas.setBackground(new java.awt.Color(106, 117, 146));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("No. Arsip");

        txtNoArsip.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtNoArsip.setForeground(new java.awt.Color(255, 255, 255));
        txtNoArsip.setBorder(null);
        txtNoArsip.setOpaque(false);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tanggal");

        txtTanggal.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtTanggal.setForeground(new java.awt.Color(255, 255, 255));
        txtTanggal.setBorder(null);
        txtTanggal.setOpaque(false);

        javax.swing.GroupLayout panelAtasLayout = new javax.swing.GroupLayout(panelAtas);
        panelAtas.setLayout(panelAtasLayout);
        panelAtasLayout.setHorizontalGroup(
            panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAtasLayout.createSequentialGroup()
                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAtasLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(txtNoArsip, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAtasLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAtasLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(20, 20, 20)
                        .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAtasLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelAtasLayout.setVerticalGroup(
            panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAtasLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtNoArsip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelUtama.setLayout(new java.awt.CardLayout());

        panelArsip.setBackground(new java.awt.Color(30, 49, 92));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel3.setText("No. Jenazah");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel4.setText("Nama Jenazah");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel5.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel5.setText("Tempat Lahir");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        jLabel6.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel6.setText("Tanggal Lahir");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel7.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel7.setText("Jenis Kelamin");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jLabel8.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel8.setText("Alamat");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel9.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel9.setText("Tempat Wafat");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        jLabel10.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel10.setText("Tanggal Wafat");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        txtNamaJenazah.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtNamaJenazah.setBorder(null);
        txtNamaJenazah.setOpaque(false);
        jPanel1.add(txtNamaJenazah, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 240, -1));

        txtNoJenazah.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtNoJenazah.setBorder(null);
        txtNoJenazah.setOpaque(false);
        jPanel1.add(txtNoJenazah, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 160, -1));

        txtTempatLahir.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtTempatLahir.setBorder(null);
        txtTempatLahir.setOpaque(false);
        jPanel1.add(txtTempatLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 240, -1));

        txtTanggalLahir.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtTanggalLahir.setBorder(null);
        txtTanggalLahir.setOpaque(false);
        jPanel1.add(txtTanggalLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 240, -1));

        txtKelaminJenazah.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtKelaminJenazah.setBorder(null);
        txtKelaminJenazah.setOpaque(false);
        jPanel1.add(txtKelaminJenazah, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 240, -1));

        txtalamat.setColumns(20);
        txtalamat.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtalamat.setRows(5);
        jScrollPane1.setViewportView(txtalamat);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));

        txtTempatWafat.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtTempatWafat.setBorder(null);
        txtTempatWafat.setOpaque(false);
        jPanel1.add(txtTempatWafat, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 240, -1));

        txtTanggalWafat.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtTanggalWafat.setBorder(null);
        txtTanggalWafat.setOpaque(false);
        jPanel1.add(txtTanggalWafat, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 240, -1));

        btCariJenazah.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        btCariJenazah.setText("Cari");
        btCariJenazah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariJenazahActionPerformed(evt);
            }
        });
        jPanel1.add(btCariJenazah, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 70, -1));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 240, 10));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 240, 10));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 240, 10));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 240, 10));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 240, 10));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 160, 10));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 240, 10));

        jLabel31.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel31.setText("No. Waris");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, -1, -1));

        jLabel32.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel32.setText("Nama Waris");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, -1, -1));

        jLabel33.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel33.setText("Tanggal Lahir");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, -1, -1));

        jLabel34.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel34.setText("Jenis Kelamin");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, -1, -1));

        jLabel35.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel35.setText("No. Telpon");
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, -1, -1));

        jLabel36.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel36.setText("Alamat");
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, -1, -1));

        txtJenisKelaminWaris.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtJenisKelaminWaris.setBorder(null);
        txtJenisKelaminWaris.setOpaque(false);
        jPanel1.add(txtJenisKelaminWaris, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 160, 240, -1));

        txtTelp.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtTelp.setBorder(null);
        txtTelp.setOpaque(false);
        jPanel1.add(txtTelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, 240, -1));

        txtTanggalLahirWaris.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtTanggalLahirWaris.setBorder(null);
        txtTanggalLahirWaris.setOpaque(false);
        jPanel1.add(txtTanggalLahirWaris, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 240, -1));

        txtNamaWaris.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtNamaWaris.setBorder(null);
        txtNamaWaris.setOpaque(false);
        jPanel1.add(txtNamaWaris, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 240, -1));

        txtNoWaris.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtNoWaris.setBorder(null);
        txtNoWaris.setOpaque(false);
        jPanel1.add(txtNoWaris, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 170, -1));

        txtAlamatWaris.setColumns(20);
        txtAlamatWaris.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtAlamatWaris.setRows(5);
        jScrollPane4.setViewportView(txtAlamatWaris);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, -1, -1));

        btCariWaris.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        btCariWaris.setText("Cari");
        btCariWaris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariWarisActionPerformed(evt);
            }
        });
        jPanel1.add(btCariWaris, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 40, 60, -1));
        jPanel1.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, 240, 10));
        jPanel1.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, 240, 10));

        jSeparator17.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 20, 410));
        jPanel1.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, 170, 10));
        jPanel1.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, 240, 10));
        jPanel1.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 220, 240, 10));

        jPanel6.setBackground(new java.awt.Color(106, 117, 146));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btSelanjutnya.setBackground(new java.awt.Color(255, 255, 255));
        btSelanjutnya.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        btSelanjutnya.setText("Selanjutnya");
        btSelanjutnya.setOpaque(false);
        btSelanjutnya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelanjutnyaActionPerformed(evt);
            }
        });
        jPanel6.add(btSelanjutnya, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 30));

        javax.swing.GroupLayout panelArsipLayout = new javax.swing.GroupLayout(panelArsip);
        panelArsip.setLayout(panelArsipLayout);
        panelArsipLayout.setHorizontalGroup(
            panelArsipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1111, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelArsipLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
        );
        panelArsipLayout.setVerticalGroup(
            panelArsipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArsipLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelUtama.add(panelArsip, "card2");

        panelTransaksi.setBackground(new java.awt.Color(30, 49, 92));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel17.setText("Kode Blok");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel18.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel18.setText("Kategori");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel19.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel19.setText("Nama Blok");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel20.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel20.setText("Lokasi");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel21.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel21.setText("Harga Sewa");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        txtHargaSewa.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtHargaSewa.setBorder(null);
        txtHargaSewa.setOpaque(false);
        jPanel3.add(txtHargaSewa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 240, -1));

        txtLokasi.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtLokasi.setBorder(null);
        txtLokasi.setOpaque(false);
        jPanel3.add(txtLokasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 240, -1));

        txtNamaBlok.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtNamaBlok.setBorder(null);
        txtNamaBlok.setOpaque(false);
        jPanel3.add(txtNamaBlok, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 240, -1));

        txtKategori.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtKategori.setBorder(null);
        txtKategori.setOpaque(false);
        jPanel3.add(txtKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 240, -1));

        txtKodeBlok.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtKodeBlok.setBorder(null);
        txtKodeBlok.setOpaque(false);
        jPanel3.add(txtKodeBlok, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 160, -1));

        btCariBlok.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        btCariBlok.setText("Cari");
        btCariBlok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariBlokActionPerformed(evt);
            }
        });
        jPanel3.add(btCariBlok, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 68, -1));

        cbNisan.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        cbNisan.setText("Pasang Nisan");
        cbNisan.setOpaque(false);
        cbNisan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNisanActionPerformed(evt);
            }
        });
        jPanel3.add(cbNisan, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, 120, -1));

        cbRumput.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        cbRumput.setText("Pasang rumput");
        cbRumput.setOpaque(false);
        cbRumput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRumputActionPerformed(evt);
            }
        });
        jPanel3.add(cbRumput, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, -1, -1));

        cbKeramik.setBackground(new java.awt.Color(255, 255, 255));
        cbKeramik.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        cbKeramik.setText("Pasang Keramik");
        cbKeramik.setOpaque(false);
        cbKeramik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKeramikActionPerformed(evt);
            }
        });
        jPanel3.add(cbKeramik, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, -1, -1));

        jLabel37.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel37.setText("Rp. 500,000");
        jPanel3.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 130, -1, 30));

        jLabel38.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel38.setText("Rp. 400,000");
        jPanel3.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 90, -1, 30));

        jLabel39.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel39.setText("Rp. 125,000");
        jPanel3.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 50, -1, 30));
        jPanel3.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 240, 10));
        jPanel3.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 240, 10));
        jPanel3.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 240, 10));
        jPanel3.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 160, 10));
        jPanel3.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 240, 10));

        jSeparator23.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 10, 250));

        jLabel22.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel22.setText("Tgl Buat Izin");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, -1, -1));

        txtTanggalBuat.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtTanggalBuat.setBorder(null);
        txtTanggalBuat.setOpaque(false);
        jPanel3.add(txtTanggalBuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 240, -1));
        jPanel3.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 240, 10));

        jSeparator25.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 10, 250));

        jLabel23.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel23.setText("Tgl Habis Izin");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, -1, -1));

        txtTanggalHabis.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        txtTanggalHabis.setBorder(null);
        txtTanggalHabis.setOpaque(false);
        jPanel3.add(txtTanggalHabis, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 240, -1));
        jPanel3.add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 240, 10));

        jPanel7.setBackground(new java.awt.Color(106, 117, 146));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btKembali.setBackground(new java.awt.Color(255, 255, 255));
        btKembali.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        btKembali.setText("Kembali");
        btKembali.setOpaque(false);
        btKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btKembaliActionPerformed(evt);
            }
        });
        jPanel7.add(btKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 30));

        jPanel8.setBackground(new java.awt.Color(106, 117, 146));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Futura Bk BT", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Total Biaya");
        jPanel8.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel26.setFont(new java.awt.Font("Futura Bk BT", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Uang Bayar");
        jPanel8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel27.setFont(new java.awt.Font("Futura Bk BT", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Uang Kembali");
        jPanel8.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtUkem.setFont(new java.awt.Font("Futura Bk BT", 1, 14)); // NOI18N
        txtUkem.setForeground(new java.awt.Color(255, 255, 255));
        txtUkem.setText("0");
        jPanel8.add(txtUkem, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));

        txtUbay.setFont(new java.awt.Font("Futura Bk BT", 1, 14)); // NOI18N
        txtUbay.setForeground(new java.awt.Color(255, 255, 255));
        txtUbay.setBorder(null);
        txtUbay.setOpaque(false);
        txtUbay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUbayKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUbayKeyTyped(evt);
            }
        });
        jPanel8.add(txtUbay, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 220, -1));

        txtTotalBiaya.setFont(new java.awt.Font("Futura Bk BT", 1, 14)); // NOI18N
        txtTotalBiaya.setForeground(new java.awt.Color(255, 255, 255));
        txtTotalBiaya.setText("0");
        jPanel8.add(txtTotalBiaya, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));
        jPanel8.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 220, 10));

        javax.swing.GroupLayout panelTransaksiLayout = new javax.swing.GroupLayout(panelTransaksi);
        panelTransaksi.setLayout(panelTransaksiLayout);
        panelTransaksiLayout.setHorizontalGroup(
            panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1111, Short.MAX_VALUE)
            .addGroup(panelTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTransaksiLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelTransaksiLayout.setVerticalGroup(
            panelTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTransaksiLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelUtama.add(panelTransaksi, "card3");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUtama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelAtas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(panelAtas, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabPanelLayout = new javax.swing.GroupLayout(tabPanel);
        tabPanel.setLayout(tabPanelLayout);
        tabPanelLayout.setHorizontalGroup(
            tabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tabPanelLayout.setVerticalGroup(
            tabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPanelLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Transaksi", tabPanel);

        jPanel2.setBackground(new java.awt.Color(30, 49, 92));

        jPanel9.setBackground(new java.awt.Color(106, 117, 146));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Cari Data");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

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
        jPanel9.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 240, -1));

        btCari2.setBackground(new java.awt.Color(255, 255, 255));
        btCari2.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        btCari2.setText("Cari");
        btCari2.setOpaque(false);
        btCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCari2ActionPerformed(evt);
            }
        });
        jPanel9.add(btCari2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 100, -1));
        jPanel9.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 240, 10));

        tableArsip.setBackground(new java.awt.Color(106, 117, 146));
        tableArsip.setFont(new java.awt.Font("Futura Bk BT", 0, 11)); // NOI18N
        tableArsip.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tableArsip);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1111, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabDataLayout = new javax.swing.GroupLayout(tabData);
        tabData.setLayout(tabDataLayout);
        tabDataLayout.setHorizontalGroup(
            tabDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tabDataLayout.setVerticalGroup(
            tabDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Lihat Data", tabData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSelanjutnyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelanjutnyaActionPerformed
        panelArsip.setVisible(false);
        panelTransaksi.setVisible(true);
    }//GEN-LAST:event_btSelanjutnyaActionPerformed

    private void cbRumputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRumputActionPerformed
        hargaRumput();
    }//GEN-LAST:event_cbRumputActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void btCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCari2ActionPerformed
        cariTable(txtCari.getText());
    }//GEN-LAST:event_btCari2ActionPerformed

    private void btCariBlokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariBlokActionPerformed
        PopBlokMakam.getObj().FT=this;
        PopBlokMakam.getObj().setVisible(true);
        PopBlokMakam.getObj().tampilTableMakam();
    }//GEN-LAST:event_btCariBlokActionPerformed

    private void btCariJenazahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariJenazahActionPerformed
        PopJenazah.getObj().FT=this;
        PopJenazah.getObj().setVisible(true);
        PopJenazah.getObj().tampilTableJenazah();
        
    }//GEN-LAST:event_btCariJenazahActionPerformed

    private void btCariWarisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariWarisActionPerformed
        PopWaris.getObj().FT=this;
        PopWaris.getObj().setVisible(true);
        PopWaris.getObj().tampilTableWaris();
    }//GEN-LAST:event_btCariWarisActionPerformed

    private void btKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btKembaliActionPerformed
        panelArsip.setVisible(true);
        panelTransaksi.setVisible(false);
    }//GEN-LAST:event_btKembaliActionPerformed

    private void cbNisanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNisanActionPerformed
        hargaNisan();
    }//GEN-LAST:event_cbNisanActionPerformed

    private void cbKeramikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKeramikActionPerformed
        hargaKeramik();
    }//GEN-LAST:event_cbKeramikActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtNoArsip.setEnabled(false);
        txtTanggal.setEnabled(false);
        txtNoJenazah.setEnabled(false);
        txtNamaJenazah.setEnabled(false);
        btCariJenazah.setEnabled(false);
        txtTempatLahir.setEnabled(false);
        txtTanggalLahir.setEnabled(false);
        txtKelaminJenazah.setEnabled(false);
        txtalamat.setEnabled(false);
        txtTempatWafat.setEnabled(false);
        txtTanggalWafat.setEnabled(false);
        txtNoWaris.setEnabled(false);
        txtNamaWaris.setEnabled(false);
        txtTanggalLahirWaris.setEnabled(false);
        txtJenisKelaminWaris.setEnabled(false);
        txtTanggalLahir.setEnabled(false);
        txtTelp.setEnabled(false);
        txtAlamatWaris.setEnabled(false);
        btCariWaris.setEnabled(false);
        btSelanjutnya.setEnabled(false);
        txtKodeBlok.setEnabled(false);
        btCariBlok.setEnabled(false);
        txtKategori.setEnabled(false);
        txtNamaBlok.setEnabled(false);
        txtLokasi.setEnabled(false);
        txtHargaSewa.setEnabled(false);
        txtTanggalBuat.setEnabled(false);
        txtTanggalHabis.setEnabled(false);
        cbNisan.setEnabled(false);
        cbRumput.setEnabled(false);
        cbKeramik.setEnabled(false);
        txtTotalBiaya.setEnabled(false);
        txtUbay.setEnabled(false);
        txtUkem.setEnabled(false);
        btKembali.setEnabled(false);
        
    }//GEN-LAST:event_formInternalFrameActivated

    private void txtUbayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbayKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            ubay=Double.parseDouble(txtUbay.getText());
            if(ubay<total){
                JOptionPane.showMessageDialog(null, "Uang bayar kurang!");
            }else{
                ukem=ubay-total;
                txtUkem.setText(String.valueOf(ukem));
            }
        }
    }//GEN-LAST:event_txtUbayKeyPressed

    private void txtUbayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUbayKeyTyped
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACKSPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtUbayKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari2;
    private javax.swing.JButton btCariBlok;
    private javax.swing.JButton btCariJenazah;
    private javax.swing.JButton btCariWaris;
    private javax.swing.JButton btKembali;
    private javax.swing.JButton btSelanjutnya;
    private javax.swing.JCheckBox cbKeramik;
    private javax.swing.JCheckBox cbNisan;
    private javax.swing.JCheckBox cbRumput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelArsip;
    private javax.swing.JPanel panelAtas;
    private javax.swing.JPanel panelTransaksi;
    private javax.swing.JPanel panelUtama;
    private javax.swing.JPanel tabData;
    private javax.swing.JPanel tabPanel;
    private javax.swing.JTable tableArsip;
    private javax.swing.JTextArea txtAlamatWaris;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHargaSewa;
    private javax.swing.JTextField txtJenisKelaminWaris;
    private javax.swing.JTextField txtKategori;
    private javax.swing.JTextField txtKelaminJenazah;
    private javax.swing.JTextField txtKodeBlok;
    private javax.swing.JTextField txtLokasi;
    private javax.swing.JTextField txtNamaBlok;
    private javax.swing.JTextField txtNamaJenazah;
    private javax.swing.JTextField txtNamaWaris;
    private javax.swing.JTextField txtNoArsip;
    private javax.swing.JTextField txtNoJenazah;
    private javax.swing.JTextField txtNoWaris;
    private javax.swing.JTextField txtTanggal;
    private javax.swing.JTextField txtTanggalBuat;
    private javax.swing.JTextField txtTanggalHabis;
    private javax.swing.JTextField txtTanggalLahir;
    private javax.swing.JTextField txtTanggalLahirWaris;
    private javax.swing.JTextField txtTanggalWafat;
    private javax.swing.JTextField txtTelp;
    private javax.swing.JTextField txtTempatLahir;
    private javax.swing.JTextField txtTempatWafat;
    private javax.swing.JLabel txtTotalBiaya;
    private javax.swing.JTextField txtUbay;
    private javax.swing.JLabel txtUkem;
    private javax.swing.JTextArea txtalamat;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        txtNoArsip.setEnabled(true);
        txtNoArsip.setEditable(false);
        txtTanggal.setEnabled(true);
        txtTanggal.setEditable(false);
        btCariJenazah.setEnabled(true);
        btCariWaris.setEnabled(true);
        btCariBlok.setEnabled(true);
        cbNisan.setEnabled(true);
        cbRumput.setEnabled(true);
        cbKeramik.setEnabled(true);
        txtUbay.setEnabled(true);
        btKembali.setEnabled(true);
        btSelanjutnya.setEnabled(true);
        txtTotalBiaya.setEnabled(true);
        txtUkem.setEnabled(true);
        setTanggal();        
    }

    @Override
    public void bersih() {
        txtNoJenazah.setText("");
        txtNamaJenazah.setText("");
        txtTempatLahir.setText("");
        txtTanggalLahir.setText("");
        txtKelaminJenazah.setText("");
        txtalamat.setText("");
        txtTempatWafat.setText("");
        txtTanggalWafat.setText("");
        txtNoWaris.setText("");
        txtNamaWaris.setText("");
        txtTanggalLahirWaris.setText("");
        txtJenisKelaminWaris.setText("");
        txtTelp.setText("");
        txtAlamatWaris.setText("");
        txtKodeBlok.setText("");
        txtKategori.setText("");
        txtNamaBlok.setText("");
        txtLokasi.setText("");
        txtHargaSewa.setText("");
        txtTanggalBuat.setText("");
        txtTanggalHabis.setText("");
        txtTotalBiaya.setText("0");
        txtUbay.setText("");
        txtUkem.setText("0");
        cbNisan.setSelected(false);
        cbRumput.setSelected(false);
        cbKeramik.setSelected(false);
        txtNoArsip.setText(aCont.nomorOtomatis());
        total=0;
        tampilTableArsip();
    }

    @Override
    public void simpan() {
        if("".equals(txtNoJenazah.getText())||"".equals(txtNoWaris.getText())||"".equals(txtKodeBlok.getText())||
                "".equals(txtUbay.getText())){
            JOptionPane.showMessageDialog(null, "Data belum lengkap!");
        }else{
            arsip.setNoArsip(txtNoArsip.getText());
            arsip.setNoJenazah(txtNoJenazah.getText());
            arsip.setNoWaris(txtNoWaris.getText());
            arsip.setTanggalBuatIzin(new Date());
            try{
                SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
                Date tglHabis=(Date) sdf.parse(txtTanggalHabis.getText());
                arsip.setTanggalHabisIzin(tglHabis);
            }catch(Exception ex){}
            arsip.setKodeBlok(txtKodeBlok.getText());
            arsip.setKeterangan("Berlaku");
            arsip.setKodeUser(Kode);
            
            transaksi.setNoArsip(txtNoArsip.getText());
            transaksi.setTanggal(new Date());
            transaksi.setBiayaSewa(Double.parseDouble(txtHargaSewa.getText()));
            transaksi.setBiayaNisan(nisan);
            transaksi.setBiayaRumput(rumput);
            transaksi.setBiayaKeramik(keramik);
            transaksi.setTotalBiaya(total);
            try{
                aCont.save(arsip);
                tCont.save(transaksi);
                jCont.updateJenazah(kdJen, txtNoArsip.getText());
                wCont.updateWaris(kdWar, txtNoArsip.getText());
                bCont.updateBlok(kdBlok, "Terpakai");
            }catch(Exception ex){}
            JOptionPane.showMessageDialog(null, "Data telah tersimpan!");
            bersih();
        }
    }

    @Override
    public void hapus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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