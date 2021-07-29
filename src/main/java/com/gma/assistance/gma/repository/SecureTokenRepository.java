package com.gma.assistance.gma.repository;

import com.gma.assistance.gma.entity.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecureTokenRepository extends JpaRepository<SecureToken,Integer> {
    SecureToken findByToken(String token);
    Integer removeByToken(String token);
}
