package com.cnesoa.manager;

import com.cnesoa.domain.Person.User;
import com.cnesoa.utils.CurrentUser;

/**
 * Created by Maxime on 27/04/2016.
 */
public interface CurrentUserManager {

    Boolean canAccessUser(CurrentUser currentUser, Long userId);

    Boolean checkAnimal(Long animalId);

    Boolean checkConsultation(Long consultationId);

    Boolean checkDiagnostic(Long diagnosticId);

    Boolean checkTraitement(Long traitementId);

    Boolean checkInfosConsult(Long infosConsult);

    User getUser();

}
