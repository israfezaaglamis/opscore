package com.opscore.repository;

import com.opscore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    //All codes are come from jpa repository already likewise findAll(),
    // save() and delete() these are methots
    // Spring Data JPA, metot isminden sorguyu otomatik anlar!
    // Bu satırı eklediğinde "email kolonunda bu değeri ara, varsa true dön" demiş oluyorsun.
}
