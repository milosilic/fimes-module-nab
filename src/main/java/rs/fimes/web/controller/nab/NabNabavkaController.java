package rs.fimes.web.controller.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.service.api.nab.NabNaruciociServiceApi;
import rs.fimes.web.controller.BaseController;

public class NabNabavkaController extends BaseController{
 
    private static final long serialVersionUID = -3120357755799010622L;

    private OrgFirma orgFirma;
    
    private NabNaruciociServiceApi nabNaruciociServiceApi;
    
    public NabNabavkaController(Module module, String controllerId)
            throws ConfigurationException {
        super(module, controllerId);
    }
    
    public void onStart() {
       
        System.out.println( ":::Ukenjao sam se od srece " + System.currentTimeMillis()+ " NabNabavkaController");
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
