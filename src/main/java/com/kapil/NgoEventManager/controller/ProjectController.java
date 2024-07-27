package com.kapil.NgoEventManager.controller;

import com.kapil.NgoEventManager.modal.Chat;
import com.kapil.NgoEventManager.modal.Invitation;
import com.kapil.NgoEventManager.modal.Project;
import com.kapil.NgoEventManager.modal.User;
import com.kapil.NgoEventManager.repository.InviteRequest;
import com.kapil.NgoEventManager.response.MessageResponse;
import com.kapil.NgoEventManager.service.InvitationService;
import com.kapil.NgoEventManager.service.ProjectService;
import com.kapil.NgoEventManager.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private InvitationService invitationService;

    @GetMapping
    public ResponseEntity<List<Project>>getProjects(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestHeader("Authorization") String jwt
    )
    throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        List<Project> projects=projectService.getProjectByTeam(user, category, tag);
        return new ResponseEntity<>(projects, HttpStatus.OK );
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project>getProjectsById(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    )
            throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        Project projects=projectService.getProjectById(projectId);

        if (!projects.getTeam().contains(user) && !projects.getOwner().equals(user)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(projects, HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<Project>createProject(
//            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project
//            Authentication authentication
    )
            throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Project createdProject = projectService.createProject(project, user);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project>updateProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project
    )throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        Project updatedproject=projectService.updateProject(project, projectId);
        return new ResponseEntity<>(updatedproject, HttpStatus.OK );
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse>deleteProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt

    )throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        projectService.deleteProject(projectId, user.getId());
        MessageResponse res=new MessageResponse("project deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK );
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>>searchProjects(
            @RequestParam(required = false) String keyword,
            @RequestHeader("Authorization") String jwt
    )throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        List<Project> projects=projectService.searchProjects(keyword, user);
        return new ResponseEntity<>(projects, HttpStatus.OK );
    }


    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat>getChatByProjectId(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        Chat chat=projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK );
    }

    @PostMapping("/invite")
    public ResponseEntity<MessageResponse>invitateProject(
            @RequestBody InviteRequest req,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project
    )
            throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        invitationService.sendInvitation(req.getEmail(), req.getProjectId());
        MessageResponse res =new MessageResponse("User invitation sent");
        return new ResponseEntity<>(res, HttpStatus.OK );
    }

    @GetMapping("/accept_invitation")
    public ResponseEntity<Invitation>acceptInvitateProject(
            @RequestParam String  token,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project
    )
            throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        Invitation invitation= invitationService.acceptInvitation(token, user.getId());
        projectService.addUserToProject(invitation.getProjectId(), user.getId());
        return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED );
    }
}
