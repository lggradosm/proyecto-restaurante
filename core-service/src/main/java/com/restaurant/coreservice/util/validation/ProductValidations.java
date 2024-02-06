package com.restaurant.coreservice.util.validation;

import com.restaurant.coreservice.domain.model.Product;
import com.restaurant.coreservice.domain.ports.out.ProductOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductValidations {

    private final ProductOut productOut;

    public ProductValidations(ProductOut productOut) {
        this.productOut = productOut;
    }

    public boolean validateInput(Product product){
        if(product == null ) return false;
        if(productOut.existProductByName(product.getName())) return false;
        if(isNullOrEmpty(product.getName() ) || product.getPrice() == null) return false;
        return true;
    }
    public boolean existProduct(Integer id){
        return productOut.existProduct(id);
    }

    public boolean isNullOrEmpty(String data){
        return  data == null || data.isEmpty() ;
    }
}
