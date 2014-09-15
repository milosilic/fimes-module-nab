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
import rs.fimes.domain.org.OrgTipOrganizacioneJedinice;
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
    
    //pretraga
    private String pretragaRb;
    private String pretragaOpis;
    private String pretragaVrstaPredmetaNabavke;
    private String pretragaVrstaPostupkaNabavke;
    private String pretragaGodinaPokretanja;
    
    private String vrstaPredmetaNabavkePretragaTitle;
    private String vrstaPostupkaNabavkePretragaTitle;
    
    public NabNabavkaController(Module module, String controllerId)
            throws ConfigurationException {
        super(module, controllerId);
    }
    
    public void onEntry(){
            List<QueryRestriction> parametri = new ArrayList<QueryRestriction>();
            parametri.add(QueryRestrictionComparison1.addIsEqual("nabPlan", nabPlan));
            nabJavnaNabavkaExtendedDataTableModelApi.setParametri(parametri);
            orgFirma = (OrgFirma) getUserSessionUtil().getCurrentUserCurrentOrgFirma();
    }
        
    public void initModalDialogBrisanje(){
        initModalDialogBrisanje(
                "nabNabavkaNabavkaBrisanjeHeader",
                new MessageBundleProperty(
                        "nabNabavkaNabavkaBrisanjePitanje"),
                "jsNabNabavkaBrisanje()");
   
    }
    
    public void obrisiNabavku(){
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
        parametri.add(QueryRestrictionComparison1.addIsEqual("nabPlan", nabPlan));
        pretragaRb = pretragaRb.trim();
        Integer intGodina = null;
        try {
            intGodina= Integer.valueOf(pretragaRb);
        } catch (NumberFormatException e) {
            pretragaRb=null;
        }catch (NullPointerException e) {
            pretragaRb = null;
        }
        
        if( pretragaRb != null) {
            parametri.add( QueryRestrictionComparison1.addIsEqual("idJavnaNabavka", intGodina.intValue()));
        }
        
        if (pretragaOpis != null && !pretragaOpis.trim().isEmpty()) {
            parametri.add(QueryRestrictionComparison1
                    .addCirToAbcStringContains(
                            "opis",
                            getStringUtil().transliterationCirToAbc(
                                    pretragaOpis.trim().replaceAll("\\s+", " "))));
        }

        pretragaVrstaPredmetaNabavke = emptyStringToNull(pretragaVrstaPredmetaNabavke);
        if (pretragaVrstaPredmetaNabavke != null) {
            int idVrstaPredmetaNabavke = Integer.parseInt(pretragaVrstaPredmetaNabavke);
            parametri.add(QueryRestrictionComparison1.addIsEqual(
                    "vrstaPredmetaNabavke.idVrstaPredmetaNabavke",
                    idVrstaPredmetaNabavke, false));
        }

        pretragaVrstaPostupkaNabavke = emptyStringToNull(pretragaVrstaPostupkaNabavke);
        if (pretragaVrstaPostupkaNabavke != null) {
            int idVrstaPostupkaNabavke = Integer.parseInt(pretragaVrstaPostupkaNabavke);
            parametri.add(QueryRestrictionComparison1.addIsEqual(
                    "vrstaPostupka.idVrstaPostupka",
                    idVrstaPostupkaNabavke, false));
        }
        nabJavnaNabavkaExtendedDataTableModelApi.setParametri(parametri);
        nabJavnaNabavkaExtendedDataTableModelApi.setDescending(false);
        //nabJavnaNabavkaExtendedDataTableModelApi.setSortField("vrstaPredmetaNabavke.naziv,naziv");
        resetSelection();

    }
    
    //po uzoru na action="#{orgEvidencijaOrganizacionaJedinicaSelectionController.promenaTipOrgJedinicePretraga}" />
    public void promenaVrstaPredmetaNabavke(){
        if (emptyStringToNull(pretragaVrstaPredmetaNabavke) == null)
            vrstaPredmetaNabavkePretragaTitle = getMessage("common_svi");
        else {
            int idVrstaPredmetaNabavke = Integer.parseInt(pretragaVrstaPredmetaNabavke);
            XnabVrstaPredmetaNabavke nabVrstaPredmetaNabavke = xnabVrstaPredmetaNabavkeServiceApi.getNabVrstaPredmetaNabavkeById(idVrstaPredmetaNabavke );
            vrstaPredmetaNabavkePretragaTitle = nabVrstaPredmetaNabavke.getNaziv();
        }
        pretraga();
    }
    
    public void promenaVrstaPostupkaNabavke(){
        if (emptyStringToNull(pretragaVrstaPostupkaNabavke) == null)
            vrstaPostupkaNabavkePretragaTitle = getMessage("common_svi");
        else {
            int idVrstaPostupkaNabavke = Integer.parseInt(pretragaVrstaPostupkaNabavke);
            XnabVrstaPostupka nabVrstaPostupkaNabavke = xnabVrstaPostupkaServiceApi.getNabVrstaPostupkaNabavkeById(idVrstaPostupkaNabavke );
            vrstaPostupkaNabavkePretragaTitle = nabVrstaPostupkaNabavke.getNaziv();
        }
        pretraga();
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
        nabJavnaNabavkaExtendedDataTableModelApi.helperWalkByRequest();
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
            NabJavnaNabavkaExtendedDataTableModelApi nabJavnaNabavkaExtendedDataTableModelApi){

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
        xnabVrstaPredmetaNabavkeSelectionItems = new ArrayList<SelectItem>();
        xnabVrstaPredmetaNabavkeSelectionItems.add(new SelectItem(null,
                getMessage("common_svi")));
        xnabVrstaPredmetaNabavkeSelectionItems.addAll(nabNovaNabavkaController.getXnabVrstaPredmetaNabavkeSelectionItems());
        return xnabVrstaPredmetaNabavkeSelectionItems;
    }

    public void setXnabVrstaPredmetaNabavkeSelectionItems(
            ArrayList<SelectItem> xnabVrstaPredmetaNabavkeSelectionItems) {
        this.xnabVrstaPredmetaNabavkeSelectionItems = xnabVrstaPredmetaNabavkeSelectionItems;
    }

    public ArrayList<SelectItem> getXnabVrstaPostupkaSelectionItems() {
        xnabVrstaPostupkaSelectionItems = new ArrayList<SelectItem>();
        xnabVrstaPostupkaSelectionItems.add(new SelectItem(null,
                getMessage("common_svi")));
        xnabVrstaPostupkaSelectionItems.addAll(nabNovaNabavkaController.getXnabVrstaPostupkaSelectionItems());

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

    public String getPretragaRb() {
        return pretragaRb;
    }

    public void setPretragaRb(String pretragaRb) {
        this.pretragaRb = pretragaRb;
    }

    public String getPretragaOpis() {
        return pretragaOpis;
    }

    public void setPretragaOpis(String pretragaOpis) {
        this.pretragaOpis = pretragaOpis;
    }

    public String getPretragaVrstaPredmetaNabavke() {
        return pretragaVrstaPredmetaNabavke;
    }

    public void setPretragaVrstaPredmetaNabavke(
            String pretragaVrstaPredmetaNabavke) {
        this.pretragaVrstaPredmetaNabavke = pretragaVrstaPredmetaNabavke;
    }

    public String getVrstaPredmetaNabavkePretragaTitle() {
        return vrstaPredmetaNabavkePretragaTitle;
    }

    public void setVrstaPredmetaNabavkePretragaTitle(
            String vrstaPredmetaNabavkePretragaTitle) {
        this.vrstaPredmetaNabavkePretragaTitle = vrstaPredmetaNabavkePretragaTitle;
    }

    public String getPretragaVrstaPostupkaNabavke() {
        return pretragaVrstaPostupkaNabavke;
    }

    public void setPretragaVrstaPostupkaNabavke(String pretragaVrstaPostupkaNabavke) {
        this.pretragaVrstaPostupkaNabavke = pretragaVrstaPostupkaNabavke;
    }

    public String getVrstaPostupkaNabavkePretragaTitle() {
        return vrstaPostupkaNabavkePretragaTitle;
    }

    public void setVrstaPostupkaNabavkePretragaTitle(
            String vrstaPostupkaNabavkePretragaTitle) {
        this.vrstaPostupkaNabavkePretragaTitle = vrstaPostupkaNabavkePretragaTitle;
    }

    public String getPretragaGodinaPokretanja() {
        return pretragaGodinaPokretanja;
    }

    public void setPretragaGodinaPokretanja(String pretragaGodinaPokretanja) {
        this.pretragaGodinaPokretanja = pretragaGodinaPokretanja;
    }

    

}
