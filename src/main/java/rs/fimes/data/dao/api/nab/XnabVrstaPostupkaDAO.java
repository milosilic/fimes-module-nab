package rs.fimes.data.dao.api.nab;

import java.util.List;

import rs.etf.rc.common.dao.api.BaseDaoApi;
import rs.fimes.domain.nab.XnabVrstaPostupka;

public interface XnabVrstaPostupkaDAO extends
        BaseDaoApi<XnabVrstaPostupka, Integer> {

    List<XnabVrstaPostupka> getXnabVrstaPostupkaList();

}
