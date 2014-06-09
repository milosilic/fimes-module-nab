package rs.fimes.web.controller.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.service.api.nab.NabNaruciociServiceApi;
import rs.fimes.web.controller.BaseController;

public class NabNaruciociController extends BaseController{
    
    private OrgFirma orgFirma;
    
    private NabNaruciociServiceApi nabNaruciociServiceApi;
    
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
        if ( orgFirma == null ) {
          setOrgFirma(nabNaruciociServiceApi.getActiveOrgFirma(getUserSessionUtil().getCurrentUserCurrentOrgFirma().getIdFirma()));
        }
        
//        orgFirma = (OrgFirma) getUserSessionUtil().getCurrentUserCurrentOrgFirma();
     
    }

    public OrgFirma getOrgFirma() {
        return orgFirma;
    }

    public void setOrgFirma(OrgFirma orgFirma) {
        System.out.println( orgFirma);
        this.orgFirma = orgFirma;
    }
    
    public void setNabNaruciociServiceApi(NabNaruciociServiceApi nabNaruciociServiceApi){
        this.nabNaruciociServiceApi = nabNaruciociServiceApi;
    }
    
    public NabNaruciociServiceApi getNabNaruciociServiceApi(){
        return nabNaruciociServiceApi;
    }


}
