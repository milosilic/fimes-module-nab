package rs.fimes.data.dao.api.nab;

import java.util.List;

import rs.etf.rc.common.dao.api.BaseDaoApi;
import rs.fimes.domain.nab.XnabOsnovIzuzeca;

public interface XnabOsnovIzuzecaDAO extends
        BaseDaoApi<XnabOsnovIzuzeca, Integer> {

    List<XnabOsnovIzuzeca> getXnabOsnovIzuzecaList();

}
