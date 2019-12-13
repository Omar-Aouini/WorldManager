package com.api.worldmanager.controller;

import com.api.worldmanager.dto.CityDTO;
import com.api.worldmanager.service.ICityServiceImpl;
import com.api.worldmanager.validation.EntityValidator;

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


    private String linktemplate = "http://localhost:8080/api/cities/";
    
    // GET
    @GetMapping
    public ResponseEntity<?> getAllCities()
    {
        List<CityDTO> list = cityService.getAllCities().parallelStream()
                                        .map(c-> c.add(new Link(linktemplate + c.getId()).withSelfRel(),
                                                       new Link(linktemplate).withRel("cities"),
                                                       new Link(linktemplate + "delete/" + c.getId()).withRel("deletecity"),
                                                       new Link(linktemplate + "update/" + c.getId()).withRel("updatecity")))          
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
    public ResponseEntity<?> getCityByName(@PathVariable String name) throws Exception
    {
        RepresentationModel<CityDTO> city = cityService.getCityByName(name);

        if(city==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(city,HttpStatus.OK);
    }

    @GetMapping("/countrycode/{countrycode}")
    public ResponseEntity<?> getCityByCountryCode(@PathVariable String countrycode) throws Exception
    {
        RepresentationModel<CityDTO> city = cityService.getCityByCountryCode(countrycode);

        if(city==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(city,HttpStatus.OK);
    }

    @GetMapping("/populationlessthan/{popnumber}")
    public ResponseEntity<?> getAllCitiesPopulationLessThan(@PathVariable Integer popnumber) throws Exception
    {
        List<CityDTO> list = cityService.getAllCitiesPopulationLessThan(popnumber).parallelStream()
                                        .map(c-> c.add(new Link(linktemplate + c.getId()).withSelfRel(),
                                                       new Link(linktemplate).withRel("cities")))          
                                        .collect(Collectors.toList());

        if(list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/populationgreaterthan/{popnumber}")
    public ResponseEntity<?> getAllCitiesPopulationGreaterThan(@PathVariable Integer popnumber) throws Exception
    {
        List<CityDTO> list = cityService.getAllCitiesPopulationGreaterThan(popnumber).parallelStream()
                                        .map(c-> c.add(new Link(linktemplate + c.getId()).withSelfRel(),
                                                       new Link(linktemplate).withRel("cities")))          
                                        .collect(Collectors.toList());

        if(list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/district/{district}")
    public ResponseEntity<?> getAllCitiesByDistrict(@PathVariable String district) throws Exception {
       List<CityDTO> list = cityService.getAllCitiesByDistrict(district).parallelStream()
                                        .map(c-> c.add(new Link(linktemplate + c.getId()).withSelfRel(),
                                                       new Link(linktemplate).withRel("cities")))          
                                        .collect(Collectors.toList());

        if(list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    //POST
    @PostMapping("/new")
    public ResponseEntity<?> addNewCity(@RequestBody CityDTO city) throws Exception
    {
        if(city==null || ! EntityValidator.getInstance().isCityValid(city))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        cityService.addCity(city);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //PUT
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCity(@PathVariable Integer id, @RequestBody CityDTO city) throws Exception
    {
                        //not
        if(city==null || !EntityValidator.getInstance().isCityValid(city) || id==null || id <=0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        cityService.addCity(city);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer id) throws Exception
    {
        if(id==null || id <= 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            cityService.deleteCity(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/cities/deleteall")
    public ResponseEntity<Object> deleteAllCities()
    {
        cityService.deleteAllCities();
        List<CityDTO> list = cityService.getAllCities();

        if(list.isEmpty())
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(list,HttpStatus.BAD_REQUEST);
        

    }

}
