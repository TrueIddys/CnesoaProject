package com.cnesoa.manager;

import com.cnesoa.domain.Binome;
import com.cnesoa.domain.Consultation.InfosConsult;

/**
 * Created by Maxime on 13/04/2016.
 */
public interface BinomeManager {

    Iterable<Binome> listAllBinome();

    Binome getBinomeById(Long id);

    Binome saveBinome(Binome binome);

    void deleteBinome(Long id);

    void removeInfosConsult(Binome binome, InfosConsult infosConsult);
}
