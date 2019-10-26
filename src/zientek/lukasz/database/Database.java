package zientek.lukasz.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import zientek.lukasz.model.City;
import zientek.lukasz.model.HighSchool;

/**
 * Responsible for communication with the database
 * @author Lukasz Zientek
 * @version 1.0.0
 */
public class Database 
{
    /**
     * Entity manager is used for communication with the database
     */
    private final EntityManager em;

    /**
     * Constructor of the Database class
     * @param em Entity manager used for communication with the database
     */
    public Database(EntityManager em) 
    {
        this.em = em;
    }
    
    /**
     * Persists an entity in transaction and clears cache
     * @param emf Entiy to persists
     */
    private void create(Object emf)
    {
        em.getTransaction().begin();
        em.persist(emf);
        em.getTransaction().commit();
        em.clear();
    }
    
    /**
     * Merges an entity in transaction and clears cache
     * @param emf Entiy to merge
     */
    private void update(Object emf)
    {
        em.getTransaction().begin();
        em.merge(emf);
        em.getTransaction().commit();
        em.clear();
    }
       
    /**
     * Removes an entity in transaction and clears cache
     * @param emf 
     */
    private void delete(Object emf)
    {
        em.getTransaction().begin();
        
        if(!em.contains(emf))
            emf = em.merge(emf);
        
        em.remove(emf);
        em.getTransaction().commit();
        em.clear();
    }
     
    /**
     * Method reads city with given id
     * @param id of city 
     * @return city with given id
     */
    public City readCity(int id)
    {
        return em.find(City.class, id);
    }
    
    /**
     * Method reads all cities which are stored in the database
     * @return all cities form database 
     */
    public List<City> readAllCities()
    {
        return em.createNamedQuery(City.FIND_ALL).getResultList();
    }
    
    /**
     * Method used to read cities which satisfy given criteria
     * @param name name of the city
     * @param founded foundation of the city
     * @param population population of the city
     * @return list of all cities which satisfy given criteria
     */
    public List<City> readCitiesQuery(String name, Date founded, Long population)
    {
        Root<City> queryRoot;
        CriteriaQuery<City> queryDefinition;
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder builder = em.getCriteriaBuilder();        
        queryDefinition = builder.createQuery(City.class);
        queryRoot = queryDefinition.from(City.class);
        queryDefinition.select(queryRoot);
        
        if (name != null) 
        {
            predicates.add(builder.like(queryRoot.get("name"), name));
        }

        if(population != null) 
        {
            predicates.add(builder.greaterThanOrEqualTo(queryRoot.get("population"), population));
        }
        
        if(founded != null) 
        { 
            predicates.add(builder.greaterThanOrEqualTo(queryRoot.get("founded"), founded));
        }

        if (!predicates.isEmpty()) 
        {
            queryDefinition.where( builder.and(predicates.toArray(new Predicate[predicates.size()])));
        }
        
        return em.createQuery(queryDefinition).getResultList(); 
    }
    
    /**
     * Method reads high school with given id
     * @param id of high school 
     * @return high school with given id
     */
    public HighSchool readHighSchool(int id)
    {
        return em.find(HighSchool.class, id);
    }
    
     /**
     * Method reads all high schools which are stored in the database
     * @return all high schools form database 
     */
    public List<HighSchool> readAllHighSchools()
    {
        return em.createNamedQuery(HighSchool.FIND_ALL).getResultList();
    } 
    
    /**
     * Method used to read high schools which satisfy given criteria
     * @param name name of school
     * @param founded foundation
     * @param passRate pass rate 
     * @param studentsCount number of students
     * @param cityId id of the city to which high school belongs
     * @return high schools which satisfy given criteria
     */
    public List<HighSchool> readHighSchoolsQuery(String name,Date founded, Double passRate, Long studentsCount, Integer cityId)
    {
        Root<HighSchool> queryRoot;
        CriteriaQuery<HighSchool> queryDefinition;
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder builder = em.getCriteriaBuilder();

        queryDefinition = builder.createQuery(HighSchool.class);
        queryRoot = queryDefinition.from(HighSchool.class);
        queryDefinition.select(queryRoot);
        
        if (name != null) 
        {
            predicates.add(builder.like(queryRoot.get("name"), name));
        }

        if(studentsCount != null) 
        {
            predicates.add(builder.greaterThanOrEqualTo(queryRoot.get("studentsCount"), studentsCount));
        }
        
        if(passRate != null) 
        {
            predicates.add(builder.greaterThanOrEqualTo(queryRoot.get("passRate"), passRate));
        }
        
        if(founded != null) 
        { 
            predicates.add(builder.greaterThanOrEqualTo(queryRoot.get("founded"), founded));
        }              

        if(cityId != null) 
        { 
            predicates.add(builder.equal(queryRoot.join("city").get("id"), cityId));
        }

        if (!predicates.isEmpty()) 
        {
            queryDefinition.where( builder.and(predicates.toArray(new Predicate[predicates.size()])));
        }
        
        return em.createQuery(queryDefinition).getResultList(); 
    }
    
    /**
     * Method is used to add city into database
     * @param city City which is going to be added into the database
     */
    public void createCity(City city)
    {    
        this.create(city);
    }
    
    /**
     * Method is used to update existing city in the database
     * @param city City which is going to be updated 
     */
    public void updateCity(City city)
    {
        update(city);     
    }
    
     /**
     * Method is used to delete city form the database
     * @param city City which is going to be deleted
     */
    public void deleteCity(City city)
    {
        delete(city);
    }
    
    /**
     * Method is used to add high school into database
     * @param highSchool High school which is going to be added
     */
    public void createHighSchool(HighSchool highSchool)
    {    
        create(highSchool);
    }
    
    /**
     * Method is used to update existing high school in the database
     * @param highSchool High school which is going to be updated
     */
    public void updateHighSchool(HighSchool highSchool)
    {
        update(highSchool);     
    }
    
    /**
     * Method is used to delete high school form the database
     * @param highSchool High school which is going to be deleted
     */
    public void deleteHighSchool(HighSchool highSchool)
    {
        delete(highSchool);
    }
    
    /**
     * Method clears resources and releases them
     */
    public void clear()
    {
        em.clear();
    }
    
}
