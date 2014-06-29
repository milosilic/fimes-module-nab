package rs.fimes.data.dao.api.nab;

import java.util.List;

import rs.etf.rc.common.dao.api.BaseDaoApi;
import rs.fimes.domain.nab.XnabKonto;

public interface XnabKontoDAO extends
        BaseDaoApi<XnabKonto, Integer> {

    List<XnabKonto> getXnabKontoList();

}