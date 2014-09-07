package rs.fimes.service.impl.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.NabJavnaNabavkaDAO;
import rs.fimes.data.dao.api.nab.NabProcenaPoGodiniDAO;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.service.api.nab.NabJavnaNabavkaServiceApi;
import rs.fimes.service.exception.FimesServiceException;

public class NabJavnaNabavkaServiceImpl extends BaseServiceImpl
implements NabJavnaNabavkaServiceApi{

    private static final long serialVersionUID = -509698140590760044L;
    private NabJavnaNabavkaDAO nabJavnaNabavkaDAO;
    private NabProcenaPoGodiniDAO nabProcenaPoGodiniDAO;
    
    public NabJavnaNabavkaServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);
       
    }
    @Override
    public void deleteNabNabavka(NabJavnaNabavka izabranaNabavka) throws FimesServiceException {
       if( nabProcenaPoGodiniDAO.countAllProcenaPoNabavci(izabranaNabavka) >0){
           throw new FimesServiceException("Procena");
       }
    }


    @Override
    public void createNabJavnaNabavka(NabJavnaNabavka novaNabavka) {
      nabJavnaNabavkaDAO.merge(novaNabavka);
        
    }

    public NabJavnaNabavkaDAO getNabJavnaNabavkaDAO() {
        return nabJavnaNabavkaDAO;
    }

    public void setNabJavnaNabavkaDAO(NabJavnaNabavkaDAO nabJavnaNabavkaDAO) {
        this.nabJavnaNabavkaDAO = nabJavnaNabavkaDAO;
    }

    public NabProcenaPoGodiniDAO getNabProcenaPoGodiniDAO() {
        return nabProcenaPoGodiniDAO;
    }

    public void setNabProcenaPoGodiniDAO(NabProcenaPoGodiniDAO nabProcenaPoGodiniDAO) {
        this.nabProcenaPoGodiniDAO = nabProcenaPoGodiniDAO;
    }

}
