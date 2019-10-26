/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Luke
 */
public class Database 
{
    private final EntityManager em;

    public Database(EntityManager em) 
    {
        this.em = em;
    }
    
    private void create(Object emf)
    {
        em.getTransaction().begin();
        em.persist(emf);
        em.getTransaction().commit();
        em.clear();
    }
    
    private void update(Object emf)
    {
        em.getTransaction().begin();
        em.merge(emf);
        em.getTransaction().commit();
        em.clear();
    }
       
    private void delete(Object emf)
    {
        em.getTransaction().begin();
        
        if(!em.contains(emf))
            emf = em.merge(emf);
        
        em.remove(emf);
        em.getTransaction().commit();
        em.clear();
    }
     
    public City readCity(int id)
    {
        return em.find(City.class, id);
    }
    
    public List<City> readAllCities()
    {
        return em.createNamedQuery(City.FIND_ALL).getResultList();
    }
    
    public List<City> readCitiesQuery(String name,Date founded,Long population)
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
    
    public HighSchool readHighSchool(int id)
    {
        return em.find(HighSchool.class, id);
    }
    
    public List<HighSchool> readAllHighSchools()
    {
        return em.createNamedQuery(HighSchool.FIND_ALL).getResultList();
    } 
    
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
            
    public void createCity(City city)
    {    
        this.create(city);
    }
    
    public void updateCity(City city)
    {
        update(city);     
    }
    
    public void deleteCity(City city)
    {
        delete(city);
    }
    
    public void createHighSchool(HighSchool highSchool)
    {    
        create(highSchool);
    }
    
    public void updateHighSchool(HighSchool highSchool)
    {
        update(highSchool);     
    }
    
    public void deleteHighSchool(HighSchool highSchool)
    {
        delete(highSchool);
    }
    
    public void clear()
    {
        em.clear();
    }
    
}
