package rs.fimes.web.controller.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.domain.nab.NabPlan;
import rs.fimes.service.api.nab.NabNaruciociServiceApi;
import rs.fimes.service.api.nab.NabPlanServiceApi;
import rs.fimes.web.controller.BaseController;
import rs.fimes.web.datamodel.api.nab.NabPlanExtendedDataTableModelApi;

public class NabPlanController extends BaseController{

    private NabPlanServiceApi nabPlanServiceApi;
    private NabNaruciociServiceApi nabNaruciociServiceApi;
    private OrgFirma orgFirma;
    private NabPlanExtendedDataTableModelApi nabPlanExtendedDataTableModelApi;
    private NabPlan nabPlanSelected;
    

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
        nabPlanExtendedDataTableModelApi.helperWalkByRequest();
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

    public NabPlanExtendedDataTableModelApi getNabPlanExtendedDataTableModelApi() {
        return nabPlanExtendedDataTableModelApi;
    }

    public void setNabPlanExtendedDataTableModelApi(
            NabPlanExtendedDataTableModelApi nabPlanExtendedDataTableModelApi) {
        this.nabPlanExtendedDataTableModelApi = nabPlanExtendedDataTableModelApi;
    }

    public NabPlan getNabPlanSelected() {
        System.out.println( "selektovao si plan : " + nabPlanSelected);
        return nabPlanSelected;
    }

    public void setNabPlanSelected(NabPlan nabPlanSelected) {
        System.out.println( nabPlanSelected);
        this.nabPlanSelected = nabPlanSelected;
    }
}
