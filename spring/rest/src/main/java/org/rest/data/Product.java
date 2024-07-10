package org.rest.data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    private Long id;
    private String name;
    private double price;
    private int quantity;
}
