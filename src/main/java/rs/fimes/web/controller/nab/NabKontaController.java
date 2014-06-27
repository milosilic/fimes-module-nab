package rs.fimes.web.controller.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.web.controller.BaseController;

public class NabKontaController extends BaseController {

    public NabKontaController(Module module, String controllerId)
            throws ConfigurationException {
        super(module, controllerId);

    }

    private static final long serialVersionUID = -1102872899744848852L;

    public void onStart(){
        System.out.println("učitao se nabKonta");
    }
}
