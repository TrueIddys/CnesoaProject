package com.cnesoa.manager;

import com.cnesoa.domain.Cotisation;

/**
 * Created by Maxime on 30/03/2016.
 */
public interface CotisationManager {

    Cotisation getCotisationById(Long id);

    Cotisation saveCotisation(Cotisation cotisation);

    void deleteCotisation(Long id);
}
