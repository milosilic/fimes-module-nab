package rs.fimes.web.controller.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.web.WebComponent;
import rs.etf.rc.common.web.exception.CommonWebException;
import rs.fimes.web.controller.BaseWebController;

public class NabWebController extends BaseWebController {

    public NabWebController(Module module, String shortId)
            throws ConfigurationException {
        super(module, shortId);
        // TODO Auto-generated constructor stub
    }

    public WebComponent getNabNoviPlanModalPanel() {
        return nabNoviPlanModalPanel;
    }

    public void setNabNoviPlanModalPanel(WebComponent nabNoviPlanModalPanel) {
        this.nabNoviPlanModalPanel = nabNoviPlanModalPanel;
    }

    /**
     * 
     */
    private static final long serialVersionUID = -4708362441986936471L;
    
    private WebComponent nabNoviPlanModalPanel;
    
    public String getNabNoviPlanModalPanelView(){
        try {
            return getNavigationUtil().getView("nabNoviPlanModalPanel");
        } catch (CommonWebException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "null";
        }
    }
    
    

}
