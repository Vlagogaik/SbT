package org.rest.repository;

import org.rest.data.Client;
import java.util.Optional;

public interface ClientRepository {

    Client save(Client client);

    Optional<Client> findById(long id);

    boolean update(Client client);

    boolean deleteById(long id);
}
