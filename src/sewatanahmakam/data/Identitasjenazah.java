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
@Table(name = "identitasjenazah")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Identitasjenazah.findAll", query = "SELECT i FROM Identitasjenazah i"),
    @NamedQuery(name = "Identitasjenazah.findByNoJenazah", query = "SELECT i FROM Identitasjenazah i WHERE i.noJenazah = :noJenazah"),
    @NamedQuery(name = "Identitasjenazah.findByNamaJenazah", query = "SELECT i FROM Identitasjenazah i WHERE i.namaJenazah = :namaJenazah"),
    @NamedQuery(name = "Identitasjenazah.findByTempatLahir", query = "SELECT i FROM Identitasjenazah i WHERE i.tempatLahir = :tempatLahir"),
    @NamedQuery(name = "Identitasjenazah.findByTanggalLahir", query = "SELECT i FROM Identitasjenazah i WHERE i.tanggalLahir = :tanggalLahir"),
    @NamedQuery(name = "Identitasjenazah.findByJenisKelamin", query = "SELECT i FROM Identitasjenazah i WHERE i.jenisKelamin = :jenisKelamin"),
    @NamedQuery(name = "Identitasjenazah.findByAlamat", query = "SELECT i FROM Identitasjenazah i WHERE i.alamat = :alamat"),
    @NamedQuery(name = "Identitasjenazah.findByTempatWafat", query = "SELECT i FROM Identitasjenazah i WHERE i.tempatWafat = :tempatWafat"),
    @NamedQuery(name = "Identitasjenazah.findByTanggalWafat", query = "SELECT i FROM Identitasjenazah i WHERE i.tanggalWafat = :tanggalWafat")})
public class Identitasjenazah implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NoJenazah")
    private String noJenazah;
    @Column(name = "NamaJenazah")
    private String namaJenazah;
    @Column(name = "TempatLahir")
    private String tempatLahir;
    @Column(name = "TanggalLahir")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    @Column(name = "JenisKelamin")
    private String jenisKelamin;
    @Column(name = "Alamat")
    private String alamat;
    @Column(name = "TempatWafat")
    private String tempatWafat;
    @Column(name = "TanggalWafat")
    @Temporal(TemporalType.DATE)
    private Date tanggalWafat;
    @Lob
    @Column(name = "FotoKTP")
    private byte[] fotoKTP;
    @Lob
    @Column(name = "Keterangan")
    private String keterangan;

    public Identitasjenazah() {
    }

    public Identitasjenazah(String noJenazah) {
        this.noJenazah = noJenazah;
    }

    public String getNoJenazah() {
        return noJenazah;
    }

    public void setNoJenazah(String noJenazah) {
        this.noJenazah = noJenazah;
    }

    public String getNamaJenazah() {
        return namaJenazah;
    }

    public void setNamaJenazah(String namaJenazah) {
        this.namaJenazah = namaJenazah;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTempatWafat() {
        return tempatWafat;
    }

    public void setTempatWafat(String tempatWafat) {
        this.tempatWafat = tempatWafat;
    }

    public Date getTanggalWafat() {
        return tanggalWafat;
    }

    public void setTanggalWafat(Date tanggalWafat) {
        this.tanggalWafat = tanggalWafat;
    }

    public byte[] getFotoKTP() {
        return fotoKTP;
    }

    public void setFotoKTP(byte[] fotoKTP) {
        this.fotoKTP = fotoKTP;
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
        hash += (noJenazah != null ? noJenazah.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Identitasjenazah)) {
            return false;
        }
        Identitasjenazah other = (Identitasjenazah) object;
        if ((this.noJenazah == null && other.noJenazah != null) || (this.noJenazah != null && !this.noJenazah.equals(other.noJenazah))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Images.Identitasjenazah[ noJenazah=" + noJenazah + " ]";
    }
    
}
