package rs.fimes.service.api.nab;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.nab.NabNabavkaKontoPartija;

public interface NabNabavkaKontoPartijaServiceApi extends BaseServiceApi{

    void createNabNabavkaKontoPartija(NabNabavkaKontoPartija novaNabavka);

    void deleteNabavkaKontoPartija(NabNabavkaKontoPartija novaPartija);

}
