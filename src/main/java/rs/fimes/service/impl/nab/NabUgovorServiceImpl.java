package rs.fimes.service.impl.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.NabUgovorDAO;
import rs.fimes.domain.nab.NabUgovor;
import rs.fimes.service.api.nab.NabUgovorServiceApi;

public class NabUgovorServiceImpl extends BaseServiceImpl
implements NabUgovorServiceApi{

    private static final long serialVersionUID = -509698140590760044L;
    private NabUgovorDAO nabUgovorDAO;
    
    public NabUgovorServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);
       
    }

    @Override
    public void createNabUgovor(NabUgovor novaNabavka) {
      nabUgovorDAO.merge(novaNabavka);
        
    }

    public NabUgovorDAO getNabUgovorDAO() {
        return nabUgovorDAO;
    }

    public void setNabUgovorDAO(NabUgovorDAO nabUgovorDAO) {
        this.nabUgovorDAO = nabUgovorDAO;
    }

    @Override
    public void deleteUgovor(NabUgovor nabUgovor) {
        NabUgovor ugovor = nabUgovorDAO.findById(nabUgovor.getIdUgovor(), false);
        nabUgovorDAO.delete(ugovor);
        
    }

}
