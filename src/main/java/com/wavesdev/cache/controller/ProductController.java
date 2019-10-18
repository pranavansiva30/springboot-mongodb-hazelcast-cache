package com.wavesdev.cache.controller;

/**
 * Created by pranavan on 2/27/18.
 */
import com.wavesdev.cache.model.Product;
import com.wavesdev.cache.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;
    @RequestMapping(value = "product",method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        LOGGER.info("addProduct called for name {}",product.getName());
        productService.save(product);
        return productService.getProduct(product.getId());
    }
    @RequestMapping(value ="product/{id}",method = RequestMethod.GET)
    public Product getProduct(@PathVariable("id") String id) {
        LOGGER.info("getProduct called for id {}", id);
        return productService.getProduct(id);
    }

    @RequestMapping(value ="product/{id}",method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        LOGGER.info("updateProduct called for id {}", id);
        product.setId(id);
        Product updatedProduct=productService.updateProduct(product);
        return updatedProduct;
    }
    @RequestMapping(value ="product/{id}",method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable("id") String id) {
        LOGGER.info("deleteProduct called for id {}", id);
        try {
            productService.delete(id);
        }
        catch(Exception e){
            return "FAIL";

        }
      return "SUCCESS";
    }
    @RequestMapping(value ="product/cache/clear",method = RequestMethod.GET)
    public String clearProductCache() {
        LOGGER.info("delete All cache");
        try {
            productService.clearCache();
        }
        catch(Exception e){
            return "FAIL";

        }
        return "SUCCESS";
    }
}