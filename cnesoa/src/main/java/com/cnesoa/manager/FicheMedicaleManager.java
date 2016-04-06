package com.cnesoa.manager;

import com.cnesoa.domain.FicheMedicale;

/**
 * Created by Maxime on 06/04/2016.
 */
public interface FicheMedicaleManager {

    Iterable<FicheMedicale> listAllFicheMedicale();

    FicheMedicale getFicheMedicaleById(Long id);

    FicheMedicale saveFicheMedicale(FicheMedicale ficheMedicale);

    void deleteFicheMedicale(Long id);
}
