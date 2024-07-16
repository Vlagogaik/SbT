package org.rest.data;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCart {
    private Long id;
    private Long productId;
    private Long cartId;
    private int count;
}
