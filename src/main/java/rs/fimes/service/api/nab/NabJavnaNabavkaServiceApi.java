package rs.fimes.service.api.nab;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.service.exception.FimesServiceException;

public interface NabJavnaNabavkaServiceApi extends BaseServiceApi{

    void createNabJavnaNabavka(NabJavnaNabavka novaNabavka);

    void deleteNabNabavka(NabJavnaNabavka izabranaNabavka) throws FimesServiceException;

}
