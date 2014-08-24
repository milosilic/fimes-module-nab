package rs.fimes.web.controller.nab;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.component.html.HtmlAjaxCommandButton;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.web.controller.BaseSelectionController;
import rs.fimes.data.dao.generic.QueryRestriction;
import rs.fimes.data.dao.generic.QueryRestrictionComparison1;
import rs.fimes.domain.nab.XnabJrn;
import rs.fimes.web.datamodel.api.nab.XnabJrnExtendedDataTableModelApi;


/**
 * @author milos
 *
 */
public class NabJrnLovSelectionController extends
        BaseSelectionController {

    private static final long serialVersionUID = -4391465865132285599L;
//po uzoru na OrgGrupaRadnihMestaSifarnikLovSelectionController
    public NabJrnLovSelectionController(Module module,
            String messageSource) throws ConfigurationException {
        super(module, messageSource);
        initDugme();
    }

    // DATA MODEL
    private XnabJrnExtendedDataTableModelApi xnabJrnExtendedDataTableModelApi;
    
    private XnabJrn nabJrn;
    
    // polja za pretragu
    private String kod;
    private String opis;

    private HtmlAjaxCommandButton dugme;
    private String focusIdOK;
    private String focusIdCancel;
    
    
    /** inicijalizacija */
    public void onEntry() {
        resetSelection();
        resetParametriPretrage();
        pretraga();
    }

    public void resetParametriPretrage() {
        opis = null;
        kod = null;
    }
    
    @Override
    public void pretraga() {   
        
        List<QueryRestriction> parametri = new ArrayList<QueryRestriction>();
        
        if (opis != null && !opis.trim().isEmpty()) {
            parametri.add(QueryRestrictionComparison1.addCirToAbcStringContains("opis",
                   getStringUtil().transliterationCirToAbc (opis.trim().replaceAll("\\s+", " "))));
        }
        if (kod != null && !kod.trim().isEmpty()) {
            parametri.add(QueryRestrictionComparison1.addCirToAbcStringContains("kod",
                   getStringUtil().transliterationCirToAbc (kod.trim().replaceAll("\\s+", " "))));
        }
        
        xnabJrnExtendedDataTableModelApi.setParametri( parametri);
        xnabJrnExtendedDataTableModelApi.setSortField("kod asc");
        resetSelection();
    }

    @Override
    public void handleSelection() {
        nabJrn = xnabJrnExtendedDataTableModelApi.getDomainObject();
    }

    @Override
    public void resetSelection() {
        nabJrn = null;
        xnabJrnExtendedDataTableModelApi.clearSelection();
    }

    public void odrediSort() {
        xnabJrnExtendedDataTableModelApi.odrediSort();
        resetSelection();
    }

    private void initDugme() {
        dugme = new HtmlAjaxCommandButton();
        dugme.setValue(getMessage("common_odaberi"));
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public void setDugmeAction(String action) {
        dugme.setActionExpression(createExpresion(action));
    }

    public void setDugmeReRender(String reRenderList) {
        dugme.setReRender(reRenderList);
    }

    
    // GETTERS AND SETTERS
    

    public XnabJrnExtendedDataTableModelApi getXnabJrnExtendedDataTableModelApi() {
        return xnabJrnExtendedDataTableModelApi;
    }

    public void setXnabJrnExtendedDataTableModelApi(
            XnabJrnExtendedDataTableModelApi xnabJrnExtendedDataTableModelApi) {
        this.xnabJrnExtendedDataTableModelApi = xnabJrnExtendedDataTableModelApi;
    }

    public XnabJrn getNabJrn() {
        return nabJrn;
    }

    public void setNabJrn(XnabJrn nabJrn) {
        this.nabJrn = nabJrn;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public HtmlAjaxCommandButton getDugme() {
        return dugme;
    }
    public void setDugme(HtmlAjaxCommandButton dugme) {
        this.dugme = dugme;
    }
    public String getFocusIdOK() {
        return focusIdOK;
    }
    public void setFocusIdOK(String focusIdOK) {
        this.focusIdOK = focusIdOK;
    }
    public String getFocusIdCancel() {
        return focusIdCancel;
    }
    public void setFocusIdCancel(String focusIdCancel) {
        this.focusIdCancel = focusIdCancel;
    }

}
