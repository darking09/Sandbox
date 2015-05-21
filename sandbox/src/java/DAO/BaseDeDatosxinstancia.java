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
@Table(name = "base_de_datosxinstancia")
@NamedQueries({
    @NamedQuery(name = "BaseDeDatosxinstancia.findAll", query = "SELECT b FROM BaseDeDatosxinstancia b")})
public class BaseDeDatosxinstancia implements Serializable {
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
    @Column(name = "id_base_de_datos")
    private int idBaseDeDatos;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
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

    public BaseDeDatosxinstancia() {
    }

    public BaseDeDatosxinstancia(Integer id) {
        this.id = id;
    }

    public BaseDeDatosxinstancia(Integer id, int idInstancia, int idBaseDeDatos, String usuario, String password, Date create, Date update) {
        this.id = id;
        this.idInstancia = idInstancia;
        this.idBaseDeDatos = idBaseDeDatos;
        this.usuario = usuario;
        this.password = password;
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

    public int getIdBaseDeDatos() {
        return idBaseDeDatos;
    }

    public void setIdBaseDeDatos(int idBaseDeDatos) {
        this.idBaseDeDatos = idBaseDeDatos;
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
        if (!(object instanceof BaseDeDatosxinstancia)) {
            return false;
        }
        BaseDeDatosxinstancia other = (BaseDeDatosxinstancia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.BaseDeDatosxinstancia[ id=" + id + " ]";
    }
    
}
