package com.kapil.NgoEventManager.service;

import com.kapil.NgoEventManager.modal.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(Long senderId, Long chatId, String content) throws Exception;

    List<Message> getMessagesByProjectId(Long projectId) throws Exception;
}
