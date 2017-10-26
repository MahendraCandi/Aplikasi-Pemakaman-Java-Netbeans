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
@Table(name = "identitaswaris")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Identitaswaris.findAll", query = "SELECT i FROM Identitaswaris i"),
    @NamedQuery(name = "Identitaswaris.findByNoWaris", query = "SELECT i FROM Identitaswaris i WHERE i.noWaris = :noWaris"),
    @NamedQuery(name = "Identitaswaris.findByNamaWaris", query = "SELECT i FROM Identitaswaris i WHERE i.namaWaris = :namaWaris"),
    @NamedQuery(name = "Identitaswaris.findByTanggalLahir", query = "SELECT i FROM Identitaswaris i WHERE i.tanggalLahir = :tanggalLahir"),
    @NamedQuery(name = "Identitaswaris.findByJenisKelamin", query = "SELECT i FROM Identitaswaris i WHERE i.jenisKelamin = :jenisKelamin"),
    @NamedQuery(name = "Identitaswaris.findByNoTelp", query = "SELECT i FROM Identitaswaris i WHERE i.noTelp = :noTelp"),
    @NamedQuery(name = "Identitaswaris.findByAlamat", query = "SELECT i FROM Identitaswaris i WHERE i.alamat = :alamat")})
public class Identitaswaris implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NoWaris")
    private String noWaris;
    @Column(name = "NamaWaris")
    private String namaWaris;
    @Column(name = "TanggalLahir")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    @Column(name = "JenisKelamin")
    private String jenisKelamin;
    @Column(name = "NoTelp")
    private String noTelp;
    @Column(name = "Alamat")
    private String alamat;
    @Lob
    @Column(name = "FotoKTP")
    private byte[] fotoKTP;
    @Lob
    @Column(name = "FotoKK")
    private byte[] fotoKK;
    @Lob
    @Column(name = "Keterangan")
    private String keterangan;

    public Identitaswaris() {
    }

    public Identitaswaris(String noWaris) {
        this.noWaris = noWaris;
    }

    public String getNoWaris() {
        return noWaris;
    }

    public void setNoWaris(String noWaris) {
        this.noWaris = noWaris;
    }

    public String getNamaWaris() {
        return namaWaris;
    }

    public void setNamaWaris(String namaWaris) {
        this.namaWaris = namaWaris;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public byte[] getFotoKTP() {
        return fotoKTP;
    }

    public void setFotoKTP(byte[] fotoKTP) {
        this.fotoKTP = fotoKTP;
    }

    public byte[] getFotoKK() {
        return fotoKK;
    }

    public void setFotoKK(byte[] fotoKK) {
        this.fotoKK = fotoKK;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noWaris != null ? noWaris.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Identitaswaris)) {
            return false;
        }
        Identitaswaris other = (Identitaswaris) object;
        if ((this.noWaris == null && other.noWaris != null) || (this.noWaris != null && !this.noWaris.equals(other.noWaris))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Images.Identitaswaris[ noWaris=" + noWaris + " ]";
    }
    
}
