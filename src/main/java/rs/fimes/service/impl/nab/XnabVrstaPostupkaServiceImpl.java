package rs.fimes.service.impl.nab;

import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.data.dao.api.nab.XnabVrstaPostupkaDAO;
import rs.fimes.domain.nab.XnabVrstaPostupka;
import rs.fimes.service.api.nab.XnabVrstaPostupkaServiceApi;

public class XnabVrstaPostupkaServiceImpl extends BaseServiceImpl implements
        XnabVrstaPostupkaServiceApi {

    private static final long serialVersionUID = 2887585299901281815L;
    private XnabVrstaPostupkaDAO xnabVrstaPostupkaDAO;

    public XnabVrstaPostupkaServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);

    }

    @Override
    public List<XnabVrstaPostupka> getAllVrstaPostupka() {
        
        return xnabVrstaPostupkaDAO.getXnabVrstaPostupkaList();
    }

    public XnabVrstaPostupkaDAO getXnabVrstaPostupkaDAO() {
        return xnabVrstaPostupkaDAO;
    }

    public void setXnabVrstaPostupkaDAO(XnabVrstaPostupkaDAO xnabVrstaPostupkaDAO) {
        this.xnabVrstaPostupkaDAO = xnabVrstaPostupkaDAO;
    }

    @Override
    public XnabVrstaPostupka getNabVrstaPostupkaNabavkeById(
            int idVrstaPostupkaNabavke) {

        return xnabVrstaPostupkaDAO.findById(idVrstaPostupkaNabavke, false);
    }
    
    

}
