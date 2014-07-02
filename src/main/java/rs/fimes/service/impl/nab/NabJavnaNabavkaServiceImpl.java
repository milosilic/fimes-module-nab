package rs.fimes.service.impl.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.NabJavnaNabavkaDAO;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.service.api.nab.NabJavnaNabavkaServiceApi;

public class NabJavnaNabavkaServiceImpl extends BaseServiceImpl
implements NabJavnaNabavkaServiceApi{

    private static final long serialVersionUID = -509698140590760044L;
    private NabJavnaNabavkaDAO nabJavnaNabavkaDAO;
    
    public NabJavnaNabavkaServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);
       
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

}
