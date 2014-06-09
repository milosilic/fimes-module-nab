package rs.fimes.service.impl.nab;

import java.util.List;

import rs.etf.rc.common.application.Application;
import rs.etf.rc.common.application.ComponentType;
import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.service.impl.BaseServiceImpl;
import rs.etf.rc.common.utils.StringUtil;
import rs.fimes.data.dao.api.core.OrgFirmaDAO;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.service.api.nab.NabNaruciociServiceApi;

public class NabNaruciociServiceImpl extends BaseServiceImpl  implements NabNaruciociServiceApi {

    public NabNaruciociServiceImpl(Module module, String serviceId)
            throws ConfigurationException {
        super(module, serviceId);
        
    }

    private static final long serialVersionUID = -3121554525501306290L;
    
    private OrgFirmaDAO orgFirmaDAO;

    @Override
    public OrgFirma getActiveOrgFirma( int id ) {
        OrgFirma orgFirmica = orgFirmaDAO.findById(id);
        System.out.println( id + "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNs");
        System.out.println( orgFirmica);

        return orgFirmica;
    }

    public OrgFirmaDAO getOrgFirmaDaO() {
        return orgFirmaDAO;
    }

    public void setOrgFirmaDAO(OrgFirmaDAO orgFirmaDAO) {
        this.orgFirmaDAO = orgFirmaDAO;
    }
    
    

   

}
