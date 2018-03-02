package com.wavesdev.cache.dao;

import com.wavesdev.cache.model.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pranavan on 2/28/18.
 */
public interface ProductDao extends CrudRepository<Product, String> {
}
