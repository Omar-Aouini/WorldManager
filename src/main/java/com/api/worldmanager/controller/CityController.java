package com.api.worldmanager.controller;

import com.api.worldmanager.dto.CityDTO;
import com.api.worldmanager.service.ICityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
@RequestMapping("api/cities")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
*/
@RestController
public class CityController
{

    @Autowired
    ICityServiceImpl cityService;

    //GET
    @GetMapping("/api/cities")
    public List<CityDTO> getAllCities()
    {
        return cityService.getAllCities();
    }

    @GetMapping("/api/cities/{id}")
    public CityDTO getCityById(@PathVariable Integer id) throws Exception {
        return cityService.getCityById(id);
    }

    @GetMapping("/api/cities/name/{name}")
    public CityDTO getCityByName(@PathVariable String name) throws Exception {
        return cityService.getCityByName(name);
    }

    @GetMapping("/api/cities/countrycode/{countrycode}")
    public CityDTO getCityByCountryCode(@PathVariable String countrycode) throws Exception {
        return cityService.getCityByCountryCode(countrycode);
    }

    @GetMapping("/api/cities/populationlessthan/{popnumber}")
    public List<CityDTO> getAllCitiesPopulationLessThan(@PathVariable Integer popnumber) throws Exception {
        return cityService.getAllCitiesPopulationLessThan(popnumber);
    }

    @GetMapping("/api/cities/populationgreaterthan/{popnumber}")
    public List<CityDTO> getAllCitiesPopulationGreaterThan(@PathVariable Integer popnumber) throws Exception {
        return cityService.getAllCitiesPopulationGreaterThan(popnumber);
    }

    @GetMapping("/api/cities/district/{district}")
    public List<CityDTO> getAllCitiesByDistrict(@PathVariable String district) throws Exception {
        return cityService.getAllCitiesByDistrict(district);
    }


    //POST
    @PostMapping("/api/cities")
    public void addNewCity(@RequestBody CityDTO city) throws Exception {
        cityService.addCity(city);
    }


    //PUT
    @PutMapping("/api/cities/{id}")
    public void updateCity(@PathVariable Integer id, @RequestBody CityDTO city) throws Exception {
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
