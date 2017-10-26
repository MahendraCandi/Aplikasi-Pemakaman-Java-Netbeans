/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewatanahmakam.form;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicMenuBarUI;
import sewatanahmakam.SewaTanahMakam;
import sewatanahmakam.controller.LaporanController;

/**
 *
 * @author Candi-PC
 */
public class FormUtama extends javax.swing.JFrame {

    LaporanController LC=new LaporanController(SewaTanahMakam.emf);
    String Kode, Nama, Akses;
    /**
     * Creates new form FormUtama
     */
    public FormUtama(String kode, String nama, String akses) {
        initComponents();
        setLocationRelativeTo(null);
        jMenuBar1.setUI(new BasicMenuBarUI(){
            public void paint(Graphics g, JComponent c){
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        });
        Kode=kode;
        Nama=nama;
        Akses=akses;
        txtNama1.setText(Nama);
        txtAkses.setText(Akses);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public JMenu menuLaporan(){
        return menuLaporan;
    }
    
    public JMenuItem menuItemUser(){
        return menuUser;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelUtama = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btNew = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtAkses = new javax.swing.JLabel();
        txtNama1 = new javax.swing.JLabel();
        desktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        txtWaris = new javax.swing.JMenuItem();
        mnJenazah = new javax.swing.JMenuItem();
        mnBlok = new javax.swing.JMenuItem();
        menuUser = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnTransaksi = new javax.swing.JMenuItem();
        mnTransaksi1 = new javax.swing.JMenuItem();
        menuLaporan = new javax.swing.JMenu();
        mnLaporan = new javax.swing.JMenuItem();
        mnLaporan1 = new javax.swing.JMenuItem();
        mnLaporan2 = new javax.swing.JMenuItem();
        mnLaporan3 = new javax.swing.JMenuItem();
        mnLaporan4 = new javax.swing.JMenuItem();
        mnLogout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Sewa Tanah Makam");
        setBackground(new java.awt.Color(0, 0, 0));
        setUndecorated(true);
        setOpacity(0.96F);

        panelUtama.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 18, 52));

        jToolBar1.setFloatable(false);
        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);
        jToolBar1.setOpaque(false);

