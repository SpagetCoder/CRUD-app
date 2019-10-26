package zientek.lukasz.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * City entity
 * @author Lukasz Zientek
 * @version 1.0.0
 */
@Entity
@Table(name="city")
@NamedQueries({@NamedQuery(name = City.FIND_ALL, query = "SELECT c FROM City c")})
public class City implements Serializable
{
    /**
     * Key for query used to find all cities
     */
    public static final String FIND_ALL = "City.findAll";
   
    /**
     * Unique id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Name of the city
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
     * Population of the city
     */
    @Column(name = "population")
    private Long population;
    
    /**
     * High schools which belong to this city
     */
    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<HighSchool> highSchool;

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

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public List<HighSchool> getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(List<HighSchool> highSchool) {
        this.highSchool = highSchool;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final City other = (City) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() 
    {
        StringBuilder result = new StringBuilder("City{").append("id=").append(id).append(", name=").append(name).append(", founded=")
                .append(founded).append(", population=").append(population).append(", highschool=").append("\n").append(highSchool).append("}").append("\n");
        
       return result.toString();
    }
   
}
