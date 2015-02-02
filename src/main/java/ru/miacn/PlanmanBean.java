package ru.miacn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.primefaces.event.CellEditEvent;

import ru.miacn.orm.PlanOrm;
import ru.miacn.persistence.model.Plan;
import ru.miacn.persistence.reference.ListConverter;
import ru.miacn.persistence.reference.RDecrGroup;
import ru.miacn.persistence.reference.RMedGroup;
import ru.miacn.persistence.reference.RMedicalOrgMain;
import ru.miacn.persistence.reference.RMedicalOrgRegion;
import ru.miacn.persistence.reference.RMedicalOrgTer;
import ru.miacn.persistence.reference.RSocGroup;
import ru.miacn.utils.JpaUtils;

@Named
@SessionScoped
public class PlanmanBean implements Serializable {
	private static final long serialVersionUID = -6589620147650856228L;
	private static final String colBegYearName = "Подлежащие обследованию";
	private static final String colPlanPatName = "План в лицах";
	private static final String colPlanExamName = "План в обследованиях";
	
	@PersistenceContext(unitName = "fluor-PU")
	private EntityManager em;
	
	private int selIdx;
	
	private List<PlanOrm> socList;
	private List<PlanOrm> medList;
	private List<PlanOrm> decrList;
	private RMedicalOrgRegion selectedMor;
	private ListConverter morConverter;
	private List<RMedicalOrgRegion> morList;
	private RMedicalOrgTer selectedMot;
	private ListConverter motConverter;
	private List<RMedicalOrgTer> motList;
	private RMedicalOrgMain selectedMom;
	private ListConverter momConverter;
	private List<RMedicalOrgMain> momList;
	private int year;
	
	public PlanmanBean() {
		setMorConverter(new ListConverter());
		setMotConverter(new ListConverter());
		setMomConverter(new ListConverter());
	}
	
	@PostConstruct
	private void init() {
		clearPlans();
		setMorList(em.createQuery("SELECT r FROM " + RMedicalOrgRegion.class.getName() + " r ORDER BY r.id", RMedicalOrgRegion.class).getResultList());
		year = Calendar.getInstance().get(Calendar.YEAR);
	}
	
	private void loadPlans() {
		switch (selIdx) {
		case 0:
			loadSocPlan();
			break;
		case 1:
			loadMedPlan();
			break;
		case 2:
			loadDecrPlan();
			break;
		default:
			throw new RuntimeException("Wrong tab index.");
		}
	}
	
	private void clearPlans() {
		setSocList(new ArrayList<PlanOrm>());
		setMedList(new ArrayList<PlanOrm>());
		setDecrList(new ArrayList<PlanOrm>());
	}
	
	private void loadSocPlan() {
		if ((getSelectedMor() != null) && (getSelectedMot() != null) && (getYear() > 0)) {
			String sql = ""
					+ "SELECT r.id AS group_id, r.name AS group_name, CASE WHEN (p.id IS NULL) THEN -r.id ELSE p.id END AS id, p.cnt_beg_year, p.cnt_plan_pat, p.cnt_plan_exam "
					+ "FROM r_soc_group r "
					+ "LEFT JOIN plan p ON (r.id = p.soc_group_id AND p.med_reg_id = :med_reg_id AND p.med_city_id = :med_city_id AND p.year = :year %s) ";
			String where = "";
	    	Map<String, Object> params = new HashMap<>();
			
			params.put("med_reg_id", getSelectedMor().getRegId());
			params.put("med_city_id", getSelectedMot().getId().getTerId());
			params.put("year", getYear());
			if (getSelectedMom() != null) {
				where += " AND p.med_lpu_id = :med_lpu_id";
				params.put("med_lpu_id", getSelectedMom().getId().getLpuId());
			} else {
				where += " AND p.med_lpu_id IS NULL";
			}
			sql = String.format(sql, where);
			
			setSocList(JpaUtils.getNativeResultList(em, sql, params, PlanOrm.class));
		} else {
			clearPlans();
		}
	}
	
