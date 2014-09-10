package rs.fimes.web.controller.nab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.utils.MessageBundleProperty;
import rs.fimes.data.dao.generic.QueryRestriction;
import rs.fimes.data.dao.generic.QueryRestrictionComparison1;
import rs.fimes.domain.core.PpPoslovniPartner;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.domain.nab.NabUgovor;
import rs.fimes.domain.nab.XnabKriterijum;
import rs.fimes.service.api.nab.NabUgovorServiceApi;
import rs.fimes.service.api.nab.XnabKriterijumServiceApi;
import rs.fimes.web.controller.BaseController;
import rs.fimes.web.controller.pp.PpPoslovniPartnerSelectionController;
import rs.fimes.web.datamodel.api.nab.NabUgovorExtendedDataTableModelApi;

public class NabUgovorController extends BaseController{

    //01.09.2014.
    private static final long serialVersionUID = 1L;
    private NabUgovorExtendedDataTableModelApi nabUgovorExtendedDataTableModelApi;
    
    private String interniBroj;
    private NabUgovor nabUgovorSelected;
    private NabUgovor noviUgovor;
    private PpPoslovniPartnerSelectionController ppPoslovniPartnerSelectionController;
    private PpPoslovniPartner ppPoslovniPartner;
    //02.09.2014.
    private NabNabavkaSelectionController nabNabavkaSelectionController;
    private NabJavnaNabavka nabNabavka;
    //03.09.2014.
    private XnabKriterijumServiceApi xnabKriterijumServiceApi;
    private ArrayList<SelectItem> xnabKriterijumSelectionItems;
    //04.09.2014
    private NabUgovorServiceApi nabUgovorServiceApi;
    private String pretragaIzvrsenUgovor;
    private String pretragaInterniBroj;
    private String pretragaInterniBrojNabavke;
    //10.09.2014.
    private boolean azuriranje;
    private String skrivenoPolje;

    
    public NabUgovorController(Module module, String controllerId)
            throws ConfigurationException {
        super(module, controllerId);
    }
    
    public void onStart(){
        System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/");
        resetUgovorPretraga();
        ugovorPretraga();
        nabUgovorExtendedDataTableModelApi.helperWalkByRequest();
        azuriranje = false;
        
    }
    
    public void azuriranjePodataka() {
      azuriranje = true;
    }
    
    public void clearIzvrsenjeUgovora(){
        azuriranje=false;
    }
    private void resetUgovorPretraga() {
        pretragaInterniBroj = null;
        pretragaInterniBrojNabavke = null;
    }

    public void initNoviUgovor(){
        noviUgovor = new NabUgovor();
        noviUgovor.setKriterijum(new XnabKriterijum());
    }
    
    public void initModalDialogBrisanje(){
        initModalDialogBrisanje(
                "nabUgovoriBrisanjeUgovoraHeader",
                new MessageBundleProperty(
                        "nabUgovoriBrisanjeUgovoraPitanje"),
                "jsNabUgovorBrisanje()");

    }
    
    public void obrisiUgovor(){
        if ( null != nabUgovorSelected ){
            try {
                nabUgovorServiceApi.deleteUgovor( nabUgovorSelected);
                populateModalOkPanelSnimanjeDefaultMessages(true,
                        "nabUgovoriBrisanjeUgovoraHeader");
            }catch (Exception e) {
                e.printStackTrace();
                populateModalOkPanelSnimanjeDefaultMessages(false,
                        "nabUgovoriBrisanjeUgovoraHeader");
            } finally {
                resetSelection();
            }
        }
            
    }

    public void resetSelection(){
        nabUgovorSelected = null;
        nabUgovorExtendedDataTableModelApi.clearSelection();
 
    }
    
    public void snimiPromenePodaci(){
        azuriranje = false;
        
    }
    
    public void initAzuriranjeUgovora(){
        noviUgovor = nabUgovorSelected;
    }
    
    
    public void snimiNabUgovor(){
        try {
            nabUgovorServiceApi.createNabUgovor( noviUgovor);
            populateModalOkPanelSnimanjeDefaultMessagesWithHeaderMessage(true,
                    getMessage("nabUgovoriSnimanjeHeader"));
        } catch (Exception e) {
            
            e.printStackTrace();
            populateModalOkPanelSnimanjeDefaultMessagesWithHeaderMessage(false,
                    getMessage("nabUgovoriSnimanjeHeader"));
        }finally{
            //resetSelection();
        }

    }
    
