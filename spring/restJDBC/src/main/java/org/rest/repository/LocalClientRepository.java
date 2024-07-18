package org.rest.repository;

import org.rest.data.Client;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.sql.*;

@Repository
public class LocalClientRepository implements ClientRepository {

    public static final String JDBC = "jdbc:h2:mem:testdb";


    @Override
    public Client save(Client client) {
        var insertSql = "INSERT INTO clients (name, username, password, email, cart_id) VALUES (?, ?, ?, ?, ?);";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setString(1, client.getName());
            prepareStatement.setString(2, client.getUsername());
            prepareStatement.setString(3, client.getPassword());
            prepareStatement.setString(4, client.getEmail());
            prepareStatement.setLong(5, client.getCart());

            prepareStatement.executeUpdate();

            ResultSet rs = prepareStatement.getGeneratedKeys();
            if (rs.next()) {
                client.setId(rs.getLong(1));
                return client;
            } else {
                throw new RuntimeException("Error in obtaining the ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Client> findById(long id) {
        var selectSql = "SELECT * FROM clients WHERE id = ?";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(selectSql)) {
            prepareStatement.setLong(1, id);

            var resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                Client client = mapResultSetToClient(resultSet);
                return Optional.of(client);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Client client) {
        var updateSql = """
                UPDATE clients
                SET 
                name = ?,
                username = ?,
                password = ?,
                email = ?,
                cart_id = ?
                WHERE id = ?;
                """;

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(updateSql)) {
            prepareStatement.setString(1, client.getName());
            prepareStatement.setString(2, client.getUsername());
            prepareStatement.setString(3, client.getPassword());
            prepareStatement.setString(4, client.getEmail());
            prepareStatement.setLong(5, client.getCart());
            prepareStatement.setLong(6, client.getId());

            var rows = prepareStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(long id) {
        var deleteSql = "DELETE FROM clients WHERE id = ?";

        try (var connection = DriverManager.getConnection(JDBC);
             var prepareStatement = connection.prepareStatement(deleteSql)) {
            prepareStatement.setLong(1, id);

            var rows = prepareStatement.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Client mapResultSetToClient(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        int cartId = resultSet.getInt("cart_id");
        return new Client(id, name, username, password, email, cartId);
    }
}
