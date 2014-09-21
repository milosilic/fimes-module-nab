package rs.fimes.web.controller.nab;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.utils.MessageBundleProperty;
import rs.fimes.data.dao.generic.QueryJoin;
import rs.fimes.data.dao.generic.QueryRestriction;
import rs.fimes.data.dao.generic.QueryRestrictionComparison1;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.domain.nab.NabPlan;
import rs.fimes.service.api.nab.NabNaruciociServiceApi;
import rs.fimes.service.api.nab.NabPlanServiceApi;
import rs.fimes.service.exception.FimesServiceException;
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
    
    //13.09.2014.
    private String pretragaNazivPlana;
    private String pretragaInterniBrojPlana;
    private String pretragaDatumIzradePocetak;
    private String pretragaDatumIzradeKraj;
    private String pretragaDatumUsvajanjaPocetak;
    private String pretragaDatumUsvajanjaKraj;
    
    

    private static final long serialVersionUID = -788600541631559492L;

    public NabPlanController(Module module, String controllerId)
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
    
    public void initModalDialogBrisanje() {

        initModalDialogBrisanje(
              "nabPlanoviPlanBrisanjeHeader",
              new MessageBundleProperty(
                      "nabPlanBrisanjePitanje"),
              "nabPlanBrisanje()");
              
        
    }
    
    public void obrisiPlan(){
        if ( null != nabPlanSelected ){
            try {
                nabPlanServiceApi.deleteNabPlan( nabPlanSelected);
                populateModalOkPanelSnimanjeDefaultMessages(true,
                        "nabPlanoviPlanBrisanjeHeader");
            } catch (FimesServiceException e) {
                e.printStackTrace();
                populateModalOkPanelBrisanje(false,
                        "nabPlanoviPlanBrisanjeHeader",
                        new MessageBundleProperty(
                                "nabPlanoviPlanBrisanjeImaNabavki"));
            }catch (Exception e) {
                e.printStackTrace();
                populateModalOkPanelSnimanjeDefaultMessages(false,
                        "nabPlanoviPlanBrisanjeHeader");
            } finally {
                resetSelection();
            }

        }
    }
    
    public void initModalDialogPonistavanjeUsvajanja(){

        initModalDialogBrisanje(
                "nabPlanoviPonistiUsvajanje",
                new MessageBundleProperty(
                        "nabPlanPonisitiUsvajanjPitanje"),
                "nabPlanPonistavanjeUsvajanje()");
                
        
    }
    
    public void ponistiUsvajanjePlana(){
        if ( null != nabPlanSelected ){
            try {
                nabPlanSelected.setDatumUsvajanja(null);
                nabPlanServiceApi.createNabPlan(nabPlanSelected);
                populateModalOkPanelSnimanjeDefaultMessages(true,
                        "nabPlanoviPlanBrisanjeHeader");
            }catch (Exception e) {
                e.printStackTrace();
                populateModalOkPanelSnimanjeDefaultMessages(false,
                        "nabPlanoviPlanBrisanjeHeader");
            } finally {
                resetSelection();
            }

        }
        
    }

    public void resetSelection() {
        nabPlanSelected = null;
        nabPlanExtendedDataTableModelApi.clearSelection();
        setKopirajPodatke(false);
        
    }

    public void onStart() {
        orgFirma = (OrgFirma) getUserSessionUtil().getCurrentUserCurrentOrgFirma();
        nabPlanExtendedDataTableModelApi.clearSelection();

        List<QueryRestriction> parametri = new ArrayList<QueryRestriction>();
        parametri.add(QueryRestrictionComparison1.addIsEqual("orgFirma",
                orgFirma));
        nabPlanExtendedDataTableModelApi.setParametri(parametri);
        nabPlanExtendedDataTableModelApi.setDescending(true);
        nabPlanExtendedDataTableModelApi.helperWalkByRequest();

             
    }
    
    public void noviPlan(){
        System.out.println("------------------------>" + kopirajPodatke + "<------------------------");
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
        if (pretragaNazivPlana != null && !pretragaNazivPlana.trim().isEmpty()) {
            parametri.add(QueryRestrictionComparison1
                    .addCirToAbcStringContains(
                            "naziv",
                            getStringUtil().transliterationCirToAbc(
                                    pretragaNazivPlana.trim().replaceAll("\\s+", " "))));
        }
        if (pretragaDatumIzradePocetak != null
                && !pretragaDatumIzradePocetak.trim().isEmpty()) {
            Date vaziOd = getDateTimeUtil().getDateFromString(
                    pretragaDatumIzradePocetak.trim());
            parametri.add(QueryRestrictionComparison1.addGreaterEqual("datumIzrade",
                    vaziOd));
        }

        if (pretragaDatumIzradeKraj != null && !pretragaDatumIzradeKraj.trim().isEmpty()) {
            Date vaziOd = getDateTimeUtil().getDateFromString(
                    pretragaDatumIzradeKraj.trim());
            parametri.add(QueryRestrictionComparison1.addLessEqual("datumIzrade",
                    vaziOd));
        }

        if (pretragaInterniBrojPlana != null && !pretragaInterniBrojPlana.trim().isEmpty()) {
            parametri.add(QueryRestrictionComparison1
                    .addCirToAbcStringContains(
                            "brojPlana",
                            getStringUtil().transliterationCirToAbc(
                                    pretragaInterniBrojPlana.trim().replaceAll("\\s+", " "))));
        }

        if (pretragaDatumUsvajanjaPocetak != null
                && !pretragaDatumUsvajanjaPocetak.trim().isEmpty()) {
            Date vaziOd = getDateTimeUtil().getDateFromString(
                    pretragaDatumUsvajanjaPocetak.trim());
            parametri.add(QueryRestrictionComparison1.addGreaterEqual("datumUsvajanja",
                    vaziOd));
        }

        if (pretragaDatumUsvajanjaKraj != null && !pretragaDatumUsvajanjaKraj.trim().isEmpty()) {
            Date vaziOd = getDateTimeUtil().getDateFromString(
                    pretragaDatumUsvajanjaKraj.trim());
            parametri.add(QueryRestrictionComparison1.addLessEqual("datumUsvajanja",
                    vaziOd));
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

    public String getPretragaNazivPlana() {
        return pretragaNazivPlana;
    }

    public void setPretragaNazivPlana(String pretragaNazivPlana) {
        this.pretragaNazivPlana = pretragaNazivPlana;
    }

    public String getPretragaInterniBrojPlana() {
        return pretragaInterniBrojPlana;
    }

    public void setPretragaInterniBrojPlana(String pretragaInterniBrojPlana) {
        this.pretragaInterniBrojPlana = pretragaInterniBrojPlana;
    }

    public String getPretragaDatumIzradePocetak() {
        return pretragaDatumIzradePocetak;
    }

    public void setPretragaDatumIzradePocetak(String pretragaDatumIzradePocetak) {
        this.pretragaDatumIzradePocetak = pretragaDatumIzradePocetak;
    }

    public String getPretragaDatumIzradeKraj() {
        return pretragaDatumIzradeKraj;
    }

    public void setPretragaDatumIzradeKraj(String pretragaDatumIzradeKraj) {
        this.pretragaDatumIzradeKraj = pretragaDatumIzradeKraj;
    }

    public String getPretragaDatumUsvajanjaPocetak() {
        return pretragaDatumUsvajanjaPocetak;
    }

    public void setPretragaDatumUsvajanjaPocetak(
            String pretragaDatumUsvajanjaPocetak) {
        this.pretragaDatumUsvajanjaPocetak = pretragaDatumUsvajanjaPocetak;
    }

    public String getPretragaDatumUsvajanjaKraj() {
        return pretragaDatumUsvajanjaKraj;
    }

    public void setPretragaDatumUsvajanjaKraj(String pretragaDatumUsvajanjaKraj) {
        this.pretragaDatumUsvajanjaKraj = pretragaDatumUsvajanjaKraj;
    }

    
}
