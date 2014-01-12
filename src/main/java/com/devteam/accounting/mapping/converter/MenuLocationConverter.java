package com.devteam.accounting.mapping.converter;

import com.devteam.accounting.persistence.type.MenuLocation;
import com.devteam.accounting.persistence.type.MenuLocation;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

/**
 * User: pancara
 * Date: 1/12/14
 * Time: 9:38 AM
 */
public class MenuLocationConverter implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {

        if (sourceFieldValue == null) {
            return null;
        }

        if (sourceFieldValue instanceof MenuLocation) {
            return ((MenuLocation) sourceFieldValue).name();
        } else if (sourceFieldValue instanceof String) {
            String value = (String) sourceFieldValue;

            if (value.equals(MenuLocation.TOP.name()))
                return MenuLocation.TOP;

            if (value.equals(MenuLocation.LEFT.name()))
                return MenuLocation.LEFT;
        }
        throw new MappingException("Converter TestCustomConverter "
                + "used incorrectly. Arguments passed in were:"
                + existingDestinationFieldValue + " and " + sourceFieldValue);
    }
}
