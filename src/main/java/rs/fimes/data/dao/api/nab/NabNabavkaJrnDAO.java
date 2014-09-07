package rs.fimes.data.dao.api.nab;

import java.util.List;

import rs.etf.rc.common.dao.api.BaseDaoApi;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.domain.nab.NabNabavkaJrn;

public interface NabNabavkaJrnDAO extends BaseDaoApi<NabNabavkaJrn, Integer> {

    List<NabNabavkaJrn> dohvatiNabavkaJrn(Integer idJavnaNabavka);

    long countAllJrnByNabavka(NabJavnaNabavka izabranaNabavka);

    
}
