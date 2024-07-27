package com.kapil.NgoEventManager.service;

import com.kapil.NgoEventManager.modal.PlanType;
import com.kapil.NgoEventManager.modal.Subcription;
import com.kapil.NgoEventManager.modal.User;
import com.kapil.NgoEventManager.repository.SubcriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubcriptionServiceImpl implements SubcriptionService{

    @Autowired
    private UserService userService;

    @Autowired
    private SubcriptionRepository subcriptionRepository;

    @Override
    public Subcription createSubcription(User user) {
        Subcription subcription = new Subcription();
        subcription.setUser(user);
        subcription.setSubcriptionStartDate(LocalDate.now());
        subcription.setGetSubcriptionEndDate(LocalDate.now().plusMonths(12));
        subcription.setValid(true);
        subcription.setPlantype(PlanType.FREE);

        return subcriptionRepository.save(subcription);
    }

    @Override
    public Subcription getUsersSubcription(Long userId) throws Exception {
        Subcription subcription= subcriptionRepository.findByUserId(userId);
        if(isValid(subcription)){
            subcription.setPlantype(PlanType.FREE);
            subcription.setGetSubcriptionEndDate(LocalDate.now().plusMonths(12));
            subcription.setSubcriptionStartDate(LocalDate.now());
        }
        return subcriptionRepository.save(subcription);
    }

    @Override
    public Subcription upgradeSubcription(Long userId, PlanType planType) {
        Subcription subcription=subcriptionRepository.findByUserId(userId);
        subcription.setPlantype(planType);
        subcription.setSubcriptionStartDate(LocalDate.now());
        if(planType.equals(PlanType.ANNUALLY)){
            subcription.setGetSubcriptionEndDate(LocalDate.now().plusMonths(12));
        }else{
            subcription.setGetSubcriptionEndDate(LocalDate.now().plusMonths(1));
        }

        return subcriptionRepository.save(subcription);
    }

    @Override
    public boolean isValid(Subcription subcription) {
        if(subcription.getPlantype().equals(PlanType.FREE)){
            return true;
        }
        LocalDate endDate=subcription.getGetSubcriptionEndDate();
        LocalDate currentDate=LocalDate.now();

        return endDate.isAfter(currentDate) || endDate.isEqual(currentDate);
    }
}
