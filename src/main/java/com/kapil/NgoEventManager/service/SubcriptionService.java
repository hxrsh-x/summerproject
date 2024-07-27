package com.kapil.NgoEventManager.service;

import com.kapil.NgoEventManager.modal.PlanType;
import com.kapil.NgoEventManager.modal.Subcription;
import com.kapil.NgoEventManager.modal.User;

public interface SubcriptionService {

    Subcription createSubcription(User user);

    Subcription getUsersSubcription(Long userId)throws Exception;

    Subcription upgradeSubcription(Long userdId, PlanType planType);

    boolean isValid(Subcription subcription);

}