        btNew.setFont(new java.awt.Font("Futura Bk BT", 0, 11)); // NOI18N
        btNew.setForeground(new java.awt.Color(255, 255, 255));
        btNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Add File_36px_1 copy.png"))); // NOI18N
        btNew.setText("Baru");
        btNew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btNew.setFocusable(false);
        btNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btNew.setOpaque(false);
        btNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewActionPerformed(evt);
            }
        });
        jToolBar1.add(btNew);

        btSave.setFont(new java.awt.Font("Futura Bk BT", 0, 11)); // NOI18N
        btSave.setForeground(new java.awt.Color(255, 255, 255));
        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save_36px_2 copy.png"))); // NOI18N
        btSave.setText("Save");
        btSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btSave.setFocusable(false);
        btSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSave.setOpaque(false);
        btSave.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(btSave);

        btDelete.setFont(new java.awt.Font("Futura Bk BT", 0, 11)); // NOI18N
        btDelete.setForeground(new java.awt.Color(255, 255, 255));
        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete File_36px copy.png"))); // NOI18N
        btDelete.setText("Hapus");
        btDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btDelete.setFocusable(false);
        btDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btDelete.setOpaque(false);
        btDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });
        jToolBar1.add(btDelete);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Name_52px.png"))); // NOI18N

        txtAkses.setFont(new java.awt.Font("Futura Bk BT", 0, 11)); // NOI18N
        txtAkses.setForeground(new java.awt.Color(255, 255, 255));
        txtAkses.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtAkses.setText("Akses");

        txtNama1.setFont(new java.awt.Font("Futura Bk BT", 0, 11)); // NOI18N
        txtNama1.setForeground(new java.awt.Color(255, 255, 255));
        txtNama1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNama1.setText("Nama");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(txtNama1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(txtAkses, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNama1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAkses)
                .addContainerGap())
        );

        desktopPane.setBackground(new java.awt.Color(30, 49, 92));
        desktopPane.setForeground(new java.awt.Color(36, 47, 65));

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 588, Short.MAX_VALUE)
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelUtamaLayout = new javax.swing.GroupLayout(panelUtama);
        panelUtama.setLayout(panelUtamaLayout);
        panelUtamaLayout.setHorizontalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPane))
        );
        panelUtamaLayout.setVerticalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(desktopPane)
        );

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setBorder(null);
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar1.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Master");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N

        txtWaris.setBackground(new java.awt.Color(255, 255, 255));
        txtWaris.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        txtWaris.setText("Master Waris");
        txtWaris.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtWaris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtWarisActionPerformed(evt);
            }
        });
        jMenu1.add(txtWaris);

        mnJenazah.setBackground(new java.awt.Color(255, 255, 255));
        mnJenazah.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        mnJenazah.setText("Master Jenazah");
        mnJenazah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnJenazah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnJenazahActionPerformed(evt);
            }
        });
        jMenu1.add(mnJenazah);

        mnBlok.setBackground(new java.awt.Color(255, 255, 255));
        mnBlok.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        mnBlok.setText("Master Blok Makam");
        mnBlok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnBlok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBlokActionPerformed(evt);
            }
        });
        jMenu1.add(mnBlok);

        menuUser.setBackground(new java.awt.Color(255, 255, 255));
        menuUser.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        menuUser.setText("Master User");
        menuUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUserActionPerformed(evt);
            }
        });
        jMenu1.add(menuUser);

        jMenuBar1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("Transaksi");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu2.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N

        mnTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        mnTransaksi.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        mnTransaksi.setText("Buka Transaksi");
        mnTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnTransaksiActionPerformed(evt);
            }
        });
        jMenu2.add(mnTransaksi);

        mnTransaksi1.setBackground(new java.awt.Color(255, 255, 255));
        mnTransaksi1.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        mnTransaksi1.setText("Buka Form Perpanjang Arsip");
        mnTransaksi1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnTransaksi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnTransaksi1ActionPerformed(evt);
            }
        });
        jMenu2.add(mnTransaksi1);

        jMenuBar1.add(jMenu2);

        menuLaporan.setBackground(new java.awt.Color(255, 255, 255));
        menuLaporan.setText("Laporan");
        menuLaporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuLaporan.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N

        mnLaporan.setBackground(new java.awt.Color(255, 255, 255));
        mnLaporan.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        mnLaporan.setText("Buka Laporan Arsip");
        mnLaporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLaporanActionPerformed(evt);
            }
        });
        menuLaporan.add(mnLaporan);

        mnLaporan1.setBackground(new java.awt.Color(255, 255, 255));
        mnLaporan1.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        mnLaporan1.setText("Buka Laporan Jenazah");
        mnLaporan1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnLaporan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLaporan1ActionPerformed(evt);
            }
        });
        menuLaporan.add(mnLaporan1);

        mnLaporan2.setBackground(new java.awt.Color(255, 255, 255));
        mnLaporan2.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        mnLaporan2.setText("Buka Laporan Waris");
        mnLaporan2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnLaporan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLaporan2ActionPerformed(evt);
            }
        });
        menuLaporan.add(mnLaporan2);

        mnLaporan3.setBackground(new java.awt.Color(255, 255, 255));
        mnLaporan3.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        mnLaporan3.setText("Buka Laporan Blok Makam");
        mnLaporan3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnLaporan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLaporan3ActionPerformed(evt);
            }
        });
        menuLaporan.add(mnLaporan3);

        mnLaporan4.setBackground(new java.awt.Color(255, 255, 255));
        mnLaporan4.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        mnLaporan4.setText("Buka Laporan User");
        mnLaporan4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnLaporan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLaporan4ActionPerformed(evt);
            }
        });
        menuLaporan.add(mnLaporan4);

        jMenuBar1.add(menuLaporan);

        mnLogout.setBackground(new java.awt.Color(255, 255, 255));
        mnLogout.setText("Logout");
        mnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mnLogout.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        mnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnLogoutMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnLogout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        if(JOptionPane.showConfirmDialog(null, "Hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            nav = (NavigatorFormInterface) desktopPane.getSelectedFrame();
            nav.hapus();
        }
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        nav = (NavigatorFormInterface) desktopPane.getSelectedFrame();
        nav.simpan();
    }//GEN-LAST:event_btSaveActionPerformed

    private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
        nav = (NavigatorFormInterface) desktopPane.getSelectedFrame();
        nav.bersih();
        nav.aktif();
    }//GEN-LAST:event_btNewActionPerformed

    private void mnTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnTransaksiActionPerformed
        FormTransaksi FT=new FormTransaksi(Kode);
        showForm(FT);
    }//GEN-LAST:event_mnTransaksiActionPerformed

    private void mnJenazahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnJenazahActionPerformed
        FormJenazah FP=new FormJenazah();
        showForm(FP);
    }//GEN-LAST:event_mnJenazahActionPerformed

    private void mnBlokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBlokActionPerformed
        FormBlokMakam FBM=new FormBlokMakam();
        showForm(FBM);
    }//GEN-LAST:event_mnBlokActionPerformed

    private void menuUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUserActionPerformed
        FormUser FU=new FormUser();
        showForm(FU);
    }//GEN-LAST:event_menuUserActionPerformed

    private void txtWarisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtWarisActionPerformed
        FormWaris FW=new FormWaris();
        showForm(FW);
    }//GEN-LAST:event_txtWarisActionPerformed

    private void mnTransaksi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnTransaksi1ActionPerformed
        FormPerpanjangan FP=new FormPerpanjangan();
        showForm(FP);
    }//GEN-LAST:event_mnTransaksi1ActionPerformed

    private void mnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnLogoutMouseClicked
        FormLogin FL=new FormLogin();
        this.dispose();
        FL.setVisible(true);
        FL.setAutoRequestFocus(true);
    }//GEN-LAST:event_mnLogoutMouseClicked

    private void mnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLaporanActionPerformed
        FormLaporanArsip.getObj().setVisible(true);
    }//GEN-LAST:event_mnLaporanActionPerformed

    private void mnLaporan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLaporan1ActionPerformed
        LC.cetakJenazah();
    }//GEN-LAST:event_mnLaporan1ActionPerformed

    private void mnLaporan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLaporan2ActionPerformed
        LC.cetakWaris();
    }//GEN-LAST:event_mnLaporan2ActionPerformed

    private void mnLaporan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLaporan3ActionPerformed
        LC.cetakBlokMakam();
    }//GEN-LAST:event_mnLaporan3ActionPerformed

    private void mnLaporan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLaporan4ActionPerformed
        LC.cetakUser();
    }//GEN-LAST:event_mnLaporan4ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormUtama(this.toString(),this.toString(),this.toString()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btSave;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenu menuLaporan;
    private javax.swing.JMenuItem menuUser;
    private javax.swing.JMenuItem mnBlok;
    private javax.swing.JMenuItem mnJenazah;
    private javax.swing.JMenuItem mnLaporan;
    private javax.swing.JMenuItem mnLaporan1;
    private javax.swing.JMenuItem mnLaporan2;
    private javax.swing.JMenuItem mnLaporan3;
    private javax.swing.JMenuItem mnLaporan4;
    private javax.swing.JMenu mnLogout;
    private javax.swing.JMenuItem mnTransaksi;
    private javax.swing.JMenuItem mnTransaksi1;
    private javax.swing.JPanel panelUtama;
    private javax.swing.JLabel txtAkses;
    private javax.swing.JLabel txtNama1;
    private javax.swing.JMenuItem txtWaris;
    // End of variables declaration//GEN-END:variables

NavigatorFormInterface nav;
    private void showForm(Object obj){
        JInternalFrame jf=null;
        jf=(JInternalFrame) obj;
        desktopPane.add(jf);
        jf.setVisible(true);
        try{
            jf.setMaximum(true);
            jf.setSelected(true);
        }catch(java.beans.PropertyVetoException e){
        }
    }  
}
