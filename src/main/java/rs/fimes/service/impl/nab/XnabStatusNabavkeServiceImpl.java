package rs.fimes.service.impl.nab;

import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.XnabStatusNabavkeDAO;
import rs.fimes.domain.nab.XnabStatusNabavke;
import rs.fimes.service.api.nab.XnabStatusNabavkeServiceApi;

public class XnabStatusNabavkeServiceImpl extends BaseServiceImpl
        implements XnabStatusNabavkeServiceApi {

    private static final long serialVersionUID = -1293467406667274933L;
    private XnabStatusNabavkeDAO xnabStatusNabavkeDAO; 



    public XnabStatusNabavkeServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);

    }

    @Override
    public List<XnabStatusNabavke> getAllStatusNabavke() {
        
        return xnabStatusNabavkeDAO.getXnabStatusNabavkeList();
    }

    public XnabStatusNabavkeDAO getXnabStatusNabavkeDAO() {
        return xnabStatusNabavkeDAO;
    }

    public void setXnabStatusNabavkeDAO(
            XnabStatusNabavkeDAO xnabStatusNabavkeDAO) {
        this.xnabStatusNabavkeDAO = xnabStatusNabavkeDAO;
    }
    
}
