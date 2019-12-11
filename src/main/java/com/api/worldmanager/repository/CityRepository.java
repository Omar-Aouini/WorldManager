package com.api.worldmanager.repository;

import com.api.worldmanager.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CityRepository extends JpaRepository<City, Integer>
{
    @Query(value = "SELECT * FROM city where CountryCode = ?1", nativeQuery = true)
    Optional<City> findByCountryCode(String countrycode);
    Optional<City> findByName(String name);
    List<City> findByPopulationLessThan(Integer popnumber);
    List<City> findByPopulationGreaterThan(Integer popnumber);
    List<City> findByDistrict(String district);
}
