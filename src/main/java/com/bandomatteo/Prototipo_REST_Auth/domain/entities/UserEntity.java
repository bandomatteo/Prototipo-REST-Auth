package com.bandomatteo.Prototipo_REST_Auth.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity()
@Table(name = "_users")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;
}
