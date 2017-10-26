/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewatanahmakam.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "datatransaksi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datatransaksi.findAll", query = "SELECT d FROM Datatransaksi d"),
    @NamedQuery(name = "Datatransaksi.findById", query = "SELECT d FROM Datatransaksi d WHERE d.id = :id"),
    @NamedQuery(name = "Datatransaksi.findByTanggal", query = "SELECT d FROM Datatransaksi d WHERE d.tanggal = :tanggal"),
    @NamedQuery(name = "Datatransaksi.findByNoArsip", query = "SELECT d FROM Datatransaksi d WHERE d.noArsip = :noArsip"),
    @NamedQuery(name = "Datatransaksi.findByBiayaSewa", query = "SELECT d FROM Datatransaksi d WHERE d.biayaSewa = :biayaSewa"),
    @NamedQuery(name = "Datatransaksi.findByBiayaRumput", query = "SELECT d FROM Datatransaksi d WHERE d.biayaRumput = :biayaRumput"),
    @NamedQuery(name = "Datatransaksi.findByBiayaNisan", query = "SELECT d FROM Datatransaksi d WHERE d.biayaNisan = :biayaNisan"),
    @NamedQuery(name = "Datatransaksi.findByBiayaKeramik", query = "SELECT d FROM Datatransaksi d WHERE d.biayaKeramik = :biayaKeramik"),
    @NamedQuery(name = "Datatransaksi.findByTotalBiaya", query = "SELECT d FROM Datatransaksi d WHERE d.totalBiaya = :totalBiaya")})
public class Datatransaksi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "Tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @Basic(optional = false)
    @Column(name = "NoArsip")
    private String noArsip;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BiayaSewa")
    private Double biayaSewa;
    @Column(name = "BiayaRumput")
    private Double biayaRumput;
    @Column(name = "BiayaNisan")
    private Double biayaNisan;
    @Column(name = "BiayaKeramik")
    private Double biayaKeramik;
    @Column(name = "TotalBiaya")
    private Double totalBiaya;

    public Datatransaksi() {
    }

    public Datatransaksi(Integer id) {
        this.id = id;
    }

    public Datatransaksi(Integer id, String noArsip) {
        this.id = id;
        this.noArsip = noArsip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getNoArsip() {
        return noArsip;
    }

    public void setNoArsip(String noArsip) {
        this.noArsip = noArsip;
    }

    public Double getBiayaSewa() {
        return biayaSewa;
    }

    public void setBiayaSewa(Double biayaSewa) {
        this.biayaSewa = biayaSewa;
    }

    public Double getBiayaRumput() {
        return biayaRumput;
    }

    public void setBiayaRumput(Double biayaRumput) {
        this.biayaRumput = biayaRumput;
    }

    public Double getBiayaNisan() {
        return biayaNisan;
    }

    public void setBiayaNisan(Double biayaNisan) {
        this.biayaNisan = biayaNisan;
    }

    public Double getBiayaKeramik() {
        return biayaKeramik;
    }

    public void setBiayaKeramik(Double biayaKeramik) {
        this.biayaKeramik = biayaKeramik;
    }

    public Double getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(Double totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datatransaksi)) {
            return false;
        }
        Datatransaksi other = (Datatransaksi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Images.Datatransaksi[ id=" + id + " ]";
    }
    
}
