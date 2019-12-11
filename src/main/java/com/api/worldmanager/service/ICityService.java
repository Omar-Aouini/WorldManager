package com.api.worldmanager.service;

import com.api.worldmanager.model.City;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ICityService
{
    List<City> getAllCities();
    Optional<City> getCityById(Integer id) throws Exception;
    void addCity(City city) throws Exception;
    void updateCity(Integer id, City city);Optional<City> getCityByName(String name) throws Exception;
    Optional<City> getCityByCountryCode(String countrycode) throws Exception;
    List<City> getAllCitiesPopulationLessThan(Integer popnumber) throws Exception;
    List<City> getAllCitiesPopulationGreaterThan(Integer popnumber) throws Exception;
    List<City> getAllCitiesByDistrict(String district) throws Exception;
    void deleteCity(Integer id) throws Exception;
    void deleteAllCities();
}
