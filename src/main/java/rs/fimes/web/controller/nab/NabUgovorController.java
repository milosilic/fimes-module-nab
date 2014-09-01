package rs.fimes.web.controller.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.domain.core.PpPoslovniPartner;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.domain.nab.NabUgovor;
import rs.fimes.web.controller.BaseController;
import rs.fimes.web.controller.pp.PpPoslovniPartnerSelectionController;
import rs.fimes.web.datamodel.api.nab.NabUgovorExtendedDataTableModelApi;

public class NabUgovorController extends BaseController{

    private static final long serialVersionUID = 1L;
    private NabUgovorExtendedDataTableModelApi nabUgovorExtendedDataTableModelApi;
    
    private String interniBroj;
    private NabUgovor nabUgovorSelected;
    private NabUgovor noviUgovor;
    private PpPoslovniPartnerSelectionController ppPoslovniPartnerSelectionController;
    private PpPoslovniPartner ppPoslovniPartner;

    public NabUgovorController(Module module, String controllerId)
            throws ConfigurationException {
        super(module, controllerId);
        System.out.println("*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/");
    }
    
    // TODO implementirati  
    public void initNoviUgovor(){
        noviUgovor = new NabUgovor();
        noviUgovor.setNabJavnaNabavka(new NabJavnaNabavka());
        
    }
    
    public void initAzuriranjeUgovora(){
        noviUgovor = nabUgovorSelected;
    }
    
    public void snimiNabUgovor(){
        
    }
    
    public void odustani(){
        super.resetForm();
    }
    
    public void resetPpPoslovniPartner(){
        if ( ( null != noviUgovor ) && ( null != noviUgovor.getPpPoslovniPartner())){
            noviUgovor.setPpPoslovniPartner(null);
        }
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

}
