package com.api.worldmanager.service;

import com.api.worldmanager.dto.CityDTO;
import com.api.worldmanager.model.City;
import com.api.worldmanager.repository.CityRepository;
import com.api.worldmanager.validation.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;


@Service("cityService")
public class ICityServiceImpl implements ICityService
{
    @Autowired
    CityRepository cityRepository;

    @Autowired
    ModelMapper modelMapper;


    private boolean isValidId(Integer id)
    {
        return (id != null && !id.equals("") && id > 0) ? true : false;
    }


    @Override
    public List<CityDTO> getAllCities()
    {
        return cityRepository.findAll()
                                .parallelStream()
                                .map(city -> modelMapper.map(city, CityDTO.class))
                                .collect(Collectors.toList());
    }

    @Override
    public CityDTO getCityById(Integer id) throws Exception, EntityNotFoundException
    {
        if(isValidId(id))
        {
            try
            {
                return cityRepository.findById(id)
                                        .map(c -> modelMapper.map(c, CityDTO.class))
                                        .get();
            }
            catch(EntityNotFoundException e)
            {
                throw new EntityNotFoundException("entity by id " + id + " not found!");
            }
        }
        else
            throw new Exception("id " + id + " is not valid!");
    }

    @Override
    public CityDTO getCityByName(String name) throws Exception, EntityNotFoundException {
        if(name != null && !name.equals(""))
        {
            try
            {
                return cityRepository.findByName(name)
                                        .map(c -> modelMapper.map(c, CityDTO.class))
                                        .get();
            }
            catch(EntityNotFoundException e)
            {
                throw new EntityNotFoundException("entity by name " + name + " not found!");
            }
        }    
        else
            throw new Exception("name is not valid or null!");
    }

    @Override
    public void addCity(CityDTO cityDto) throws Exception
    {
        if(EntityValidator.getInstance().isCityValid(cityDto))
        {
            City city = modelMapper.map(cityDto,City.class);
            cityRepository.save(city);
        }
        else
            throw new Exception("entity not valid for saving! " + cityDto.toString());

    }

    @Override
    public void updateCity(Integer id, CityDTO cityDto) throws Exception
    {
        if(isValidId(id))
        {
            if(EntityValidator.getInstance().isCityValid(cityDto))
            {
                City city = modelMapper.map(cityDto,City.class);
                cityRepository.save(city);
            }
            else
                throw new Exception("entity not valid for saving! " + cityDto.toString());
        }
        else
            throw new Exception("id " + id + " is not valid!");
    }

    @Override
    public CityDTO getCityByCountryCode(String countrycode) throws Exception, EntityNotFoundException
    {
        if(countrycode != null && !countrycode.equals(""))
        {
            try
            {
                return cityRepository.findByCountryCode(countrycode)
                                        .map(c -> modelMapper.map(c, CityDTO.class))
                                        .get();
            }
            catch(EntityNotFoundException e)
            {
                throw new EntityNotFoundException("entity by countrycode " + countrycode + " not found!");        
            }
        }
        else
            throw new Exception("countrycode " + countrycode + " not valid or null!");
    }

    @Override
    public List<CityDTO> getAllCitiesPopulationLessThan(Integer popnumber) throws Exception {
        if(popnumber != null && popnumber >= 0)
        {
           return cityRepository.findByPopulationGreaterThan(popnumber)
                                    .parallelStream()
                                    .map(city -> modelMapper.map(city, CityDTO.class))
                                    .collect(Collectors.toList());
        }
        else
            throw new Exception("popnumber + " + popnumber + " is null or not valid!");
    }

    @Override
    public List<CityDTO> getAllCitiesPopulationGreaterThan(Integer popnumber) throws Exception {
        if(popnumber != null && popnumber >= 0)
        {
            return cityRepository.findByPopulationGreaterThan(popnumber)
                                    .parallelStream()
                                    .map(city -> modelMapper.map(city, CityDTO.class))
                                    .collect(Collectors.toList());

        }
        else
            throw new Exception("popnumber is null or not valid!");

    }

    @Override
    public List<CityDTO> getAllCitiesByDistrict(String district) throws Exception
    {
        if(district != null && !district.equals(""))
        {
            try
            {
                return cityRepository.findByDistrict(district)
                                    .parallelStream()
                                    .map(city -> modelMapper.map(city, CityDTO.class))
                                    .collect(Collectors.toList());
            }
            catch(EntityNotFoundException e)
            {
                throw new EntityNotFoundException("entity by district " + district + " not found!");        
            }
        }
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
