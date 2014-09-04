package rs.fimes.web.controller.nab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
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

    
    public NabUgovorController(Module module, String controllerId)
            throws ConfigurationException {
        super(module, controllerId);
        System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/");
    }
    
    public void initNoviUgovor(){
        noviUgovor = new NabUgovor();
        noviUgovor.setKriterijum(new XnabKriterijum());
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
        
        if (interniBroj != null && !interniBroj.trim().isEmpty()) {
            parametri.add(QueryRestrictionComparison1
                    .addCirToAbcStringContains(
                            "interniBroj",
                            getStringUtil().transliterationCirToAbc(
                                    interniBroj.trim().replaceAll("\\s+", " "))));
        }
        
        nabUgovorExtendedDataTableModelApi.setParametri(parametri);
        nabUgovorExtendedDataTableModelApi.clearSelection();

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
    
    

}
