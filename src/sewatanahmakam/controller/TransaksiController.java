/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewatanahmakam.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sewatanahmakam.data.Datatransaksi;

/**
 *
 * @author Candi-PC
 */
public class TransaksiController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public TransaksiController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Datatransaksi transaksi) throws Exception {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(transaksi);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public Datatransaksi findArsip(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(Datatransaksi.class, nama);
        }finally{}
    }
    

}
