package com.restaurant.coreservice.infraestructure.config;

import com.restaurant.coreservice.application.service.OrderService;
import com.restaurant.coreservice.application.service.ProductService;
import com.restaurant.coreservice.application.service.RedisService;
import com.restaurant.coreservice.application.service.TableService;
import com.restaurant.coreservice.application.usecase.OrderServiceImpl;
import com.restaurant.coreservice.application.usecase.ProductServiceImpl;
import com.restaurant.coreservice.application.usecase.TableServiceImpl;
import com.restaurant.coreservice.domain.ports.out.*;
import com.restaurant.coreservice.infraestructure.feignClient.ReniecClient;
import com.restaurant.coreservice.infraestructure.feignClient.SunatClient;
import com.restaurant.coreservice.infraestructure.repository.*;
import com.restaurant.coreservice.util.validation.OrderValidations;
import com.restaurant.coreservice.util.validation.ProductValidations;
import com.restaurant.coreservice.util.validation.TableValidations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ProductOut productOut (ProductRepositoryAdapter productRepositoryAdapter){
        return productRepositoryAdapter;
    }
    @Bean
    public ProductService productService (ProductOut productOut, RedisService redisService){
        return new ProductService(new ProductServiceImpl(productOut,new ProductValidations(productOut),redisService));
    }

    @Bean
    public TableOut tableOut (TableRepositoryAdapter tableRepositoryAdapter){
        return tableRepositoryAdapter;
    }
    @Bean
    public TableService tableService (TableOut tableOut){
        return new TableService(new TableServiceImpl(tableOut,new TableValidations(tableOut)));
    }
    @Bean
    public EmployeeOut employeeOut(EmployeeRepositoryAdapter employeeRepositoryAdapter){ return employeeRepositoryAdapter;}

    @Bean
    public OrderDetailOut orderDetailOut(OrderDetailRepositoryAdapter orderDetailRepositoryAdapter){ return orderDetailRepositoryAdapter;}


    @Bean
    public OrderOut orderOut (OrderRepositoryAdapter orderRepositoryAdapter){
        return orderRepositoryAdapter;
    }

    @Bean
    public OrderService orderService (OrderOut orderOut, EmployeeOut employeeOut, OrderDetailOut orderDetailOut, ProductOut productOut, TableOut tableOut, SunatClient sunatClient, ReniecClient reniecClient){
        return new OrderService(
                new OrderServiceImpl(orderOut, employeeOut, orderDetailOut, new OrderValidations( employeeOut,  tableOut,  productOut, orderOut),productOut,tableOut, sunatClient, reniecClient));
    }

}
