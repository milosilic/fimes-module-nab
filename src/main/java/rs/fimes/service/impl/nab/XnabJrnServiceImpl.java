package rs.fimes.service.impl.nab;

import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.XnabJrnDAO;
import rs.fimes.domain.nab.XnabJrn;
import rs.fimes.service.api.nab.XnabJrnServiceApi;

public class XnabJrnServiceImpl extends BaseServiceImpl
        implements XnabJrnServiceApi {

    private static final long serialVersionUID = -1293467406667274933L;
    private XnabJrnDAO xnabJrnDAO; 



    public XnabJrnServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);

    }

    @Override
    public List<XnabJrn> getAllJrn() {
        
        return xnabJrnDAO.getXnabJrnList();
    }

    public XnabJrnDAO getXnabJrnDAO() {
        return xnabJrnDAO;
    }

    public void setXnabJrnDAO(
            XnabJrnDAO xnabJrnDAO) {
        this.xnabJrnDAO = xnabJrnDAO;
    }
    
}
