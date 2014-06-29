package rs.fimes.web.datamodel.impl.nab;

import rs.fimes.data.dao.api.nab.XnabKontoDAO;
import rs.fimes.domain.nab.XnabKonto;
import rs.fimes.web.datamodel.FimesExtendedDataTableModelImpl;
import rs.fimes.web.datamodel.api.nab.XnabKontoExtendedDataTableModelApi;

public class XnabKontoExtendedDataTableModelImpl extends
        FimesExtendedDataTableModelImpl<XnabKontoDAO, XnabKonto, Integer> implements
        XnabKontoExtendedDataTableModelApi {

    private static final long serialVersionUID = 2533327380034952021L;

}
