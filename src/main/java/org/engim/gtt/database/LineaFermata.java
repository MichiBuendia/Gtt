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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author svilupposw
 */
@Entity
@Table(name = "linea_fermata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineaFermata.findAll", query = "SELECT l FROM LineaFermata l")
    , @NamedQuery(name = "LineaFermata.findById", query = "SELECT l FROM LineaFermata l WHERE l.id = :id")
    , @NamedQuery(name = "LineaFermata.findByFermata", query = "SELECT l FROM LineaFermata l WHERE l.fermata = :fermata")
    , @NamedQuery(name = "LineaFermata.findByCapolinea", query = "SELECT l FROM LineaFermata l WHERE l.capolinea = :capolinea")})
public class LineaFermata implements Serializable,
                                ChiavePrimaria  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "capolinea")
    private Boolean capolinea;
    @JoinColumn(name = "linea", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Linea linea;
    @JoinColumn(name = "fermata", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fermata fermata;

    public LineaFermata() {
    }

    public LineaFermata(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(Boolean capolinea) {
        this.capolinea = capolinea;
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public Fermata getFermata() {
        return fermata;
    }

    public void setFermata(Fermata fermata) {
        this.fermata = fermata;
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
        if (!(object instanceof LineaFermata)) {
            return false;
        }
        LineaFermata other = (LineaFermata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.engim.gtt.database.LineaFermata[ id=" + id + " ]";
    }
    
}
