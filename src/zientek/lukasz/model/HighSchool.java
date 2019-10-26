/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zientek.lukasz.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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

/**
 * High school entity
 * @author Lukasz Zientek
 * @version 1.0.0
 */
@Entity
@Table(name = "high_school")
@NamedQueries({@NamedQuery(name = HighSchool.FIND_ALL, query = "SELECT hs FROM HighSchool hs")})
public class HighSchool implements Serializable
{  
    /**
     * Key for query used to find all high schools
     */
    public static final String FIND_ALL= "HighSchool.findAll";
    
    /**
     * Unique id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Name of the school
     */
    @Column(name = "name")
    private String name;
       
    /**
     * Foundation year
     */
    @Column(name = "foundation_year")
    @Temporal(TemporalType.DATE)
    private Date founded;
    
    /**
     * Pass rate of final exam
     */
    @Column(name = "pass_rate")
    private Double passRate;
    
    /**
     * Number of students
     */
    @Column(name = "number_of_students")
    private Long studentsCount;
    
    /**
     * City to which high school belongs to
     */
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFounded() {
        return founded;
    }

    public void setFounded(Date founded) {
        this.founded = founded;
    }

    public Double getPassRate() {
        return passRate;
    }

    public void setPassRate(Double passRate) {
        this.passRate = passRate;
    }

    public Long getStutendsCount() {
        return studentsCount;
    }

    public void setStutendsCount(Long stutendsCount) {
        this.studentsCount = stutendsCount;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HighSchool other = (HighSchool) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() 
    {
        StringBuilder result = new StringBuilder("HighSchool{").append("id=").append(id).append(", name=").append(name).append(", founded=")
                .append(founded).append(", passRate=").append(passRate).append(", stutendsCount=").append(studentsCount).append(", cityId=")
                .append(city == null ? null : city.getId()).append("}").append("\n");
        
       return result.toString();
        
    }
    
}
