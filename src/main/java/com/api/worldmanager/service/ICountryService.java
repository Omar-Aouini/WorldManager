package com.api.worldmanager.service;

import com.api.worldmanager.model.Country;

import java.util.List;
import java.util.Optional;

public interface ICountryService
{
    List<Country> getAllCountries();
    Optional<Country> getCountryByCountryCode(String countrycode) throws Exception;
    void addCountry(Country country) throws Exception;
    void updateCountry(String code, Country country);
    Optional<Country> getCountryByName(String name) throws Exception;
    List<Country> getAllCountriesPopulationLessThan(Integer popnumber) throws Exception;
    List<Country> getAllCountriesPopulationGreaterThan(Integer popnumber) throws Exception;
    List<Country> getAllCountriesByRegion(String region) throws Exception;
    void deleteCountry(String code) throws Exception;
    void deleteAllCountries();
    List<Country> getAllCountriesByContinent(String continent) throws Exception;
    List<Country> getAllCountriesByGovernmentForm(String govern) throws Exception;
    List<Country> findCountriesBySurfaceAreaLessThan(double surfacearea) throws Exception;
    List<Country> findCountriesBySurfaceAreaGreaterThan(double surfacearea) throws Exception;
    List<Country> getAllCountriesLifeExpectancyLessThan(double yearsoflife) throws Exception;
    List<Country> getAllCountriesLifeExpectancyGreaterThan(double yearsoflife) throws Exception;
    List<String> getAllCountriesHeadOfState();
}
