package rs.fimes.service.impl.nab;

import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.XnabKriterijumDAO;
import rs.fimes.domain.nab.XnabKriterijum;
import rs.fimes.service.api.nab.XnabKriterijumServiceApi;

public class XnabKriterijumServiceImpl extends BaseServiceImpl
        implements XnabKriterijumServiceApi {

    private static final long serialVersionUID = -1293467406667274933L;
    private XnabKriterijumDAO xnabKriterijumDAO; 



    public XnabKriterijumServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);

    }

    @Override
    public List<XnabKriterijum> getAllKriterijum() {
        
        return xnabKriterijumDAO.getXnabKriterijumList();
    }

    public XnabKriterijumDAO getXnabKriterijumDAO() {
        return xnabKriterijumDAO;
    }

    public void setXnabKriterijumDAO(
            XnabKriterijumDAO xnabKriterijumDAO) {
        this.xnabKriterijumDAO = xnabKriterijumDAO;
    }
    
}
