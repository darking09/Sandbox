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
@Table(name = "base_de_datos")
@NamedQueries({
    @NamedQuery(name = "BaseDeDatos.findAll", query = "SELECT b FROM BaseDeDatos b")})
public class BaseDeDatos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "url_administrador")
    private String urlAdministrador;
    @Basic(optional = false)
    @Column(name = "create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date create;
    @Basic(optional = false)
    @Column(name = "update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date update;

    public BaseDeDatos() {
    }

    public BaseDeDatos(Integer id) {
        this.id = id;
    }

    public BaseDeDatos(Integer id, String nombre, String urlAdministrador, Date create, Date update) {
        this.id = id;
        this.nombre = nombre;
        this.urlAdministrador = urlAdministrador;
        this.create = create;
        this.update = update;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlAdministrador() {
        return urlAdministrador;
    }

    public void setUrlAdministrador(String urlAdministrador) {
        this.urlAdministrador = urlAdministrador;
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
        if (!(object instanceof BaseDeDatos)) {
            return false;
        }
        BaseDeDatos other = (BaseDeDatos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.BaseDeDatos[ id=" + id + " ]";
    }
    
}
