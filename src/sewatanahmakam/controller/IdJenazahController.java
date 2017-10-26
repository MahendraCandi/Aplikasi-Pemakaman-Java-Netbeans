/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewatanahmakam.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import sewatanahmakam.data.Identitasjenazah;

/**
 *
 * @author Candi-PC
 */
public class IdJenazahController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public IdJenazahController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Identitasjenazah jenazah) throws Exception {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(jenazah);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void update(Identitasjenazah jenazah) throws Exception{
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(jenazah);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String nama) throws Exception{
        EntityManager em = getEntityManager();
        Identitasjenazah jen;
        try{
            jen=em.getReference(Identitasjenazah.class, nama);
            jen.getNoJenazah();
            em.getTransaction().begin();
            em.remove(jen);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Identitasjenazah findJenazah(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(Identitasjenazah.class, nama);
        }finally{}
    }
    
    public String nomorOtomatis(){
        String kode="JN-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select b from Identitasjenazah b order by b.noJenazah desc");
            q.setMaxResults(1);
            Identitasjenazah jen=(Identitasjenazah) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("JN-000");
                String nomorurut = jen.getNoJenazah().substring(3);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
    
    //form jenazah
    public DefaultTableModel showTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("SELECT b.noJenazah, b.namaJenazah, b.tempatLahir, b.tanggalLahir, b.jenisKelamin, b.alamat, b.tempatWafat, b.tanggalWafat, b.keterangan FROM Identitasjenazah b");
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    //cari jenazah
    public DefaultTableModel cari(DefaultTableModel model, String cari){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
                            //SQL = SELECT * FROM `kereta` WHERE `KodeKereta` like '%Cimahi%' or `StasiunTujuan` like '%Cimahi%'
            Query q=em.createQuery("SELECT b.noJenazah, b.namaJenazah, b.tempatLahir, b.tanggalLahir, b.jenisKelamin, b.alamat, b.tempatWafat, b.tanggalWafat, b.keterangan FROM Identitasjenazah b WHERE b.noJenazah like '%"+cari+"%'"
                                 + " or b.namaJenazah like '%"+cari+"%'"
                                 + " or b.tempatLahir like '%"+cari+"%'"
                                 + " or b.tanggalLahir like '%"+cari+"%'"
                                 + " or b.jenisKelamin like '%"+cari+"%'"
                                 + " or b.alamat like '%"+cari+"%'"
                                 + " or b.tempatWafat like '%"+cari+"%'"
                                 + " or b.tanggalWafat like '%"+cari+"%'"
                                 + " or b.keterangan like '%"+cari+"%'");
            List<Object> hasil=(List<Object>) q.getResultList();
                Iterator itr = hasil.iterator();
                while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    //Pop Jenazah
    public DefaultTableModel showPopTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("SELECT b.noJenazah, b.namaJenazah, b.tempatLahir, b.tanggalLahir, b.jenisKelamin, b.alamat, b.tempatWafat, b.tanggalWafat, b.keterangan FROM Identitasjenazah b WHERE b.keterangan like '%Terdaftar%'");
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    //cari pop jenazah
    public DefaultTableModel cariPop(DefaultTableModel model, String cari){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
                            //SQL = SELECT * FROM `kereta` WHERE `KodeKereta` like '%Cimahi%' or `StasiunTujuan` like '%Cimahi%'
            Query q=em.createQuery("SELECT b.noJenazah, b.namaJenazah, b.tempatLahir, b.tanggalLahir, b.jenisKelamin, b.alamat, b.tempatWafat, b.tanggalWafat, b.keterangan FROM Identitasjenazah b WHERE b.noJenazah like '%"+cari+"%'"
                                 + " or b.namaJenazah like '%"+cari+"%'"
                                 + " or b.tempatLahir like '%"+cari+"%'"
                                 + " or b.tanggalLahir like '%"+cari+"%'"
                                 + " or b.jenisKelamin like '%"+cari+"%'"
                                 + " or b.alamat like '%"+cari+"%'"
                                 + " or b.tempatWafat like '%"+cari+"%'"
                                 + " or b.tanggalWafat like '%"+cari+"%'"
                                 + " AND b.keterangan like '%Terdaftar%'");
            List<Object> hasil=(List<Object>) q.getResultList();
                Iterator itr = hasil.iterator();
                while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    //formTransaksi
    public void updateJenazah(String kode, String ket){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Query q=em.createQuery("UPDATE Identitasjenazah k SET k.keterangan = '"+ket+"' where k.noJenazah = '"+kode+"'");
            q.executeUpdate();
            em.getTransaction().commit();
        }catch(Exception ex){
            em.close();
        }
    }
}
