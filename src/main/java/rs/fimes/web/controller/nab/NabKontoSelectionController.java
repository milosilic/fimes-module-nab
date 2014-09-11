package rs.fimes.web.controller.nab;

import java.util.ArrayList;
import java.util.List;









import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.utils.MessageBundleProperty;
import rs.etf.rc.common.web.controller.BaseSelectionController;
import rs.fimes.data.dao.generic.QueryRestriction;
import rs.fimes.data.dao.generic.QueryRestrictionComparison1;
import rs.fimes.domain.nab.XnabKonto;
import rs.fimes.service.api.nab.XnabKontoServiceApi;
import rs.fimes.service.exception.FimesServiceException;
import rs.fimes.web.datamodel.api.nab.XnabKontoExtendedDataTableModelApi;

public class NabKontoSelectionController extends BaseSelectionController {
    
    //27.06.2014
    private List<XnabKonto> listaKonta;
//    ukloniti ovaj reference
    private XnabKontoServiceApi xnabKontoServiceApi;
    private XnabKontoExtendedDataTableModelApi xnabKontoExtendedDataTableModelApi;

    private XnabKonto konto;
    
    //29.06.2014.
    /* parametri pretrage */

    private String pretragaNaziv;
    
    //10.09.2014
    private XnabKonto xnabKontoSelected;
    
    
    public NabKontoSelectionController(Module module, String messageSource)
            throws ConfigurationException {
        super(module, messageSource);
        // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = -1102872899744848852L;

    public void onStart(){
        xnabKontoExtendedDataTableModelApi.helperWalkByRequest();
//        ukloniti ovo
//        setListaKonta(xnabKontoServiceApi.getAllKonto());
        
    }

    public XnabKontoServiceApi getXnabKontoServiceApi() {
        return xnabKontoServiceApi;
    }

    public void setXnabKontoServiceApi(XnabKontoServiceApi xnabKontoServiceApi) {
        this.xnabKontoServiceApi = xnabKontoServiceApi;
    }

    public XnabKonto getKonto() {
        return konto;
    }

    public void setKonto(XnabKonto konto) {
        this.konto = konto;
    }

    public List<XnabKonto> getListaKonta() {
        return listaKonta;
    }

    public void setListaKonta(List<XnabKonto> listaKonta) {
        this.listaKonta = listaKonta;
    }

    public XnabKontoExtendedDataTableModelApi getXnabKontoExtendedDataTableModelApi() {
        return xnabKontoExtendedDataTableModelApi;
    }

    public void setXnabKontoExtendedDataTableModelApi(
            XnabKontoExtendedDataTableModelApi xnabKontoExtendedDataTableModelApi) {
        this.xnabKontoExtendedDataTableModelApi = xnabKontoExtendedDataTableModelApi;
    }

    public String getPretragaNaziv() {
        return pretragaNaziv;
    }

    public void setPretragaNaziv(String pretragaNaziv) {
        this.pretragaNaziv = pretragaNaziv;
    }

    public XnabKonto getXnabKontoSelected() {
        return xnabKontoSelected;
    }

    public void setXnabKontoSelected(XnabKonto xnabKontoSelected) {
        this.xnabKontoSelected = xnabKontoSelected;
    }

    @Override
    public void pretraga() {
        System.out.println("ovde se implemetira pretraga");

        resetSelection();
        List<QueryRestriction> parametri = new ArrayList<QueryRestriction>();
        pretragaNaziv = emptyStringToNull(pretragaNaziv);
        if (pretragaNaziv != null) {
            parametri.add(QueryRestrictionComparison1
                    .addCirToAbcStringContains(
                            "konto",
                            getStringUtil().transliterationCirToAbc(
                                    pretragaNaziv.replaceAll("\\s+", " "))));
        }
        xnabKontoExtendedDataTableModelApi.setParametri(parametri);

    }
    
    public void initModalDialogBrisanje(){
        initModalDialogBrisanje(
                "nabKontoBrisanjeKontaHeader",
                new MessageBundleProperty(
                        "nabKontoBrisanjeKontaPitanje"),
                "jsXnabKontoBrisanje()");


    }
    
    public void obrisiKonto(){
        if ( null != xnabKontoSelected ){
            try {
                xnabKontoServiceApi.deleteKonto( xnabKontoSelected);
                populateModalOkPanelSnimanjeDefaultMessages(true,
                        "nabKontoBrisanjeKontaHeader");
            }catch (FimesServiceException e) {
                e.printStackTrace();
                populateModalOkPanelSnimanjeDefaultMessages(false,
                        "nabKontoBrisanjePostojeReference");
            }catch (Exception e) {
                e.printStackTrace();
                populateModalOkPanelSnimanjeDefaultMessages(false,
                        "nabKontoBrisanjeKontaHeader");
            } finally {
                resetSelection();
            }
        }
    }

    @Override
    public void handleSelection() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resetSelection() {
      xnabKontoExtendedDataTableModelApi.clearSelection();
      xnabKontoSelected = null;
        
    }
    
}
