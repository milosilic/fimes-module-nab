package rs.fimes.service.impl.nab;

import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.NabNabavkaKontoPartijaDAO;
import rs.fimes.data.dao.api.nab.XnabKontoDAO;
import rs.fimes.domain.nab.NabUgovor;
import rs.fimes.domain.nab.XnabKonto;
import rs.fimes.service.api.nab.XnabKontoServiceApi;
import rs.fimes.service.exception.FimesServiceException;

public class XnabKontoServiceImpl extends BaseServiceImpl
        implements XnabKontoServiceApi {

    private static final long serialVersionUID = -1293467406667274933L;
    private XnabKontoDAO xnabKontoDAO; 
    private NabNabavkaKontoPartijaDAO nabNabavkaKontoPartijaDAO;



    public XnabKontoServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);

    }

    @Override
    public List<XnabKonto> getAllKonto() {
        
        return xnabKontoDAO.getXnabKontoList();
    }

    public XnabKontoDAO getXnabKontoDAO() {
        return xnabKontoDAO;
    }

    public void setXnabKontoDAO(
            XnabKontoDAO xnabKontoDAO) {
        this.xnabKontoDAO = xnabKontoDAO;
    }


    public NabNabavkaKontoPartijaDAO getNabNabavkaKontoPartijaDAO() {
        return nabNabavkaKontoPartijaDAO;
    }

    public void setNabNabavkaKontoPartijaDAO(NabNabavkaKontoPartijaDAO nabNabavkaKontoPartijaDAO) {
        this.nabNabavkaKontoPartijaDAO = nabNabavkaKontoPartijaDAO;
    }

    public void deleteKonto(XnabKonto xnabKontoSelected) throws FimesServiceException {
        if ( nabNabavkaKontoPartijaDAO.countAllPoXnabKontu( xnabKontoSelected) > 0) {
            throw new FimesServiceException("nabKontoBrisanjePostojeReference");
        }
        XnabKonto konto = xnabKontoDAO.findById(xnabKontoSelected.getIdKonto(), false);
        xnabKontoDAO.delete(konto);
        
    }
    
}
