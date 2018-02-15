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
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author svilupposw
 */
@Entity
@Table(name = "corsa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Corsa.findAll", query = "SELECT c FROM Corsa c")
    , @NamedQuery(name = "Corsa.findById", query = "SELECT c FROM Corsa c WHERE c.id = :id")
    , @NamedQuery(name = "Corsa.findByData", query = "SELECT c FROM Corsa c WHERE c.data = :data")})
public class Corsa implements Serializable,
                                ChiavePrimaria {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "mezzo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Mezzo mezzo;
    @JoinColumn(name = "orario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Orario orario;

    public Corsa() {
    }

    public Corsa(Integer id) {
        this.id = id;
    }

    public Corsa(Integer id, Date data) {
        this.id = id;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public Orario getOrario() {
        return orario;
    }

    public void setOrario(Orario orario) {
        this.orario = orario;
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
        if (!(object instanceof Corsa)) {
            return false;
        }
        Corsa other = (Corsa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.engim.gtt.database.Corsa[ id=" + id + " ]";
    }
    
}
