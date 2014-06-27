package rs.fimes.service.impl.nab;

import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.XnabPredmetNabavkeDAO;
import rs.fimes.domain.nab.XnabPredmetNabavke;
import rs.fimes.service.api.nab.XnabPredmetNabavkeServiceApi;

public class XnabPredmetNabavkeServiceImpl extends BaseServiceImpl implements
        XnabPredmetNabavkeServiceApi {

    private static final long serialVersionUID = -488707840186642269L;
    
    private XnabPredmetNabavkeDAO xnabPredmetNabavkeDAO;

    public XnabPredmetNabavkeServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);

    }

    @Override
    public List<XnabPredmetNabavke> getAllPredmetNabavke() {
        return xnabPredmetNabavkeDAO.getXnabPredmetNabavkeList();
    }

    public XnabPredmetNabavkeDAO getXnabPredmetNabavkeDAO() {
        return xnabPredmetNabavkeDAO;
    }

    public void setXnabPredmetNabavkeDAO(XnabPredmetNabavkeDAO xnabPredmetNabavkeDAO) {
        this.xnabPredmetNabavkeDAO = xnabPredmetNabavkeDAO;
    }

    
}
