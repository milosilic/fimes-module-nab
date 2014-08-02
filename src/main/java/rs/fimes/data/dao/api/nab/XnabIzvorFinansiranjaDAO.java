package rs.fimes.data.dao.api.nab;

import java.util.List;

import rs.etf.rc.common.dao.api.BaseDaoApi;
import rs.fimes.domain.nab.XnabIzvorFinansiranja;

public interface XnabIzvorFinansiranjaDAO extends
        BaseDaoApi<XnabIzvorFinansiranja, Integer> {

    List<XnabIzvorFinansiranja> getXnabIzvorFinansiranjaList();

}
