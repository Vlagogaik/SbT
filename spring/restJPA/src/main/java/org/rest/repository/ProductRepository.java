package org.rest.repository;

import jakarta.transaction.Transactional;
import org.rest.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.name = :name, p.count = :count WHERE p.id = :id")
    void update(@Param("id") Long id, @Param("name") String name, @Param("count") int count);
}

