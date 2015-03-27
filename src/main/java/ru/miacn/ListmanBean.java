package ru.miacn;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ru.miacn.fias.FiasEditorFilter;
import ru.miacn.orm.PatientOrm;
import ru.miacn.utils.JpaUtils;

@Named
@SessionScoped
public class ListmanBean implements Serializable {
	public static final long serialVersionUID = -229673017810787765L;
	
	private static final String searchSql = ""
			+ "WITH t AS ( "
			+ "SELECT DISTINCT ON (p.last_name, p.first_name, p.father_name, p._ver_parent_id) p.id, "
			+ "(SELECT e.id AS lastExamId FROM examination e WHERE e.patient_id = p._ver_parent_id ORDER BY e.dat DESC LIMIT 1) "
			+ "FROM patient p "
			+ "LEFT JOIN examination e ON (e.patient_id = p._ver_parent_id) "
			+ "WHERE p._ver_active = TRUE %s"
			+ "ORDER BY p.last_name, p.first_name, p.father_name, p._ver_parent_id "
			+ "OFFSET %d LIMIT %d "
			+ ") SELECT *, e.dat AS lastExam, rrt.name AS result, rv.name AS verification, "
			+ "p._ver_parent_id AS patientId, p.sex_id AS gender, "
			+ "p.soc_group_id AS socGroup, p.med_group_id AS medGroup, p.decr_group_id AS decrGroup  "
			+ "FROM t "
			+ "JOIN patient p ON (p.id = t.id) "
			+ "LEFT JOIN examination e ON (e.id = t.lastExamId) "
			+ "LEFT JOIN r_result_type rrt ON (rrt.id = e.result_id) "
			+ "LEFT JOIN r_verification rv ON (rv.id = e.verification_id)";
	private static final String countSql = ""
			+ "WITH t AS ( "
			+ "SELECT DISTINCT ON (p.last_name, p.first_name, p.father_name, p._ver_parent_id) p.id "
			+ "FROM patient p "
			+ "LEFT JOIN examination e ON (e.patient_id = p._ver_parent_id) "
			+ "WHERE p._ver_active = TRUE %s "
			+ "ORDER BY p.last_name, p.first_name, p.father_name, p._ver_parent_id "
			+ "LIMIT 2048 "
			+ ") SELECT count(*) "
			+ "FROM t ";
	
	private String srcFam;
	private String srcIm;
	private String srcOt;
	private String selectedFIO;
	private Date srcDr;
	private List<PatientOrm> patients;
	private LazyDataModel<PatientOrm> model;
	private int countPatients;
	private int paginatorFirst;
	private int paginatorRowsCount = 10;
	
	@PersistenceContext(unitName = "fluor-PU")
	private EntityManager em;
	@Inject
	private FilterBean fpar;
	@Inject
	private FiasEditorFilter fias;
	private boolean filtering;
	
