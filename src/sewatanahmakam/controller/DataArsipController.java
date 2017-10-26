/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewatanahmakam.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import sewatanahmakam.data.Dataarsip;

/**
 *
 * @author Candi-PC
 */
public class DataArsipController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public DataArsipController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Dataarsip arsip) throws Exception {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(arsip);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Dataarsip findArsip(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(Dataarsip.class, nama);
        }finally{}
    }
    
    public String nomorOtomatis(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Calendar cal=Calendar.getInstance();
        String tahun=sdf.format(cal.getTime());
        String kode="Arsip"+tahun+"-001"; 
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select b from Dataarsip b order by b.noArsip desc");
            q.setMaxResults(1);
            Dataarsip arp=(Dataarsip) q.getSingleResult();
            if(q!=null){
                //String no=arp.getNoArsip().substring(0,14); ----not use----
                String nomorurut = arp.getNoArsip().substring(14);
                int urut = (Integer.parseInt(nomorurut)+1);
                String formatted=String.format("%03d", urut);
                //kode=no+(formatted);  ---- not use -----
                kode="Arsip"+tahun+"-"+(formatted);  // ------- this better code ----
            }
        }catch(NoResultException ex){}
        return kode;
    }
    
    //form Arsip 
    public DefaultTableModel showTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Connection connect=em.unwrap(Connection.class);
            Statement st=(Statement) connect.createStatement();
            ResultSet rs=st.executeQuery("SELECT NoArsip, TanggalBuatIzin, TanggalHabisIzin, NoJenazah, identitasjenazah.NamaJenazah, NoWaris, identitaswaris.NamaWaris, KodeBlok, blokmakam.NamaBlok, blokmakam.Lokasi, Dataarsip.Keterangan\n" +
                                        "FROM dataarsip\n" +
                                        "INNER JOIN identitasjenazah USING (NoJenazah)\n" +
                                        "INNER JOIN identitaswaris USING (NoWaris)\n" +
                                        "INNER JOIN blokmakam USING (KodeBlok)");
            while(rs.next()){
                Object[] obj = new Object[11];
                obj[0]=rs.getString("NoArsip");
                obj[1]=rs.getDate("TanggalBuatIzin");
                obj[2]=rs.getDate("TanggalHabisIzin");
                obj[3]=rs.getString("NoJenazah");
                obj[4]=rs.getString("NamaJenazah");
                obj[5]=rs.getString("NoWaris");
                obj[6]=rs.getString("NamaWaris");
                obj[7]=rs.getString("KodeBlok");
                obj[8]=rs.getString("NamaBlok");
                obj[9]=rs.getString("Lokasi");
                obj[10]=rs.getString("Keterangan");
                model.addRow(obj);
            }
            
        }catch(Exception ex){}
        return model;
    }
    
    //cari Arsip
    public DefaultTableModel cari(DefaultTableModel model, String cari){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Connection connect=em.unwrap(Connection.class);
            Statement st=(Statement) connect.createStatement();
            ResultSet rs=st.executeQuery("SELECT NoArsip, TanggalBuatIzin, TanggalHabisIzin, NoJenazah, identitasjenazah.NamaJenazah, NoWaris, identitaswaris.NamaWaris, KodeBlok, blokmakam.NamaBlok, blokmakam.Lokasi, dataarsip.Keterangan\n" +
                                        "FROM dataarsip\n" +
                                        "INNER JOIN identitasjenazah USING (NoJenazah)\n" +
                                        "INNER JOIN identitaswaris USING (NoWaris)\n" +
                                        "INNER JOIN blokmakam USING (KodeBlok)\n" +
                                        "WHERE NoArsip LIKE '%"+cari+"%'\n" +
                                        "OR NoJenazah LIKE '%"+cari+"%'\n" +
                                        "OR identitasjenazah.NamaJenazah LIKE '%"+cari+"%'\n" +
                                        "OR NoWaris LIKE '%"+cari+"%'\n" +
                                        "OR identitaswaris.NamaWaris LIKE '%"+cari+"%'\n" +
                                        "OR KodeBlok LIKE '%"+cari+"%'\n" +
                                        "OR blokmakam.NamaBlok LIKE '%"+cari+"%'\n" +
                                        "OR blokmakam.Lokasi LIKE '%"+cari+"%'\n" +
                                        "OR dataarsip.Keterangan LIKE '%"+cari+"%'");
            while(rs.next()){
                Object[] obj = new Object[11];
                obj[0]=rs.getString("NoArsip");
                obj[1]=rs.getDate("TanggalBuatIzin");
                obj[2]=rs.getDate("TanggalHabisIzin");
                obj[3]=rs.getString("NoJenazah");
                obj[4]=rs.getString("NamaJenazah");
                obj[5]=rs.getString("NoWaris");
                obj[6]=rs.getString("NamaWaris");
                obj[7]=rs.getString("KodeBlok");
                obj[8]=rs.getString("NamaBlok");
                obj[9]=rs.getString("Lokasi");
                obj[10]=rs.getString("Keterangan");
                model.addRow(obj);
            }
        }catch(Exception ex){}
        return model;
    }
    
    //Form Perpanjangan
    public void updateIzin(String kode, String tgl){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Query q=em.createQuery("UPDATE Dataarsip a SET a.tanggalBuatIzin = CURRENT_DATE, a.tanggalHabisIzin = '"+tgl+"' WHERE a.noArsip LIKE '%"+kode+"%'");
            q.executeUpdate();
            em.getTransaction().commit();
        }catch(Exception ex){
            em.close();
        }
        
    }
}
