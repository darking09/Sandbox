/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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

/**
 *
 * @author asus 01
 */
@Entity
@Table(name = "tecnologiaxinstancia")
@NamedQueries({
    @NamedQuery(name = "Tecnologiaxinstancia.findAll", query = "SELECT t FROM Tecnologiaxinstancia t")})
public class Tecnologiaxinstancia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_instancia")
    private int idInstancia;
    @Basic(optional = false)
    @Column(name = "id_tecnologia")
    private int idTecnologia;
    @Basic(optional = false)
    @Column(name = "registro")
    private short registro;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date create;
    @Basic(optional = false)
    @Column(name = "update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date update;

    public Tecnologiaxinstancia() {
    }

    public Tecnologiaxinstancia(Integer id) {
        this.id = id;
    }

    public Tecnologiaxinstancia(Integer id, int idInstancia, int idTecnologia, short registro, Date create, Date update) {
        this.id = id;
        this.idInstancia = idInstancia;
        this.idTecnologia = idTecnologia;
        this.registro = registro;
        this.create = create;
        this.update = update;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdInstancia() {
        return idInstancia;
    }

    public void setIdInstancia(int idInstancia) {
        this.idInstancia = idInstancia;
    }

    public int getIdTecnologia() {
        return idTecnologia;
    }

    public void setIdTecnologia(int idTecnologia) {
        this.idTecnologia = idTecnologia;
    }

    public short getRegistro() {
        return registro;
    }

    public void setRegistro(short registro) {
        this.registro = registro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
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
        if (!(object instanceof Tecnologiaxinstancia)) {
            return false;
        }
        Tecnologiaxinstancia other = (Tecnologiaxinstancia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Tecnologiaxinstancia[ id=" + id + " ]";
    }
    
}
