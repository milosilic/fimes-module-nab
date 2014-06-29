package rs.fimes.web.controller.nab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.domain.nab.XnabKonto;
import rs.fimes.service.api.nab.XnabKontoServiceApi;
import rs.fimes.web.controller.BaseController;

public class NabKontaController extends BaseController {
    
    //27.06.2014
    private List<XnabKonto> listaKonta;
    private XnabKontoServiceApi xnabKontoServiceApi;

    private XnabKonto konto;

    public NabKontaController(Module module, String controllerId)
            throws ConfigurationException {
        super(module, controllerId);

    }

    private static final long serialVersionUID = -1102872899744848852L;

    public void onStart(){
        System.out.println("učitao se nabKonta");
        setListaKonta(xnabKontoServiceApi.getAllKonto());
        
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
    
}
