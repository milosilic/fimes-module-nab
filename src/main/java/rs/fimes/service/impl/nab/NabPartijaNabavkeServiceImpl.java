package rs.fimes.service.impl.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.NabPartijaNabavkeDAO;
import rs.fimes.domain.nab.NabPartijaNabavke;
import rs.fimes.service.api.nab.NabPartijaNabavkeServiceApi;

public class NabPartijaNabavkeServiceImpl extends BaseServiceImpl
implements NabPartijaNabavkeServiceApi{

    private static final long serialVersionUID = -509698140590760044L;
    private NabPartijaNabavkeDAO nabPartijaNabavkeDAO;
    
    public NabPartijaNabavkeServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);
       
    }

    @Override
    public void createNabPartijaNabavke(NabPartijaNabavke novaNabavka) {
      nabPartijaNabavkeDAO.merge(novaNabavka);
        
    }

    public NabPartijaNabavkeDAO getNabPartijaNabavkeDAO() {
        return nabPartijaNabavkeDAO;
    }

    public void setNabPartijaNabavkeDAO(NabPartijaNabavkeDAO nabPartijaNabavkeDAO) {
        this.nabPartijaNabavkeDAO = nabPartijaNabavkeDAO;
    }

}