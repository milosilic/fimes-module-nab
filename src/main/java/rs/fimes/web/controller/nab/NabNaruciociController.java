package rs.fimes.web.controller.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.core.OrgFirmaDAO;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.web.controller.BaseController;

public class NabNaruciociController extends BaseController{
    
    private OrgFirma orgFirma;


    public NabNaruciociController(Module module, String controllerId)
            throws ConfigurationException {
        super(module, controllerId);
    }

    /**
     * 
     */
    private static final long serialVersionUID = -813507081136207329L;
    
    public void onStart() {
        System.out.println( ":::Ukenjao sam se od srece " + System.currentTimeMillis()); 
        orgFirma = (OrgFirma) getUserSessionUtil().getCurrentUserCurrentOrgFirma();

    }

    public OrgFirma getOrgFirma() {
        return orgFirma;
    }

    public void setOrgFirma(OrgFirma orgFirma) {
        this.orgFirma = orgFirma;
    }



}
