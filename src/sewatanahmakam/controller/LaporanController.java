/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewatanahmakam.controller;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Candi-PC
 */

public class LaporanController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public LaporanController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    //SELECT * FROM `dataarsip` WHERE  (`TanggalBuatIzin` BETWEEN '2017-08-14' AND '2017-08-26')
    public void cetakArsip(Date jdc1, Date jdc2){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\report\\reportArsip.jasper";
            
            HashMap param = new HashMap();
            param.put("tgl1", jdc1);
            param.put("tgl2", jdc2);
            
            JasperPrint jprint=JasperFillManager.fillReport (namafile, param,connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.getTransaction().commit();
                em.close();
                connect.close();
        }catch(Exception e){e.printStackTrace();}
        
    }
        
        public void cetakBlokMakam(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\report\\reportBlokMakam.jasper";
            JasperPrint jprint=JasperFillManager.fillReport (namafile, new HashMap(),connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.getTransaction().commit();
                em.close();
                connect.close();
            
        }catch(Exception e){e.printStackTrace();
        }
    }   
        
        public void cetakJenazah(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\report\\reportJenazah.jasper";
            JasperPrint jprint=JasperFillManager.fillReport (namafile, new HashMap(),connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.getTransaction().commit();
                em.close();
                connect.close();
            
        }catch(Exception e){e.printStackTrace();
        }
    }
        
        public void cetakWaris(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\report\\reportWaris.jasper";
            JasperPrint jprint=JasperFillManager.fillReport (namafile, new HashMap(),connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.getTransaction().commit();
                em.close();
                connect.close();
        }catch(Exception e){e.printStackTrace();
        }
    }
        
    public void cetakUser(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\report\\reportUser.jasper";
            JasperPrint jprint=JasperFillManager.fillReport (namafile, new HashMap(),connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.getTransaction().commit();
                connect.close();
        }catch(Exception e){}
    }
}