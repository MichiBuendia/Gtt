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
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author svilupposw
 */
@Entity
@Table(name = "orario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orario.findAll", query = "SELECT o FROM Orario o")
    , @NamedQuery(name = "Orario.findById", query = "SELECT o FROM Orario o WHERE o.id = :id")
    , @NamedQuery(name = "Orario.findByPartenza", query = "SELECT o FROM Orario o WHERE o.partenza = :partenza")})
public class Orario implements Serializable,
                                ChiavePrimaria  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "partenza")
    @Temporal(TemporalType.TIME)
    private Date partenza;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orario")
    private Collection<Corsa> corsaCollection;
    @JoinColumn(name = "linea", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Linea linea;
    @JoinColumn(name = "capolinea", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fermata capolinea;

    public Orario() {
    }

    public Orario(Integer id) {
        this.id = id;
    }

    public Orario(Integer id, Date partenza) {
        this.id = id;
        this.partenza = partenza;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPartenza() {
        return partenza;
    }

    public void setPartenza(Date partenza) {
        this.partenza = partenza;
    }

    @XmlTransient
    public Collection<Corsa> getCorsaCollection() {
        return corsaCollection;
    }

    public void setCorsaCollection(Collection<Corsa> corsaCollection) {
        this.corsaCollection = corsaCollection;
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public Fermata getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(Fermata capolinea) {
        this.capolinea = capolinea;
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
        if (!(object instanceof Orario)) {
            return false;
        }
        Orario other = (Orario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.engim.gtt.database.Orario[ id=" + id + " ]";
    }
    
}
