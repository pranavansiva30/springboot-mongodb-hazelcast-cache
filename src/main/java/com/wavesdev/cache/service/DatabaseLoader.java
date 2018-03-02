package com.wavesdev.cache.service;

import com.wavesdev.cache.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by pranavan on 2/28/18.
 */
@Service
public class DatabaseLoader {
    @Autowired
    private ProductService productService;


    public DatabaseLoader(ProductService productService){
        productService=this.productService;
    }

    @PostConstruct
    private void initDatabase(){
        productService.deleteAll();
       Product product1= new Product("1", "0826663141405", "The Angry Beavers: The Complete Series");
        Product product2= new Product("2", "0826663141406", "The Angry Beavers 2: The Complete Series 2");
        Product product3= new Product("3", "0826663141407", "The Angry Beavers 3: The Complete Series 3");
        productService.save(product1);
        productService.save(product2);
        productService.save(product3);

    }

}
