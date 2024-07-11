package org.rest.repository.mappers;

import org.rest.data.Client;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
        Client client = new Client();
        client.setId(resultSet.getLong("id"));
        client.setName(resultSet.getString("name"));
        client.setLogin(resultSet.getString("username"));
        client.setPassword(resultSet.getString("password"));
        client.setEmail(resultSet.getString("email"));
//        client.setCart(resultSet.getLong("cart_id"));
        return client;
    }
}

