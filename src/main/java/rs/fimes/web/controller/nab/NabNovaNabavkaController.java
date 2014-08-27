package rs.fimes.web.controller.nab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlForm;
import javax.faces.model.SelectItem;

import org.ajax4jsf.component.html.HtmlAjaxCommandButton;

import antlr.NoViableAltException;
import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.utils.MessageBundleProperty;
import rs.fimes.data.dao.generic.QueryRestriction;
import rs.fimes.data.dao.generic.QueryRestrictionComparison1;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.domain.nab.NabNabavkaJrn;
import rs.fimes.domain.nab.NabNabavkaKontoPartija;
import rs.fimes.domain.nab.NabPartijaNabavke;
import rs.fimes.domain.nab.NabPlan;
import rs.fimes.domain.nab.NabProcenaPoGodini;
import rs.fimes.domain.nab.XnabIzvorFinansiranja;
import rs.fimes.domain.nab.XnabJrn;
import rs.fimes.domain.nab.XnabKonto;
import rs.fimes.domain.nab.XnabPredmetNabavke;
import rs.fimes.domain.nab.XnabStatusNabavke;
import rs.fimes.domain.nab.XnabTipNabavke;
import rs.fimes.domain.nab.XnabVrstaPostupka;
import rs.fimes.domain.nab.XnabVrstaPredmetaNabavke;
import rs.fimes.service.api.core.UsrKorisnikServiceApi;
import rs.fimes.service.api.nab.NabJavnaNabavkaServiceApi;
import rs.fimes.service.api.nab.NabNabavkaJrnServiceApi;
import rs.fimes.service.api.nab.NabNabavkaKontoPartijaServiceApi;
import rs.fimes.service.api.nab.NabNaruciociServiceApi;
import rs.fimes.service.api.nab.NabPartijaNabavkeServiceApi;
import rs.fimes.service.api.nab.NabPlanServiceApi;
import rs.fimes.service.api.nab.NabProcenaPoGodiniServiceApi;
import rs.fimes.service.api.nab.XnabIzvorFinansiranjaServiceApi;
import rs.fimes.service.api.nab.XnabPredmetNabavkeServiceApi;
import rs.fimes.service.api.nab.XnabStatusNabavkeServiceApi;
import rs.fimes.service.api.nab.XnabTipNabavkeServiceApi;
import rs.fimes.service.api.nab.XnabVrstaPostupkaServiceApi;
import rs.fimes.service.api.nab.XnabVrstaPredmetaNabavkeServiceApi;
import rs.fimes.web.controller.BaseController;
import rs.fimes.web.datamodel.api.nab.NabNabavkaJrnExtendedDataTableModelApi;
import rs.fimes.web.datamodel.api.nab.NabNabavkaKontoPartijaExtendedDataTableModelApi;
import rs.fimes.web.datamodel.api.nab.NabPartijaNabavkeExtendedDataTableModelApi;
import rs.fimes.web.datamodel.api.nab.NabProcenaPoGodiniExtendedDataTableModelApi;
import rs.fimes.web.datamodel.api.nab.XnabJrnExtendedDataTableModelApi;

public class NabNovaNabavkaController extends BaseController{

    private NabPlanServiceApi nabPlanServiceApi;
    private NabNaruciociServiceApi nabNaruciociServiceApi;
    private OrgFirma orgFirma;
    private HtmlForm formNabNoviPlan;
    private HtmlAjaxCommandButton dugmePotvrdi;
    private UsrKorisnikServiceApi usrKorisnikServiceApi;
    
    //27.06.2014
    private ArrayList<SelectItem> xnabVrstaPredmetaNabavkeSelectionItems;
    private XnabVrstaPredmetaNabavkeServiceApi xnabVrstaPredmetaNabavkeServiceApi; 
    private Integer idVrstaPredmetaNabavke;
    
    private ArrayList<SelectItem> xnabVrstaPostupkaSelectionItems;
    private XnabVrstaPostupkaServiceApi xnabVrstaPostupkaServiceApi;
    private Integer idVrstaPostupka;
    
    private List<SelectItem> xnabPredmetNabavkeSelectionItems;
    private XnabPredmetNabavkeServiceApi xnabPredmetNabavkeServiceApi;
    private Integer idPredmetNabavke;
    private NabJavnaNabavka novaNabavka;
    
    //@TODO skloni ove objekte iz kontrolera, kada rešiš gde se pamte (nab_javna_nabavka)
    private XnabVrstaPredmetaNabavke vrstaPredmetaNabavke;
    private XnabPredmetNabavke predmetNabavke;
   
    //28.06.2014
    
    private ArrayList<SelectItem> xnabTipNabavkeSelectionItems;
    private XnabTipNabavkeServiceApi xnabTipNabavkeServiceApi; 
    
    private ArrayList<SelectItem> xnabStatusNabavkeSelectionItems;
    private XnabStatusNabavkeServiceApi xnabStatusNabavkeServiceApi;
    
    //29.06.2014
    //dodata funkcija initNovaProcenjenaVrednost, vidi da li da je premestiš u neki sub kontroler

    //30.06.2014
    //dodat property nabJavnaNabavkaServiceApi, da bih mogao da sačuvam nabavku
    private NabJavnaNabavkaServiceApi nabJavnaNabavkaServiceApi;
    
    //01.07.2014.
    private UIInput inputPredmetNabavke;
    
    private NabProcenaPoGodiniExtendedDataTableModelApi nabProcenaPoGodiniExtendedDataTableModelApi;

    
    //@TODO skloni ove objekte iz kontrolera, kada rešiš gde se pamte (nab_javna_nabavka)
    private XnabTipNabavke tipNabavke;
    private XnabStatusNabavke statusNabavke;
    //03.07.2014.
    private XnabVrstaPostupka vrstaPostupka;
    
