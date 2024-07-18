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
    private String login;
    private String password;
    private String email;
    private long cart;
}
