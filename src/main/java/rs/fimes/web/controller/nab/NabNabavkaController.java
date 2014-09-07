package rs.fimes.web.controller.nab;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.utils.MessageBundleProperty;
import rs.fimes.data.dao.generic.QueryRestriction;
import rs.fimes.data.dao.generic.QueryRestrictionComparison1;
import rs.fimes.data.domain.obj.XobjTipObjekta;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.domain.nab.NabPlan;
import rs.fimes.domain.nab.XnabVrstaPostupka;
import rs.fimes.domain.nab.XnabVrstaPredmetaNabavke;
import rs.fimes.service.api.nab.NabJavnaNabavkaServiceApi;
import rs.fimes.service.api.nab.NabNaruciociServiceApi;
import rs.fimes.service.api.nab.XnabVrstaPostupkaServiceApi;
import rs.fimes.service.api.nab.XnabVrstaPredmetaNabavkeServiceApi;
import rs.fimes.service.exception.FimesServiceException;
import rs.fimes.web.controller.BaseController;
import rs.fimes.web.datamodel.api.nab.NabJavnaNabavkaExtendedDataTableModelApi;

public class NabNabavkaController extends BaseController{
 
    private static final long serialVersionUID = -3120357755799010622L;

    private OrgFirma orgFirma;
    
    private NabNaruciociServiceApi nabNaruciociServiceApi;
    //28.07.2014.
    private NabJavnaNabavka izabranaNabavka;
    private NabJavnaNabavkaExtendedDataTableModelApi nabJavnaNabavkaExtendedDataTableModelApi;
    
    //29.07.2014.
    private NabJavnaNabavka novaNabavka;

    private NabNovaNabavkaController nabNovaNabavkaController;
    
    //31.07.2014.
    private NabPlan nabPlan;
    
    //04.09.2014.
    private int idVrstaPredmetaNabavke; 
    private int idVrstaPostupka;
    //ova 4 su prekopirana iz NabNovaNabavkaController-a - prekršen DRY princip
    private ArrayList<SelectItem> xnabVrstaPredmetaNabavkeSelectionItems;
    private ArrayList<SelectItem> xnabVrstaPostupkaSelectionItems;
    private XnabVrstaPredmetaNabavkeServiceApi xnabVrstaPredmetaNabavkeServiceApi;
    private XnabVrstaPostupkaServiceApi xnabVrstaPostupkaServiceApi; 
    
    //07.09.2014.
    private NabJavnaNabavkaServiceApi nabJavnaNabavkaServiceApi;
    
    public NabNabavkaController(Module module, String controllerId)
            throws ConfigurationException {
        super(module, controllerId);
    }
    
    public void onStart() {
       
        if ( orgFirma == null ) {
          setOrgFirma(nabNaruciociServiceApi.getActiveOrgFirma(getUserSessionUtil().getCurrentUserCurrentOrgFirma().getIdFirma()));
        }
        
        nabJavnaNabavkaExtendedDataTableModelApi.helperWalkByRequest();
        nabNovaNabavkaController.onStart();
        xnabVrstaPredmetaNabavkeSelectionItems = nabNovaNabavkaController.getXnabVrstaPredmetaNabavkeSelectionItems();
        xnabVrstaPostupkaSelectionItems = nabNovaNabavkaController.getXnabVrstaPostupkaSelectionItems();
//        orgFirma = (OrgFirma) getUserSessionUtil().getCurrentUserCurrentOrgFirma();
     
    }
    
    public void initModalDialogBrisanje(){
        initModalDialogBrisanje(
                "nabNabavkaNabavkaBrisanjeHeader",
                new MessageBundleProperty(
                        "nabNabavkaNabavkaBrisanjePitanje"),
                "jsNabNabavkaBrisanje()");
   
    }
    
    public void obrisiNabavku(){
        System.out.println( "Započet proces brisanja");
        try {
            if (null != izabranaNabavka) {
                nabJavnaNabavkaServiceApi.deleteNabNabavka(izabranaNabavka);
                populateModalOkPanelSnimanjeDefaultMessages(true,
                        "nabPlanoviPlanBrisanjeHeader");

            }
        } catch (FimesServiceException e) {
            e.printStackTrace();
            populateModalOkPanelBrisanje(false, "nabPlanoviPlanBrisanjeHeader",
                    new MessageBundleProperty(
                            "nabPlanoviPlanBrisanjeImaNabavki"));
        } catch (Exception e) {
            e.printStackTrace();
            populateModalOkPanelSnimanjeDefaultMessages(false,
                    "nabPlanoviPlanBrisanjeHeader");
        } finally {
            resetSelection();
        }
    }
    public void pretraga(){
        List<QueryRestriction> parametri = new ArrayList<QueryRestriction>();
                
        if (idVrstaPredmetaNabavke != 0) {
            XnabVrstaPredmetaNabavke xnabVrstaPredmetaNabavke = xnabVrstaPredmetaNabavkeServiceApi
                    .findById(idVrstaPredmetaNabavke);
            parametri.add(QueryRestrictionComparison1.addIsEqual(
                    "vrstaPredmetaNabavke", xnabVrstaPredmetaNabavke));
        }

        nabJavnaNabavkaExtendedDataTableModelApi.setParametri(parametri);
        //nabJavnaNabavkaExtendedDataTableModelApi.setSortField("vrstaPredmetaNabavke.naziv,naziv");
        resetSelection();

    }
    
