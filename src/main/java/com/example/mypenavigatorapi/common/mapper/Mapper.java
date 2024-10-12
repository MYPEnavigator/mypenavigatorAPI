package com.example.mypenavigatorapi.common.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public abstract class Mapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <T, D> D map(final T inputClass, Class<D> outClass) {
        return modelMapper.map(inputClass, outClass);
    }

    public static <T, D> D map(final T inputClass, Class<D> outClass, ModelMapper customMapper){
        return customMapper.map(inputClass, outClass);
    }

    public static <T, D> void merge(final T inputClass, D outClass){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(inputClass, outClass);
    }
}
