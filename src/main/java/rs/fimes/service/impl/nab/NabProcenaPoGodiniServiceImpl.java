package rs.fimes.service.impl.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.NabProcenaPoGodiniDAO;
import rs.fimes.domain.nab.NabProcenaPoGodini;
import rs.fimes.service.api.nab.NabProcenaPoGodiniServiceApi;

public class NabProcenaPoGodiniServiceImpl extends BaseServiceImpl
implements NabProcenaPoGodiniServiceApi{

    private static final long serialVersionUID = -509698140590760044L;
    private NabProcenaPoGodiniDAO nabProcenaPoGodiniDAO;
    
    public NabProcenaPoGodiniServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);
       
    }

    @Override
    public void createNabProcenaPoGodini(NabProcenaPoGodini novaNabavka) {
      nabProcenaPoGodiniDAO.merge(novaNabavka);
        
    }

    public NabProcenaPoGodiniDAO getNabProcenaPoGodiniDAO() {
        return nabProcenaPoGodiniDAO;
    }

    public void setNabProcenaPoGodiniDAO(NabProcenaPoGodiniDAO nabProcenaPoGodiniDAO) {
        this.nabProcenaPoGodiniDAO = nabProcenaPoGodiniDAO;
    }

    @Override
    public void deleteProcenaPoGodini(NabProcenaPoGodini nabProcenaPoGodini) {
        NabProcenaPoGodini procena = nabProcenaPoGodiniDAO.findById(nabProcenaPoGodini.getIdProcenaPoGodini(), false);
        nabProcenaPoGodiniDAO.delete(procena);
        
    }

}
