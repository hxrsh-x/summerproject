package com.kapil.NgoEventManager.repository;

import com.kapil.NgoEventManager.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

//   User FindEmail(String email);

    User findByEmail(String email);
}
