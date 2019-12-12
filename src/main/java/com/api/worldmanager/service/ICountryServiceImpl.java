package com.api.worldmanager.service;

import com.api.worldmanager.dto.CountryDTO;
import com.api.worldmanager.model.Country;
import com.api.worldmanager.repository.CountryRepository;
import com.api.worldmanager.validation.EntityValidator;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Service("countryService")
public class ICountryServiceImpl implements ICountryService {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    ModelMapper modelMapper;

    private boolean isValidCode(String countrycode) {
        return (countrycode != null && !countrycode.equals("") && countrycode.length() == 3) ? true : false;
    }

    @Override
    public List<CountryDTO> getAllCountries() {
        List<Country> list = countryRepository.findAll();
        List<CountryDTO> listCountryDto = new ArrayList<>();
        for (Country c : list) {
            CountryDTO countrydto = modelMapper.map(c, CountryDTO.class);
            listCountryDto.add(countrydto);
        }
        return listCountryDto;
    }

    @Override
    public CountryDTO getCountryByCountryCode(String countrycode) throws Exception, EntityNotFoundException {
        if (isValidCode(countrycode)) {
            try {
                Optional<Country> country = countryRepository.findByCode(countrycode);
                return modelMapper.map(country.get(), CountryDTO.class);
            } catch (EntityNotFoundException e) {
                throw new EntityNotFoundException("entity by countrycode " + countrycode + " not found!");
            }
        } else
            throw new Exception("countrycode not valid or null!");
    }

    @Override
    public void addCountry(CountryDTO countryDto) throws Exception {
        if (EntityValidator.getInstance().isCountryValid(countryDto)) {
            Country country = modelMapper.map(countryDto, Country.class);
            countryRepository.save(country);
        } else
            throw new Exception("entity not valid for saving! " + countryDto.toString());
    }

    @Override
    public void updateCountry(String countrycode, CountryDTO countryDto) throws Exception {
        if (isValidCode(countrycode)) {
            if (EntityValidator.getInstance().isCountryValid(countryDto)) {
                Country country = modelMapper.map(countryDto, Country.class);
                countryRepository.save(country);
            } else
                throw new Exception("entity not valid for saving! " + countryDto.toString());
        } else
            throw new Exception("countrycode " + countrycode + " is not valid!");
    }

    @Override
    public CountryDTO getCountryByName(String name) throws Exception {
        if (name != null && !name.equals("")) {
            try {
                Optional<Country> country = countryRepository.findByName(name);
                return modelMapper.map(country.get(), CountryDTO.class);
            } catch (EntityNotFoundException e) {
                throw new EntityNotFoundException("entity by name " + name + " not found!");
            }
        } else
            throw new Exception("name is not valid or null!");
    }

    @Override
    public List<CountryDTO> getAllCountriesPopulationGreaterThan(Integer popnumber) throws Exception {
        if (popnumber != null && popnumber >= 0) {
            List<Country> list = countryRepository.findByPopulationGreaterThan(popnumber);
            List<CountryDTO> listDto = new ArrayList<>();
            for (Country c : list) {
                CountryDTO countryDto = modelMapper.map(c, CountryDTO.class);
                listDto.add(countryDto);
            }
            return listDto;
        } else
            throw new Exception("popnumber is null or not valid!");
    }

    @Override
    public List<CountryDTO> getAllCountriesPopulationLessThan(Integer popnumber) throws Exception {
        if (popnumber != null && popnumber >= 0) {
            List<Country> list = countryRepository.findByPopulationLessThan(popnumber);
            List<CountryDTO> listDto = new ArrayList<>();
            for (Country c : list) {
                CountryDTO countryDto = modelMapper.map(c, CountryDTO.class);
                listDto.add(countryDto);
            }
            return listDto;
        } else
            throw new Exception("popnumber is null or not valid!");
    }

