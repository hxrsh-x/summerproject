package com.kapil.NgoEventManager.controller;

import com.kapil.NgoEventManager.modal.Chat;
import com.kapil.NgoEventManager.modal.Message;
import com.kapil.NgoEventManager.modal.User;
import com.kapil.NgoEventManager.request.CreateMessageRequest;
import com.kapil.NgoEventManager.service.MessageService;
import com.kapil.NgoEventManager.service.ProjectService;
import com.kapil.NgoEventManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;


    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(
            @RequestBody CreateMessageRequest request)
            throws Exception {

        User user = userService.findUserById(request.getSenderId());
        Chat chats = projectService.getProjectById(request.getProjectId()).getChat();
        if (chats == null) {
            throw new Exception("Chats not found");
        }

        Message sentMessage = messageService.sendMessage(request.getSenderId(),
                request.getProjectId(),
                request.getContent());

        return ResponseEntity.ok(sentMessage);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(
            @PathVariable Long projectId)
            throws Exception {

        List<Message> messages = messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }

}
