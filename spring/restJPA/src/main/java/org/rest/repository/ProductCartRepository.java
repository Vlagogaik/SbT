package org.rest.repository;

import jakarta.transaction.Transactional;
import org.rest.data.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
    
    List<ProductCart> findByCartId(Long cartId);

    ProductCart save(ProductCart productCart);

    @Transactional
    @Modifying
    @Query("UPDATE ProductCart pc SET pc.productId = :#{#productCart.productId}, pc.cartId = :#{#productCart.cartId}, pc.count = :#{#productCart.count} WHERE pc.id = :#{#productCart.id}")
    int update(@Param("productCart") ProductCart productCart);

}
