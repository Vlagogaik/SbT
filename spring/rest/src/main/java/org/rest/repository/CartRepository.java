package org.rest.repository;

import org.rest.data.Cart;
import java.util.Optional;

public interface CartRepository{

    long save(Cart cart);

    Optional<Cart> findById(long id);

    boolean update(Cart cart);

    boolean deleteById(long id);
}
