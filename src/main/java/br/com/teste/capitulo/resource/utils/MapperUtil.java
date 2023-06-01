package br.com.teste.capitulo.resource.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component("mapperUtil")
public class MapperUtil {
    protected ModelMapper modelMapper;


    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public <S, D> D mapTo(S source, Class<D> destClass){
        return this.modelMapper.map(source, destClass);
    }

    public <S, D> List<D> toList(List<S> source, Type destClass){
        return this.modelMapper.map(source, destClass);
    }
}
