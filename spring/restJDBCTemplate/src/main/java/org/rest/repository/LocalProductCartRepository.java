package org.rest.repository;

import org.rest.data.ProductCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class LocalProductCartRepository implements ProductCartRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocalProductCartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProductCart save(ProductCart productCart) {
        String insertSql = "INSERT INTO products_carts (id_product, id_cart, count) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertSql, productCart.getProductId(), productCart.getCartId(), productCart.getCount());
        return productCart;
    }

    @Override
    public List<ProductCart> findByCartId(Long cartId) {
        String sql = "SELECT * FROM products_carts WHERE id_cart = ?";
        return jdbcTemplate.query(sql, new Object[]{cartId}, (rs, rowNum) ->
                new ProductCart(
                        rs.getLong("id"),
                        rs.getLong("id_product"),
                        rs.getLong("id_cart"),
                        rs.getInt("count")
                ));
    }
    @Override
    public Optional<ProductCart> findById(long id) {
        String selectSql = "SELECT * FROM products_carts WHERE id = ?";
        return jdbcTemplate.query(selectSql, new Object[]{id}, mapToProductCart()).stream().findFirst();
    }

    @Override
    public List<ProductCart> findAll() {
        String selectSql = "SELECT * FROM products_carts";
        return jdbcTemplate.query(selectSql, mapToProductCart());
    }

    @Override
    public boolean deleteById(long id) {
        String deleteSql = "DELETE FROM products_carts WHERE id = ?";
        return jdbcTemplate.update(deleteSql, id) > 0;
    }

    @Override
    public boolean update(ProductCart productCart) {
        String updateSql = "UPDATE products_carts SET id_product = ?, id_cart = ?, count = ? WHERE id = ?";
        return jdbcTemplate.update(updateSql,
                productCart.getProductId(),
                productCart.getCartId(),
                productCart.getCount(),
                productCart.getId()) > 0;
    }



    private RowMapper<ProductCart> mapToProductCart() {
        return (resultSet, rowNum) -> {
            long id = resultSet.getLong("id");
            long productId = resultSet.getLong("id_product");
            long cartId = resultSet.getLong("id_cart");
            int count = resultSet.getInt("count");
            return new ProductCart(id, productId, cartId, count);
        };
    }
}
