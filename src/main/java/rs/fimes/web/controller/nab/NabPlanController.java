package rs.fimes.web.controller.nab;

import java.util.ArrayList;
import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.generic.QueryJoin;
import rs.fimes.data.dao.generic.QueryRestriction;
import rs.fimes.data.dao.generic.QueryRestrictionComparison1;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.domain.nab.NabPlan;
import rs.fimes.service.api.nab.NabNaruciociServiceApi;
import rs.fimes.service.api.nab.NabPlanServiceApi;
import rs.fimes.web.controller.BaseController;
import rs.fimes.web.datamodel.api.nab.NabJavnaNabavkaExtendedDataTableModelApi;
import rs.fimes.web.datamodel.api.nab.NabPlanExtendedDataTableModelApi;

public class NabPlanController extends BaseController{

    private NabPlanServiceApi nabPlanServiceApi;
    private NabNaruciociServiceApi nabNaruciociServiceApi;
    private OrgFirma orgFirma;
    private NabPlanExtendedDataTableModelApi nabPlanExtendedDataTableModelApi;
    private NabPlan nabPlanSelected;
    //05.07.2014.
    private NabJavnaNabavkaExtendedDataTableModelApi nabJavnaNabavkaExtendedDataTableModelApi;
    
    //29.08.2014.
    private boolean kopirajPodatke;
    private NabNoviPlanController nabNoviPlanController;
    private String godina;

    

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
    
    public void initModalDialogBrisanje() {
        
    }

    public void onStart() {
        nabPlanExtendedDataTableModelApi.helperWalkByRequest();
        if ( orgFirma == null ) {
            setOrgFirma(nabNaruciociServiceApi.getActiveOrgFirma(getUserSessionUtil().getCurrentUserCurrentOrgFirma().getIdFirma()));
          }
             
    }
    
    public void noviPlan(){
        if ( kopirajPodatke ){
            nabNoviPlanController.setIzabraniPlan( nabPlanSelected);
        }else{
            nabNoviPlanController.setIzabraniPlan( null);
        }
    }
    
    public void planPretraga(){
        List<QueryRestriction> parametri = new ArrayList<QueryRestriction>();
 
        godina = godina.trim();
        Integer intGodina = null;
        try {
            intGodina= Integer.valueOf(godina);
        } catch (NumberFormatException e) {
            godina=null;
        }catch (NullPointerException e) {
            godina = null;
        }
        
        if( godina != null) {
            parametri.add( QueryRestrictionComparison1.addIsEqual("godina", intGodina.intValue()));
        }
        
        nabPlanExtendedDataTableModelApi.setParametri(parametri);
        nabPlanExtendedDataTableModelApi.clearSelection();
        
        
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
        return nabPlanSelected;
    }

    public void setNabPlanSelected(NabPlan nabPlanSelected) {
        System.out.println( nabPlanSelected);
        this.nabPlanSelected = nabPlanSelected;
    }

    public NabJavnaNabavkaExtendedDataTableModelApi getNabJavnaNabavkaExtendedDataTableModelApi() {
        List<QueryRestriction> parametri = new ArrayList<QueryRestriction>();
        parametri.add(QueryRestrictionComparison1.addIsEqual("nabPlan", nabPlanSelected));
        nabJavnaNabavkaExtendedDataTableModelApi.setParametri(parametri);
        return nabJavnaNabavkaExtendedDataTableModelApi;
    }

    public void setNabJavnaNabavkaExtendedDataTableModelApi(
            NabJavnaNabavkaExtendedDataTableModelApi nabJavnaNabavkaExtendedDataTableModelApi) {
        this.nabJavnaNabavkaExtendedDataTableModelApi = nabJavnaNabavkaExtendedDataTableModelApi;
    }

    public boolean isKopirajPodatke() {
        return kopirajPodatke;
    }

    public void setKopirajPodatke(boolean kopirajPodatke) {
        this.kopirajPodatke = kopirajPodatke;
    }

    public NabNoviPlanController getNabNoviPlanController() {
        return nabNoviPlanController;
    }

    public void setNabNoviPlanController(NabNoviPlanController nabNoviPlanController) {
        this.nabNoviPlanController = nabNoviPlanController;
    }

    public String getGodina() {
        return godina;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

    
}
