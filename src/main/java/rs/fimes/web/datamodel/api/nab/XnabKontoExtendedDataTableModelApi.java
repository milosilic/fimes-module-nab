package rs.fimes.web.datamodel.api.nab;

import rs.fimes.data.dao.api.nab.XnabKontoDAO;
import rs.fimes.domain.nab.XnabKonto;
import rs.fimes.web.datamodel.FimesExtendedDataTableModelApi;

public interface XnabKontoExtendedDataTableModelApi extends
        FimesExtendedDataTableModelApi<XnabKontoDAO, XnabKonto, Integer> {

}
