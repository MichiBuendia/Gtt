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
@Table(name = "fermata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fermata.findAll", query = "SELECT f FROM Fermata f")
    , @NamedQuery(name = "Fermata.findById", query = "SELECT f FROM Fermata f WHERE f.id = :id")
    , @NamedQuery(name = "Fermata.findByCodice", query = "SELECT f FROM Fermata f WHERE f.codice = :codice")
    , @NamedQuery(name = "Fermata.findByLatitudine", query = "SELECT f FROM Fermata f WHERE f.latitudine = :latitudine")
    , @NamedQuery(name = "Fermata.findByLongitudine", query = "SELECT f FROM Fermata f WHERE f.longitudine = :longitudine")})
public class Fermata implements Serializable,
                                ChiavePrimaria  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "codice")
    private String codice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitudine")
    private BigDecimal latitudine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitudine")
    private BigDecimal longitudine;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "capolinea")
    private Collection<Orario> orarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fermata")
    private Collection<LineaFermata> lineaFermataCollection;

    public Fermata() {
    }

    public Fermata(Integer id) {
        this.id = id;
    }

    public Fermata(Integer id, String codice, BigDecimal latitudine, BigDecimal longitudine) {
        this.id = id;
        this.codice = codice;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public BigDecimal getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(BigDecimal latitudine) {
        this.latitudine = latitudine;
    }

    public BigDecimal getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(BigDecimal longitudine) {
        this.longitudine = longitudine;
    }

    @XmlTransient
    public Collection<Orario> getOrarioCollection() {
        return orarioCollection;
    }

    public void setOrarioCollection(Collection<Orario> orarioCollection) {
        this.orarioCollection = orarioCollection;
    }

    @XmlTransient
    public Collection<LineaFermata> getLineaFermataCollection() {
        return lineaFermataCollection;
    }

    public void setLineaFermataCollection(Collection<LineaFermata> lineaFermataCollection) {
        this.lineaFermataCollection = lineaFermataCollection;
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
        if (!(object instanceof Fermata)) {
            return false;
        }
        Fermata other = (Fermata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.engim.gtt.database.Fermata[ id=" + id + " ]";
    }
    
}
