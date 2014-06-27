package rs.fimes.data.dao.api.nab;

import java.util.List;

import rs.etf.rc.common.dao.api.BaseDaoApi;
import rs.fimes.domain.nab.XnabTipNabavke;

public interface XnabTipNabavkeDAO extends
        BaseDaoApi<XnabTipNabavke, Integer> {

    List<XnabTipNabavke> getXnabTipNabavkeList();

}
