package com.bank.bankingappbackend.repo;

import com.bank.bankingappbackend.Enum.Role;
import com.bank.bankingappbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}
