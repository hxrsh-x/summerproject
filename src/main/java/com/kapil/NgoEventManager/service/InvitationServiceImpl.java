package com.kapil.NgoEventManager.service;

import com.kapil.NgoEventManager.modal.Invitation;
import com.kapil.NgoEventManager.repository.InvitationRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService{
    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void sendInvitation(String email, Long projectId) throws MessagingException {

        String invitationToken= UUID.randomUUID().toString();

        Invitation invitation=new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);

        invitationRepository.save(invitation);

        //Don't forget to paste your frontend actual link (youtube:3:40:11)
        String invitationLink="http://locahost:5173/accept_invitation?token="+invitationToken;
        emailService.sendEmailWithToken(email, invitationLink);
    }

    @Override
    public Invitation acceptInvitation(String token, Long userId) throws Exception {
        Invitation invitation=invitationRepository.findByToken(token);
        if(invitation==null){
            throw new Exception("invalid invitation token");
        }
        return invitation;
    }

    @Override
    public String getTokenByUserMail(String userEmail) {
        Invitation invitation=invitationRepository.findByEmail(userEmail);
        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) {

        Invitation invitation=invitationRepository.findByToken(token);
        invitationRepository.delete(invitation);
    }
}
