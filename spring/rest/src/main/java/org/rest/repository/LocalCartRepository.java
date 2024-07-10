package org.rest.repository;

import org.rest.data.Cart;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class LocalCartRepository implements CartRepository {
    private List<Cart> carts = new ArrayList<>();

    @Override
    public long save(Cart cart) {
        long id = generateId();
        cart.setId(id);
        carts.add(cart);
        return id;
    }

    @Override
    public Optional<Cart> findById(long id) {
        return carts.stream()
                .filter(cart -> cart.getId() == id)
                .findAny();
    }

    @Override
    public boolean update(Cart cart) {
        for (Cart c : carts) {
            if (c.getId() == cart.getId()) {
                c.setProducts(cart.getProducts());
                c.setPromoCode(cart.getPromoCode());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return carts.removeIf(cart -> cart.getId() == id);
    }

    private long generateId() {
        return new Random().nextLong(1, 1_000_000);
    }
}

