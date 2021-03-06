package rs.fimes.data.dao.api.nab;

import java.util.List;

import rs.etf.rc.common.dao.api.BaseDaoApi;
import rs.fimes.domain.nab.XnabJrn;

public interface XnabJrnDAO extends
        BaseDaoApi<XnabJrn, Integer> {

    List<XnabJrn> getXnabJrnList();

}
