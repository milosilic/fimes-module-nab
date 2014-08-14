package rs.fimes.web.controller.nab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlForm;
import javax.faces.model.SelectItem;

import org.ajax4jsf.component.html.HtmlAjaxCommandButton;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.generic.QueryRestriction;
import rs.fimes.data.dao.generic.QueryRestrictionComparison1;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.domain.nab.NabPartijaNabavke;
import rs.fimes.domain.nab.NabPlan;
import rs.fimes.domain.nab.NabProcenaPoGodini;
import rs.fimes.domain.nab.XnabPredmetNabavke;
import rs.fimes.domain.nab.XnabStatusNabavke;
import rs.fimes.domain.nab.XnabTipNabavke;
import rs.fimes.domain.nab.XnabVrstaPostupka;
import rs.fimes.domain.nab.XnabVrstaPredmetaNabavke;
import rs.fimes.service.api.core.UsrKorisnikServiceApi;
import rs.fimes.service.api.nab.NabJavnaNabavkaServiceApi;
import rs.fimes.service.api.nab.NabNaruciociServiceApi;
import rs.fimes.service.api.nab.NabPartijaNabavkeServiceApi;
import rs.fimes.service.api.nab.NabPlanServiceApi;
import rs.fimes.service.api.nab.NabProcenaPoGodiniServiceApi;
import rs.fimes.service.api.nab.XnabPredmetNabavkeServiceApi;
import rs.fimes.service.api.nab.XnabStatusNabavkeServiceApi;
import rs.fimes.service.api.nab.XnabTipNabavkeServiceApi;
import rs.fimes.service.api.nab.XnabVrstaPostupkaServiceApi;
import rs.fimes.service.api.nab.XnabVrstaPredmetaNabavkeServiceApi;
import rs.fimes.web.controller.BaseController;
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
    private static final long serialVersionUID = -788600541631559492L;

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
        System.out.println( "**************************  : aaaaaaaaaaaa"  + idPredmetNabavke );
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
    

}
