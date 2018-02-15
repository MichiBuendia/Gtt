/*
 * Copyright 2017 svilupposw.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.engim.gtt.database;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author svilupposw
 */
@Entity
@Table(name = "mezzo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mezzo.findAll", query = "SELECT m FROM Mezzo m")
    , @NamedQuery(name = "Mezzo.findById", query = "SELECT m FROM Mezzo m WHERE m.id = :id")
    , @NamedQuery(name = "Mezzo.findByTipo", query = "SELECT m FROM Mezzo m WHERE m.tipo = :tipo")
    , @NamedQuery(name = "Mezzo.findByPasseggeri", query = "SELECT m FROM Mezzo m WHERE m.passeggeri = :passeggeri")
    , @NamedQuery(name = "Mezzo.findByVelocit\u00e0Max", query = "SELECT m FROM Mezzo m WHERE m.velocit\u00e0Max = :velocit\u00e0Max")})
public class Mezzo implements Serializable,
                                ChiavePrimaria  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "passeggeri")
    private int passeggeri;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "velocit\u00e0_max")
    private BigDecimal velocitàMax;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mezzo")
    private Collection<Corsa> corsaCollection;

    public Mezzo() {
    }

    public Mezzo(Integer id) {
        this.id = id;
    }

    public Mezzo(Integer id, String tipo, int passeggeri, BigDecimal velocitàMax) {
        this.id = id;
        this.tipo = tipo;
        this.passeggeri = passeggeri;
        this.velocitàMax = velocitàMax;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPasseggeri() {
        return passeggeri;
    }

    public void setPasseggeri(int passeggeri) {
        this.passeggeri = passeggeri;
    }

    public BigDecimal getVelocitàMax() {
        return velocitàMax;
    }

    public void setVelocitàMax(BigDecimal velocitàMax) {
        this.velocitàMax = velocitàMax;
    }

    @XmlTransient
    public Collection<Corsa> getCorsaCollection() {
        return corsaCollection;
    }

    public void setCorsaCollection(Collection<Corsa> corsaCollection) {
        this.corsaCollection = corsaCollection;
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
        if (!(object instanceof Mezzo)) {
            return false;
        }
        Mezzo other = (Mezzo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.engim.gtt.database.Mezzo[ id=" + id + " ]";
    }
    
}
