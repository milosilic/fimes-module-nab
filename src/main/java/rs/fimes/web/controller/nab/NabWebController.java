package rs.fimes.web.controller.nab;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.web.WebComponent;
import rs.etf.rc.common.web.exception.CommonWebException;
import rs.fimes.web.controller.BaseWebController;

public class NabWebController extends BaseWebController {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4708362441986936471L;
    
    private WebComponent nabNoviPlanModalPanel;
    private WebComponent nabProcenjenaVrednostUnosModalPanel;
    private WebComponent nabNovaNabavkaModalPanel;
    private WebComponent nabNoviJrnModalPanel;
    
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
    
    public String getNabNoviPlanModalPanelView(){
        try {
            return getNavigationUtil().getView("nabNoviPlanModalPanel");
        } catch (CommonWebException e) {

            e.printStackTrace();
            return "null";
        }
    }
    
    public String getNabProcenjenaVrednostUnosModalPanelView(){
        try {
            return getNavigationUtil().getView("nabProcenjenaVrednostUnosModalPanel");
        } catch (CommonWebException e) {
          
            e.printStackTrace();
            return "null";
        }
    }
    
    public String getNabNovaNabavkaModalPanelView(){
        try {
            return getNavigationUtil().getView("nabNovaNabavkaModalPanel");
        } catch (CommonWebException e) {
          
            e.printStackTrace();
            return "null";
        }
        
    }
    
    public String getNabNoviJrnModalPanelView(){
        try {
            System.out.println("ČČČČČČČČČČČČČČČČČČČČČČČČČČČČČČ");
            return getNavigationUtil().getView("nabNoviJrnModalPanel");
        } catch (CommonWebException e) {
          
            e.printStackTrace();
            return "null";
        }
        
    }

    public WebComponent getNabProcenjenaVrednostUnosModalPanel() {
        return nabProcenjenaVrednostUnosModalPanel;
    }

    public void setNabProcenjenaVrednostUnosModalPanel(
            WebComponent nabProcenjenaVrednostUnosModalPanel) {
        this.nabProcenjenaVrednostUnosModalPanel = nabProcenjenaVrednostUnosModalPanel;
    }

    public WebComponent getNabNovaNabavkaModalPanel() {
        return nabNovaNabavkaModalPanel;
    }

    public void setNabNovaNabavkaModalPanel(WebComponent nabNovaNabavkaModalPanel) {
        this.nabNovaNabavkaModalPanel = nabNovaNabavkaModalPanel;
    }

    public WebComponent getNabNoviJrnModalPanel() {
        return nabNoviJrnModalPanel;
    }

    public void setNabNoviJrnModalPanel(WebComponent nabNoviJrnModalPanel) {
        this.nabNoviJrnModalPanel = nabNoviJrnModalPanel;
    }
    
    

}
