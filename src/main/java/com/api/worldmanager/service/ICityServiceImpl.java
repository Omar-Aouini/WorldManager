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
        List<City> list = cityRepository.findAll();
        List<CityDTO> listCityDto = new ArrayList<>();
        for (City c : list)
        {
            CityDTO citydto = modelMapper.map(c, CityDTO.class);
            listCityDto.add(citydto);
        }
        return listCityDto;
    }

    @Override
    public CityDTO getCityById(Integer id) throws Exception, EntityNotFoundException
    {
        if(isValidId(id))
        {
            try
            {
                Optional<City> city = cityRepository.findById(id);
                return modelMapper.map(city.get(), CityDTO.class);
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
            Optional<City> city = cityRepository.findByName(name);
            return modelMapper.map(city.get(), CityDTO.class);
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
            Optional<City> city = cityRepository.findByCountryCode(countrycode);
            return modelMapper.map(city.get(), CityDTO.class);
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
           List<City> list = cityRepository.findByPopulationLessThan(popnumber);
           List<CityDTO> listDto = new ArrayList<>();
           for (City c : list)
           {
               CityDTO cityDto = modelMapper.map(c, CityDTO.class);
               listDto.add(cityDto);
           }
           return listDto;
        }
        else
            throw new Exception("popnumber + " + popnumber + " is null or not valid!");
    }

    @Override
    public List<CityDTO> getAllCitiesPopulationGreaterThan(Integer popnumber) throws Exception {
        if(popnumber != null && popnumber >= 0)
        {
           List<City> list = cityRepository.findByPopulationGreaterThan(popnumber);
           List<CityDTO> listDto = new ArrayList<>();
           for (City c : list)
           {
               CityDTO cityDto = modelMapper.map(c, CityDTO.class);
               listDto.add(cityDto);
           }
           return listDto;

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
                List<City> list = cityRepository.findByDistrict(district);
                List<CityDTO> listDto = new ArrayList<>(); 
                for (City c : list)
                {
                    CityDTO cityDto = modelMapper.map(c, CityDTO.class);
                    listDto.add(cityDto);
                }
                return listDto;
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
