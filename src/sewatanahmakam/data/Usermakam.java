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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "usermakam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usermakam.findAll", query = "SELECT u FROM Usermakam u"),
    @NamedQuery(name = "Usermakam.findByKodeUser", query = "SELECT u FROM Usermakam u WHERE u.kodeUser = :kodeUser"),
    @NamedQuery(name = "Usermakam.findByNamaUser", query = "SELECT u FROM Usermakam u WHERE u.namaUser = :namaUser"),
    @NamedQuery(name = "Usermakam.findByPassword", query = "SELECT u FROM Usermakam u WHERE u.password = :password"),
    @NamedQuery(name = "Usermakam.findByHakAkses", query = "SELECT u FROM Usermakam u WHERE u.hakAkses = :hakAkses")})
public class Usermakam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "KodeUser")
    private String kodeUser;
    @Column(name = "NamaUser")
    private String namaUser;
    @Column(name = "Password")
    private String password;
    @Column(name = "HakAkses")
    private String hakAkses;

    public Usermakam() {
    }

    public Usermakam(String kodeUser) {
        this.kodeUser = kodeUser;
    }

    public String getKodeUser() {
        return kodeUser;
    }

    public void setKodeUser(String kodeUser) {
        this.kodeUser = kodeUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHakAkses() {
        return hakAkses;
    }

    public void setHakAkses(String hakAkses) {
        this.hakAkses = hakAkses;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeUser != null ? kodeUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usermakam)) {
            return false;
        }
        Usermakam other = (Usermakam) object;
        if ((this.kodeUser == null && other.kodeUser != null) || (this.kodeUser != null && !this.kodeUser.equals(other.kodeUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Images.Usermakam[ kodeUser=" + kodeUser + " ]";
    }
    
}
