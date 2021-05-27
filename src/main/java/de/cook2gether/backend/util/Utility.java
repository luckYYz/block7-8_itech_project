package de.cook2gether.backend.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Utility {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <T> T convert(Object mappable, Class<T> destinationClass){
        return modelMapper.map(mappable, destinationClass);
    }
}
