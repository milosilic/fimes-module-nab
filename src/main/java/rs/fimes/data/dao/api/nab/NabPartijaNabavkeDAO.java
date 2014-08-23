package rs.fimes.data.dao.api.nab;

import java.util.List;

import rs.etf.rc.common.dao.api.BaseDaoApi;
import rs.fimes.domain.nab.NabPartijaNabavke;

public interface NabPartijaNabavkeDAO extends BaseDaoApi<NabPartijaNabavke, Integer> {

    List<NabPartijaNabavke> dohvatiPartijeNabavke(Integer idJavnaNabavka);

    
}
