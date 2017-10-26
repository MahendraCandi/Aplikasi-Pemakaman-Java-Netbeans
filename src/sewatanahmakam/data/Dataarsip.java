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
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "dataarsip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dataarsip.findAll", query = "SELECT d FROM Dataarsip d"),
    @NamedQuery(name = "Dataarsip.findByNoArsip", query = "SELECT d FROM Dataarsip d WHERE d.noArsip = :noArsip"),
    @NamedQuery(name = "Dataarsip.findByTanggalBuatIzin", query = "SELECT d FROM Dataarsip d WHERE d.tanggalBuatIzin = :tanggalBuatIzin"),
    @NamedQuery(name = "Dataarsip.findByTanggalHabisIzin", query = "SELECT d FROM Dataarsip d WHERE d.tanggalHabisIzin = :tanggalHabisIzin"),
    @NamedQuery(name = "Dataarsip.findByNoJenazah", query = "SELECT d FROM Dataarsip d WHERE d.noJenazah = :noJenazah"),
    @NamedQuery(name = "Dataarsip.findByNoWaris", query = "SELECT d FROM Dataarsip d WHERE d.noWaris = :noWaris"),
    @NamedQuery(name = "Dataarsip.findByKodeBlok", query = "SELECT d FROM Dataarsip d WHERE d.kodeBlok = :kodeBlok"),
    @NamedQuery(name = "Dataarsip.findByKodeUser", query = "SELECT d FROM Dataarsip d WHERE d.kodeUser = :kodeUser")})
public class Dataarsip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NoArsip")
    private String noArsip;
    @Column(name = "TanggalBuatIzin")
    @Temporal(TemporalType.DATE)
    private Date tanggalBuatIzin;
    @Column(name = "TanggalHabisIzin")
    @Temporal(TemporalType.DATE)
    private Date tanggalHabisIzin;
    @Column(name = "NoJenazah")
    private String noJenazah;
    @Column(name = "NoWaris")
    private String noWaris;
    @Column(name = "KodeBlok")
    private String kodeBlok;
    @Lob
    @Column(name = "Keterangan")
    private String keterangan;
    @Column(name = "KodeUser")
    private String kodeUser;

    public Dataarsip() {
    }

    public Dataarsip(String noArsip) {
        this.noArsip = noArsip;
    }

    public String getNoArsip() {
        return noArsip;
    }

    public void setNoArsip(String noArsip) {
        this.noArsip = noArsip;
    }

    public Date getTanggalBuatIzin() {
        return tanggalBuatIzin;
    }

    public void setTanggalBuatIzin(Date tanggalBuatIzin) {
        this.tanggalBuatIzin = tanggalBuatIzin;
    }

    public Date getTanggalHabisIzin() {
        return tanggalHabisIzin;
    }

    public void setTanggalHabisIzin(Date tanggalHabisIzin) {
        this.tanggalHabisIzin = tanggalHabisIzin;
    }

    public String getNoJenazah() {
        return noJenazah;
    }

    public void setNoJenazah(String noJenazah) {
        this.noJenazah = noJenazah;
    }

    public String getNoWaris() {
        return noWaris;
    }

    public void setNoWaris(String noWaris) {
        this.noWaris = noWaris;
    }

    public String getKodeBlok() {
        return kodeBlok;
    }

    public void setKodeBlok(String kodeBlok) {
        this.kodeBlok = kodeBlok;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKodeUser() {
        return kodeUser;
    }

    public void setKodeUser(String kodeUser) {
        this.kodeUser = kodeUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noArsip != null ? noArsip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dataarsip)) {
            return false;
        }
        Dataarsip other = (Dataarsip) object;
        if ((this.noArsip == null && other.noArsip != null) || (this.noArsip != null && !this.noArsip.equals(other.noArsip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Images.Dataarsip[ noArsip=" + noArsip + " ]";
    }
    
}
