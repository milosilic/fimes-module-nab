package rs.fimes.data.dao.impl.hibernate.nab;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rs.etf.rc.common.application.ConfigurationException;
import rs.etf.rc.common.application.Module;
import rs.fimes.data.dao.api.core.SysMenuItemDAO;
import rs.fimes.data.dao.generic.BaseDaoImplHibernate;
import rs.fimes.data.dao.generic.OrderBy;
import rs.fimes.domain.core.XusrKategorija;
import rs.fimes.domain.sys.SysMenuItem;

public class SysMenuItemDAOImpl extends
        BaseDaoImplHibernate<SysMenuItem, Integer> implements
        SysMenuItemDAO, Serializable {

    private static final long serialVersionUID = 9065857407617824817L;

    public SysMenuItemDAOImpl(Module module, String daoId)
            throws ConfigurationException {
        super(module, daoId);
    }

    @Override
    public List<SysMenuItem> selectSysMenuItems() {
        String search = "select o from SysMenuItem o";
        // "where (o.xsysKomponenta.idKomponenta < 2 or o.xsysKomponenta.idKomponenta > 14) ";
        List<OrderBy> orderBy = new ArrayList<OrderBy>();
        orderBy.add(new OrderBy("xsysKomponenta.idKomponenta"));
        orderBy.add(new OrderBy("roditelj.idMenuItem"));
        orderBy.add(new OrderBy("idMenuItem"));
        Map<String, ?> paramMap = new HashMap<String, Object>();
        return customSearch(search, paramMap, orderBy);
    }

    @Override
    public List<SysMenuItem> selectSysMenuItemChildren(
            SysMenuItem roditelj) {
        String search = "select o " + "from SysMenuItem o "
                + "where o.roditelj = :roditelj ";
        List<OrderBy> orderBy = new ArrayList<OrderBy>();
        orderBy.add(new OrderBy("idMenuItem"));
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roditelj", roditelj);
        return customSearch(search, paramMap, orderBy);
    }

    @Override
    public List<SysMenuItem> selectSysMenuItemParents() {
        String search = "select o "
                + "from SysMenuItem o "
                + "where (o.roditelj = 0 or o.roditelj is null) "
                + "and o.idMenuItem != 0 "
                + "and o.xsysKomponenta.key in ('core','sif','pp','prj','osb','obj','vrm','mat','dok','efz','gk','isp','blg','dev','rac','rfn') "
                + " order by o.sortRedniBroj ASC NULLS LAST ";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        return customSearch(search, paramMap);
    }
 
    @Override
    public List<SysMenuItem> selectSysMenuItemParentsWithChildrenLevel4() {
       
        String search = "select distinct o "
                + "from SysMenuItem o "
                + "left join fetch o.deca d "
                + "left join fetch o.xsysKomponenta komponenta "
                + "left join fetch d.deca dd "
                + "left join fetch dd.deca ddd "
                + "left join fetch ddd.deca dddd "
                + "where (o.roditelj = 0 or o.roditelj is null) "
                + "and o.idMenuItem != 0 "
                + "and komponenta.key in ('core','sif','pp','prj','osb','obj','vrm','mat','dok','efz','gk','isp','blg','dev','rac','rfn') "
                + " order by o.sortRedniBroj ASC NULLS LAST ";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        return customSearch(search, paramMap);
    }
    
    @Override
    public List<SysMenuItem> selectSysMenuItemParentsWithChildrenLevel4(List<XusrKategorija> xusrKategorijas) {
        
        // formira string kljuceva kategorija tog korisnika
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < xusrKategorijas.size(); i++){
            if (i!=0) sb.append(',');
            sb.append('\''+xusrKategorijas.get(i).getKey()+'\'');
        }
        // ovde treba selektovati sve root meni stavke koje imaju privilegije
        String search = "select distinct o "
                + "from SysMenuItem o "
                + "left join fetch o.deca d "
                + "left join fetch o.xsysKomponenta komponenta "
                + "left join fetch d.deca dd "
                + "left join fetch dd.deca ddd "
                + "left join fetch ddd.deca dddd "
                // parent is null
                + "where o.sysMenuItem is null "
                
                + "and (o.idMenuItem in (select p.sysMenuItem.idMenuItem from UsrKategorijaMenuItem p " 
                + "where p.xusrKategorija.key in (" + sb.toString() + ")))"
                
                + "and (d is null or d.idMenuItem in (select p.sysMenuItem.idMenuItem from UsrKategorijaMenuItem p " 
                + "where p.xusrKategorija.key in (" + sb.toString() + "))"
               // + " or d.idMenuItem not in (select k.sysMenuItem.idMenuItem from UsrKategorijaMenuItem k)" 
                + ")"
                
                + "and (dd is null or dd.idMenuItem in (select p.sysMenuItem.idMenuItem from UsrKategorijaMenuItem p " 
                + "where p.xusrKategorija.key in (" + sb.toString() + "))"
             //   + " or dd.idMenuItem not in (select k.sysMenuItem.idMenuItem from UsrKategorijaMenuItem k)" 
                + ")"
                
                + " and (ddd is null or ddd.idMenuItem in (select p.sysMenuItem.idMenuItem from UsrKategorijaMenuItem p " 
                + " where p.xusrKategorija.key in (" + sb.toString() + "))"
              //  + " or ddd.idMenuItem not in (select k.sysMenuItem.idMenuItem from UsrKategorijaMenuItem k)" 
                +") "
                
                //+ "and komponenta.key in ('core','sif','lpr','prv','zrd','pp','prj','osb','obj','vrm','mat','dok','efz','gk','isp','ugn','blg','dev','rac') ";
                
                + "order by o.sortRedniBroj ASC NULLS LAST ";
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        return customSearch(search, paramMap);
    }
}
