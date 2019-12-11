package com.api.worldmanager.validation;

import com.api.worldmanager.model.City;
import com.api.worldmanager.model.Country;
import lombok.experimental.UtilityClass;


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


    public boolean isCityValid(City city) throws Exception {
        if(city != null)
            return  (city.getId() > 0) &&
                (city.getName() != null && !city.getName().equals("")) &&
                (city.getDistrict() != null && !city.getDistrict().equals("")) &&
                (city.getPopulation() >= 0);
        else
            throw new Exception("City" + city + " entity not valid!");
    }


    public boolean isCountryValid(Country c) throws Exception
    {
        if(c != null)
            return (c.getCode().length() > 0   &&
                    c.getCode().length() <= 3  &&
                    c.getCode() != null        &&
                    !c.getCode().equals(""))   &&

                    (c.getName().length() > 0   &&
                    c.getName().length() <= 52 &&
                    c.getName() != null        &&
                    !c.getName().equals(""))   &&

                    (c.getContinent().length() > 0 &&
                    c.getContinent() != null &&
                    !c.getContinent().equals("")) &&

                    (c.getRegion().length() > 0 &&
                    c.getRegion().length() <= 26 &&
                    c.getRegion() != null &&
                    !c.getRegion().equals(""))   &&

                    (c.getSurfaceArea() >= 0.0 &&
                    c.getSurfaceArea() != null) &&

                    (c.getIndepYear() >= 0) &&

                    (c.getGnp() >= 0.0 &&
                      c.getGnp() != null) &&

                    (c.getGnpOld() >= 0.0 &&
                    c.getGnpOld() != null) &&

                    (c.getLocalName().length() > 0 &&
                    !c.getLocalName().equals("")) &&

                    (c.getGovernmentForm().length() > 0 &&
                    c.getGovernmentForm() != null &&
                    !c.getGovernmentForm().equals("")) &&

                    (c.getHeadOfState().length() > 0 &&
                    !c.getHeadOfState().equals("")) &&

                    (c.getCapital() >= 0 &&
                     c.getCapital() != null)  &&

                    (c.getCode2() != null &&
                     c.getCode2().length() > 0 &&
                     c.getCode2().length() <= 2)
                    ;
        else
            throw new Exception("Country entity " + c + " is not valid!");

    }

}
