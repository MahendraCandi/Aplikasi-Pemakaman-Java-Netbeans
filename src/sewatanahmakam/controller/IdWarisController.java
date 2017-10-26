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
import sewatanahmakam.data.Identitaswaris;

/**
 *
 * @author Candi-PC
 */
public class IdWarisController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public IdWarisController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Identitaswaris waris) throws Exception {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(waris);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void update(Identitaswaris waris) throws Exception{
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(waris);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String nama) throws Exception{
        EntityManager em = getEntityManager();
        Identitaswaris wrs;
        try{
            wrs=em.getReference(Identitaswaris.class, nama);
            wrs.getNoWaris();
            em.getTransaction().begin();
            em.remove(wrs);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Identitaswaris findWaris(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(Identitaswaris.class, nama);
        }finally{}
    }
    
    public String nomorOtomatis(){
        String kode="WRS-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select b from Identitaswaris b order by b.noWaris desc");
            q.setMaxResults(1);
            Identitaswaris wrs=(Identitaswaris) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("WRS-000");
                String nomorurut = wrs.getNoWaris().substring(5);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
    
    //form waris
    public DefaultTableModel showTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("SELECT b.noWaris, b.namaWaris, b.tanggalLahir, b.jenisKelamin, b.noTelp, b.alamat,b.keterangan FROM Identitaswaris b");
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    //cari waris
    public DefaultTableModel cari(DefaultTableModel model, String cari){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
                            //SQL = SELECT * FROM `kereta` WHERE `KodeKereta` like '%Cimahi%' or `StasiunTujuan` like '%Cimahi%'
            Query q=em.createQuery("SELECT b.noWaris, b.namaWaris, b.tanggalLahir, b.jenisKelamin, b.noTelp, b.alamat, b.keterangan FROM Identitaswaris b WHERE b.noWaris like '%"+cari+"%'"
                                 + " or b.namaWaris like '%"+cari+"%'"
                                 + " or b.tanggalLahir like '%"+cari+"%'"
                                 + " or b.jenisKelamin like '%"+cari+"%'"
                                 + " or b.noTelp like '%"+cari+"%'"
                                 + " or b.alamat like '%"+cari+"%'"
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
    
    //Pop Waris
    public DefaultTableModel showPopTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("SELECT b.noWaris, b.namaWaris, b.tanggalLahir, b.jenisKelamin, b.noTelp, b.alamat,b.keterangan FROM Identitaswaris b WHERE b.keterangan like '%Terdaftar%'");
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    //cari Pop waris
    public DefaultTableModel cariPop(DefaultTableModel model, String cari){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
                            //SQL = SELECT * FROM `kereta` WHERE `KodeKereta` like '%Cimahi%' or `StasiunTujuan` like '%Cimahi%'
            Query q=em.createQuery("SELECT b.noWaris, b.namaWaris, b.tanggalLahir, b.jenisKelamin, b.noTelp, b.alamat, b.keterangan FROM Identitaswaris b WHERE b.noWaris like '%"+cari+"%'"
                                 + " or b.namaWaris like '%"+cari+"%'"
                                 + " or b.tanggalLahir like '%"+cari+"%'"
                                 + " or b.jenisKelamin like '%"+cari+"%'"
                                 + " or b.noTelp like '%"+cari+"%'"
                                 + " or b.alamat like '%"+cari+"%'"
                                 + " And b.keterangan like '%Terdaftar%'");
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
    public void updateWaris(String kode, String ket){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Query q=em.createQuery("UPDATE Identitaswaris k SET k.keterangan = '"+ket+"' where k.noWaris = '"+kode+"'");
            q.executeUpdate();
            em.getTransaction().commit();
        }catch(Exception ex){
            em.close();
        }
    }
}
