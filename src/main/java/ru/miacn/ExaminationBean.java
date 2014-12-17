package ru.miacn;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import ru.miacn.utils.JpaUtils;
import ru.miacn.persistence.model.Examination;
import ru.miacn.persistence.reference.ListConverter;
import ru.miacn.persistence.reference.RExamType;
import ru.miacn.persistence.reference.RMedicalOrgMain;
import ru.miacn.persistence.reference.RMedicalOrgPoliclinic;
import ru.miacn.persistence.reference.RMedicalOrgRegion;
import ru.miacn.persistence.reference.RMedicalOrgTer;
import ru.miacn.persistence.reference.RResultType;

@Named
@SessionScoped
@Transactional
public class ExaminationBean implements Serializable {
	private static final long serialVersionUID = 5513014259706142020L;

	@PersistenceContext
	private EntityManager em;
	
	private int patientId;
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
	private RMedicalOrgPoliclinic selectedMop;
	private ListConverter mopConverter;
	private List<RMedicalOrgPoliclinic> moPoliclinicList;
	
	@PostConstruct
	private void init() {
		selectedExamination = new Examination();
//		setSelectedExamination(new Examination());
		
		setExtConverter(new ListConverter());
		setRestypeConverter(new ListConverter());
		setMomConverter(new ListConverter());
		setMotConverter(new ListConverter());
		setMopConverter(new ListConverter());
		setMorConverter(new ListConverter());
		
		setExtList(em.createQuery("SELECT r FROM " + RExamType.class.getName() + " r ORDER BY r.id", RExamType.class).getResultList());
		setRestypeList(em.createQuery("SELECT r FROM " + RResultType.class.getName() + " r ORDER BY r.id", RResultType.class).getResultList());
		setMoRegionList(em.createQuery("SELECT r FROM " + RMedicalOrgRegion.class.getName() + " r ORDER BY r.id", RMedicalOrgRegion.class).getResultList());
	}

	public void loadExam(int patId) {
		setPatientId(patId);
		
    	String sql = ""
    		+ "SELECT * "
    		+ "FROM examination e "
    		+ "WHERE e.patient_id = :id "
			+ "ORDER BY e.dat desc ";
    	Map<String, Object> params = new HashMap<>();
    	
   		params.put("id", getPatientId());
    	
    	setExaminations(JpaUtils.getNativeResultList(em, sql, params, Examination.class));
    }

	public void findExam(int exId) {
		setSelectedExamination(new Examination());			
		if (exId == 0){
			selectedExamination.setDat(new Date());
		}
		if (exId != 0){
			setSelectedExamination(em.find(Examination.class, exId));

			int i;
			for (i = 0; i < moRegionList.size(); i++) {
				if (moRegionList.get(i).getRegId() == getSelectedExamination().getRMedicalOrgPoliclinic().getId().getRegId())
					break;
			}
			setSelectedMor((i < moRegionList.size()) ? moRegionList.get(i) : null);
			morSelected();
			
			for (i = 0; i < moTerList.size(); i++) {
				if (moTerList.get(i).getId().getTerId() == getSelectedExamination().getRMedicalOrgPoliclinic().getId().getTerId())
					break;
			}
			setSelectedMot((i < moTerList.size()) ? moTerList.get(i) : null);
			motSelected();
			
			for (i = 0; i < moMainList.size(); i++) {
				if (moMainList.get(i).getId().getLpuId() == getSelectedExamination().getRMedicalOrgPoliclinic().getId().getLpuId())
					break;
			}
			setSelectedMom((i < moMainList.size()) ? moMainList.get(i) : null);
			momSelected();
		
		}
    }

	public void delExam() {
		em.remove(em.merge(selectedExamination));
		em.flush();
		//em.refresh(selectedExamination);
//		loadExam(selectedExamination.getPatient().getId());
//		return "editman?faces-redirect=true";
    }

	public void saveExam() {
        if (selectedExamination.getId() == null) {
    		BigInteger id = (BigInteger) em.createNativeQuery("SELECT nextval('examination_id_seq')").getSingleResult();
        	selectedExamination.setId(id.intValue());
//        	selectedExamination.setDat(new Date());
	    }

//        if ((selectedMor != null) && (selectedMot != null) && (selectedMom != null) && (selectedMop != null)) {
//            selectedExamination.setRMedicalOrgPoliclinic(new RMedicalOrgPoliclinic());
//            selectedExamination.getRMedicalOrgPoliclinic().getRMedicalOrgMain().getRMedicalOrgTer().getRMedicalOrgRegion().setRegId(selectedMor.getRegId());
//            selectedExamination.getRMedicalOrgPoliclinic().getRMedicalOrgMain().getRMedicalOrgTer().setId(selectedMot.getId());
//            selectedExamination.getRMedicalOrgPoliclinic().getRMedicalOrgMain().setId(selectedMom.getId());
//            selectedExamination.getRMedicalOrgPoliclinic().setId(selectedMop.getId());
//	    } else {
//	            patient.setRMedicalOrgPoliclinic(null);
//	    }

        em.merge(selectedExamination);
		em.flush();
		em.refresh(selectedExamination);
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

	public void extSelected() {
		if (selectedExt != null) {
			TypedQuery<RExamType> query = em.createQuery("SELECT r FROM " + RExamType.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RExamType.class);
			
			query.setParameter("id", selectedExt.getId());
			setExtList(query.getResultList());
		} else {
			setExtList(new ArrayList<RExamType>());
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

	public List<RMedicalOrgPoliclinic> getMoPoliclinicList() {
		return moPoliclinicList;
	}

	public void setMoPoliclinicList(List<RMedicalOrgPoliclinic> moPoliclinicList) {
		this.moPoliclinicList = moPoliclinicList;
		mopConverter.setList(moPoliclinicList);
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

	public RMedicalOrgPoliclinic getSelectedMop() {
		return selectedMop;
	}

	public void setSelectedMop(RMedicalOrgPoliclinic selectedMop) {
		this.selectedMop = selectedMop;
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

	public ListConverter getMopConverter() {
		return mopConverter;
	}

	public void setMopConverter(ListConverter mopConverter) {
		this.mopConverter = mopConverter;
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
	}

	@SuppressWarnings("unchecked")
	public void momSelected() {
		if (selectedMom != null) {
			Query query = em.createNativeQuery("SELECT * FROM r_medical_org_policlinic r WHERE r.ter_id = :t_id and r.reg_id = :r_id  and r.lpu_id = :l_id ORDER BY r.pol_id", RMedicalOrgPoliclinic.class);
			
			query.setParameter("r_id", selectedMom.getId().getRegId());
			query.setParameter("t_id", selectedMom.getId().getTerId());
			query.setParameter("l_id", selectedMom.getId().getLpuId());
			
			setMoPoliclinicList(query.getResultList());
		} else {
			setMoPoliclinicList(new ArrayList<RMedicalOrgPoliclinic>());
		}
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

}
