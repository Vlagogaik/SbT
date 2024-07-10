package org.rest.data;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;
}
