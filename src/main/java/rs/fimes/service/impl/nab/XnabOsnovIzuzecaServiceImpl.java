package rs.fimes.service.impl.nab;

import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.XnabOsnovIzuzecaDAO;
import rs.fimes.domain.nab.XnabOsnovIzuzeca;
import rs.fimes.service.api.nab.XnabOsnovIzuzecaServiceApi;

public class XnabOsnovIzuzecaServiceImpl extends BaseServiceImpl implements
        XnabOsnovIzuzecaServiceApi {

    private static final long serialVersionUID = 2887585299901281815L;
    private XnabOsnovIzuzecaDAO xnabOsnovIzuzecaDAO;

    public XnabOsnovIzuzecaServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);

    }

    @Override
    public List<XnabOsnovIzuzeca> getAllOsnovIzuzeca() {
        
        return xnabOsnovIzuzecaDAO.getXnabOsnovIzuzecaList();
    }

    public XnabOsnovIzuzecaDAO getXnabOsnovIzuzecaDAO() {
        return xnabOsnovIzuzecaDAO;
    }

    public void setXnabOsnovIzuzecaDAO(XnabOsnovIzuzecaDAO xnabOsnovIzuzecaDAO) {
        this.xnabOsnovIzuzecaDAO = xnabOsnovIzuzecaDAO;
    }
    
    

}
