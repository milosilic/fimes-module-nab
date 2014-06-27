package rs.fimes.service.impl.nab;

import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.XnabTipNabavkeDAO;
import rs.fimes.domain.nab.XnabTipNabavke;
import rs.fimes.service.api.nab.XnabTipNabavkeServiceApi;

public class XnabTipNabavkeServiceImpl extends BaseServiceImpl
        implements XnabTipNabavkeServiceApi {

    private static final long serialVersionUID = -1293467406667274933L;
    private XnabTipNabavkeDAO xnabTipNabavkeDAO; 



    public XnabTipNabavkeServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);

    }

    @Override
    public List<XnabTipNabavke> getAllTipNabavke() {
        
        return xnabTipNabavkeDAO.getXnabTipNabavkeList();
    }

    public XnabTipNabavkeDAO getXnabTipNabavkeDAO() {
        return xnabTipNabavkeDAO;
    }

    public void setXnabTipNabavkeDAO(
            XnabTipNabavkeDAO xnabTipNabavkeDAO) {
        this.xnabTipNabavkeDAO = xnabTipNabavkeDAO;
    }
    
}
