package com.kapil.NgoEventManager.repository;

import com.kapil.NgoEventManager.modal.Subcription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcriptionRepository extends JpaRepository<Subcription, Long> {

    Subcription findByUserId(Long userId);
}
