package com.cnesoa.manager.Animal;

import com.cnesoa.domain.Animal.FicheMedicale;

/**
 * Created by Maxime on 06/04/2016.
 */
public interface FicheMedicaleManager {

    Iterable<FicheMedicale> listAllFicheMedicale();

    FicheMedicale getFicheMedicaleById(Long id);

    FicheMedicale saveFicheMedicale(FicheMedicale ficheMedicale);

    void deleteFicheMedicale(Long id);
}