    //05.07.2014.
    //ovde se smešta iz nabPlan kontrolera plan za koje se odnose ove nabavke
    //ako ne treba ukloni
    private NabPlan nabPlan;
    
    //07.08.2014.
    private NabProcenaPoGodini novaProcenjenaVrednost;
    private NabProcenaPoGodiniServiceApi nabProcenaPoGodiniServiceApi;
    
    
    //09.08.2014.
    private XnabJrnExtendedDataTableModelApi xnabJrnExtendedDataTableModelApi; 
    
    //12.08.2014.
    private NabPartijaNabavkeExtendedDataTableModelApi nabPartijaNabavkeExtendedDataTableModelApi;
    private NabPartijaNabavke novaPartija;
    //13.08.2014
    private NabPartijaNabavkeServiceApi nabPartijaNabavkeServiceApi;
    //14.08.2014.
    private NabProcenaPoGodini nabProcenaPoGodini;
    
    //19.08.2014.
    private NabNabavkaKontoPartijaExtendedDataTableModelApi nabNabavkaKontoPartijaExtendedDataTableModelApi;
    private NabNabavkaKontoPartija novaNabNabavkaKontoPartija;
    private NabNabavkaKontoPartijaServiceApi nabNabavkaKontoPartijaServiceApi;
    private XnabIzvorFinansiranjaServiceApi xnabIzvorFinansiranjaServiceApi;
    private ArrayList<SelectItem> xnabIzvorFinansiranjaSelectionItems;
    
    //21.08.2014.
    private NabKontoLovSelectionController nabKontoLovSelectionController;
    private static final long serialVersionUID = -788600541631559492L;

    //23.08.2014
    private XnabKonto xnabKonto;
    private ArrayList<SelectItem> nabPartijaNabavkeSelectionItems;  
    
    //24.08.2014.
    private NabNabavkaJrn novaNabavkaJrn;
    private NabNabavkaJrnExtendedDataTableModelApi nabNabavkaJrnExtendedDataTableModelApi;
    private NabNabavkaJrnServiceApi nabNabavkaJrnServiceApi;
    private XnabJrn xnabJrn;
    private NabJrnLovSelectionController nabJrnLovSelectionController;
    
    public NabNovaNabavkaController(Module module, String controllerId)
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
        if( null == novaNabavka ) {
            novaNabavka = new NabJavnaNabavka();
            vrstaPredmetaNabavke = new XnabVrstaPredmetaNabavke();
            predmetNabavke = new XnabPredmetNabavke();
            tipNabavke = new XnabTipNabavke();
            statusNabavke = new XnabStatusNabavke();
            vrstaPostupka = new XnabVrstaPostupka();
            novaNabavka.setVrstaPredmetaNabavke(vrstaPredmetaNabavke);
            novaNabavka.setPredmetNabavke(predmetNabavke);
            novaNabavka.setTipNabavke(tipNabavke);
            novaNabavka.setStatusNabavke(statusNabavke);
            novaNabavka.setVrstaPostupka(vrstaPostupka);
            novaProcenjenaVrednost= new NabProcenaPoGodini();
            novaNabNabavkaKontoPartija = new NabNabavkaKontoPartija();
            novaNabNabavkaKontoPartija.setPartijaNabavke(new NabPartijaNabavke());
            novaNabNabavkaKontoPartija.setIzvorFinansiranja(new XnabIzvorFinansiranja());
            // TODO Skloniti u posebne funkcije ili kontrolere.
            novaNabavkaJrn = new NabNabavkaJrn();
            novaNabavkaJrn.setNabJrn(new XnabJrn());
        }
        
       
        //inicijalizacija padajućih menija šifarnika
        xnabVrstaPredmetaNabavkeSelectionItems = new ArrayList<SelectItem>();
//        padajući meniji , inicijalizacija
        List<XnabVrstaPredmetaNabavke> xnabVrstaPredmetaNabavkes = xnabVrstaPredmetaNabavkeServiceApi.getAllVrstaPredmetaNabavke();
        Iterator<XnabVrstaPredmetaNabavke> iter = xnabVrstaPredmetaNabavkes.iterator();
        while ( iter.hasNext()){
            XnabVrstaPredmetaNabavke xnabVrstaPredmetaNabavke = (XnabVrstaPredmetaNabavke) iter.next();
            xnabVrstaPredmetaNabavkeSelectionItems.add(new SelectItem( xnabVrstaPredmetaNabavke.getPrimaryKey(), String.valueOf( xnabVrstaPredmetaNabavke.getNaziv())));
         }
        xnabVrstaPostupkaSelectionItems = new ArrayList<SelectItem>();
        List<XnabVrstaPostupka> xnabVrstaPostupkas = xnabVrstaPostupkaServiceApi.getAllVrstaPostupka();
        Iterator<XnabVrstaPostupka> iterXnabVrstaPostupka = xnabVrstaPostupkas.iterator();
        while ( iterXnabVrstaPostupka.hasNext()){
            XnabVrstaPostupka xnabVrstaPostupka = (XnabVrstaPostupka) iterXnabVrstaPostupka.next();
//            System.out.println( xnabVrstaPostupka.getNaziv());
//            System.out.println( xnabVrstaPostupka.getPrimaryKey());
            xnabVrstaPostupkaSelectionItems.add(new SelectItem( xnabVrstaPostupka.getPrimaryKey(), String.valueOf( xnabVrstaPostupka.getNaziv())));
         }
        xnabPredmetNabavkeSelectionItems = new ArrayList<SelectItem>();
        List<XnabPredmetNabavke> xnabPredmetNabavkes = xnabPredmetNabavkeServiceApi.getAllPredmetNabavke();
        Iterator<XnabPredmetNabavke> iterXnabPredmetNabavke = xnabPredmetNabavkes.iterator();
        while ( iterXnabPredmetNabavke.hasNext()){
            XnabPredmetNabavke xnabPredmetNabavke = (XnabPredmetNabavke) iterXnabPredmetNabavke.next();
           
            xnabPredmetNabavkeSelectionItems.add(new SelectItem( xnabPredmetNabavke.getIdPredmetNabavke(),  xnabPredmetNabavke.getNaziv()));
         }
//        System.out.println( xnabPredmetNabavkeSelectionItems);
        xnabTipNabavkeSelectionItems = new ArrayList<SelectItem>();
        List<XnabTipNabavke> xnabTipNabavkes = xnabTipNabavkeServiceApi.getAllTipNabavke();
        Iterator<XnabTipNabavke> iterXnabTipNabavke = xnabTipNabavkes.iterator();
        while ( iterXnabTipNabavke.hasNext()){
            XnabTipNabavke xnabTipNabavke = (XnabTipNabavke) iterXnabTipNabavke.next();
//            System.out.println( xnabTipNabavke);
            xnabTipNabavkeSelectionItems.add(new SelectItem( xnabTipNabavke.getPrimaryKey(), String.valueOf( xnabTipNabavke.getNaziv())));
         }
        xnabStatusNabavkeSelectionItems = new ArrayList<SelectItem>();
        List<XnabStatusNabavke> xnabStatusNabavkes = xnabStatusNabavkeServiceApi.getAllStatusNabavke();
        Iterator<XnabStatusNabavke> iterXnabStatusNabavke = xnabStatusNabavkes.iterator();
        while ( iterXnabStatusNabavke.hasNext()){
            XnabStatusNabavke xnabStatusNabavke = (XnabStatusNabavke) iterXnabStatusNabavke.next();
//            System.out.println( xnabStatusNabavke);
            xnabStatusNabavkeSelectionItems.add(new SelectItem( xnabStatusNabavke.getPrimaryKey(), String.valueOf( xnabStatusNabavke.getNaziv())));
         }
        xnabIzvorFinansiranjaSelectionItems = new ArrayList<SelectItem>();
        List<XnabIzvorFinansiranja> xnabIzvorFinansiranjas = xnabIzvorFinansiranjaServiceApi.getAllIzvorFinansiranja();
        Iterator<XnabIzvorFinansiranja> iterXnabIzvorFinansiranja = xnabIzvorFinansiranjas.iterator();
        while ( iterXnabIzvorFinansiranja.hasNext()){
            XnabIzvorFinansiranja xnabIzvorFinansiranja = (XnabIzvorFinansiranja) iterXnabIzvorFinansiranja.next();
           
            xnabIzvorFinansiranjaSelectionItems.add(new SelectItem( xnabIzvorFinansiranja.getIdIzvorFinansiranja(),  xnabIzvorFinansiranja.getNaziv()));
         }

