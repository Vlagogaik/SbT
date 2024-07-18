package org.rest.repository;

import org.rest.data.Cart;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.sql.*;

@Repository
public class LocalCartRepository implements CartRepository {
    public static final String JDBC = "jdbc:h2:mem:testdb";

    @Override
    public long save(Cart cart) {
        var insertSql = "INSERT INTO carts (promocode) VALUES (?);";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, cart.getPromoCode());

            prepareStatement.executeUpdate();

            ResultSet rs = prepareStatement.getGeneratedKeys();
            if (rs.next()) {
                cart.setId(rs.getLong(1));
                return cart.getId();
            } else {
                throw new RuntimeException("Error in obtaining the ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Cart> findById(long id) {
        var selectSql = "SELECT * FROM carts WHERE id = ?";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setLong(1, id);

            var resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                Cart cart = mapResultSetToCart(resultSet);
                return Optional.of(cart);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Cart cart) {
        var updateSql = """
                UPDATE carts
                SET 
                promocode = ?
                WHERE id = ?;
                """;

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(updateSql)) {
            prepareStatement.setString(1, cart.getPromoCode());
            prepareStatement.setLong(2, cart.getId());

            var rows = prepareStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(long id) {
        var deleteSql = "DELETE FROM carts WHERE id = ?";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(deleteSql)) {
            prepareStatement.setLong(1, id);

            var rows = prepareStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Cart mapResultSetToCart(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String promoCode = resultSet.getString("promocode");
        return new Cart(id, null, promoCode);
    }
}
