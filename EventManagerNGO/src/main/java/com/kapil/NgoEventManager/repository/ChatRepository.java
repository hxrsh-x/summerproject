package com.kapil.NgoEventManager.repository;

import com.kapil.NgoEventManager.modal.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Long> {
}
