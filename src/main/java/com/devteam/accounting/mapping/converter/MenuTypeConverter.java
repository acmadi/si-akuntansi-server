package com.devteam.accounting.mapping.converter;

import com.devteam.accounting.persistence.type.MenuType;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

/**
 * User: pancara
 * Date: 1/12/14
 * Time: 9:38 AM
 */
public class MenuTypeConverter implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {

        if (sourceFieldValue == null) {
            return null;
        }

        if (sourceFieldValue instanceof MenuType) {
            return ((MenuType) sourceFieldValue).name();
        } else if (sourceFieldValue instanceof String) {
            String value = (String) sourceFieldValue;

            if (value.equals(MenuType.MENU_ITEM.name()))
                return MenuType.MENU_ITEM;

            if (value.equals(MenuType.SEPARATOR.name()))
                return MenuType.SEPARATOR;
        }
        throw new MappingException("Converter TestCustomConverter "
                + "used incorrectly. Arguments passed in were:"
                + existingDestinationFieldValue + " and " + sourceFieldValue);
    }
}
