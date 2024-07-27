package com.kapil.NgoEventManager.controller;

import com.kapil.NgoEventManager.modal.PlanType;
import com.kapil.NgoEventManager.modal.Subcription;
import com.kapil.NgoEventManager.modal.User;
import com.kapil.NgoEventManager.service.SubcriptionService;
import com.kapil.NgoEventManager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subcription")
public class SubcriptionController {

    private SubcriptionService subcriptionService;

    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Subcription> getUserSubcription(
            @RequestHeader("Authorization") String jwt) throws Exception{
        User user=userService.findUserProfileByJwt(jwt);

        Subcription subcription=subcriptionService.getUsersSubcription(user.getId());

        return new ResponseEntity<>(subcription, HttpStatus.OK);
    }

    @PatchMapping("/upgrade")
    public ResponseEntity<Subcription> upgradeSubcription(
            @RequestHeader("Authorization") String jwt,
            @RequestParam PlanType planType)
            throws Exception{
        User user=userService.findUserProfileByJwt(jwt);

        Subcription subcription=subcriptionService.upgradeSubcription(user.getId(),planType);

        return new ResponseEntity<>(subcription, HttpStatus.OK);
    }
}
