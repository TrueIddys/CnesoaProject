package com.cnesoa.manager.impl;

import com.cnesoa.domain.Person.User;
import com.cnesoa.manager.CurrentUserManager;
import com.cnesoa.manager.Person.EleveManager;
import com.cnesoa.manager.Person.ProfesseurManager;
import com.cnesoa.manager.Person.UserManager;
import com.cnesoa.utils.CurrentUser;
import com.cnesoa.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by Maxime on 27/04/2016.
 */

@Service
public class CurrentUserManagerImpl implements CurrentUserManager {

    //Autowiring of beans

    @Autowired
    private UserManager userManager;

    @Autowired
    private EleveManager eleveManager;

    @Autowired
    private ProfesseurManager professeurManager;

   /*_________________________________________________*/

    /**
     * check if the current user has right to access the page of himself
     * @param currentUser
     * @param userId the id of user X
     * @return
     */
    @Override
    public Boolean canAccessUser(CurrentUser currentUser, Long userId) {
        return currentUser != null && (currentUser.getRole() == Role.ROLE_ADMIN || currentUser.getId().equals(userId));
    }

    /**
     * check if the current user is related to the animaml
     * @param animalId
     * @return
     */
    @Override
    public Boolean checkAnimal(Long animalId){
        switch (getUser().getRole()) {
            case ROLE_ADMIN: return true;
            case ROLE_ELEVE: return eleveManager.checkLienAnimal(animalId, getUser().getId());
            case ROLE_PROF: return professeurManager.checkLienAnimal(animalId, getUser().getId());
            default : return false;
        }
    }

    /**
     * check if the current user is related to the consultation
     * @param consultationId
     * @return
     */
    @Override
    public Boolean checkConsultation(Long consultationId){
        switch (getUser().getRole()) {
            case ROLE_ADMIN: return true;
            case ROLE_ELEVE: return eleveManager.checkLienConsultation(consultationId, getUser().getId());
            case ROLE_PROF: return professeurManager.checkLienConsultation(consultationId, getUser().getId());
            default : return false;
        }
    }

    /**
     * check if the current user is related to the diagnostic
     * @param diagnosticId
     * @return
     */
    @Override
    public Boolean checkDiagnostic(Long diagnosticId){
        switch (getUser().getRole()) {
            case ROLE_ADMIN: return true;
            case ROLE_ELEVE: return eleveManager.checkLienDiagnostic(diagnosticId, getUser().getId());
            case ROLE_PROF: return professeurManager.checkLienDiagnostic(diagnosticId, getUser().getId());
            default : return false;
        }
    }

    /**
     * check if the current user is related to the treatment
     * @param traitementId
     * @return
     */
    @Override
    public Boolean checkTraitement(Long traitementId){
        switch (getUser().getRole()) {
            case ROLE_ADMIN: return true;
            case ROLE_ELEVE: return eleveManager.checkLienTraitement(traitementId, getUser().getId());
            case ROLE_PROF: return professeurManager.checkLienTraitement(traitementId, getUser().getId());
            default : return false;
        }
    }

    /**
     * check if the current user is related to the consultation
     * @param infosConsultId
     * @return
     */
    @Override
    public Boolean checkInfosConsult(Long infosConsultId){
        switch (getUser().getRole()){
            case ROLE_ADMIN: return true;
            case ROLE_ELEVE: return eleveManager.checkLienInfosConsult(infosConsultId, getUser().getId());
            case ROLE_PROF: return professeurManager.checkLienInfosConsult(infosConsultId, getUser().getId());
            default: return false;
        }
    }

    /**
     * get the current user
     * @return
     */
    @Override
    public User getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails) auth.getPrincipal();
        User user = userManager.getUserByUsername(currentUser.getUsername());
        return user;
    }
}
