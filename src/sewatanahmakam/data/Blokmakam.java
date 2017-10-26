/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sewatanahmakam.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "blokmakam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Blokmakam.findAll", query = "SELECT b FROM Blokmakam b"),
    @NamedQuery(name = "Blokmakam.findByKodeBlok", query = "SELECT b FROM Blokmakam b WHERE b.kodeBlok = :kodeBlok"),
    @NamedQuery(name = "Blokmakam.findByKategori", query = "SELECT b FROM Blokmakam b WHERE b.kategori = :kategori"),
    @NamedQuery(name = "Blokmakam.findByNamaBlok", query = "SELECT b FROM Blokmakam b WHERE b.namaBlok = :namaBlok"),
    @NamedQuery(name = "Blokmakam.findByLokasi", query = "SELECT b FROM Blokmakam b WHERE b.lokasi = :lokasi"),
    @NamedQuery(name = "Blokmakam.findByHargaSewa", query = "SELECT b FROM Blokmakam b WHERE b.hargaSewa = :hargaSewa")})
public class Blokmakam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "KodeBlok")
    private String kodeBlok;
    @Column(name = "Kategori")
    private String kategori;
    @Column(name = "NamaBlok")
    private String namaBlok;
    @Column(name = "Lokasi")
    private String lokasi;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HargaSewa")
    private Double hargaSewa;
    @Lob
    @Column(name = "Keterangan")
    private String keterangan;

    public Blokmakam() {
    }

    public Blokmakam(String kodeBlok) {
        this.kodeBlok = kodeBlok;
    }

    public String getKodeBlok() {
        return kodeBlok;
    }

    public void setKodeBlok(String kodeBlok) {
        this.kodeBlok = kodeBlok;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNamaBlok() {
        return namaBlok;
    }

    public void setNamaBlok(String namaBlok) {
        this.namaBlok = namaBlok;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Double getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(Double hargaSewa) {
        this.hargaSewa = hargaSewa;
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
        hash += (kodeBlok != null ? kodeBlok.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blokmakam)) {
            return false;
        }
        Blokmakam other = (Blokmakam) object;
        if ((this.kodeBlok == null && other.kodeBlok != null) || (this.kodeBlok != null && !this.kodeBlok.equals(other.kodeBlok))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Images.Blokmakam[ kodeBlok=" + kodeBlok + " ]";
    }
    
}
