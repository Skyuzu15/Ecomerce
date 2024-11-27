package com.meuprojeto.ecomerce.repository;

import com.meuprojeto.ecomerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
