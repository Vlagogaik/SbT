package org.rest.repository;

import org.rest.data.Cart;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class LocalCartRepository implements CartRepository {

    private final JdbcTemplate jdbcTemplate;

    public LocalCartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Cart cart) {
        String insertSql = "INSERT INTO carts (promocode) VALUES (?)";
        jdbcTemplate.update(insertSql, cart.getPromoCode());
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
    }

    @Override
    public Optional<Cart> findById(long id) {
        String selectSql = "SELECT * FROM carts WHERE id = ?";
        try {
            Cart cart = jdbcTemplate.queryForObject(selectSql, new BeanPropertyRowMapper<>(Cart.class), id);
            return Optional.ofNullable(cart);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean update(Cart cart) {
        String updateSql = "UPDATE carts SET promocode = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(updateSql, cart.getPromoCode(), cart.getId());
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(long id) {
        String deleteSql = "DELETE FROM carts WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(deleteSql, id);
        return rowsAffected > 0;
    }
}
