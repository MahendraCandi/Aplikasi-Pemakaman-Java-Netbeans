/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewatanahmakam.form;

import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import sewatanahmakam.SewaTanahMakam;
import sewatanahmakam.controller.DataArsipController;

/**
 *
 * @author Candi-PC
 */
public class PopArsip extends javax.swing.JFrame {

    public static PopArsip obj=null;
    DataArsipController aCont=new DataArsipController(SewaTanahMakam.emf);
    DefaultTableModel model;
    FormPerpanjangan FP=null;
    /**
     * Creates new form PopArsip
     */
    public PopArsip() {
        initComponents();
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
        setLocationRelativeTo(null);
    }
    
    public static PopArsip getObj(){
        if(obj==null){
            obj=new PopArsip();
        }
        return obj;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btCari2 = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableArsip = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        btPilih = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(30, 49, 92));

        jPanel1.setBackground(new java.awt.Color(30, 49, 92));

        jPanel9.setBackground(new java.awt.Color(106, 117, 146));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Cari Data");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtCari.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
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
        btCari2.setFont(new java.awt.Font("Futura Bk BT", 0, 12)); // NOI18N
        btCari2.setText("Cari");
        btCari2.setOpaque(false);
        btCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCari2ActionPerformed(evt);
            }
        });
        jPanel9.add(btCari2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 100, -1));
        jPanel9.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 240, 10));

        tableArsip.setAutoCreateRowSorter(true);
        tableArsip.setBackground(new java.awt.Color(106, 117, 146));
        tableArsip.setFont(new java.awt.Font("Futura Bk BT", 0, 11)); // NOI18N
        tableArsip.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableArsip);

        jPanel6.setBackground(new java.awt.Color(106, 117, 146));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btPilih.setBackground(new java.awt.Color(255, 255, 255));
        btPilih.setFont(new java.awt.Font("Futura Bk BT", 0, 14)); // NOI18N
        btPilih.setText("Pilih Data");
        btPilih.setOpaque(false);
        btPilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPilihActionPerformed(evt);
            }
        });
        jPanel6.add(btPilih, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(292, 292, 292)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void btCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCari2ActionPerformed
              cariTable(txtCari.getText());
    }//GEN-LAST:event_btCari2ActionPerformed

    private void btPilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPilihActionPerformed
        int baris=tableArsip.getSelectedRow();
        if(baris==-1){
            JOptionPane.showMessageDialog(null, "Pilih baris!");
        }else{
            
                FP.ars=tableArsip.getValueAt(baris, 0).toString();
                FP.kdJen=tableArsip.getValueAt(baris, 3).toString();
                FP.kdWar=tableArsip.getValueAt(baris, 5).toString();
                FP.kdBlok=tableArsip.getValueAt(baris, 7).toString();
                FP.pilihArsip();
                this.dispose();
            
            
        }
    }//GEN-LAST:event_btPilihActionPerformed

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
            java.util.logging.Logger.getLogger(PopArsip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopArsip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopArsip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopArsip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopArsip().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari2;
    private javax.swing.JButton btPilih;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JTable tableArsip;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}