    public void odustani(){
        super.resetForm();
    }
    
    public void resetNabNabavka(){
        
    }
    
    
    public void setNabNabavkaAction(){
        nabNabavkaSelectionController.setDugmeAction("nabUgovorController.transferNabNabavka");
        nabNabavkaSelectionController.setDugmeReRender("panelGridNabNoviUgovorLevel1");
        
    }
    
    public void transferNabNabavka(){
        nabNabavka = nabNabavkaSelectionController.getNabNabavka();
        noviUgovor.setNabJavnaNabavka(nabNabavka);
    }
    public void resetPpPoslovniPartner(){
        if ( ( null != noviUgovor ) && ( null != noviUgovor.getPpPoslovniPartner())){
            noviUgovor.setPpPoslovniPartner(null);
        }
    }
    public void ugovorPretraga(){
        List<QueryRestriction> parametri = new ArrayList<QueryRestriction>();
        
        if (pretragaInterniBroj != null && !pretragaInterniBroj.trim().isEmpty()) {
            parametri.add(QueryRestrictionComparison1
                    .addCirToAbcStringContains(
                            "interniBroj",
                            getStringUtil().transliterationCirToAbc(
                                    pretragaInterniBroj.trim().replaceAll("\\s+", " "))));
        }
        System.out.println(pretragaInterniBrojNabavke);
        if (pretragaInterniBrojNabavke != null && !pretragaInterniBrojNabavke.trim().isEmpty()) {
            parametri.add(QueryRestrictionComparison1
                    .addCirToAbcStringContains(
                            "nabJavnaNabavka.interniBroj",
                            getStringUtil().transliterationCirToAbc(
                                    pretragaInterniBrojNabavke.trim().replaceAll("\\s+", " "))));
        }
        if (pretragaIzvrsenUgovor != null && !pretragaIzvrsenUgovor.isEmpty() && !pretragaIzvrsenUgovor.equals("1")) {
            parametri.add(QueryRestrictionComparison1
                    .addIsEqual(
                            "fUgovorIzvrsen", pretragaIzvrsenUgovor.equals("2")));
        }

        nabUgovorExtendedDataTableModelApi.setParametri(parametri);
        nabUgovorExtendedDataTableModelApi.setSortField("interniBroj");
        nabUgovorExtendedDataTableModelApi.setDescending(false);
//        nabUgovorExtendedDataTableModelApi.clearSelection();

    }
    
    // -------------- VALIDACIJE ----------------------

    public void validateSkrivenoPolje(FacesContext context,
            UIComponent validate, Object value) throws ValidatorException {
        if (checkUgovor()) {
            throwValidatorException("ppPpUnosNovogPartneraRequiredRzsDrzava");
        }
    }
    
    private boolean checkUgovor(){
        return false;
    }

    
    public void setPpPoslovniPartnerAction(){
        ppPoslovniPartnerSelectionController.setDugmeAction("nabUgovorController.transferPpPoslovniPartner");
        ppPoslovniPartnerSelectionController.setDugmeReRender("nabUgovorDobavljacId");
    }
    
    public void transferPpPoslovniPartner(){
        ppPoslovniPartner = ppPoslovniPartnerSelectionController.getPpPoslovniPartner();
        noviUgovor.setPpPoslovniPartner(ppPoslovniPartner);
    }

    public NabUgovorExtendedDataTableModelApi getNabUgovorExtendedDataTableModelApi() {
        return nabUgovorExtendedDataTableModelApi;
    }

    public void setNabUgovorExtendedDataTableModelApi(
            NabUgovorExtendedDataTableModelApi nabUgovorExtendedDataTableModelApi) {
        this.nabUgovorExtendedDataTableModelApi = nabUgovorExtendedDataTableModelApi;
    }

    public String getInterniBroj() {
        return interniBroj;
    }

    public void setInterniBroj(String interniBroj) {
        this.interniBroj = interniBroj;
    }

    public NabUgovor getNabUgovorSelected() {
        return nabUgovorSelected;
    }

    public void setNabUgovorSelected(NabUgovor nabUgovorSelected) {
        this.nabUgovorSelected = nabUgovorSelected;
    }

