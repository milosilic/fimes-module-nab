package rs.fimes.web.datamodel.impl.nab;

import rs.fimes.data.dao.api.nab.XnabJrnDAO;
import rs.fimes.domain.nab.XnabJrn;
import rs.fimes.web.datamodel.FimesExtendedDataTableModelImpl;
import rs.fimes.web.datamodel.api.nab.XnabJrnExtendedDataTableModelApi;

public class XnabJrnExtendedDataTableModelImpl extends
        FimesExtendedDataTableModelImpl<XnabJrnDAO, XnabJrn, Integer> implements
        XnabJrnExtendedDataTableModelApi {

    private static final long serialVersionUID = 2533327380034952021L;

}