    @PostConstruct
    public void init() {
    	try {
			clearSearch();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public LazyDataModel<PatientOrm> getModel() {
		return model;
	}

    public void searchOrFilter() throws Exception {
    	if (filtering)
    		filter();
    	else
    		search();
    }
    
	public void search() throws Exception {
		filtering = false;
		
    	try{
	    	model = new LazyDataModel<PatientOrm>() {
				private static final long serialVersionUID = 9043096992321295134L;

				@Override
	    		public List<PatientOrm> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
					String sql;
					String sql_params = "";
			    	Map<String, Object> params = new HashMap<>();

			    	if (getSrcFam() != null && !getSrcFam().isEmpty()) {
			    		String fSrcFam = getSrcFam().substring(0, 1).toUpperCase() + getSrcFam().substring(1).toLowerCase();
			    		
			    		sql_params += "AND p.last_name LIKE :last_name ";
			    		params.put("last_name", fSrcFam + "%");
			    	}
			    	if (getSrcIm() != null && !getSrcIm().isEmpty()) {
			    		String fSrcIm = getSrcIm().substring(0, 1).toUpperCase() + getSrcIm().substring(1).toLowerCase();
			    		
			    		sql_params += "AND p.first_name LIKE :first_name ";
			    		params.put("first_name", fSrcIm + "%");
			    	}
			    	if (getSrcOt() != null && !getSrcOt().isEmpty()) {
			    		String fSrcOt = getSrcOt().substring(0, 1).toUpperCase() + getSrcOt().substring(1).toLowerCase();
			    		
			    		sql_params += "AND p.father_name LIKE :father_name ";
			    		params.put("father_name", fSrcOt + "%");
			    	}
			    	if (getSrcDr() != null) {
			    		sql_params += "AND p.dat_birth = :dat_birth ";
			    		params.put("dat_birth", getSrcDr());
			    	}
			    	sql = String.format(searchSql, sql_params, first, pageSize);
					
			    	setPatients(JpaUtils.getNativeResultList(em, sql, params, PatientOrm.class));
					setRowCount(((Number) JpaUtils.getNativeQuery(em, String.format(countSql, sql_params), params).getSingleResult()).intValue());
					
			    	return patients;
	    	   	}
	    	};
        } catch (Exception e) {
        	throw new Exception("Произошла ошибка при выполнении поиска пациентов");
    	}
    }
    
	public void filter() throws Exception {
		filtering = true;
		
        try {
	    	model = new LazyDataModel<PatientOrm>(){
				private static final long serialVersionUID = 4631239035126444909L;

				@Override
	    		public List<PatientOrm> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
					String sql;
					String sql_params = "";
					Map<String, Object> params = new HashMap<>();

					if (fpar.getSelectedRegObs() != null) {
			    		sql_params += "AND e.med_reg_id = :reg_id ";
			    		params.put("reg_id", fpar.getSelectedRegObs().getRegId());
			    	}
			    	if (fpar.getSelectedTerObs() != null) {
			    		sql_params += "AND e.med_city_id = :city_id ";
			    		params.put("city_id", fpar.getSelectedTerObs().getId().getTerId());
			    	}
			    	if (fpar.getSelectedLpuObs() != null) {
			    		sql_params += "AND e.med_lpu_id = :lpu_id ";
			    		params.put("lpu_id", fpar.getSelectedLpuObs().getId().getLpuId());
			    	}
			    	if (fpar.getSelectedRezType() != null) {
			    		sql_params += "AND e.result_id = :res_id ";
			    		params.put("res_id", fpar.getSelectedRezType().getId());
			    	}
					
			    	if (fpar.getSelectedVer() != null) {
			    		sql_params += "AND e.verification_id = :verification_id ";
			    		params.put("verification_id", fpar.getSelectedVer().getId());
			    	}

			    	if ((fpar.getDatStart() != null && fpar.getDatEnd() != null) && fpar.getDatEnd().compareTo(fpar.getDatStart()) >= 0) {
			    		sql_params += "AND e.dat between :dn and :dk ";
			    		params.put("dn", fpar.getDatStart());
			    		params.put("dk", fpar.getDatEnd());
					}
			
					if (fpar.getSelectedMor() != null) {
			    		sql_params += "AND p.med_reg_id = :med_reg_id ";
			    		params.put("med_reg_id", fpar.getSelectedMor().getRegId());
			    	}
			
			    	if (fpar.getSelectedMot() != null) {
			    		sql_params += "AND p.med_city_id = :med_city_id ";
			    		params.put("med_city_id", fpar.getSelectedMot().getId().getTerId());
			    	}
			
			    	if (fpar.getSelectedMom() != null) {
			    		sql_params += "AND p.med_lpu_id = :med_lpu_id ";
			    		params.put("med_lpu_id", fpar.getSelectedMom().getId().getLpuId());
			    	}
			
			    	if (fpar.getSelectedMop() != null) {
			    		sql_params += "AND p.med_pol_id = :med_pol_id ";
			    		params.put("med_pol_id", fpar.getSelectedMop().getId().getPolId());
			    	}
			
			    	if (fpar.getSelectedDg() != null) {
			    		sql_params += "AND p.decr_group_id = :decr_group_id ";
			    		params.put("decr_group_id", fpar.getSelectedDg().getId());
			    	}
			
			    	if (fpar.getSelectedMg() != null) {
			    		sql_params += "AND p.med_group_id = :med_group_id ";
			    		params.put("med_group_id", fpar.getSelectedMg().getId());
			    	}
			
			    	if (fpar.getSelectedSg() != null) {
			    		sql_params += "AND p.soc_group_id = :soc_group_id ";
			    		params.put("soc_group_id", fpar.getSelectedSg().getId());
			    	}
			
			    	if ((fias.getRegion() != null) && (fias.getRegion().getFormalname() != null) && (!fias.getRegion().getFormalname().isEmpty())) {
			    		sql_params += "AND p.liv_reg = :reg ";
			    		params.put("reg", fias.getRegion().getFormalname());
			    	}
			
			    	if ((fias.getGorod() != null) && (fias.getGorod().getFormalname() != null) && (!fias.getGorod().getFormalname().isEmpty())) {
			    		sql_params += "AND p.liv_city = :city ";
			    		params.put("city", fias.getGorod().getFormalname());
			    	}
			    	
			    	if ((fias.getUlica() != null) && (fias.getUlica().getFormalname() != null) && (!fias.getUlica().getFormalname().isEmpty())) {
			    		sql_params += "AND p.liv_street = :street ";
			    		params.put("street", fias.getUlica().getFormalname());
			    	}
			    	
			    	if ((fias.getDom() != null) && (!fias.getDom().isEmpty())) {
			    		sql_params += "AND p.liv_house = :dom ";
			    		params.put("dom", fias.getDom());
			    	}
			    	
			    	if ((fias.getKorp() != null) && (!fias.getKorp().isEmpty())) {
			    		sql_params += "AND p.liv_facility = :fac ";
			    		params.put("fac", fias.getKorp());
			    	}
			    	
			    	if ((fias.getStr() != null) && (!fias.getStr().isEmpty())) {
			    		sql_params += "AND p.liv_building = :building ";
			    		params.put("building", fias.getStr());
			    	}
			    	
			    	if ((fias.getKv() != null) && (!fias.getKv().isEmpty())) {
			    		sql_params += "AND p.liv_flat = :flat ";
			    		params.put("flat", fias.getKv());
			    	}
			    	sql = String.format(searchSql, sql_params, first, pageSize);
					
			    	setPatients(JpaUtils.getNativeResultList(em, sql, params, PatientOrm.class));
					setRowCount(((Number) JpaUtils.getNativeQuery(em, String.format(countSql, sql_params), params).getSingleResult()).intValue());

			    	return patients;
					
				}
	    	};
        } catch (Exception e) {
        	throw new Exception("Произошла ошибка при выполнении фильтра записей");
    	}

	}
		
