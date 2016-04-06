package com.cnesoa.manager;

import com.cnesoa.domain.Eleve;

/**
 * Created by Maxime on 05/04/2016.
 */
public interface EleveManager {

    Iterable<Eleve> listAllEleve();

    Eleve getEleveById(Long id);

    Eleve saveEleve(Eleve eleve);

    void deleteEleve(Long id);
}
