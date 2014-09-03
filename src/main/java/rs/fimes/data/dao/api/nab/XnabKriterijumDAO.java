package rs.fimes.data.dao.api.nab;

import java.util.List;

import rs.etf.rc.common.dao.api.BaseDaoApi;
import rs.fimes.domain.nab.XnabKriterijum;

public interface XnabKriterijumDAO extends
        BaseDaoApi<XnabKriterijum, Integer> {

    List<XnabKriterijum> getXnabKriterijumList();

}