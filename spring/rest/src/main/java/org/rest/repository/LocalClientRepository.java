package org.rest.repository;

import org.rest.data.Client;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class LocalClientRepository implements ClientRepository {
    private List<Client> clients = new ArrayList<>();

    @Override
    public Client save(Client client) {
        long id = generateId();
        client.setId(id);
        clients.add(client);
        return client;
    }

    @Override
    public Optional<Client> findById(long id) {
        return clients.stream()
                .filter(client -> client.getId() == id)
                .findAny();
    }

    @Override
    public boolean update(Client client) {
        for (Client c : clients) {
            if (c.getId() == client.getId()) {
                c.setName(client.getName());
                c.setLogin(client.getLogin());
                c.setPassword(client.getPassword());
                c.setEmail(client.getEmail());
                c.setCart(client.getCart());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return clients.removeIf(client -> client.getId() == id);
    }

    private long generateId() {
        return new Random().nextLong(1, 1_000_000);
    }
}
