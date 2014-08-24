package rs.fimes.service.impl.nab;

import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.NabNabavkaJrnDAO;
import rs.fimes.domain.nab.NabNabavkaJrn;
import rs.fimes.service.api.nab.NabNabavkaJrnServiceApi;

public class NabNabavkaJrnServiceImpl extends BaseServiceImpl
implements NabNabavkaJrnServiceApi{

    private static final long serialVersionUID = -509698140590760044L;
    private NabNabavkaJrnDAO nabNabavkaJrnDAO;
    
    public NabNabavkaJrnServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);
       
    }

    @Override
    public void createNabNabavkaJrn(NabNabavkaJrn novaNabavka) {
      nabNabavkaJrnDAO.merge(novaNabavka);
        
    }

    public NabNabavkaJrnDAO getNabNabavkaJrnDAO() {
        return nabNabavkaJrnDAO;
    }

    public void setNabNabavkaJrnDAO(NabNabavkaJrnDAO nabNabavkaJrnDAO) {
        this.nabNabavkaJrnDAO = nabNabavkaJrnDAO;
    }

    @Override
    public void deleteNabavkaJrn(NabNabavkaJrn nabavkaJrn) {
        NabNabavkaJrn NabavkaJrn = nabNabavkaJrnDAO.findById(nabavkaJrn.getIdNabavkaJrn(), true);
        nabNabavkaJrnDAO.delete(NabavkaJrn);
    }

    @Override
    public List<NabNabavkaJrn> dohvatiNabavkaJrn(Integer idJavnaNabavka) {
       
        return nabNabavkaJrnDAO.dohvatiNabavkaJrn( idJavnaNabavka);
    }

}
