package com.api.worldmanager.repository;

import com.api.worldmanager.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,String>
{
    Optional<Country> findByCode(String code);
    Optional<Country> findByName(String name);
    List<Country> findByPopulationLessThan(Integer popnumber);
    List<Country> findByPopulationGreaterThan(Integer popnumber);
    List<Country> findByRegion(String region);
    void deleteByCode(String code);
    List<Country> findByContinent(String continent);
    List<Country> findByGovernmentForm(String governmentform);
    List<Country> findBySurfaceAreaLessThan(double surfacearea);
    List<Country> findBySurfaceAreaGreaterThan(double surfaceArea);
    List<Country> findByLifeExpectancyLessThan(double yearsoflife);
    List<Country> findByLifeExpectancyGreaterThan(double yearsoflife);
}