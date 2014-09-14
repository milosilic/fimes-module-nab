package rs.fimes.service.impl.nab;

import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.XnabVrstaPredmetaNabavkeDAO;
import rs.fimes.domain.nab.XnabVrstaPredmetaNabavke;
import rs.fimes.service.api.nab.XnabVrstaPredmetaNabavkeServiceApi;

public class XnabVrstaPredmetaNabavkeServiceImpl extends BaseServiceImpl
        implements XnabVrstaPredmetaNabavkeServiceApi {

    private static final long serialVersionUID = -1293467406667274933L;
    private XnabVrstaPredmetaNabavkeDAO xnabVrstaPredmetaNabavkeDAO; 
    @Override
    public XnabVrstaPredmetaNabavke findById(int idVrstaPredmetaNabavke) {

        return xnabVrstaPredmetaNabavkeDAO.findById(new Integer(idVrstaPredmetaNabavke), false);
    }


    public XnabVrstaPredmetaNabavkeServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);

    }

    @Override
    public List<XnabVrstaPredmetaNabavke> getAllVrstaPredmetaNabavke() {
        
        return xnabVrstaPredmetaNabavkeDAO.getXnabVrstaPredmetaNabavkeList();
    }

    public XnabVrstaPredmetaNabavkeDAO getXnabVrstaPredmetaNabavkeDAO() {
        return xnabVrstaPredmetaNabavkeDAO;
    }

    public void setXnabVrstaPredmetaNabavkeDAO(
            XnabVrstaPredmetaNabavkeDAO xnabVrstaPredmetaNabavkeDAO) {
        this.xnabVrstaPredmetaNabavkeDAO = xnabVrstaPredmetaNabavkeDAO;
    }


    @Override
    public XnabVrstaPredmetaNabavke getNabVrstaPredmetaNabavkeById(
            int idVrstaPredmetaNabavke) {
 
        return xnabVrstaPredmetaNabavkeDAO.findById(idVrstaPredmetaNabavke, true);
    }

    
}
