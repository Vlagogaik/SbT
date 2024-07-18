package org.rest.data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private int cart;
}
