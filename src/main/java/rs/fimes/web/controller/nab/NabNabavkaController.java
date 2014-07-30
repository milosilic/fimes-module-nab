package rs.fimes.web.controller.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.service.api.nab.NabNaruciociServiceApi;
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

    public NabNabavkaController(Module module, String controllerId)
            throws ConfigurationException {
        super(module, controllerId);
    }
    
    public void onStart() {
       
        System.out.println( ":::Ukenjao sam se od srece " + System.currentTimeMillis()+ " NabNabavkaController");
        if ( orgFirma == null ) {
          setOrgFirma(nabNaruciociServiceApi.getActiveOrgFirma(getUserSessionUtil().getCurrentUserCurrentOrgFirma().getIdFirma()));
        }
        
        nabJavnaNabavkaExtendedDataTableModelApi.helperWalkByRequest();
        nabNovaNabavkaController.onStart();
//        orgFirma = (OrgFirma) getUserSessionUtil().getCurrentUserCurrentOrgFirma();
     
    }
    
    public void handleSelection(){
        System.out.println( nabJavnaNabavkaExtendedDataTableModelApi.getSelection());
        izabranaNabavka = nabJavnaNabavkaExtendedDataTableModelApi.getDomainObject();
        System.out.println( nabJavnaNabavkaExtendedDataTableModelApi);
    }
    
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
        System.out.println( orgFirma);
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

    

}
