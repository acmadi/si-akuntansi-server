package com.devteam.accounting.util;

import org.dozer.Mapper;

import java.util.LinkedList;
import java.util.List;

/**
 * Programmer : pancara
 * Date       : 10/12/13
 * Time       : 11:45 AM
 */
public class MappingUtil {

    private Mapper mapper;

    public MappingUtil() {
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public <S, T> T map(S source, Class<T> destType) {
        return mapper.map(source, destType);
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetType) {
        List<T> target = new LinkedList();
        for (S element : source) {
            target.add(mapper.map(element, targetType));
        }
        return target;
    }

    public void map(Object source, Object target) {
        mapper.map(source, target);
    }
}