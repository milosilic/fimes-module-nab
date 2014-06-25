package rs.fimes.web.controller.nab;

import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlForm;

import org.ajax4jsf.component.html.HtmlAjaxCommandButton;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.domain.nab.NabPlan;
import rs.fimes.service.api.core.UsrKorisnikServiceApi;
import rs.fimes.service.api.nab.NabNaruciociServiceApi;
import rs.fimes.service.api.nab.NabPlanServiceApi;
import rs.fimes.web.controller.BaseController;
import rs.fimes.web.datamodel.api.nab.NabPlanExtendedDataTableModelApi;

public class NabNoviPlanController extends BaseController{

    private NabPlanServiceApi nabPlanServiceApi;
    private NabNaruciociServiceApi nabNaruciociServiceApi;
    private OrgFirma orgFirma;
    private NabPlan noviPlan;
    private HtmlForm formNabNoviPlan;
    private HtmlAjaxCommandButton dugmePotvrdi;
    private UsrKorisnikServiceApi usrKorisnikServiceApi;

    private static final long serialVersionUID = -788600541631559492L;

    public NabNoviPlanController(Module module, String controllerId)
            throws ConfigurationException {
        super(module, controllerId);
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
    
    public void initNoviPlan(){
        System.out.println(" ----> initNoviPlan");
        noviPlan = new NabPlan();
    }
    
    public void snimiNabPlan(){
       NabPlan nabPlan = null;
      
       
    try {
        noviPlan.setOrgFirma(usrKorisnikServiceApi.getUlogovaniKorisnikFirma());
        noviPlan.setUsrKorisnik(getUserSessionUtil().getCurrentUsrKorisnik());
        noviPlan.setTrenutakKreiranja(new java.util.Date());
        nabPlan = nabPlanServiceApi.createNabPlan(noviPlan);
        System.out.println( nabPlan );
        populateModalOkPanelSnimanjeDefaultMessages(true,
                "nabPlanoviNoviPlan");
    } catch (Exception e) {
        populateModalOkPanelSnimanjeDefaultMessages(false,
                "nabPlanoviNoviPlan");
        e.printStackTrace();
    }
       System.out.println( "Završeno snimanje");
       
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

    public NabPlan getNoviPlan() {
        return noviPlan;
    }

    public void setNoviPlan(NabPlan noviPlan) {
        System.out.println(noviPlan);
        this.noviPlan = noviPlan;
    }

    public HtmlForm getFormNabNoviPlan() {
        System.out.println( "ovo he geter form");
        return formNabNoviPlan;
    }

    public void setFormNabNoviPlan(HtmlForm formNabNoviPlan) {
        this.formNabNoviPlan = formNabNoviPlan;
        for (Iterator iterator = formNabNoviPlan.getChildren().iterator(); iterator.hasNext();) {
            UIComponent type = (UIComponent) iterator.next();
            System.out.println( type);
        }
        
    }

    public HtmlAjaxCommandButton getDugmePotvrdi() {
        return dugmePotvrdi;
    }

    public void setDugmePotvrdi(HtmlAjaxCommandButton dugmePotvrdi) {
        this.dugmePotvrdi = dugmePotvrdi;
    }

    public UsrKorisnikServiceApi getUsrKorisnikServiceApi() {
        return usrKorisnikServiceApi;
    }

    public void setUsrKorisnikServiceApi(UsrKorisnikServiceApi usrKorisnikServiceApi) {
        this.usrKorisnikServiceApi = usrKorisnikServiceApi;
    }
    

}