    public void clearSearch() throws Exception {
    	setSrcFam("");
    	setSrcIm("");
    	setSrcOt("");
    	setSrcDr(null);
    	
		search();
    }
    
	public String getSrcFam() {
		return srcFam;
	}

	public void setSrcFam(String srcFam) {
		this.srcFam = srcFam;
	}

	public String getSrcIm() {
		return srcIm;
	}

	public void setSrcIm(String srcIm) {
		this.srcIm = srcIm;
	}

	public String getSrcOt() {
		return srcOt;
	}

	public void setSrcOt(String srcOt) {
		this.srcOt = srcOt;
	}

	public Date getSrcDr() {
		return srcDr;
	}

	public void setSrcDr(Date srcDr) {
		this.srcDr = srcDr;
	}

	public List<PatientOrm> getPatients() {
		return (List<PatientOrm>) patients;
	}

	public void setPatients(List<PatientOrm> patients) {
		this.patients = patients;
	}

	public int getCountPatients() {
		return countPatients;
	}

	public void setCountPatients(int countPatients) {
		this.countPatients = countPatients;
	}

	public String getSelectedFIO() {
		return selectedFIO;
	}

	public void setSelectedFIO(String last_name, String first_name, String father_name) {	
		this.selectedFIO = last_name+" "+first_name+" "+father_name;
	}

	public int getPaginatorFirst() {
		return paginatorFirst;
	}

	public void setPaginatorFirst(int paginatorFirst) {
		this.paginatorFirst = paginatorFirst;
	}
	
	public void onPageChange (PageEvent event) {
			this.setPaginatorFirst( ((DataTable) event.getSource()).getFirst());
	}

	public int getPaginatorRowsCount() {
		return paginatorRowsCount;
	}

	public void setPaginatorRowsCount(int paginatorRowsCount) {
		this.paginatorRowsCount = paginatorRowsCount;
	}
	
}
