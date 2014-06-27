package rs.fimes.data.dao.api.nab;

import java.util.List;

import rs.etf.rc.common.dao.api.BaseDaoApi;
import rs.fimes.domain.nab.XnabVrstaPredmetaNabavke;

public interface XnabVrstaPredmetaNabavkeDAO extends
        BaseDaoApi<XnabVrstaPredmetaNabavke, Integer> {
    
    List<XnabVrstaPredmetaNabavke> getXnabVrstaPredmetaNabavkeList();

}
