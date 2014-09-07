package rs.fimes.data.dao.api.nab;

import rs.etf.rc.common.dao.api.BaseDaoApi;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.domain.nab.NabNabavkaKontoPartija;

public interface NabNabavkaKontoPartijaDAO extends BaseDaoApi<NabNabavkaKontoPartija, Integer> {

    long countAllKontoPoNabavci(NabJavnaNabavka izabranaNabavka);

    
}
