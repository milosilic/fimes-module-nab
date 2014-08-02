package rs.fimes.service.impl.nab;

import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.XnabIzvorFinansiranjaDAO;
import rs.fimes.domain.nab.XnabIzvorFinansiranja;
import rs.fimes.service.api.nab.XnabIzvorFinansiranjaServiceApi;

public class XnabIzvorFinansiranjaServiceImpl extends BaseServiceImpl implements
        XnabIzvorFinansiranjaServiceApi {

    private static final long serialVersionUID = 2887585299901281815L;
    private XnabIzvorFinansiranjaDAO xnabIzvorFinansiranjaDAO;

    public XnabIzvorFinansiranjaServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);

    }

    @Override
    public List<XnabIzvorFinansiranja> getAllIzvorFinansiranja() {
        
        return xnabIzvorFinansiranjaDAO.getXnabIzvorFinansiranjaList();
    }

    public XnabIzvorFinansiranjaDAO getXnabIzvorFinansiranjaDAO() {
        return xnabIzvorFinansiranjaDAO;
    }

    public void setXnabIzvorFinansiranjaDAO(XnabIzvorFinansiranjaDAO xnabIzvorFinansiranjaDAO) {
        this.xnabIzvorFinansiranjaDAO = xnabIzvorFinansiranjaDAO;
    }
    
    

}