        if ( orgFirma == null ) {
            setOrgFirma(nabNaruciociServiceApi.getActiveOrgFirma(getUserSessionUtil().getCurrentUserCurrentOrgFirma().getIdFirma()));
          }
        
        
             
    }   
    
    public void initNovaProcenjenaVrednost(){
        System.out.println("initNovaProcenjenaVrednost");
    }
    
    public void resetSelection(){
        novaNabavka = new NabJavnaNabavka();
        vrstaPredmetaNabavke = new XnabVrstaPredmetaNabavke();
        predmetNabavke = new XnabPredmetNabavke();
        tipNabavke = new XnabTipNabavke();
        statusNabavke = new XnabStatusNabavke();
        vrstaPostupka = new XnabVrstaPostupka();
        novaNabavka.setVrstaPredmetaNabavke(vrstaPredmetaNabavke);
        novaNabavka.setPredmetNabavke(predmetNabavke);
        novaNabavka.setTipNabavke(tipNabavke);
        novaNabavka.setStatusNabavke(statusNabavke);
        novaNabavka.setVrstaPostupka(vrstaPostupka);
    }
    
    public void resetNabNovuProcenjenuVrednost(){
        novaProcenjenaVrednost = new NabProcenaPoGodini();
    }
    
    public void snimiNabavku(){
        try {
            System.out.println("Ovde ide kood za snimanje nabavke i sve propratno sa tim: " + predmetNabavke.getIdPredmetNabavke());
            novaNabavka.setKorisnikAzurirao(getUserSessionUtil().getCurrentUsrKorisnik());
            novaNabavka.setKorisnikKreirao(getUserSessionUtil().getCurrentUsrKorisnik());
            novaNabavka.setOrgFirma(orgFirma);
            //31.07.2014. ovo je sada nepotrebno, jer smo uveli da direktno iz forme ide u novuNabavku
            //novaNabavka.setVrstaPostupka(vrstaPostupka);
            //novaNabavka.setStatusNabavke(statusNabavke);
            //novaNabavka.setTipNabavke(tipNabavke);
            //novaNabavka.setVrstaPredmetaNabavke(vrstaPredmetaNabavke);
            novaNabavka.setNabPlan(nabPlan);
            //novaNabavka.setPredmetNabavke(predmetNabavke);
            nabJavnaNabavkaServiceApi.createNabJavnaNabavka( novaNabavka);
            populateModalOkPanelSnimanjeDefaultMessagesWithHeaderMessage(true,
                    getMessage("nabNabavkaAzuriranjeNabavkeHeader"));
        } catch (Exception e) {
            
            e.printStackTrace();
            populateModalOkPanelSnimanjeDefaultMessagesWithHeaderMessage(false,
                    getMessage("nabNabavkaAzuriranjeNabavkeHeader"));
        }finally{
            resetSelection();
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

    public HtmlForm getFormNabNoviPlan() {
        System.out.println( "ovo he geter form");
        return formNabNoviPlan;
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

    public ArrayList<SelectItem> getXnabVrstaPredmetaNabavkeSelectionItems() {
        return xnabVrstaPredmetaNabavkeSelectionItems;
    }

    public void setXnabVrstaPredmetaNabavkeSelectionItems(
            ArrayList<SelectItem> xnabVrstaPredmetaNabavkeSelectionItems) {
        this.xnabVrstaPredmetaNabavkeSelectionItems = xnabVrstaPredmetaNabavkeSelectionItems;
    }

    public Integer getIdVrstaPredmetaNabavke() {
        return idVrstaPredmetaNabavke;
    }

    public void setIdVrstaPredmetaNabavke(Integer idVrstaPredmetaNabavke) {
        this.idVrstaPredmetaNabavke = idVrstaPredmetaNabavke;
    }

    public XnabVrstaPredmetaNabavkeServiceApi getXnabVrstaPredmetaNabavkeServiceApi() {
        return xnabVrstaPredmetaNabavkeServiceApi;
    }

    public void setXnabVrstaPredmetaNabavkeServiceApi(
            XnabVrstaPredmetaNabavkeServiceApi xnabVrstaPredmetaNabavkeServiceApi) {
        this.xnabVrstaPredmetaNabavkeServiceApi = xnabVrstaPredmetaNabavkeServiceApi;
    }

    public ArrayList<SelectItem> getXnabVrstaPostupkaSelectionItems() {
        return xnabVrstaPostupkaSelectionItems;
    }

    public void setXnabVrstaPostupkaSelectionItems(
            ArrayList<SelectItem> xnabVrstaPostupkaSelectionItems) {
        this.xnabVrstaPostupkaSelectionItems = xnabVrstaPostupkaSelectionItems;
    }

    public XnabVrstaPostupkaServiceApi getXnabVrstaPostupkaServiceApi() {
        return xnabVrstaPostupkaServiceApi;
    }

    public void setXnabVrstaPostupkaServiceApi(
            XnabVrstaPostupkaServiceApi xnabVrstaPostupkaServiceApi) {
        this.xnabVrstaPostupkaServiceApi = xnabVrstaPostupkaServiceApi;
    }

    public Integer getIdVrstaPostupka() {
        return idVrstaPostupka;
    }

    public void setIdVrstaPostupka(Integer idVrstaPostupka) {
        this.idVrstaPostupka = idVrstaPostupka;
    }

    public List<SelectItem> getXnabPredmetNabavkeSelectionItems() {
        return xnabPredmetNabavkeSelectionItems;
    }

    public void setXnabPredmetNabavkeSelectionItems(
            List<SelectItem> xnabPredmetNabavkeSelectionItems) {
        this.xnabPredmetNabavkeSelectionItems = xnabPredmetNabavkeSelectionItems;
    }

    public XnabPredmetNabavkeServiceApi getXnabPredmetNabavkeServiceApi() {
        return xnabPredmetNabavkeServiceApi;
    }

    public void setXnabPredmetNabavkeServiceApi(
            XnabPredmetNabavkeServiceApi xnabPredmetNabavkeServiceApi) {
        this.xnabPredmetNabavkeServiceApi = xnabPredmetNabavkeServiceApi;
    }

    public Integer getIdPredmetNabavke() {
        return idPredmetNabavke;
    }

    public void setIdPredmetNabavke(Integer idPredmetNabavke) {
        this.idPredmetNabavke = idPredmetNabavke;
    }

    public NabJavnaNabavka getNovaNabavka() {
        return novaNabavka;
    }

    public void setNovaNabavka(NabJavnaNabavka novaNabavka) {
        this.novaNabavka = novaNabavka;
    }

    public XnabVrstaPredmetaNabavke getVrstaPredmetaNabavke() {
        return vrstaPredmetaNabavke;
    }

    public void setVrstaPredmetaNabavke(XnabVrstaPredmetaNabavke vrstaPredmetaNabavke) {
        this.vrstaPredmetaNabavke = vrstaPredmetaNabavke;
    }

    public XnabPredmetNabavke getPredmetNabavke() {
        return predmetNabavke;
    }

    public void setPredmetNabavke(XnabPredmetNabavke predmetNabavke) {
        this.predmetNabavke = predmetNabavke;
    }

    public UIInput getInputPredmetNabavke() {
        return inputPredmetNabavke;
    }

    public void setInputPredmetNabavke(UIInput inputPredmetNabavke) {
        this.inputPredmetNabavke = inputPredmetNabavke;
    }

    public ArrayList<SelectItem> getXnabTipNabavkeSelectionItems() {
        return xnabTipNabavkeSelectionItems;
    }

    public void setXnabTipNabavkeSelectionItems(
            ArrayList<SelectItem> xnabTipNabavkeSelectionItems) {
        this.xnabTipNabavkeSelectionItems = xnabTipNabavkeSelectionItems;
    }



    public XnabTipNabavkeServiceApi getXnabTipNabavkeServiceApi() {
        return xnabTipNabavkeServiceApi;
    }

    public void setXnabTipNabavkeServiceApi(
            XnabTipNabavkeServiceApi xnabTipNabavkeServiceApi) {
        this.xnabTipNabavkeServiceApi = xnabTipNabavkeServiceApi;
    }

    public XnabTipNabavke getTipNabavke() {
        return tipNabavke;
    }

    public void setTipNabavke(XnabTipNabavke tipNabavke) {
        this.tipNabavke = tipNabavke;
    }

    public ArrayList<SelectItem> getXnabStatusNabavkeSelectionItems() {
        return xnabStatusNabavkeSelectionItems;
    }

    public void setXnabStatusNabavkeSelectionItems(
            ArrayList<SelectItem> xnabStatusNabavkeSelectionItems) {
        this.xnabStatusNabavkeSelectionItems = xnabStatusNabavkeSelectionItems;
    }

    public XnabStatusNabavkeServiceApi getXnabStatusNabavkeServiceApi() {
        return xnabStatusNabavkeServiceApi;
    }

    public void setXnabStatusNabavkeServiceApi(
            XnabStatusNabavkeServiceApi xnabStatusNabavkeServiceApi) {
        this.xnabStatusNabavkeServiceApi = xnabStatusNabavkeServiceApi;
    }

    public XnabStatusNabavke getStatusNabavke() {
        return statusNabavke;
    }

    public void setStatusNabavke(XnabStatusNabavke statusNabavke) {
        this.statusNabavke = statusNabavke;
    }

    public XnabVrstaPostupka getVrstaPostupka() {
        return vrstaPostupka;
    }

    public void setVrstaPostupka(XnabVrstaPostupka vrstaPostupka) {
        this.vrstaPostupka = vrstaPostupka;
    }

    public NabJavnaNabavkaServiceApi getNabJavnaNabavkaServiceApi() {
        return nabJavnaNabavkaServiceApi;
    }

    public void setNabJavnaNabavkaServiceApi(
            NabJavnaNabavkaServiceApi nabJavnaNabavkaServiceApi) {
        this.nabJavnaNabavkaServiceApi = nabJavnaNabavkaServiceApi;
    }

    public NabPlan getNabPlan() {
        return nabPlan;
    }

    public void setNabPlan(NabPlan nabPlan) {
        this.nabPlan = nabPlan;
    }
    
    public NabProcenaPoGodiniExtendedDataTableModelApi getNabProcenaPoGodiniExtendedDataTableModelApi() {
        if ( null != novaNabavka.getIdJavnaNabavka()){
            List<QueryRestriction> parametri = new ArrayList<QueryRestriction>();
            parametri.add(QueryRestrictionComparison1.addIsEqual("nabJavnaNabavka", novaNabavka));
            nabProcenaPoGodiniExtendedDataTableModelApi.setParametri(parametri);
        }
        return nabProcenaPoGodiniExtendedDataTableModelApi;
    }

    public void setNabProcenaPoGodiniExtendedDataTableModelApi(
            NabProcenaPoGodiniExtendedDataTableModelApi nabProcenaPoGodiniExtendedDataTableModelApi) {
        this.nabProcenaPoGodiniExtendedDataTableModelApi = nabProcenaPoGodiniExtendedDataTableModelApi;
    }

    public NabProcenaPoGodini getNovaProcenjenaVrednost() {
        return novaProcenjenaVrednost;
    }

    public void setNovaProcenjenaVrednost(NabProcenaPoGodini novaProcenjenaVrednost) {
        this.novaProcenjenaVrednost = novaProcenjenaVrednost;
    }

    public NabProcenaPoGodiniServiceApi getNabProcenaPoGodiniServiceApi() {
        return nabProcenaPoGodiniServiceApi;
    }

    public void setNabProcenaPoGodiniServiceApi(
            NabProcenaPoGodiniServiceApi nabProcenaPoGodiniServiceApi) {
        this.nabProcenaPoGodiniServiceApi = nabProcenaPoGodiniServiceApi;
    }

    public XnabJrnExtendedDataTableModelApi getXnabJrnExtendedDataTableModelApi() {
        return xnabJrnExtendedDataTableModelApi;
    }

    public void setXnabJrnExtendedDataTableModelApi(
            XnabJrnExtendedDataTableModelApi xnabJrnExtendedDataTableModelApi) {
        this.xnabJrnExtendedDataTableModelApi = xnabJrnExtendedDataTableModelApi;
    }

    public NabPartijaNabavkeExtendedDataTableModelApi getNabPartijaNabavkeExtendedDataTableModelApi() {
        return nabPartijaNabavkeExtendedDataTableModelApi;
    }

    public void setNabPartijaNabavkeExtendedDataTableModelApi(
            NabPartijaNabavkeExtendedDataTableModelApi nabPartijaNabavkeExtendedDataTableModelApi) {
        this.nabPartijaNabavkeExtendedDataTableModelApi = nabPartijaNabavkeExtendedDataTableModelApi;
    }

    public NabPartijaNabavke getNovaPartija() {
        return novaPartija;
    }

    public void setNovaPartija(NabPartijaNabavke novaPartija) {
        this.novaPartija = novaPartija;
    }
    
    public NabPartijaNabavkeServiceApi getNabPartijaNabavkeServiceApi() {
        return nabPartijaNabavkeServiceApi;
    }

    public void setNabPartijaNabavkeServiceApi(
            NabPartijaNabavkeServiceApi nabPartijaNabavkeServiceApi) {
        this.nabPartijaNabavkeServiceApi = nabPartijaNabavkeServiceApi;
    }

    public NabProcenaPoGodini getNabProcenaPoGodini() {
        return nabProcenaPoGodini;
    }

    public void setNabProcenaPoGodini(NabProcenaPoGodini nabProcenaPoGodini) {
        this.nabProcenaPoGodini = nabProcenaPoGodini;
    }

    public NabNabavkaKontoPartijaExtendedDataTableModelApi getNabNabavkaKontoPartijaExtendedDataTableModelApi() {
        return nabNabavkaKontoPartijaExtendedDataTableModelApi;
    }

    public void setNabNabavkaKontoPartijaExtendedDataTableModelApi(
            NabNabavkaKontoPartijaExtendedDataTableModelApi nabNabavkaKontoPartijaExtendedDataTableModelApi) {
        this.nabNabavkaKontoPartijaExtendedDataTableModelApi = nabNabavkaKontoPartijaExtendedDataTableModelApi;
    }

    public NabNabavkaKontoPartija getNovaNabNabavkaKontoPartija() {
        return novaNabNabavkaKontoPartija;
    }

    public void setNovaNabNabavkaKontoPartija(
            NabNabavkaKontoPartija novaNabNabavkaKontoPartija) {
        this.novaNabNabavkaKontoPartija = novaNabNabavkaKontoPartija;
    }

    public NabNabavkaKontoPartijaServiceApi getNabNabavkaKontoPartijaServiceApi() {
        return nabNabavkaKontoPartijaServiceApi;
    }

    public void setNabNabavkaKontoPartijaServiceApi(
            NabNabavkaKontoPartijaServiceApi nabNabavkaKontoPartijaServiceApi) {
        this.nabNabavkaKontoPartijaServiceApi = nabNabavkaKontoPartijaServiceApi;
    }

    public XnabIzvorFinansiranjaServiceApi getXnabIzvorFinansiranjaServiceApi() {
        return xnabIzvorFinansiranjaServiceApi;
    }

    public void setXnabIzvorFinansiranjaServiceApi(
            XnabIzvorFinansiranjaServiceApi xnabIzvorFinansiranjaServiceApi) {
        this.xnabIzvorFinansiranjaServiceApi = xnabIzvorFinansiranjaServiceApi;
    }

    public ArrayList<SelectItem> getXnabIzvorFinansiranjaSelectionItems() {
        return xnabIzvorFinansiranjaSelectionItems;
    }

    public void setXnabIzvorFinansiranjaSelectionItems(
            ArrayList<SelectItem> xnabIzvorFinansiranjaSelectionItems) {
        this.xnabIzvorFinansiranjaSelectionItems = xnabIzvorFinansiranjaSelectionItems;
    }

    public NabKontoLovSelectionController getNabKontoLovSelectionController() {
        return nabKontoLovSelectionController;
    }

    public void setNabKontoLovSelectionController(
            NabKontoLovSelectionController nabKontoLovSelectionController) {
        this.nabKontoLovSelectionController = nabKontoLovSelectionController;
    }

    public XnabKonto getXnabKonto() {
        return xnabKonto;
    }

    public void setXnabKonto(XnabKonto xnabKonto) {
        this.xnabKonto = xnabKonto;
    }

    public ArrayList<SelectItem> getNabPartijaNabavkeSelectionItems() {
        nabPartijaNabavkeSelectionItems = new ArrayList<SelectItem>();
        // po uzoru na OrgGrupaRadnihMestaDAOImpl.dohvatiGrupeSaVecimIliJednakimRedonimBrojem
        if (null != novaNabavka && null != novaNabavka.getIdJavnaNabavka()) {
            List<NabPartijaNabavke> NabPartijaNabavkes = nabPartijaNabavkeServiceApi.dohvatiPartijeNabavke( novaNabavka.getIdJavnaNabavka() );
            if( null != NabPartijaNabavkes) {
                Iterator<NabPartijaNabavke> iterNabPartijaNabavke = NabPartijaNabavkes.iterator();
                while ( iterNabPartijaNabavke.hasNext()){
                    NabPartijaNabavke NabPartijaNabavke = (NabPartijaNabavke) iterNabPartijaNabavke.next();
                    nabPartijaNabavkeSelectionItems.add(new SelectItem( NabPartijaNabavke.getIdPartijaNabavke(),  NabPartijaNabavke.getOpisPredmetaNabavke()));
                 }
            }
        }
        return nabPartijaNabavkeSelectionItems;
    }

    public void setNabPartijaNabavkeSelectionItems(
            ArrayList<SelectItem> nabPartijaNabavkeSelectionItems) {
        this.nabPartijaNabavkeSelectionItems = nabPartijaNabavkeSelectionItems;
    }

    public NabNabavkaJrn getNovaNabavkaJrn() {
        return novaNabavkaJrn;
    }

    public void setNovaNabavkaJrn(NabNabavkaJrn novaNabavkaJrn) {
        this.novaNabavkaJrn = novaNabavkaJrn;
    }

    public NabNabavkaJrnExtendedDataTableModelApi getNabNabavkaJrnExtendedDataTableModelApi() {
        if ( null != novaNabavka.getIdJavnaNabavka()){
            List<QueryRestriction> parametri = new ArrayList<QueryRestriction>();
            parametri.add(QueryRestrictionComparison1.addIsEqual("nabJavnaNabavka", novaNabavka));
            nabNabavkaJrnExtendedDataTableModelApi.setParametri(parametri);
        }

        return nabNabavkaJrnExtendedDataTableModelApi;
    }

    public void setNabNabavkaJrnExtendedDataTableModelApi(
            NabNabavkaJrnExtendedDataTableModelApi nabavkaJrnExtendedDataTableModelApi) {
        this.nabNabavkaJrnExtendedDataTableModelApi = nabavkaJrnExtendedDataTableModelApi;
    }

    public NabNabavkaJrnServiceApi getNabNabavkaJrnServiceApi() {
        return nabNabavkaJrnServiceApi;
    }

    public void setNabNabavkaJrnServiceApi(
            NabNabavkaJrnServiceApi nabNabavkaJrnServiceApi) {
        this.nabNabavkaJrnServiceApi = nabNabavkaJrnServiceApi;
    }

    public XnabJrn getXnabJrn() {
        return xnabJrn;
    }

    public void setXnabJrn(XnabJrn xnabJrn) {
        this.xnabJrn = xnabJrn;
    }

    public NabJrnLovSelectionController getNabJrnLovSelectionController() {
        return nabJrnLovSelectionController;
    }

    public void setNabJrnLovSelectionController(
            NabJrnLovSelectionController nabJrnLovSelectionController) {
        this.nabJrnLovSelectionController = nabJrnLovSelectionController;
    }

    public void clearPartijaSelection(){
        novaPartija = new NabPartijaNabavke();
    }
    
    public void dodajPartiju(){
        try {
            if (null == novaNabavka || null == novaNabavka.getIdJavnaNabavka()) {
                throw new Exception(
                        "Nije Setovana nabavka za koju se pravi partija");
            }
            novaPartija.setNabJavnaNabavka(novaNabavka);
            nabPartijaNabavkeServiceApi.createNabPartijaNabavke(novaPartija);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ovo treba ukloniti
    public void actionInitSffPoslovniZiroRacunLov(){
        System.out.println( "ovde se okica akcija actionInitSffPoslovniZiroRacunLov");
    }
    
    public void dodajProcenu(){
        
        try {
            if ( null == novaNabavka || null == novaNabavka.getIdJavnaNabavka()) {
                throw new Exception( "Nije Setovana nabavka za koju se radi procena");
            }
            novaProcenjenaVrednost.setNabJavnaNabavka(novaNabavka);
            System.out.println( novaProcenjenaVrednost );
            nabProcenaPoGodiniServiceApi.createNabProcenaPoGodini(novaProcenjenaVrednost);
            populateModalOkPanelSnimanjeDefaultMessagesWithHeaderMessage(true,
                    getMessage("nabNabavkaAzuriranjeNabavkeHeader"));
        } catch (Exception e) {
            populateModalOkPanelSnimanjeDefaultMessagesWithHeaderMessage(false,
                    getMessage("nabNabavkaAzuriranjeNabavkeHeader"));

            e.printStackTrace();
        }finally{
            resetNabNovuProcenjenuVrednost();
        }
    }
    
    // 14.08.2014
    // TODO treba preimenovati, previše je opšte
    public void initModalDialogBrisanje() {
        System.out.println( getModalPanelUtil().getFullId());
//        System.out.println( getModalPanelUtil().initModalDialogBrisanje("nabNabavkaProcenaPoGodiniHeader", message, dugmeDaOncomplete, dugmeDaForProcess));
        initModalDialogBrisanje(
                "nabNabavkaProcenaPoGodiniHeader",
                new MessageBundleProperty(
                        "nabNabavkaProcenaBrisanjePitanje"),
                "nabProcenaBrisanje()");
        
        System.out.println( "Ovde se izvršio iniModalDialogBrisanje");
    }
    
    public void obrisiProcenu(){
       
        if ( null != nabProcenaPoGodini){
            try {
                nabProcenaPoGodiniServiceApi.deleteProcenaPoGodini( nabProcenaPoGodini);
                populateModalOkPanelSnimanjeDefaultMessages(true,"nabNabavkaProcenaPoGodiniBrisanjeHeader" );
                nabProcenaPoGodini = null;
            } catch (Exception e) {
               
                populateModalOkPanelSnimanjeDefaultMessages(false,"nabNabavkaProcenaPoGodiniBrisanjeHeader" );                e.printStackTrace();
            }
            
        }else {
            System.out.println( "Ne postoji nabProcenaPoGodini");
            populateModalOkPanelSnimanjeDefaultMessages(false,"nabNabavkaProcenaPoGodiniBrisanjeHeader" );
        }
    }
    
    public void initModalDialogBrisanjePartija(){

        initModalDialogBrisanje(
                "nabNabavkaPartijaHeader",
                new MessageBundleProperty(
                        "nabNabavkaPartijaBrisanjePitanje"),
                "nabPartijaBrisanje()");

    }
    
    public void obrisiPartiju(){
        if ( null != novaPartija ){
            try {
                nabPartijaNabavkeServiceApi.deletePartija( novaPartija );
                populateModalOkPanelSnimanjeDefaultMessagesCommonHeader(true);
            } catch (Exception e) {
                populateModalOkPanelSnimanjeDefaultMessagesCommonHeader(false);
                e.printStackTrace();
            }
            novaPartija = null;
        }else {
            populateModalOkPanelSnimanjeDefaultMessagesCommonHeader(false);
        }
    }
    
    public void actionSnimiPlaniranuVrednostPoKontima(){
        if ( null != novaNabNabavkaKontoPartija) {
            try {
                novaNabNabavkaKontoPartija.setNabJavnaNabavka(novaNabavka);
                novaNabNabavkaKontoPartija.setNabKonto(xnabKonto);
                nabNabavkaKontoPartijaServiceApi.createNabNabavkaKontoPartija(novaNabNabavkaKontoPartija);
                populateModalOkPanelSnimanjeDefaultMessagesWithHeaderMessage(true,getMessage("nabNabavkaPlaniranaVrednostPoKontimaHeader"));
            } catch (Exception e) {
                populateModalOkPanelSnimanjeDefaultMessagesWithHeaderMessage(false, getMessage("nabNabavkaPlaniranaVrednostPoKontimaHeader"));populateModalOkPanelSnimanjeDefaultMessagesWithHeaderMessage(false, getMessage("nabNabavkaPlaniranaVrednostPoKontimaHeader"));
                e.printStackTrace();
            }
        }else{
            populateModalOkPanelSnimanjeDefaultMessagesWithHeaderMessage(false, getMessage("nabNabavkaPlaniranaVrednostPoKontimaHeader")); 
        }
        
    }
    
    public void actionInitNabKontoLovAction(){
        
        nabKontoLovSelectionController.onEntry();
        nabKontoLovSelectionController.setDugmeAction("nabNovaNabavkaController.actionTransferKonto");
        nabKontoLovSelectionController.setDugmeReRender("panelKonto"); 
        nabKontoLovSelectionController.setFocusIdCancel("inputNabKonto");
        nabKontoLovSelectionController.setFocusIdOK("inputNazivPartije");
        
    }
    
    //za konto lov modal panel
    public void actionTransferKonto(){
        setXnabKonto(nabKontoLovSelectionController.getNabKonto());
    }
    
    // kada se klikne na delete konta iz modalnog prozora partijekontanabavke
    public void resetNabKonto(){
        // TODO implementirati
    }
    
    public void initModalDialogBrisanjeJrn(){
        initModalDialogBrisanje(
                "nabNabavkaJrnBrisanjeHeader",
                new MessageBundleProperty(
                        "nabNabavkaJrnBrisanjePitanje"),
                "nabNabavkaJrnBrisanje()");

    }
    
    public void obrisiNabavkaJrn(){
         
            if ( null != novaNabavkaJrn){
                try {
                    nabNabavkaJrnServiceApi.deleteNabavkaJrn(novaNabavkaJrn);
                    populateModalOkPanelSnimanjeDefaultMessages(true,"nabNabavkaProcenaPoGodiniBrisanjeHeader" );
                    novaNabavkaJrn = null;
                } catch (Exception e) {
                   
                    populateModalOkPanelSnimanjeDefaultMessages(false,"nabNabavkaProcenaPoGodiniBrisanjeHeader" );                e.printStackTrace();
                }
                
            }else {
                System.out.println( "Ne postoji novaNabavkaJrn");
                populateModalOkPanelSnimanjeDefaultMessages(false,"nabNabavkaProcenaPoGodiniBrisanjeHeader" );
            }


    }
    
    public void actionInitNabJrnLovAction(){
        nabJrnLovSelectionController.onEntry();
        nabJrnLovSelectionController.setDugmeAction("nabNovaNabavkaController.actionTransferJrn");
        nabJrnLovSelectionController.setDugmeReRender("panelGridNabavkaJrn"); 
        nabJrnLovSelectionController.setFocusIdCancel("inputNabJrn");
        nabJrnLovSelectionController.setFocusIdOK("nabJrnModalPanelDugmePotvrdi");

    }
    
    public void actionTransferJrn(){
        xnabJrn = nabJrnLovSelectionController.getNabJrn();
    }
    
    public void dodajOrn(){
        try {
            if ( null == novaNabavka || null == novaNabavka.getIdJavnaNabavka()) {
                throw new Exception( "Nije Setovana nabavka za koju se radi procena");
            }
            novaNabavkaJrn.setNabJavnaNabavka(novaNabavka);
            novaNabavkaJrn.setNabJrn(xnabJrn);
            nabNabavkaJrnServiceApi.createNabNabavkaJrn(novaNabavkaJrn);
            populateModalOkPanelSnimanjeDefaultMessagesWithHeaderMessage(true,
                    getMessage("nabNabavkaAzuriranjeNabavkeHeader"));
        } catch (Exception e) {
            populateModalOkPanelSnimanjeDefaultMessagesWithHeaderMessage(false,
                    getMessage("nabNabavkaAzuriranjeNabavkeHeader"));

            e.printStackTrace();
        }finally{
            resetNabNovuProcenjenuVrednost();
        }

    }
    
    public void resetNabNabavkaJrn(){
        novaNabavkaJrn = new NabNabavkaJrn();
        
    }
    
    public void actionInitVrednostPoKontimaAzur(){
        xnabKonto = novaNabNabavkaKontoPartija.getNabKonto();
    }
    
    public void initModalDialogBrisanjeKontoPartija(){
        System.out.println( "JEESII LI SIIIIGUURANN");
        initModalDialogBrisanje(
                "nabNabavkaPartijaiHeader",
                new MessageBundleProperty(
                        "nabNabavkaJrnBrisanjePitanje"),
                "nabNabavkaKontoPartijaBrisanje()");
       
    }
    
    public void obrisiNabKontoPartiju(){
        System.out.println( "Ok , tražio si brisanje Nab Konto Partija i dobio si");
        
        try {
            if ( null == novaNabNabavkaKontoPartija) throw new Exception( "Ne postoji objekat");
            nabNabavkaKontoPartijaServiceApi.deleteNabavkaKontoPartija(novaNabNabavkaKontoPartija);
            populateModalOkPanelSnimanjeDefaultMessages(true,"nabNabavkaKontoPartijaBrisanjeHeader" );
            novaNabNabavkaKontoPartija = null;
        } catch (Exception e) {               
            populateModalOkPanelSnimanjeDefaultMessages(false,"nabNabavkaKontoPartijaBrisanjeHeader" );
            e.printStackTrace();
        }
    }
    
    public void actionInitProcenaPoGodiniAzur(){
        
    }
    

}
