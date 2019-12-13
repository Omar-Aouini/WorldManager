package com.api.worldmanager.controller;

import com.api.worldmanager.dto.CityDTO;
import com.api.worldmanager.service.ICityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.*;
import java.util.List;
import java.util.stream.Collectors;

/*
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
*/
@RequestMapping("api/cities")
@RestController
public class CityController {

    @Autowired
    ICityServiceImpl cityService;

    String linktemplate = "http://localhost:8080/api/cities/";
    
    // GET
    @GetMapping
    public ResponseEntity<?> getAllCities()
    {
        List<CityDTO> list = cityService.getAllCities().parallelStream()
                                        .map(c-> c.add(new Link(linktemplate + c.getId()).withSelfRel(),
                                                       new Link(linktemplate).withRel("cities")))          
                                        .collect(Collectors.toList());
        if(list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<?> getCityById(@PathVariable Integer id) throws Exception
    {
        RepresentationModel<CityDTO> city = cityService.getCityById(id);

        if(city==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        city.add(new Link(linktemplate + id).withSelfRel());
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public CityDTO getCityByName(@PathVariable String name) throws Exception {
        return cityService.getCityByName(name);
    }

    @GetMapping("/countrycode/{countrycode}")
    public CityDTO getCityByCountryCode(@PathVariable String countrycode) throws Exception {
        return cityService.getCityByCountryCode(countrycode);
    }

    @GetMapping("/populationlessthan/{popnumber}")
    public List<CityDTO> getAllCitiesPopulationLessThan(@PathVariable Integer popnumber) throws Exception {
        return cityService.getAllCitiesPopulationLessThan(popnumber);
    }

    @GetMapping("/populationgreaterthan/{popnumber}")
    public List<CityDTO> getAllCitiesPopulationGreaterThan(@PathVariable Integer popnumber) throws Exception {
        return cityService.getAllCitiesPopulationGreaterThan(popnumber);
    }

    @GetMapping("/district/{district}")
    public List<CityDTO> getAllCitiesByDistrict(@PathVariable String district) throws Exception {
        return cityService.getAllCitiesByDistrict(district);
    }


    //POST
    @PostMapping("/new")
    public void addNewCity(@RequestBody CityDTO city) throws Exception {
        cityService.addCity(city);
    }


    //PUT
    @PutMapping("/{id}")
    public void updateCity(@PathVariable Integer id, @RequestBody CityDTO city) throws Exception {
        cityService.updateCity(id,city);
    }


    //DELETE
    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Integer id) throws Exception {
        cityService.deleteCity(id);
    }

    @DeleteMapping("/api/cities")
    public void deleteAllCities()
    {
        cityService.deleteAllCities();
    }

}
