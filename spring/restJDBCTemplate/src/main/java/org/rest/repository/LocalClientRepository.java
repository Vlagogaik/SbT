package org.rest.repository;

import org.rest.data.Client;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LocalClientRepository implements ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    public LocalClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Client save(Client client) {
        String insertSql = "INSERT INTO clients (name, username, password, email, cart_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertSql, client.getName(), client.getLogin(), client.getPassword(), client.getEmail(), client.getCart());
        return client;
    }

    @Override
    public Optional<Client> findById(long id) {
        String selectSql = "SELECT * FROM clients WHERE id = ?";
        try {
            Client client = jdbcTemplate.queryForObject(selectSql, new BeanPropertyRowMapper<>(Client.class), id);
            return Optional.ofNullable(client);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean update(Client client) {
        String updateSql = "UPDATE clients SET name = ?, username = ?, password = ?, email = ?, cart_id = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(updateSql, client.getName(), client.getLogin(), client.getPassword(), client.getEmail(), client.getCart(), client.getId());
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(long id) {
        String deleteSql = "DELETE FROM clients WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(deleteSql, id);
        return rowsAffected > 0;
    }
}

