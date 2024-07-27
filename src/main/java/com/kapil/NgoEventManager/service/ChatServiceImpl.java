package com.kapil.NgoEventManager.service;

import com.kapil.NgoEventManager.modal.Chat;
import com.kapil.NgoEventManager.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{
    @Autowired
    private ChatRepository chatRepository;
    @Override
    public Chat createdChat(Chat chat) {
        return chatRepository.save(chat);
    }
}
