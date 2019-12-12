package com.api.worldmanager.service;

import com.api.worldmanager.dto.CityDTO;
import java.util.List;
import javax.persistence.EntityNotFoundException;

public interface ICityService
{
    List<CityDTO> getAllCities();
    CityDTO getCityByName(String name) throws Exception, EntityNotFoundException;
    CityDTO getCityById(Integer id) throws Exception, EntityNotFoundException;
    void addCity(CityDTO city) throws Exception;
    void updateCity(Integer id, CityDTO city) throws Exception;
    CityDTO getCityByCountryCode(String countrycode) throws Exception, EntityNotFoundException;
    List<CityDTO> getAllCitiesPopulationLessThan(Integer popnumber) throws Exception;
    List<CityDTO> getAllCitiesPopulationGreaterThan(Integer popnumber) throws Exception;
    List<CityDTO> getAllCitiesByDistrict(String district) throws Exception;
    void deleteCity(Integer id) throws Exception;
    void deleteAllCities();
}
