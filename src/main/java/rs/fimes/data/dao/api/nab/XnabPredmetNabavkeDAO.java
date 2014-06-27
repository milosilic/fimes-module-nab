package rs.fimes.data.dao.api.nab;

import java.util.List;

import rs.etf.rc.common.dao.api.BaseDaoApi;
import rs.fimes.domain.nab.XnabPredmetNabavke;

public interface XnabPredmetNabavkeDAO extends
        BaseDaoApi<XnabPredmetNabavke, Integer> {

    List<XnabPredmetNabavke> getXnabPredmetNabavkeList();

}
