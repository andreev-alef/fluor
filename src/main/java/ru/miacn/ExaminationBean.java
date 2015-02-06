package ru.miacn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import ru.miacn.persistence.model.Examination;
import ru.miacn.persistence.model.Patient;
import ru.miacn.persistence.model.PatientId;
import ru.miacn.persistence.reference.ListConverter;
import ru.miacn.persistence.reference.RExamMethod;
import ru.miacn.persistence.reference.RExamType;
import ru.miacn.persistence.reference.RMedicalOrgMain;
import ru.miacn.persistence.reference.RMedicalOrgRegion;
import ru.miacn.persistence.reference.RMedicalOrgTer;
import ru.miacn.persistence.reference.RResultType;
import ru.miacn.persistence.reference.RVerification;
import ru.miacn.utils.JpaUtils;

@Named
@SessionScoped
@Transactional
public class ExaminationBean implements Serializable {
	private static final long serialVersionUID = 5513014259706142020L;

	@PersistenceContext(unitName = "fluor-PU")	
	private EntityManager em;
	@Inject
	private LoginBean login;
	
	private int patientId;
	private int patientParentId;
	private List<Examination> examinations;
	private Examination selectedExamination;

	private List<RExamType> extList;
	private ListConverter extConverter;
	private RExamType selectedExt;
	private List<RResultType> restypeList;
	private ListConverter restypeConverter;
	private RResultType selectedResType;
	private RMedicalOrgRegion selectedMor;
	private ListConverter morConverter;
	private List<RMedicalOrgRegion> moRegionList;
	private RMedicalOrgTer selectedMot;
	private ListConverter motConverter;
	private List<RMedicalOrgTer> moTerList;
	private RMedicalOrgMain selectedMom;
	private ListConverter momConverter;
	private List<RMedicalOrgMain> moMainList;
	private List<RExamMethod> exmList;
	private ListConverter exmConverter;
	private RExamMethod selectedExm;
	private List<RVerification> verList;
	private ListConverter verConverter;
	private RVerification selectedVer;
	
	private boolean editMode;
	
	@PostConstruct
	private void init() {
		setSelectedExamination(new Examination());
		
		setExtConverter(new ListConverter());
		setRestypeConverter(new ListConverter());
		setMomConverter(new ListConverter());
		setMotConverter(new ListConverter());
		setMorConverter(new ListConverter());
		setExmConverter(new ListConverter());
		setVerConverter(new ListConverter());
		
		setExtList(em.createQuery("SELECT r FROM " + RExamType.class.getName() + " r ORDER BY r.id", RExamType.class).getResultList());
		setRestypeList(em.createQuery("SELECT r FROM " + RResultType.class.getName() + " r ORDER BY r.id", RResultType.class).getResultList());
		setVerList(em.createQuery("SELECT r FROM " + RVerification.class.getName() + " r ORDER BY r.id", RVerification.class).getResultList());
		setMoRegionList(em.createQuery("SELECT r FROM " + RMedicalOrgRegion.class.getName() + " r ORDER BY r.id", RMedicalOrgRegion.class).getResultList());
	}

	public void loadExam(int patId, int parentId, boolean editMode) {
		setPatientId(patId);
		setPatientParentId(parentId);
		setEditMode(editMode);
		
    	String sql = ""
    		+ "SELECT * "
    		+ "FROM examination e "
    		+ "WHERE e.patient_id = :id "
			+ "ORDER BY e.dat desc ";
    	Map<String, Object> params = new HashMap<>();
    	
   		params.put("id", getPatientParentId());
    	
    	setExaminations(JpaUtils.getNativeResultList(em, sql, params, Examination.class));
    	
    	setEditMode(isEditMode() && !patientIsDead());
    }

