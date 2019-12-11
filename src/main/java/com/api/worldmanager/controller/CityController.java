package com.api.worldmanager.controller;

import com.api.worldmanager.model.City;
import com.api.worldmanager.service.ICityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CityController
{

    @Autowired
    ICityServiceImpl cityService;

    //GET
    @GetMapping("/api/cities")
    public List<City> getAllCities()
    {
        return cityService.getAllCities();
    }

    @GetMapping("/api/cities/{id}")
    public Optional<City> getCityById(@PathVariable Integer id) throws Exception {
        return cityService.getCityById(id);
    }

    @GetMapping("/api/cities/name/{name}")
    public Optional<City> getCityByName(@PathVariable String name) throws Exception {
        return cityService.getCityByName(name);
    }

    @GetMapping("/api/cities/countrycode/{countrycode}")
    public Optional<City> getCityByCountryCode(@PathVariable String countrycode) throws Exception {
        return cityService.getCityByCountryCode(countrycode);
    }

    @GetMapping("/api/cities/populationlessthan/{popnumber}")
    public List<City> getAllCitiesPopulationLessThan(@PathVariable Integer popnumber) throws Exception {
        return cityService.getAllCitiesPopulationLessThan(popnumber);
    }

    @GetMapping("/api/cities/populationgreaterthan/{popnumber}")
    public List<City> getAllCitiesPopulationGreaterThan(@PathVariable Integer popnumber) throws Exception {
        return cityService.getAllCitiesPopulationGreaterThan(popnumber);
    }

    @GetMapping("/api/cities/district/{district}")
    public List<City> getAllCitiesByDistrict(@PathVariable String district) throws Exception {
        return cityService.getAllCitiesByDistrict(district);
    }


    //POST
    @PostMapping("/api/cities")
    public void addNewCity(@RequestBody City city) throws Exception {
        cityService.addCity(city);
    }


    //PUT
    @PutMapping("/api/cities/{id}")
    public void updateCity(@PathVariable Integer id, @RequestBody City city) throws Exception {
        cityService.updateCity(id,city);
    }


    //DELETE
    @DeleteMapping("/api/cities/{id}")
    public void deleteCity(@PathVariable Integer id) throws Exception {
        cityService.deleteCity(id);
    }

    @DeleteMapping("/api/cities}")
    public void deleteAllCities()
    {
        cityService.deleteAllCities();
    }

}
