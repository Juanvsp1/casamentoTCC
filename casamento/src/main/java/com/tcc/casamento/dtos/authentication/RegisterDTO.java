package com.tcc.casamento.dtos.authentication;

import com.tcc.casamento.entities.role.Role;

public record RegisterDTO(String email,String firstName,String lastName,String password, Role role) {
}
