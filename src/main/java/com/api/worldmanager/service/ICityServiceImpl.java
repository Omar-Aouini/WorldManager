package com.api.worldmanager.service;

import com.api.worldmanager.model.City;
import com.api.worldmanager.repository.CityRepository;
import com.api.worldmanager.validation.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service("cityService")
public class ICityServiceImpl implements ICityService
{
    @Autowired
    CityRepository cityRepository;


    private boolean isValidId(Integer id)
    {
        return (id != null && !id.equals("") && id > 0) ? true : false;
    }


    @Override
    public List<City> getAllCities()
    {
        List<City> list = new ArrayList<>();
        cityRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Optional<City> getCityById(Integer id) throws Exception {
        if(isValidId(id))
            return cityRepository.findById(id);
        else
            throw new Exception("not valid id!");
    }

    @Override
    public Optional<City> getCityByName(String name) throws Exception {
        if(name != null && !name.equals(""))
            return cityRepository.findByName(name);
        else
            throw new Exception("name is not valid or null!");
    }

    @Override
    public void addCity(City city) throws Exception {
        if(EntityValidator.getInstance().isCityValid(city))
            cityRepository.save(city);

    }

    @Override
    public void updateCity(Integer id, City city)
    {
        if(isValidId(id))
            cityRepository.save(city);
    }

    @Override
    public Optional<City> getCityByCountryCode(String countrycode) throws Exception {
        if(countrycode != null && !countrycode.equals(""))
            return cityRepository.findByCountryCode(countrycode);
        else
            throw new Exception("countrycode not valid or null!");
    }

    @Override
    public List<City> getAllCitiesPopulationLessThan(Integer popnumber) throws Exception {
        if(popnumber != null && popnumber >= 0)
           return cityRepository.findByPopulationLessThan(popnumber);
        else
            throw new Exception("popnumber is null or not valid!");
    }

    @Override
    public List<City> getAllCitiesPopulationGreaterThan(Integer popnumber) throws Exception {
        if(popnumber != null && popnumber >= 0)
            return cityRepository.findByPopulationGreaterThan(popnumber);
        else
            throw new Exception("popnumber is null or not valid!");

    }

    @Override
    public List<City> getAllCitiesByDistrict(String district) throws Exception
    {
        if(district != null && !district.equals(""))
            return cityRepository.findByDistrict(district);
        else
            throw new Exception("district is not valid or null!");
    }

    @Override
    public void deleteCity(Integer id) throws Exception {
        if(isValidId(id))
            cityRepository.deleteById(id);
        else
            throw new Exception("id is null, can't delete!");
    }

    @Override
    public void deleteAllCities()
    {
        cityRepository.deleteAll();
    }
}
