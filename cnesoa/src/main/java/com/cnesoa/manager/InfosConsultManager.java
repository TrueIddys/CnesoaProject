package com.cnesoa.manager;

import com.cnesoa.domain.InfosConsult;

/**
 * Created by Maxime on 07/04/2016.
 */

public interface InfosConsultManager {


    Iterable<InfosConsult> listAllInfosConsult();

    InfosConsult getInfosConsultById(Long id);

    InfosConsult saveInfosConsult(InfosConsult infosConsult);

    void deleteInfosConsult(Long id);
}