	private void loadMedPlan() {
		if ((getSelectedMor() != null) && (getSelectedMot() != null) && (getYear() > 0)) {
			String sql = ""
					+ "SELECT r.id AS group_id, r.name AS group_name, CASE WHEN (p.id IS NULL) THEN -r.id ELSE p.id END AS id, p.cnt_beg_year, p.cnt_plan_pat, p.cnt_plan_exam "
					+ "FROM r_med_group r "
					+ "LEFT JOIN plan p ON (r.id = p.med_group_id AND p.med_reg_id = :med_reg_id AND p.med_city_id = :med_city_id AND p.year = :year %s) ";
			String where = "";
	    	Map<String, Object> params = new HashMap<>();
			
			params.put("med_reg_id", getSelectedMor().getRegId());
			params.put("med_city_id", getSelectedMot().getId().getTerId());
			params.put("year", getYear());
			if (getSelectedMom() != null) {
				where += " AND p.med_lpu_id = :med_lpu_id";
				params.put("med_lpu_id", getSelectedMom().getId().getLpuId());
			} else {
				where += " AND p.med_lpu_id IS NULL";
			}
			sql = String.format(sql, where);
			
			setMedList(JpaUtils.getNativeResultList(em, sql, params, PlanOrm.class));
		} else {
			clearPlans();
		}
	}
	
	private void loadDecrPlan() {
		if ((getSelectedMor() != null) && (getSelectedMot() != null) && (getYear() > 0)) {
			String sql = ""
					+ "SELECT r.id AS group_id, r.name AS group_name, CASE WHEN (p.id IS NULL) THEN -r.id ELSE p.id END AS id, p.cnt_beg_year, p.cnt_plan_pat, p.cnt_plan_exam "
					+ "FROM r_decr_group r "
					+ "LEFT JOIN plan p ON (r.id = p.decr_group_id AND p.med_reg_id = :med_reg_id AND p.med_city_id = :med_city_id AND p.year = :year %s) ";
			String where = "";
	    	Map<String, Object> params = new HashMap<>();
			
			params.put("med_reg_id", getSelectedMor().getRegId());
			params.put("med_city_id", getSelectedMot().getId().getTerId());
			params.put("year", getYear());
			if (getSelectedMom() != null) {
				where += " AND p.med_lpu_id = :med_lpu_id";
				params.put("med_lpu_id", getSelectedMom().getId().getLpuId());
			} else {
				where += " AND p.med_lpu_id IS NULL";
			}
			sql = String.format(sql, where);
			
			setDecrList(JpaUtils.getNativeResultList(em, sql, params, PlanOrm.class));
		} else {
			clearPlans();
		}
	}
	
	private void persistPlan(PlanOrm orm) {
		Plan plan = new Plan();
		
		plan.setMedRegId(getSelectedMor().getRegId());
		plan.setMedCityId(getSelectedMot().getId().getTerId());
		if (getSelectedMom() != null)
			plan.setMedLpuId(getSelectedMom().getId().getLpuId());
		plan.setYear(getYear());
		switch (selIdx) {
		case 0:
			RSocGroup gs = new RSocGroup();
			
			gs.setId(orm.getGroupId());
			plan.setSocGroup(gs);
			break;
		case 1:
			RMedGroup gm = new RMedGroup();
			
			gm.setId(orm.getGroupId());
			plan.setMedGroup(gm);
			break;
		case 2:
			RDecrGroup gd = new RDecrGroup();
			
			gd.setId(orm.getGroupId());
			plan.setDecrGroup(gd);
			break;
		default:
			throw new RuntimeException("Wrong tab index.");
		}
		plan.setCntBegYear(orm.getCntBegYear());
		plan.setCntPlanPat(orm.getCntPlanPat());
		plan.setCntPlanExam(orm.getCntPlanExam());
		
		if (orm.getId() > 0) {
			plan.setId(orm.getId());
			em.merge(plan);
		} else {
			em.persist(plan);
			orm.setId(plan.getId());
		}
	}
	
	public void onTabChange() {
		loadPlans();
	}
	
	@Transactional
	public void onCellEdit(CellEditEvent event) {
		int rowIdx = event.getRowIndex();
		Integer value = (Integer) event.getNewValue();
		PlanOrm item;
		
		switch (selIdx) {
		case 0:
			item = getSocList().get(rowIdx);
			break;
		case 1:
			item = getMedList().get(rowIdx);
			break;
		case 2:
			item = getDecrList().get(rowIdx);
			break;
		default:
			throw new RuntimeException("Wrong tab index.");
		}
		
		if ((value != null) && (value < 1))
			value = null;
		switch (event.getColumn().getHeaderText()) {
		case colBegYearName:
			item.setCntBegYear(value);
			break;
		case colPlanPatName:
			item.setCntPlanPat(value);
			break;
		case colPlanExamName:
			item.setCntPlanExam(value);
			break;
		default:
			throw new RuntimeException("Wrong column index.");
		}
		
		persistPlan(item);
	}
	
