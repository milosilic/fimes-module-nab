package rs.fimes.service.api.nab;

import rs.etf.rc.common.service.api.BaseServiceApi;
import rs.fimes.domain.core.OrgFirma;

public interface NabNaruciociServiceApi extends BaseServiceApi {

    OrgFirma getActiveOrgFirma(int id);

}
