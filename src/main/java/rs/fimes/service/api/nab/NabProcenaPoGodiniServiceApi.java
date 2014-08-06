package rs.fimes.service.api.nab;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.nab.NabProcenaPoGodini;

public interface NabProcenaPoGodiniServiceApi extends BaseServiceApi{

    void createNabProcenaPoGodini(NabProcenaPoGodini novaNabavka);

}