    public NabUgovor getNoviUgovor() {
        return noviUgovor;
    }

    public void setNoviUgovor(NabUgovor noviUgovor) {
        this.noviUgovor = noviUgovor;
    }

    public PpPoslovniPartnerSelectionController getPpPoslovniPartnerSelectionController() {
        return ppPoslovniPartnerSelectionController;
    }

    public void setPpPoslovniPartnerSelectionController(
            PpPoslovniPartnerSelectionController ppPoslovniPartnerSelectionController) {
        this.ppPoslovniPartnerSelectionController = ppPoslovniPartnerSelectionController;
    }

    public PpPoslovniPartner getPpPoslovniPartner() {
        return ppPoslovniPartner;
    }

    public void setPpPoslovniPartner(PpPoslovniPartner ppPoslovniPartner) {
        this.ppPoslovniPartner = ppPoslovniPartner;
    }

    public NabNabavkaSelectionController getNabNabavkaSelectionController() {
        return nabNabavkaSelectionController;
    }

    public void setNabNabavkaSelectionController(
            NabNabavkaSelectionController nabNabavkaSelectionController) {
        this.nabNabavkaSelectionController = nabNabavkaSelectionController;
    }

    public NabJavnaNabavka getNabNabavka() {
        return nabNabavka;
    }

    public void setNabNabavka(NabJavnaNabavka nabNabavka) {
        this.nabNabavka = nabNabavka;
    }

    public XnabKriterijumServiceApi getXnabKriterijumServiceApi() {
        return xnabKriterijumServiceApi;
    }

    public void setXnabKriterijumServiceApi(XnabKriterijumServiceApi xnabKriterijumServiceApi) {
        this.xnabKriterijumServiceApi = xnabKriterijumServiceApi;
    }

    public ArrayList<SelectItem> getXnabKriterijumSelectionItems() {
        xnabKriterijumSelectionItems = new ArrayList<SelectItem>();
        List<XnabKriterijum> xnabKriterijums = xnabKriterijumServiceApi.getAllKriterijum();
        Iterator<XnabKriterijum> iterXnabKriterijum = xnabKriterijums.iterator();
        while ( iterXnabKriterijum.hasNext()){
            XnabKriterijum xnabKriterijum = (XnabKriterijum) iterXnabKriterijum.next();
            xnabKriterijumSelectionItems.add(new SelectItem( xnabKriterijum.getPrimaryKey(), String.valueOf( xnabKriterijum.getNaziv())));
         }

        return xnabKriterijumSelectionItems;
    }

    public void setXnabKriterijumSelectionItems(
            ArrayList<SelectItem> xnabKriterijumSelectionItems) {
        this.xnabKriterijumSelectionItems = xnabKriterijumSelectionItems;
    }

    public NabUgovorServiceApi getNabUgovorServiceApi() {
        return nabUgovorServiceApi;
    }

    public void setNabUgovorServiceApi(NabUgovorServiceApi nabUgovorServiceApi) {
        this.nabUgovorServiceApi = nabUgovorServiceApi;
    }

    public String getPretragaIzvrsenUgovor() {
        return pretragaIzvrsenUgovor;
    }

    public void setPretragaIzvrsenUgovor(String pretragaIzvrsenUgovor) {
        this.pretragaIzvrsenUgovor = pretragaIzvrsenUgovor;
    }

    public String getPretragaInterniBroj() {
        return pretragaInterniBroj;
    }

    public void setPretragaInterniBroj(String pretragaInterniBroj) {
        this.pretragaInterniBroj = pretragaInterniBroj;
    }

    public String getPretragaInterniBrojNabavke() {
        return pretragaInterniBrojNabavke;
    }

    public void setPretragaInterniBrojNabavke(String pretragaInterniBrojNabavke) {
        this.pretragaInterniBrojNabavke = pretragaInterniBrojNabavke;
    }

    public boolean isAzuriranje() {
        return azuriranje;
    }

    public void setAzuriranje(boolean azuriranje) {
        this.azuriranje = azuriranje;
    }

    public String getSkrivenoPolje() {
        return skrivenoPolje;
    }

    public void setSkrivenoPolje(String skrivenoPolje) {
        this.skrivenoPolje = skrivenoPolje;
    }

    
    

}
