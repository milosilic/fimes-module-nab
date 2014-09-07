package rs.fimes.service.api.nab;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.nab.NabUgovor;

public interface NabUgovorServiceApi extends BaseServiceApi{

    void createNabUgovor(NabUgovor novaNabavka);

    void deleteUgovor(NabUgovor nabUgovor);


}
