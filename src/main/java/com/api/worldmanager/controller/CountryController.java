package com.api.worldmanager.controller;

import com.api.worldmanager.model.Country;
import com.api.worldmanager.service.ICountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController
{



    @Autowired
    ICountryServiceImpl countryservice;


    //GET
    @GetMapping("/api/countries")
    //@JsonIgnoreProperties(value = "GovernmentForm")
    public List<Country> getAllCountries()
    {
        return countryservice.getAllCountries();
    }

    @GetMapping("/api/countries/{code}")
    public Optional<Country> getCountryByCountryCode(@PathVariable String code) throws Exception {
        return countryservice.getCountryByCountryCode(code);
    }

    @GetMapping("/api/countries/name/{name}")
    public Optional<Country> getCountryByName(@PathVariable String name) throws Exception {
        return countryservice.getCountryByName(name);
    }

    @GetMapping("/api/countries/populationlessthan/{popnumber}")
    public List<Country> getAllCountriesPopulationLessThan(@PathVariable Integer popnumber) throws Exception {
        return countryservice.getAllCountriesPopulationLessThan(popnumber);
    }

    @GetMapping("/api/countries/populationgreaterthan/{popnumber}")
    public List<Country> getAllCountriesPopulationGreaterThan(@PathVariable Integer popnumber) throws Exception {
        return countryservice.getAllCountriesPopulationGreaterThan(popnumber);
    }

    @GetMapping("/api/countries/surfaceareagreaterthan/{surfacearea}")
    public List<Country> getAllCountriesSurfaceAreaGreaterThan(@PathVariable double surfacearea) throws Exception
    {
        return countryservice.findCountriesBySurfaceAreaGreaterThan(surfacearea);
    }

    @GetMapping("/api/countries/surfacearealessthan/{surfacearea}")
    public List<Country> getAllCountriesSurfaceAreaLessThan(@PathVariable double surfacearea) throws Exception
    {
        return countryservice.findCountriesBySurfaceAreaLessThan(surfacearea);
    }

    @GetMapping("/api/countries/region/{region}")
    public List<Country> getAllCountriesByRegion(@PathVariable String region) throws Exception {
        return countryservice.getAllCountriesByRegion(region);
    }

    @GetMapping("/api/countries/continent/{continent}")
    public List<Country> getAllCountriesByContinent(@PathVariable String continent) throws Exception
    {
        return countryservice.getAllCountriesByContinent(continent);
    }

    @GetMapping("/api/countries/government/{govern}")
    public List<Country> getAllCountriesByGovernmentForm(@PathVariable String govern) throws Exception
    {
        return countryservice.getAllCountriesByGovernmentForm(govern);
    }

    @GetMapping("/api/countries/lifeExpectancylessthan/{yearsoflife}")
    public List<Country> getAllCountriesByLifeExpectancyLessThan(@PathVariable double yearsoflife) throws Exception
    {
        return countryservice.getAllCountriesLifeExpectancyLessThan(yearsoflife);
    }

    @GetMapping("/api/countries/lifeExpectancygreaterthan/{yearsoflife}")
    public List<Country> getAllCountriesByLifeExpectancyGreaterThan(@PathVariable double yearsoflife) throws Exception
    {
        return countryservice.getAllCountriesLifeExpectancyGreaterThan(yearsoflife);
    }

    @GetMapping("/api/countries/headsofstate")
    public List<String> getAllCountriesHeadOfState() throws Exception {
        return countryservice.getAllCountriesHeadOfState();
    }


    //POST
    @PostMapping("/api/countries")
    public void addNewCountry(@RequestBody Country country) throws Exception {
        countryservice.addCountry(country);
    }


    //PUT
    @PutMapping("/api/countries/{code}")
    public void updateCountry(@PathVariable String code, @RequestBody Country country) throws Exception {
        countryservice.updateCountry(code,country);
    }


    //DELETE
    @DeleteMapping("/api/countries/{code}")
    public void deleteCountry(@PathVariable String code) throws Exception {
        countryservice.deleteCountry(code);
    }

    @DeleteMapping("/api/countries/}")
    public void deleteAllCountries()
    {
        countryservice.deleteAllCountries();
    }
}
