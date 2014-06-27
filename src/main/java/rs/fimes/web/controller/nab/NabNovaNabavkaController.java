package rs.fimes.web.controller.nab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlForm;
import javax.faces.model.SelectItem;

import org.ajax4jsf.component.html.HtmlAjaxCommandButton;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.domain.nab.XnabPredmetNabavke;
import rs.fimes.domain.nab.XnabTipNabavke;
import rs.fimes.domain.nab.XnabVrstaPostupka;
import rs.fimes.domain.nab.XnabVrstaPredmetaNabavke;
import rs.fimes.service.api.core.UsrKorisnikServiceApi;
import rs.fimes.service.api.nab.NabNaruciociServiceApi;
import rs.fimes.service.api.nab.NabPlanServiceApi;
import rs.fimes.service.api.nab.XnabPredmetNabavkeServiceApi;
import rs.fimes.service.api.nab.XnabTipNabavkeServiceApi;
import rs.fimes.service.api.nab.XnabVrstaPostupkaServiceApi;
import rs.fimes.service.api.nab.XnabVrstaPredmetaNabavkeServiceApi;
import rs.fimes.web.controller.BaseController;

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
    
    private ArrayList<SelectItem> xnabPredmetNabavkeSelectionItems;
    private XnabPredmetNabavkeServiceApi xnabPredmetNabavkeServiceApi;
    private Integer idPredmetNabavke;
    private NabJavnaNabavka novaNabavka;
    
    //@TODO skloni ove objekte iz kontrolera, kada rešiš gde se pamte (nab_javna_nabavka)
    private XnabVrstaPredmetaNabavke vrstaPredmetaNabavke;
    private XnabPredmetNabavke predmetNabavke;
   
    //28.06.2014
    
    private ArrayList<SelectItem> xnabTipNabavkeSelectionItems;
    private XnabTipNabavkeServiceApi xnabTipNabavkeServiceApi; 

    //@TODO skloni ove objekte iz kontrolera, kada rešiš gde se pamte (nab_javna_nabavka)
    private XnabTipNabavke tipNabavke;
    
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
        novaNabavka = new NabJavnaNabavka();
        vrstaPredmetaNabavke = new XnabVrstaPredmetaNabavke();
        predmetNabavke = new XnabPredmetNabavke();
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
            System.out.println( xnabVrstaPostupka.getNaziv());
            System.out.println( xnabVrstaPostupka.getPrimaryKey());
            xnabVrstaPostupkaSelectionItems.add(new SelectItem( xnabVrstaPostupka.getPrimaryKey(), String.valueOf( xnabVrstaPostupka.getNaziv())));
         }
        xnabPredmetNabavkeSelectionItems = new ArrayList<SelectItem>();
        List<XnabPredmetNabavke> xnabPredmetNabavkes = xnabPredmetNabavkeServiceApi.getAllPredmetNabavke();
        Iterator<XnabPredmetNabavke> iterXnabPredmetNabavke = xnabPredmetNabavkes.iterator();
        while ( iterXnabPredmetNabavke.hasNext()){
            XnabPredmetNabavke xnabPredmetNabavke = (XnabPredmetNabavke) iterXnabPredmetNabavke.next();
            System.out.println( xnabPredmetNabavke);
            xnabPredmetNabavkeSelectionItems.add(new SelectItem( xnabPredmetNabavke.getPrimaryKey(), String.valueOf( xnabPredmetNabavke.getNaziv())));
         }
        xnabTipNabavkeSelectionItems = new ArrayList<SelectItem>();
        List<XnabTipNabavke> xnabTipNabavkes = xnabTipNabavkeServiceApi.getAllTipNabavke();
        Iterator<XnabTipNabavke> iterXnabTipNabavke = xnabTipNabavkes.iterator();
        while ( iterXnabTipNabavke.hasNext()){
            XnabTipNabavke xnabTipNabavke = (XnabTipNabavke) iterXnabTipNabavke.next();
            System.out.println( xnabTipNabavke);
            xnabTipNabavkeSelectionItems.add(new SelectItem( xnabTipNabavke.getPrimaryKey(), String.valueOf( xnabTipNabavke.getNaziv())));
         }       
        if ( orgFirma == null ) {
            setOrgFirma(nabNaruciociServiceApi.getActiveOrgFirma(getUserSessionUtil().getCurrentUserCurrentOrgFirma().getIdFirma()));
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

    public ArrayList<SelectItem> getXnabPredmetNabavkeSelectionItems() {
        return xnabPredmetNabavkeSelectionItems;
    }

    public void setXnabPredmetNabavkeSelectionItems(
            ArrayList<SelectItem> xnabPredmetNabavkeSelectionItems) {
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
    

}
