package com.wavesdev.cache.service;

/**
 * Created by pranavan on 2/27/18.
 */
import com.wavesdev.cache.dao.ProductDao;
import com.wavesdev.cache.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "product")
public class ProductService {

    @Autowired
    private ProductDao productDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    @CacheEvict(allEntries = true)
    public void clearCache(){}


    //This "product" is delcares in ehcache.xml
    @Cacheable(cacheNames="product")
    public Product getProduct(String id) {
        LOGGER.info("getProduct Executing for id {}", id);
        Product product=productDao.findOne(id);
        return product;
    }

    @CachePut(value="product", key ="#root.args[0].id")
    public Product updateProduct(Product product) {
        LOGGER.info("updateProduct Executing for id {}", product.getId());
        Product updatedProduct=productDao.save(product);
        return updatedProduct;
    }

    public void deleteAll(){
        LOGGER.info("deleteAllProducts Excuting {}");
        productDao.deleteAll();


    }
    @CacheEvict(value="product",key="#id")
    public void delete(String id){
        LOGGER.info("deleteProducts Excuting for id {}",id);
        productDao.delete(id);


    }
    public void save(Product product){
        LOGGER.info("save product Excuting for name {}",product.getName());
        productDao.save(product);


    }
}