	public void findExam(int exId) {
		if (exId == 0){
			setSelectedExamination(new Examination());
			getSelectedExamination().setPatientId(new PatientId());
		} else {
			setSelectedExamination(em.find(Examination.class, exId));
		}
		
		if (getSelectedExamination().getRMedicalOrgMain() != null) {
			int i;
			for (i = 0; i < moRegionList.size(); i++) {
				if (moRegionList.get(i).getRegId() == getSelectedExamination().getRMedicalOrgMain().getRMedicalOrgTer().getRMedicalOrgRegion().getRegId())
					break;
			}
			setSelectedMor((i < moRegionList.size()) ? moRegionList.get(i) : null);
			morSelected();
			
			for (i = 0; i < moTerList.size(); i++) {
				if (moTerList.get(i).getId().getTerId() == getSelectedExamination().getRMedicalOrgMain().getRMedicalOrgTer().getId().getTerId())
					break;
			}
			setSelectedMot((i < moTerList.size()) ? moTerList.get(i) : null);
			motSelected();
			
			for (i = 0; i < moMainList.size(); i++) {
				if (moMainList.get(i).getId().getLpuId() == getSelectedExamination().getRMedicalOrgMain().getId().getLpuId())
					break;
			}
			setSelectedMom((i < moMainList.size()) ? moMainList.get(i) : null);
		} else {
			setSelectedMor(null);
			setSelectedMot(null);
			setSelectedMom(null);
		}
    }

	public void delExam() {
		em.remove(em.merge(selectedExamination));
		loadExam(getPatientId(), selectedExamination.getPatientId().getId(), isEditMode());
    }

