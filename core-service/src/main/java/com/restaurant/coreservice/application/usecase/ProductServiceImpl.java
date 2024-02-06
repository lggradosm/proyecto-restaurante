package com.restaurant.coreservice.application.usecase;

import com.restaurant.coreservice.aggregates.constant.Constants;
import com.restaurant.coreservice.aggregates.constant.HttpStatus;
import com.restaurant.coreservice.aggregates.response.ResponseBase;
import com.restaurant.coreservice.application.service.RedisService;
import com.restaurant.coreservice.domain.model.Product;
import com.restaurant.coreservice.domain.ports.in.ProductIn;
import com.restaurant.coreservice.domain.ports.out.ProductOut;
import com.restaurant.coreservice.infraestructure.entity.ProductsEntity;
import com.restaurant.coreservice.util.Util;
import com.restaurant.coreservice.util.validation.ProductValidations;
import org.apache.tomcat.util.bcel.Const;

import java.sql.Timestamp;
import java.util.*;

public class ProductServiceImpl implements ProductIn {
    private final ProductOut productOut;
    private final ProductValidations productValidations;
    private final RedisService redisService;
    public ProductServiceImpl(ProductOut productOut, ProductValidations productValidations, RedisService redisService) {
        this.productOut = productOut;
        this.productValidations = productValidations;
        this.redisService = redisService;
    }

    @Override
    public ResponseBase createProduct(Product product) {
        boolean validation = productValidations.validateInput(product);
        if(validation){
            product.setStockable(Constants.SCTOCKEABLE);
            product.setDateCreate(getTimestamp());
            System.out.println(getTimestamp());
            product.setStatus(Constants.ACTIVO);
            productOut.createProduct(product);
            return new ResponseBase(HttpStatus.OK,Constants.MESS_SUCCESS,Optional.of(product));
        }
        return new ResponseBase(HttpStatus.OK,Constants.MESS_ERROR_DATA_NOT_VALID,Optional.empty());
    }

    @Override
    public ResponseBase getProduct(Integer id) {
        boolean exist = productValidations.existProduct(id);
        if(exist){
            Optional<Product> product = productOut.getProduct(id);
            if(product.isPresent()){
                return new ResponseBase(HttpStatus.OK,Constants.MESS_SUCCESS,product);
            }
            return new ResponseBase(HttpStatus.NOT_FOUND,Constants.MESS_ERROR_DATA_NOT_FOUND,Optional.empty());
        }
        return new ResponseBase(HttpStatus.NOT_FOUND,Constants.MESS_ERROR_DATA_NOT_FOUND,Optional.empty());
    }

    @Override
    public ResponseBase getProducts() {
        List<Product> productList = productOut.getProducts();
        return new ResponseBase(HttpStatus.OK, Constants.MESS_SUCCESS, Optional.of(productList));
    }

    @Override
    public ResponseBase getActivesProducts() {
        List<Product> productList = new ArrayList<>();
        String redisCache = redisService.getValueByKey(Constants.REDIS_KEY_INFO);
        if(redisCache !=null){
            productList = Collections.singletonList(Util.convertFromJson(redisCache, Product.class));
        }else{
             productList = productOut.getActivesProducts();
             String redisdData = Util.convertToJsonEntity(productList);
             redisService.saveKeyValue(Constants.REDIS_KEY_INFO, redisdData,20);
        }
        return new ResponseBase(HttpStatus.OK, Constants.MESS_SUCCESS, Optional.of(productList));
    }

    @Override
    public ResponseBase updateProduct(Integer id, Product product) {
        boolean exist = productValidations.existProduct(id);
        if(exist){
            boolean validation = productValidations.validateInput(product);
            if(validation) {

                productOut.updateProduct(id,product);
                return new ResponseBase(HttpStatus.OK, Constants.MESS_SUCCESS,Optional.of(product));
            }
            return new ResponseBase(HttpStatus.OK,Constants.MESS_ERROR_DATA_NOT_VALID,Optional.empty());
        }
        return new ResponseBase(HttpStatus.NOT_FOUND,Constants.MESS_ERROR_DATA_NOT_FOUND,Optional.empty());
    }

    @Override
    public ResponseBase deleteProduct(Integer id) {
        boolean exist = productValidations.existProduct(id);
        if(exist){
            Optional<Product> product = productOut.getProduct(id);
            if(product.isPresent()){
                product.get().setDateDelete(getTimestamp());
                product.get().setStatus(Constants.INACTIVE);
                productOut.deleteProduct(product.get());
                return new ResponseBase(HttpStatus.OK, Constants.MESS_SUCCESS,Optional.of(product));
            }
            return new ResponseBase(HttpStatus.OK, Constants.MESS_SUCCESS,Optional.empty());
        }
        return new ResponseBase(HttpStatus.NOT_FOUND,Constants.MESS_ERROR_DATA_NOT_FOUND,Optional.empty());
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}
