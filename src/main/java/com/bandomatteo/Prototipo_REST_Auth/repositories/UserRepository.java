package com.bandomatteo.Prototipo_REST_Auth.repositories;

import com.bandomatteo.Prototipo_REST_Auth.domain.entities.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
