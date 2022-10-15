package com.ronald.config;

import com.ronald.dto.ProductDTO;
import com.ronald.model.Product;
import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean("categoryMapper")
    public ModelMapper categoryMapper(){
        return new ModelMapper();
    }
    @Bean("productMapper")
    public ModelMapper productMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<ProductDTO, Product> typeMap = mapper.createTypeMap(ProductDTO.class, Product.class);
        typeMap.addMapping(ProductDTO::getIdCategory, (dest, v)-> dest.getCategory().setIdCategory((Integer) v));
        return new ModelMapper();
    }
    @Bean("roleMapper")
    public ModelMapper roleMapper(){
        return new ModelMapper();
    }

    @Bean("clientMapper")
    public ModelMapper clientMapper(){
        return new ModelMapper();
    }

    @Bean("providerMapper")
    public ModelMapper providerMapper(){
        return new ModelMapper();
    }

    @Bean("userMapper")
    public ModelMapper userMapper(){
        return new ModelMapper();
    }
    @Bean("saleMapper")
    public ModelMapper saleMapper(){
        ModelMapper mapper = new ModelMapper();
        //mapper.getConfiguration().setPropertyCondition(context ->!(context.getSource() instanceof PersistentCollection));
        return mapper;
    }
}
