package rs.fimes.service.impl.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.NabNabavkaKontoPartijaDAO;
import rs.fimes.domain.nab.NabNabavkaKontoPartija;
import rs.fimes.service.api.nab.NabNabavkaKontoPartijaServiceApi;

public class NabNabavkaKontoPartijaServiceImpl extends BaseServiceImpl
implements NabNabavkaKontoPartijaServiceApi{

    private static final long serialVersionUID = -509698140590760044L;
    private NabNabavkaKontoPartijaDAO NabNabavkaKontoPartijaDAO;
    
    public NabNabavkaKontoPartijaServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);
       
    }

    @Override
    public void createNabNabavkaKontoPartija(NabNabavkaKontoPartija novaNabavka) {
      NabNabavkaKontoPartijaDAO.merge(novaNabavka);
        
    }

    public NabNabavkaKontoPartijaDAO getNabNabavkaKontoPartijaDAO() {
        return NabNabavkaKontoPartijaDAO;
    }

    public void setNabNabavkaKontoPartijaDAO(NabNabavkaKontoPartijaDAO NabNabavkaKontoPartijaDAO) {
        this.NabNabavkaKontoPartijaDAO = NabNabavkaKontoPartijaDAO;
    }
    
    @Override
    public void deleteNabavkaKontoPartija(NabNabavkaKontoPartija novaPartija) {
        NabNabavkaKontoPartija partijaNabavke = NabNabavkaKontoPartijaDAO.findById(novaPartija.getIdNabavkaKontoPartija(), true);
        NabNabavkaKontoPartijaDAO.delete(partijaNabavke);
       
    }

}
