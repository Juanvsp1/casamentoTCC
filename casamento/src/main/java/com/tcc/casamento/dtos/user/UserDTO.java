package com.tcc.casamento.dtos.user;

import com.tcc.casamento.entities.user.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public UserDTO(User entitie) {
        id = entitie.getId();
        firstName = entitie.getFirstName();
        lastName = entitie.getLastName();
        email = entitie.getEmail();
    }
}
