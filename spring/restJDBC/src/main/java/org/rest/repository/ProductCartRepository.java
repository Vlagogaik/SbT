package org.rest.repository;

import org.rest.data.ProductCart;
import java.util.List;
import java.util.Optional;

public interface ProductCartRepository {

    ProductCart save(ProductCart productCart);

    Optional<ProductCart> findById(long id);

    List<ProductCart> findAll();

    boolean update(ProductCart productCart);

    boolean deleteById(long id);
}