    public void handleSelection(){
        izabranaNabavka = nabJavnaNabavkaExtendedDataTableModelApi.getDomainObject();
     }

    @Deprecated
    /**
     * korisitilo kao akcija na dugmetu ažuriranje nabavke u nabNabavkeLista.jspx
     */
    public void azurirajNabavku(){
        nabNovaNabavkaController.setNovaNabavka(izabranaNabavka);
    }
    
    public void resetSelection(){
        izabranaNabavka=null;
        nabJavnaNabavkaExtendedDataTableModelApi.clearSelection();
    }
    

    public OrgFirma getOrgFirma() {
        return orgFirma;
    }

    public void setOrgFirma(OrgFirma orgFirma) {
        this.orgFirma = orgFirma;
    }
    
    public void setNabNaruciociServiceApi(NabNaruciociServiceApi nabNaruciociServiceApi){
        this.nabNaruciociServiceApi = nabNaruciociServiceApi;
    }
    
    public NabNaruciociServiceApi getNabNaruciociServiceApi(){
        return nabNaruciociServiceApi;
    }

    public NabJavnaNabavka getIzabranaNabavka() {
        return izabranaNabavka;
    }

    public void setIzabranaNabavka(NabJavnaNabavka izabranaNabavka) {
        this.izabranaNabavka = izabranaNabavka;
    }

    public NabJavnaNabavkaExtendedDataTableModelApi getNabJavnaNabavkaExtendedDataTableModelApi() {
        return nabJavnaNabavkaExtendedDataTableModelApi;
    }

    public void setNabJavnaNabavkaExtendedDataTableModelApi(
            NabJavnaNabavkaExtendedDataTableModelApi nabJavnaNabavkaExtendedDataTableModelApi) {
        this.nabJavnaNabavkaExtendedDataTableModelApi = nabJavnaNabavkaExtendedDataTableModelApi;
    }

    public NabJavnaNabavka getNovaNabavka() {
        return novaNabavka;
    }

    public void setNovaNabavka(NabJavnaNabavka novaNabavka) {
        this.novaNabavka = novaNabavka;
    }

    public NabNovaNabavkaController getNabNovaNabavkaController() {
        return nabNovaNabavkaController;
    }

    public void setNabNovaNabavkaController(
            NabNovaNabavkaController nabNovaNabavkaController) {
        this.nabNovaNabavkaController = nabNovaNabavkaController;
    }

    public NabPlan getNabPlan() {
        return nabPlan;
    }

    public void setNabPlan(NabPlan nabPlan) {
        this.nabPlan = nabPlan;
    }

    public int getIdVrstaPredmetaNabavke() {
        return idVrstaPredmetaNabavke;
    }

    public void setIdVrstaPredmetaNabavke(int idVrstaPredmetaNabavke) {
        this.idVrstaPredmetaNabavke = idVrstaPredmetaNabavke;
    }

    public int getIdVrstaPostupka() {
        return idVrstaPostupka;
    }

    public void setIdVrstaPostupka(int idVrstaPostupka) {
        this.idVrstaPostupka = idVrstaPostupka;
    }

    public ArrayList<SelectItem> getXnabVrstaPredmetaNabavkeSelectionItems() {
        return xnabVrstaPredmetaNabavkeSelectionItems;
    }

    public void setXnabVrstaPredmetaNabavkeSelectionItems(
            ArrayList<SelectItem> xnabVrstaPredmetaNabavkeSelectionItems) {
        this.xnabVrstaPredmetaNabavkeSelectionItems = xnabVrstaPredmetaNabavkeSelectionItems;
    }

    public ArrayList<SelectItem> getXnabVrstaPostupkaSelectionItems() {
        return xnabVrstaPostupkaSelectionItems;
    }

    public void setXnabVrstaPostupkaSelectionItems(
            ArrayList<SelectItem> xnabVrstaPostupkaSelectionItems) {
        this.xnabVrstaPostupkaSelectionItems = xnabVrstaPostupkaSelectionItems;
    }

    public XnabVrstaPredmetaNabavkeServiceApi getXnabVrstaPredmetaNabavkeServiceApi() {
        return xnabVrstaPredmetaNabavkeServiceApi;
    }

    public void setXnabVrstaPredmetaNabavkeServiceApi(
            XnabVrstaPredmetaNabavkeServiceApi xnabVrstaPredmetaNabavkeServiceApi) {
        this.xnabVrstaPredmetaNabavkeServiceApi = xnabVrstaPredmetaNabavkeServiceApi;
    }

    public XnabVrstaPostupkaServiceApi getXnabVrstaPostupkaServiceApi() {
        return xnabVrstaPostupkaServiceApi;
    }

    public void setXnabVrstaPostupkaServiceApi(
            XnabVrstaPostupkaServiceApi xnabVrstaPostupkaServiceApi) {
        this.xnabVrstaPostupkaServiceApi = xnabVrstaPostupkaServiceApi;
    }

    public NabJavnaNabavkaServiceApi getNabJavnaNabavkaServiceApi() {
        return nabJavnaNabavkaServiceApi;
    }

    public void setNabJavnaNabavkaServiceApi(
            NabJavnaNabavkaServiceApi nabJavnaNabavkaServiceApi) {
        this.nabJavnaNabavkaServiceApi = nabJavnaNabavkaServiceApi;
    }

    

}
