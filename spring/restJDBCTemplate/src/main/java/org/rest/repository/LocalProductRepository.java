package org.rest.repository;

import org.rest.data.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class LocalProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public LocalProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product save(Product product) {
        String insertSql = "INSERT INTO products (name, price, count) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertSql, product.getName(), product.getPrice(), product.getCount());
        return product;
    }

    @Override
    public Optional<Product> findById(long productId) {
        String selectSql = "SELECT * FROM products WHERE id = ?";
        try {
            Product product = jdbcTemplate.queryForObject(selectSql, new BeanPropertyRowMapper<>(Product.class), productId);
            return Optional.ofNullable(product);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findAll(String productName) {
        String selectSql = "SELECT * FROM products WHERE name LIKE ?";
        return jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(Product.class), "%" + productName + "%");
    }

    @Override
    public boolean deleteById(long id) {
        String deleteSql = "DELETE FROM products WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(deleteSql, id);
        return rowsAffected > 0;
    }

    @Override
    public boolean update(Product product) {
        String updateSql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(updateSql, product.getName(), product.getPrice(), product.getCount(), product.getId());
        return rowsAffected > 0;
    }
}

