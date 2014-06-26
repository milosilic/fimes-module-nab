package rs.fimes.service.impl.nab;

import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.fimes.domain.nab.XnabVrstaPostupka;
import rs.fimes.service.api.nab.XnabVrstaPostupkaServiceApi;

public class XnabVrstaPostupkaServiceImpl extends BaseServiceImpl implements
        XnabVrstaPostupkaServiceApi {

    private static final long serialVersionUID = 2887585299901281815L;

    public XnabVrstaPostupkaServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);

    }

    @Override
    public List<XnabVrstaPostupka> getAllVrstaPostupka() {
        // TODO Auto-generated method stub
        return null;
    }

}
