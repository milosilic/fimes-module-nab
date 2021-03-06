package rs.fimes.service.api.nab;

import java.util.List;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.nab.NabNabavkaJrn;

public interface NabNabavkaJrnServiceApi extends BaseServiceApi{

    void createNabNabavkaJrn(NabNabavkaJrn novaNabavka);

    void deleteNabavkaJrn(NabNabavkaJrn nabavkaJrn);

    List<NabNabavkaJrn> dohvatiNabavkaJrn(Integer idJavnaNabavka);

}