    @Override
    public List<CountryDTO> getAllCountriesByRegion(String region) throws Exception {
        if (region != null && !region.equals("")) {
            List<Country> list = countryRepository.findByRegion(region);
            List<CountryDTO> listDto = new ArrayList<>();
            for (Country c : list) {
                CountryDTO countryDto = modelMapper.map(c, CountryDTO.class);
                listDto.add(countryDto);
            }
            return listDto;
        } else
            throw new Exception("region is null or not valid!");

    }

    @Override
    public void deleteCountry(String code) throws Exception {
        if (code != null && !code.equals(""))
            countryRepository.deleteByCode(code);
    }

    @Override
    public void deleteAllCountries() {
        countryRepository.deleteAll();
    }

    @Override
    public List<CountryDTO> getAllCountriesByContinent(String continent) throws Exception {
        if (continent != null && !continent.equals("")) {
            List<Country> list = countryRepository.findByContinent(continent);
            List<CountryDTO> listDto = new ArrayList<>();
            for (Country c : list) {
                CountryDTO countryDto = modelMapper.map(c, CountryDTO.class);
                listDto.add(countryDto);
            }
            return listDto;
        } else
            throw new Exception("continent is null or not valid!");
    }

    @Override
    public List<CountryDTO> getAllCountriesByGovernmentForm(String govern) throws Exception {
        if (govern != null && !govern.equals("")) {
            List<Country> list = countryRepository.findByGovernmentForm(govern);
            List<CountryDTO> listDto = new ArrayList<>();
            for (Country c : list) {
                CountryDTO countryDto = modelMapper.map(c, CountryDTO.class);
                listDto.add(countryDto);
            }
            return listDto;
        } else
            throw new Exception("government form is null or not valid!");
    }

    @Override
    public List<CountryDTO> findCountriesBySurfaceAreaLessThan(double surfacearea) throws Exception {
        if (surfacearea > 0.0) {
            List<Country> list = countryRepository.findBySurfaceAreaLessThan(surfacearea);
            List<CountryDTO> listDto = new ArrayList<>();
            for (Country c : list) {
                CountryDTO countryDto = modelMapper.map(c, CountryDTO.class);
                listDto.add(countryDto);
            }
            return listDto;
        } else
            throw new Exception("surfacearea negative!");
    }

    @Override
    public List<CountryDTO> findCountriesBySurfaceAreaGreaterThan(double surfacearea) throws Exception {
        if (surfacearea > 0.0) {
            List<Country> list = countryRepository.findBySurfaceAreaGreaterThan(surfacearea);
            List<CountryDTO> listDto = new ArrayList<>();
            for (Country c : list) {
                CountryDTO countryDto = modelMapper.map(c, CountryDTO.class);
                listDto.add(countryDto);
            }
            return listDto;
        } else
            throw new Exception("surfacearea negative!");
    }

    @Override
    public List<CountryDTO> getAllCountriesLifeExpectancyLessThan(double yearsoflife) throws Exception {
        if (yearsoflife > 0.0) {
            List<Country> list = countryRepository.findByLifeExpectancyLessThan(yearsoflife);
            List<CountryDTO> listDto = new ArrayList<>();
            for (Country c : list) {
                CountryDTO countryDto = modelMapper.map(c, CountryDTO.class);
                listDto.add(countryDto);
            }
            return listDto;
        } else
            throw new Exception("years of life number negative!");
    }

    @Override
    public List<CountryDTO> getAllCountriesLifeExpectancyGreaterThan(double yearsoflife) throws Exception {
        if (yearsoflife > 0.0) {
            List<Country> list = countryRepository.findByLifeExpectancyGreaterThan(yearsoflife);
            List<CountryDTO> listDto = new ArrayList<>();
            for (Country c : list) {
                CountryDTO countryDto = modelMapper.map(c, CountryDTO.class);
                listDto.add(countryDto);
            }
            return listDto;
        } else
            throw new Exception("years of life number negative!");
    }

    @Override
    public List<String> getAllCountriesHeadOfState()
    {
        List<Country> list = countryRepository.findAll();
        return  list.parallelStream()
                    .filter(c-> !c.getHeadOfState().equals("") && c.getHeadOfState() != null)
                    .map(c->c.getHeadOfState())
                    .collect(Collectors.toList());
    }

}