	public void saveExam() throws Exception {
		try{
			if((!patientIsDead())) {
				selectedExamination.getPatientId().setId(getPatientParentId());
				if ((selectedMor != null) && (selectedMot != null) && (selectedMom != null)) {
					selectedExamination.setRMedicalOrgMain(new RMedicalOrgMain());
					selectedExamination.getRMedicalOrgMain().getRMedicalOrgTer().getRMedicalOrgRegion().setRegId(selectedMor.getRegId());
					selectedExamination.getRMedicalOrgMain().getRMedicalOrgTer().setId(selectedMot.getId());
					selectedExamination.getRMedicalOrgMain().setId(selectedMom.getId());
				} else {
					selectedExamination.setRMedicalOrgMain(null);
				}
				selectedExamination.setUser(login.getAuthedUser());
				em.persist(em.merge(selectedExamination));
				loadExam(getPatientId(), selectedExamination.getPatientId().getId(), isEditMode());
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,"Невозможно добавить обследование, пациент умер.",null));
			}
		} catch(Exception e) {
				throw new Exception("Возникла ошибка при сохранении");
		}
    }
	
	private Boolean patientIsDead() {
		Patient pat = em.find(Patient.class, getPatientId());
		
		if (pat == null)
			return false;
		else
			return pat.getDatDeath() != null;
	}

	public List<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public Examination getSelectedExamination() {
		return selectedExamination;
	}
	
	public void setSelectedExamination(Examination selectedExamination) {
		this.selectedExamination = selectedExamination;
	}
	
	public List<RExamType> getExtList() {
		return extList;
	}
	
	public void setExtList(List<RExamType> extList) {
		this.extList = extList;
		extConverter.setList(extList);
	}
	
	public ListConverter getExtConverter() {
		return extConverter;
	}

	public void setExtConverter(ListConverter extConverter) {
		this.extConverter = extConverter;
	}

	public RExamType getSelectedExt() {
		return selectedExt;
	}
	
	public void setSelectedExt(RExamType selectedExt) {
		this.selectedExt = selectedExt;
	}

	@SuppressWarnings("unchecked")
	public void extSelected() {
		if (selectedExt != null) {
			Query query = em.createNativeQuery("SELECT * FROM r_exam_method r WHERE r.type_id = :r_id ORDER BY r.name", RExamMethod.class);
			query.setParameter("r_id", selectedExt.getId());
			setExmList(query.getResultList());
		} else {
			setExmList(new ArrayList<RExamMethod>());
		}
	}
	
	public void resSelected() {
		if (selectedResType != null) {
			TypedQuery<RResultType> query = em.createQuery("SELECT r FROM " + RResultType.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RResultType.class);
			
			query.setParameter("id", selectedResType.getId());
			setRestypeList(query.getResultList());
		} else {
			setRestypeList(new ArrayList<RResultType>());
		}
	}
	
	public List<RResultType> getRestypeList() {
		return restypeList;
	}

	public void setRestypeList(List<RResultType> restypeList) {
		this.restypeList = restypeList;
		restypeConverter.setList(restypeList);
	}

	public ListConverter getRestypeConverter() {
		return restypeConverter;
	}
	
	public void setRestypeConverter(ListConverter restypeConverter) {
		this.restypeConverter = restypeConverter;
	}

	public RResultType getSelectedResType() {
		return selectedResType;
	}

	public void setSelectedResType(RResultType selectedResType) {
		this.selectedResType = selectedResType;
	}
	
	public List<RMedicalOrgMain> getMoMainList() {
		return moMainList;
	}

	public void setMoMainList(List<RMedicalOrgMain> moMainList) {
		this.moMainList = moMainList;
		momConverter.setList(moMainList);
	}

	public List<RMedicalOrgTer> getMoTerList() {
		return moTerList;
	}

	private void setMoTerList(List<RMedicalOrgTer>  moTerList) {
		this.moTerList = moTerList;
		motConverter.setList(moTerList);
	}

	public RMedicalOrgMain getSelectedMom() {
		return selectedMom;
	}

	public void setSelectedMom(RMedicalOrgMain selectedMom) {
		this.selectedMom = selectedMom;
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

	public ListConverter getMomConverter() {
		return momConverter;
	}

	public void setMomConverter(ListConverter momConverter) {
		this.momConverter = momConverter;
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
	
	public List<RMedicalOrgRegion> getMoRegionList() {
		return moRegionList;
	}

	private void setMoRegionList(List<RMedicalOrgRegion>  moRegionList) {
		this.moRegionList = moRegionList;
		morConverter.setList(moRegionList);
	}

	@SuppressWarnings("unchecked")
	public void morSelected() {
		if (selectedMor != null) {
			Query query = em.createNativeQuery("SELECT * FROM r_medical_org_ter r WHERE r.reg_id = :r_id ORDER BY r.name", RMedicalOrgTer.class);
			
			query.setParameter("r_id", selectedMor.getRegId());
			
			setMoTerList(query.getResultList());
		} else {
			setMoTerList(new ArrayList<RMedicalOrgTer>());
		}
		setSelectedMot(null);
		setSelectedMom(null);
		setMoMainList(new ArrayList<RMedicalOrgMain>());
	}

	@SuppressWarnings("unchecked")
	public void motSelected() {
		if (selectedMot != null) {
			Query query = em.createNativeQuery("SELECT * FROM r_medical_org_main r WHERE r.ter_id = :t_id and r.reg_id = :r_id ORDER BY r.lpu_id", RMedicalOrgMain.class);
			
			query.setParameter("t_id", selectedMot.getId().getTerId());
			query.setParameter("r_id", selectedMot.getId().getRegId());
			
			setMoMainList(query.getResultList());
		} else {
			setMoMainList(new ArrayList<RMedicalOrgMain>());
		}
		setSelectedMom(null);
	}

	public List<RExamMethod> getExmList() {
		return exmList;
	}

	public void setExmList(List<RExamMethod> exmList) {
		this.exmList = exmList;
		exmConverter.setList(exmList);
	}

	public ListConverter getExmConverter() {
		return exmConverter;
	}

	public void setExmConverter(ListConverter exmConverter) {
		this.exmConverter = exmConverter;
	}

	public RExamMethod getSelectedExm() {
		return selectedExm;
	}

	public void setSelectedExm(RExamMethod selectedExm) {
		this.selectedExm = selectedExm;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public List<RVerification> getVerList() {
		return verList;
	}

	public void setVerList(List<RVerification> verList) {
		this.verList = verList;
		verConverter.setList(verList);
	}

	public ListConverter getVerConverter() {
		return verConverter;
	}

	public void setVerConverter(ListConverter verConverter) {
		this.verConverter = verConverter;
	}

	public RVerification getSelectedVer() {
		return selectedVer;
	}

	public void setSelectedVer(RVerification selectedVer) {
		this.selectedVer = selectedVer;
	}

	public int getPatientParentId() {
		return patientParentId;
	}

	public void setPatientParentId(int patientParentId) {
		this.patientParentId = patientParentId;
	}
}
