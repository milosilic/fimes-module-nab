package rs.fimes.web.controller.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.service.api.nab.NabPlanServiceApi;
import rs.fimes.web.controller.BaseController;

public class NabPlanController extends BaseController{

    private NabPlanServiceApi nabPlanServiceApi;

    private static final long serialVersionUID = -788600541631559492L;

    public NabPlanController(Module module, String controllerId)
            throws ConfigurationException {
        super(module, controllerId);
        // TODO Auto-generated constructor stub
    }
    
    public void onStart() {
        
        System.out.println( ":::Ukenjao sam se od srece NAbPlanController " + System.currentTimeMillis());
             
    }

    public NabPlanServiceApi getNabPlanServiceApi() {
        return nabPlanServiceApi;
    }

    public void setNabPlanServiceApi(NabPlanServiceApi nabPlanServiceApi) {
        this.nabPlanServiceApi = nabPlanServiceApi;
    }


}
