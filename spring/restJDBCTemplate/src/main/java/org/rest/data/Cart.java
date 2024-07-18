package org.rest.data;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    private Long id;
//    private List<Product> products;
    private String promoCode;
}
