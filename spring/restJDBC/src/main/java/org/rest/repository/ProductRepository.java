package org.rest.repository;

import org.rest.data.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository{

    Product save(Product product);

    Optional<Product> findById(long id);

    List<Product> findAll(String name);

    boolean update(Product product);

    boolean deleteById(long id);
}

