package org.rest.repository;

import org.rest.data.ProductCart;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LocalProductCartRepository implements ProductCartRepository {
    public static final String JDBC = "jdbc:postgresql://localhost:5432/postgres?useSSL=false&serverTimezone=Europe/Moscow&user=root&password=admin";

    @Override
    public ProductCart save(ProductCart productCart) {
        var insertSql = "INSERT INTO products_carts (id_product, id_cart, count) VALUES (?, ?, ?);";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setLong(1, productCart.getProductId());
            prepareStatement.setLong(2, productCart.getCartId());
            prepareStatement.setInt(3, productCart.getCount());

            prepareStatement.executeUpdate();

            ResultSet rs = prepareStatement.getGeneratedKeys();
            if (rs.next()) {
                productCart.setId(rs.getLong(1));
                return productCart;
            } else {
                throw new RuntimeException("Error in obtaining the ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ProductCart> findById(long id) {
        var selectSql = "SELECT * FROM products_carts WHERE id = ?";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setLong(1, id);

            var resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                ProductCart productCart = mapResultSetToProductCart(resultSet);
                return Optional.of(productCart);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductCart> findAll() {
        var selectSql = "SELECT * FROM products_carts";
        List<ProductCart> productCarts = new ArrayList<>();

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(selectSql)) {

            var resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                ProductCart productCart = mapResultSetToProductCart(resultSet);
                productCarts.add(productCart);
            }

            return productCarts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(long id) {
        var deleteSql = "DELETE FROM products_carts WHERE id = ?";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(deleteSql)) {
            prepareStatement.setLong(1, id);

            var rows = prepareStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(ProductCart productCart) {
        var updateSql = """
                UPDATE products_carts
                SET id_product = ?,
                    id_cart = ?,
                    count = ?
                WHERE id = ?;
                """;

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(updateSql)) {
            prepareStatement.setLong(1, productCart.getProductId());
            prepareStatement.setLong(2, productCart.getCartId());
            prepareStatement.setInt(3, productCart.getCount());
            prepareStatement.setLong(4, productCart.getId());

            var rows = prepareStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ProductCart mapResultSetToProductCart(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        long productId = resultSet.getLong("id_product");
        long cartId = resultSet.getLong("id_cart");
        int count = resultSet.getInt("count");
        return new ProductCart(id, productId, cartId, count);
    }
}
