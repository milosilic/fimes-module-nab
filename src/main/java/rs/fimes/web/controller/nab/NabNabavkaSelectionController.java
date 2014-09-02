package rs.fimes.web.controller.nab;

import java.util.ArrayList;
import java.util.List;

import org.ajax4jsf.component.html.HtmlAjaxCommandButton;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.etf.rc.common.web.controller.BaseSelectionController;
import rs.fimes.data.dao.generic.QueryJoin;
import rs.fimes.data.dao.generic.QueryJoinImpl;
import rs.fimes.data.dao.generic.QueryJoinType;
import rs.fimes.data.dao.generic.QueryRestriction;
import rs.fimes.data.dao.generic.QueryRestrictionComparison1;
import rs.fimes.domain.core.OrgFirma;
import rs.fimes.domain.nab.NabJavnaNabavka;
import rs.fimes.web.datamodel.api.nab.NabJavnaNabavkaExtendedDataTableModelApi;


/**
 * @author milos
 *
 */
public class NabNabavkaSelectionController extends
        BaseSelectionController {

    private static final long serialVersionUID = -4391465865132285599L;
//po uzoru na OrgGrupaRadnihMestaSifarnikLovSelectionController
    public NabNabavkaSelectionController(Module module,
            String messageSource) throws ConfigurationException {
        super(module, messageSource);
        initDugme();
    }

    // DATA MODEL
    private NabJavnaNabavkaExtendedDataTableModelApi nabNabavkaExtendedDataTableModelApi;
    
    private NabJavnaNabavka nabKonto;
    
    // polja za pretragu
    private String naziv;
    private String konto;

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
        naziv = null;
        konto = null;
    }
    
    @Override
    public void pretraga() {   
        
        List<QueryRestriction> parametri = new ArrayList<QueryRestriction>();
        
        if (naziv != null && !naziv.trim().isEmpty()) {
            parametri.add(QueryRestrictionComparison1.addCirToAbcStringContains("naziv",
                   getStringUtil().transliterationCirToAbc (naziv.trim().replaceAll("\\s+", " "))));
        }
        if (konto != null && !konto.trim().isEmpty()) {
            parametri.add(QueryRestrictionComparison1.addCirToAbcStringContains("konto",
                   getStringUtil().transliterationCirToAbc (konto.trim().replaceAll("\\s+", " "))));
        }
        
        nabNabavkaExtendedDataTableModelApi.setParametri( parametri);
        nabNabavkaExtendedDataTableModelApi.setSortField("konto asc");
        resetSelection();
    }

    @Override
    public void handleSelection() {
        nabKonto = nabNabavkaExtendedDataTableModelApi.getDomainObject();
    }

    @Override
    public void resetSelection() {
        nabKonto = null;
        nabNabavkaExtendedDataTableModelApi.clearSelection();
    }

    public void odrediSort() {
        nabNabavkaExtendedDataTableModelApi.odrediSort();
        resetSelection();
    }

    private void initDugme() {
        dugme = new HtmlAjaxCommandButton();
        dugme.setValue(getMessage("common_odaberi"));
    }

    public String getKonto() {
        return konto;
    }

    public void setKonto(String konto) {
        this.konto = konto;
    }

    public void setDugmeAction(String action) {
        dugme.setActionExpression(createExpresion(action));
    }

    public void setDugmeReRender(String reRenderList) {
        dugme.setReRender(reRenderList);
    }

    
    // GETTERS AND SETTERS
    
    public NabJavnaNabavkaExtendedDataTableModelApi getNabJavnaNabavkaExtendedDataTableModelApi() {
        return nabNabavkaExtendedDataTableModelApi;
    }

    public void setNabJavnaNabavkaExtendedDataTableModelApi(
            NabJavnaNabavkaExtendedDataTableModelApi nabNabavkaExtendedDataTableModelApi) {
        this.nabNabavkaExtendedDataTableModelApi = nabNabavkaExtendedDataTableModelApi;
    }

    public NabJavnaNabavka getNabKonto() {
        return nabKonto;
    }

    public void setNabKonto(NabJavnaNabavka nabKonto) {
        this.nabKonto = nabKonto;
    }

    public String getNaziv() {
        return naziv;
    }
    public void setNaziv(String naziv) {
        this.naziv = naziv;
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