	public void morSelected() {
		if (selectedMor != null) {
			String sql = ""
					+ "SELECT * "
					+ "FROM r_medical_org_ter r "
					+ "WHERE r.reg_id = :r_id "
					+ "ORDER BY r.name ";
	    	Map<String, Object> params = new HashMap<>();
			
	    	params.put("r_id", selectedMor.getRegId());
			
			setMotList(JpaUtils.getNativeResultList(em, sql, params, RMedicalOrgTer.class));
		} else {
			setMotList(new ArrayList<RMedicalOrgTer>());
		}
		setSelectedMot(null);
		setSelectedMom(null);
		setMomList(new ArrayList<RMedicalOrgMain>());
		loadPlans();
	}

	public void motSelected() {
		if (selectedMot != null) {
			String sql = ""
					+ "SELECT * "
					+ "FROM r_medical_org_main r "
					+ "WHERE r.ter_id = :t_id AND r.reg_id = :r_id "
					+ "ORDER BY r.lpu_id ";
	    	Map<String, Object> params = new HashMap<>();
			
			params.put("t_id", selectedMot.getId().getTerId());
			params.put("r_id", selectedMot.getId().getRegId());
			
			setMomList(JpaUtils.getNativeResultList(em, sql, params, RMedicalOrgMain.class));
		} else {
			setMomList(new ArrayList<RMedicalOrgMain>());
		}
		setSelectedMom(null);
		loadPlans();
	}
	
	public void momSelected() {
		loadPlans();
	}
	
	public void onYearChange() {
		loadPlans();
	}

	public String getColBegYearName() {
		return colBegYearName;
	}

	public String getColPlanPatName() {
		return colPlanPatName;
	}

	public String getColPlanExamName() {
		return colPlanExamName;
	}
	
	public int getSelIdx() {
		return selIdx;
	}

	public void setSelIdx(int selIdx) {
		this.selIdx = selIdx;
	}

	public List<PlanOrm> getSocList() {
		return socList;
	}
	
	public void setSocList(List<PlanOrm> socList) {
		this.socList = socList;
	}
	
	public List<PlanOrm> getMedList() {
		return medList;
	}
	
	public void setMedList(List<PlanOrm> medList) {
		this.medList = medList;
	}
	
	public List<PlanOrm> getDecrList() {
		return decrList;
	}
	
	public void setDecrList(List<PlanOrm> decrList) {
		this.decrList = decrList;
	}

	public RMedicalOrgRegion getSelectedMor() {
		return selectedMor;
	}

	public void setSelectedMor(RMedicalOrgRegion selectedMor) {
		this.selectedMor = selectedMor;
	}

	public ListConverter getMorConverter() {
		return morConverter;
	}

	public void setMorConverter(ListConverter morConverter) {
		this.morConverter = morConverter;
	}

	public List<RMedicalOrgRegion> getMorList() {
		return morList;
	}

	public void setMorList(List<RMedicalOrgRegion> morList) {
		this.morList = morList;
		morConverter.setList(morList);
	}

	public RMedicalOrgTer getSelectedMot() {
		return selectedMot;
	}

	public void setSelectedMot(RMedicalOrgTer selectedMot) {
		this.selectedMot = selectedMot;
	}

	public ListConverter getMotConverter() {
		return motConverter;
	}

	public void setMotConverter(ListConverter motConverter) {
		this.motConverter = motConverter;
	}

	public List<RMedicalOrgTer> getMotList() {
		return motList;
	}

	public void setMotList(List<RMedicalOrgTer> motList) {
		this.motList = motList;
		motConverter.setList(motList);
	}

	public RMedicalOrgMain getSelectedMom() {
		return selectedMom;
	}

	public void setSelectedMom(RMedicalOrgMain selectedMom) {
		this.selectedMom = selectedMom;
	}

	public ListConverter getMomConverter() {
		return momConverter;
	}

	public void setMomConverter(ListConverter momConverter) {
		this.momConverter = momConverter;
	}

	public List<RMedicalOrgMain> getMomList() {
		return momList;
	}

	public void setMomList(List<RMedicalOrgMain> momList) {
		this.momList = momList;
		momConverter.setList(momList);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
