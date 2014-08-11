package rs.fimes.service.api.nab;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.nab.NabPartijaNabavke;

public interface NabPartijaNabavkeServiceApi extends BaseServiceApi{

    void createNabPartijaNabavke(NabPartijaNabavke novaNabavka);

}
