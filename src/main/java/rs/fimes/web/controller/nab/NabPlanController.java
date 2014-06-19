package rs.fimes.web.controller.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.service.api.nab.NabNaruciociServiceApi;
import rs.fimes.service.api.nab.NabPlanServiceApi;
import rs.fimes.web.controller.BaseController;

public class NabPlanController extends BaseController{

    private NabPlanServiceApi nabPlanServiceApi;
    private NabNaruciociServiceApi nabNaruciociServiceApi;
    private OrgFirma orgFirma;

    private static final long serialVersionUID = -788600541631559492L;

    public NabPlanController(Module module, String controllerId)
            throws ConfigurationException {
        super(module, controllerId);
        // TODO Auto-generated constructor stub
    }
    
    public NabNaruciociServiceApi getNabNaruciociServiceApi() {
        return nabNaruciociServiceApi;
    }

    public void setNabNaruciociServiceApi(
            NabNaruciociServiceApi nabNaruciociServiceApi) {
        this.nabNaruciociServiceApi = nabNaruciociServiceApi;
    }

    public void onStart() {
        
        System.out.println( ":::Ukenjao sam se od srece NAbPlanController " + System.currentTimeMillis());
        if ( orgFirma == null ) {
            setOrgFirma(nabNaruciociServiceApi.getActiveOrgFirma(getUserSessionUtil().getCurrentUserCurrentOrgFirma().getIdFirma()));
          }
             
    }

    public NabPlanServiceApi getNabPlanServiceApi() {
        return nabPlanServiceApi;
    }

    public void setNabPlanServiceApi(NabPlanServiceApi nabPlanServiceApi) {
        this.nabPlanServiceApi = nabPlanServiceApi;
    }

    public OrgFirma getOrgFirma() {
        return orgFirma;
    }

    public void setOrgFirma(OrgFirma orgFirma) {
        this.orgFirma = orgFirma;
    }


}
