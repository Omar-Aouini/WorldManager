package com.api.worldmanager.service;

import com.api.worldmanager.dto.CountryDTO;
import java.util.List;

import javax.persistence.EntityNotFoundException;

public interface ICountryService
{
    List<CountryDTO> getAllCountries();
    CountryDTO getCountryByCountryCode(String countrycode) throws Exception, EntityNotFoundException;
    void addCountry(CountryDTO country) throws Exception;
    void updateCountry(String code, CountryDTO country) throws Exception;
    CountryDTO getCountryByName(String name) throws Exception;
    List<CountryDTO> getAllCountriesPopulationLessThan(Integer popnumber) throws Exception;
    List<CountryDTO> getAllCountriesPopulationGreaterThan(Integer popnumber) throws Exception;
    List<CountryDTO> getAllCountriesByRegion(String region) throws Exception;
    void deleteCountry(String code) throws Exception;
    void deleteAllCountries();
    List<CountryDTO> getAllCountriesByContinent(String continent) throws Exception;
    List<CountryDTO> getAllCountriesByGovernmentForm(String govern) throws Exception;
    List<CountryDTO> findCountriesBySurfaceAreaLessThan(double surfacearea) throws Exception;
    List<CountryDTO> findCountriesBySurfaceAreaGreaterThan(double surfacearea) throws Exception;
    List<CountryDTO> getAllCountriesLifeExpectancyLessThan(double yearsoflife) throws Exception;
    List<CountryDTO> getAllCountriesLifeExpectancyGreaterThan(double yearsoflife) throws Exception;
    List<String> getAllCountriesHeadOfState();
}
