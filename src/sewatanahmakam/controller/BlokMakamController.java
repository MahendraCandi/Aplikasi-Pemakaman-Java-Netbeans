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
import sewatanahmakam.data.Blokmakam;

/**
 *
 * @author Candi-PC
 */

public class BlokMakamController implements Serializable {
    private EntityManagerFactory emf=null;
    
    public BlokMakamController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Blokmakam blokmakam) throws Exception {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(blokmakam);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void update(Blokmakam blokmakam) throws Exception{
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(blokmakam);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String nama) throws Exception{
        EntityManager em = getEntityManager();
        Blokmakam bsm;
        try{
            bsm=em.getReference(Blokmakam.class, nama);
            bsm.getNamaBlok();
            em.getTransaction().begin();
            em.remove(bsm);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Blokmakam findBlokMakam(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(Blokmakam.class, nama);
        }finally{}
    }
    
    public String nomorOtomatis(){
        String kode="Blok-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select b from Blokmakam b order by b.kodeBlok desc");
            q.setMaxResults(1);
            Blokmakam bsm=(Blokmakam) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("Blok-000");
                String nomorurut = bsm.getKodeBlok().substring(5);
                kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
            }
        }catch(NoResultException ex){}
        return kode;
    }
    
    //form Blok
    public DefaultTableModel showTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("SELECT b.kodeBlok, b.namaBlok, b.kategori, b.lokasi, b.hargaSewa, b.keterangan FROM Blokmakam b");
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    //cari blok
    public DefaultTableModel cari(DefaultTableModel model, String cari){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
                            //SQL = SELECT * FROM `kereta` WHERE `KodeKereta` like '%Cimahi%' or `StasiunTujuan` like '%Cimahi%'
            Query q=em.createQuery("SELECT b.kodeBlok, b.namaBlok, b.kategori, b.lokasi, b.hargaSewa, b.keterangan FROM Blokmakam b WHERE b.kodeBlok like '%"+cari+"%'"
                                 + " or b.namaBlok like '%"+cari+"%'"
                                 + " or b.kategori like '%"+cari+"%'"
                                 + " or b.lokasi like '%"+cari+"%'"
                                 + " or b.hargaSewa like '%"+cari+"%'"
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
    
    //formTransaksi
    public void updateBlok(String kode, String ket){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Query q=em.createQuery("UPDATE Blokmakam k SET k.keterangan = '"+ket+"' where k.kodeBlok = '"+kode+"'");
            q.executeUpdate();
            em.getTransaction().commit();
        }catch(Exception ex){
            em.close();
        }
    }
}