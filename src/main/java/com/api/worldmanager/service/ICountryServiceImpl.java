package com.api.worldmanager.service;

import com.api.worldmanager.model.Country;
import com.api.worldmanager.repository.CountryRepository;
import com.api.worldmanager.validation.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("countryService")
public class ICountryServiceImpl implements ICountryService
{

    @Autowired
    CountryRepository countryRepository;


    private boolean isValidCode(String countrycode)
    {
        return (countrycode != null && !countrycode.equals("") && countrycode.length() == 3) ? true : false;
    }


    @Override
    public List<Country> getAllCountries()
    {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> getCountryByCountryCode(String countrycode) throws Exception {
        if(isValidCode(countrycode))
            return countryRepository.findByCode(countrycode);
        else
            throw new Exception("countrycode not valid or null!");
    }

    @Override
    public void addCountry(Country country) throws Exception {
        if(EntityValidator.getInstance().isCountryValid(country))
            countryRepository.save(country);
    }

    @Override
    public void updateCountry(String countrycode, Country country)
    {
        if(isValidCode(countrycode))
            countryRepository.save(country);
    }

    @Override
    public Optional<Country> getCountryByName(String name) throws Exception
    {
        if(name != null && !name.equals(""))

            return countryRepository.findByName(name);
        else
            throw new Exception("name is not valid or null!");
    }

    @Override
    public List<Country> getAllCountriesPopulationGreaterThan(Integer popnumber) throws Exception {
        if(popnumber != null && popnumber >= 0)
            return countryRepository.findByPopulationGreaterThan(popnumber);
        else
            throw new Exception("popnumber is null or not valid!");
    }

    @Override
    public List<Country> getAllCountriesPopulationLessThan(Integer popnumber) throws Exception {
        if(popnumber != null && popnumber >= 0)
            return countryRepository.findByPopulationLessThan(popnumber);
        else
            throw new Exception("popnumber is null or not valid!");
    }

    @Override
    public List<Country> getAllCountriesByRegion(String region) throws Exception
    {
        if(region != null && !region.equals(""))
            return countryRepository.findByRegion(region);
        else
            throw new Exception("region is null or not valid!");

    }

    @Override
    public void deleteCountry(String code) throws Exception
    {
        if(code != null && !code.equals(""))
            countryRepository.deleteByCode(code);
    }

    @Override
    public void deleteAllCountries()
    {
        countryRepository.deleteAll();
    }

    @Override
    public List<Country> getAllCountriesByContinent(String continent) throws Exception {
        if(continent != null && !continent.equals(""))
            return countryRepository.findByContinent(continent);
        else
            throw new Exception("continent is null or not valid!");
    }

    @Override
    public List<Country> getAllCountriesByGovernmentForm(String govern) throws Exception
    {
        if(govern != null && !govern.equals(""))
            return countryRepository.findByGovernmentForm(govern);
        else
            throw new Exception("government form is null or not valid!");
    }

    @Override
    public List<Country> findCountriesBySurfaceAreaLessThan(double surfacearea) throws Exception {
        if(surfacearea > 0.0)
            return countryRepository.findBySurfaceAreaLessThan(surfacearea);
        else
            throw new Exception("surfacearea negative!");
    }

    @Override
    public List<Country> findCountriesBySurfaceAreaGreaterThan(double surfacearea) throws Exception {
        if(surfacearea > 0.0)
            return countryRepository.findBySurfaceAreaGreaterThan(surfacearea);
        else
            throw new Exception("surfacearea negative!");
    }

    @Override
    public List<Country> getAllCountriesLifeExpectancyLessThan(double yearsoflife) throws Exception {
        if(yearsoflife > 0.0)
            return countryRepository.findByLifeExpectancyLessThan(yearsoflife);
        else
            throw new Exception("years of life number negative!");
    }

    @Override
    public List<Country> getAllCountriesLifeExpectancyGreaterThan(double yearsoflife) throws Exception {
        if(yearsoflife > 0.0)
            return countryRepository.findByLifeExpectancyGreaterThan(yearsoflife);
        else
            throw new Exception("years of life number negative!");
    }


    @Override
    public List<String> getAllCountriesHeadOfState()
    {
        return (countryRepository.findAll()).parallelStream().map(Country::getHeadOfState).collect(Collectors.toList());
    }

}
