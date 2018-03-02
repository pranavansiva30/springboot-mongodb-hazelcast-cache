package com.wavesdev.cache.model;

/**
 * Created by pranavan on 2/27/18.
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
   @Id
    private String id;
    private String ean;
    private String name;

    public Product() {
        super();
    }

    public Product(String id, String ean, String name) {
        super();
        this.id = id;
        this.ean = ean;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
