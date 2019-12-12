package com.api.worldmanager.validation;

import com.api.worldmanager.dto.CityDTO;
import com.api.worldmanager.dto.CountryDTO;

//entity validation class, singleton utility class
public class EntityValidator
{

    private static EntityValidator instance = null;


    private EntityValidator()
    {
    }


    public static EntityValidator getInstance()
    {
        if (instance == null)
            instance = new EntityValidator();
        return instance;
    }


    public boolean isCityValid(CityDTO city) throws Exception {
        if(city != null)
            return  (city.getId() > 0) &&
                (city.getName() != null && !city.getName().equals("")) &&
                (city.getDistrict() != null && !city.getDistrict().equals("")) &&
                (city.getPopulation() >= 0);
        else
            throw new Exception("City" + city + " entity not valid!");
    }


    public boolean isCountryValid(CountryDTO countryDto) throws Exception
    {
        if(countryDto != null)
            return (countryDto.getCode().length() > 0            &&
                    countryDto.getCode().length() <= 3           &&
                    countryDto.getCode() != null                 &&
                    !countryDto.getCode().equals(""))            &&

                    (countryDto.getName().length() > 0           &&
                    countryDto.getName().length() <= 52          &&
                    countryDto.getName() != null                 &&
                    !countryDto.getName().equals(""))            &&

                    (countryDto.getContinent().length() > 0      &&
                    countryDto.getContinent() != null            &&
                    !countryDto.getContinent().equals(""))       &&

                    (countryDto.getRegion().length() > 0         &&
                    countryDto.getRegion().length() <= 26        &&
                    countryDto.getRegion() != null               &&
                    !countryDto.getRegion().equals(""))          &&

                    (countryDto.getSurfaceArea() >= 0.0          &&
                    countryDto.getSurfaceArea() != null)         &&

                    (countryDto.getIndepYear() >= 0)             &&

                    (countryDto.getGnp() >= 0.0                  &&
                      countryDto.getGnp() != null)               &&

                    (countryDto.getGnpOld() >= 0.0               &&
                    countryDto.getGnpOld() != null)              &&

                    (countryDto.getLocalName().length() > 0      &&
                    !countryDto.getLocalName().equals(""))       &&

                    (countryDto.getGovernmentForm().length() > 0 &&
                    countryDto.getGovernmentForm() != null       &&
                    !countryDto.getGovernmentForm().equals(""))  &&

                    (countryDto.getHeadOfState().length() > 0    &&
                    !countryDto.getHeadOfState().equals(""))     &&

                    (countryDto.getCapital() >= 0                &&
                     countryDto.getCapital() != null)            &&

                    (countryDto.getCode2() != null               &&
                     countryDto.getCode2().length() > 0          &&
                     countryDto.getCode2().length() <= 2)        ;
        else
            throw new Exception("Country entity " + countryDto + " is not valid!");

    }

}
