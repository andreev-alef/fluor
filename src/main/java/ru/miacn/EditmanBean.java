package ru.miacn;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import ru.miacn.fias.FiasEditor;
import ru.miacn.fias.FiasElement;
import ru.miacn.persistence.model.Address;
import ru.miacn.persistence.model.Patient;
import ru.miacn.persistence.model.PatientId;
import ru.miacn.persistence.reference.ListConverter;
import ru.miacn.persistence.reference.RCitizen;
import ru.miacn.persistence.reference.RDecrGroup;
import ru.miacn.persistence.reference.RGender;
import ru.miacn.persistence.reference.RHabitat;
import ru.miacn.persistence.reference.RMedGroup;
import ru.miacn.persistence.reference.RMedicalOrgMain;
import ru.miacn.persistence.reference.RMedicalOrgPoliclinic;
import ru.miacn.persistence.reference.RMedicalOrgRegion;
import ru.miacn.persistence.reference.RMedicalOrgTer;
import ru.miacn.persistence.reference.RSocGroup;

@Named
@SessionScoped
public class EditmanBean implements Serializable {
	public static final long serialVersionUID = -229673017810787765L;
	@PersistenceContext(unitName = "fluor-PU")
	private EntityManager em;
	@Inject
	private ExaminationBean exam;
	@Inject
	private FiasEditor fias;
	@Inject
	private LoginBean login;
	
	@Resource UserTransaction saveTransaction;

	
	private Patient patient;
	
	private ListConverter sexConverter;
	private List<RGender> sexList;
	private ListConverter jitConverter;
	private List<RHabitat> jitelList;
	private ListConverter dgConverter;
	private List<RDecrGroup> decrList;
	private ListConverter mgConverter;
	private List<RMedGroup> medList;
	private ListConverter sgConverter;
	private List<RSocGroup> socList;
	private ListConverter citizenConverter;
	private List<RCitizen> citizenList;
	
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
	
	private boolean editMode = false;
	
	@PostConstruct
	private void init() {
		patient = new Patient();
		
		setSexConverter(new ListConverter());
		setJitConverter(new ListConverter());
		setDgConverter(new ListConverter());
		setMgConverter(new ListConverter());
		setSgConverter(new ListConverter());
		setCitizenConverter(new ListConverter());
		setMomConverter(new ListConverter());
		setMotConverter(new ListConverter());
		setMopConverter(new ListConverter());
		setMorConverter(new ListConverter());
		
		setSexList(em.createQuery("SELECT r FROM " + RGender.class.getName() + " r ORDER BY r.id", RGender.class).getResultList());
		setJitelList(em.createQuery("SELECT r FROM " + RHabitat.class.getName() + " r ORDER BY r.id", RHabitat.class).getResultList());
		setCitizenList(em.createQuery("SELECT r FROM " + RCitizen.class.getName() + " r ORDER BY r.id", RCitizen.class).getResultList());
		setDecrList(em.createQuery("SELECT r FROM " + RDecrGroup.class.getName() + " r ORDER BY r.id", RDecrGroup.class).getResultList());
		setMedList(em.createQuery("SELECT r FROM " + RMedGroup.class.getName() + " r ORDER BY r.id", RMedGroup.class).getResultList());
		setSocList(em.createQuery("SELECT r FROM " + RSocGroup.class.getName() + " r ORDER BY r.id", RSocGroup.class).getResultList());
		setMoRegionList(em.createQuery("SELECT r FROM " + RMedicalOrgRegion.class.getName() + " r ORDER BY r.id", RMedicalOrgRegion.class).getResultList());
	}
	
	public void loadPatient(String idStr) {
		if (idStr.isEmpty()) {
			setPatient(new Patient());
			
			exam.loadExam(-1, isEditMode());
			setFiasValues();
			setSelectedMor(null);
			setSelectedMot(null);
			setSelectedMom(null);
			setSelectedMop(null);
			setMoTerList(new ArrayList<RMedicalOrgTer>());
			setMoMainList(new ArrayList<RMedicalOrgMain>());
			setMoPoliclinicList(new ArrayList<RMedicalOrgPoliclinic>());
		} else {
			int id = Integer.parseInt(idStr);
			
			setPatient(em.find(Patient.class, id));
			
			if (getPatient().getRMedicalOrgPoliclinic() != null) {
				for (id = 0; id < moRegionList.size(); id++) {
					if (moRegionList.get(id).getRegId().compareTo(getPatient().getRMedicalOrgPoliclinic().getId().getRegId()) == 0)
						break;
				}
				setSelectedMor((id < moRegionList.size()) ? moRegionList.get(id) : null);
				morSelected();
				
				for (id = 0; id < moTerList.size(); id++) {
					if (moTerList.get(id).getId().getTerId().compareTo(getPatient().getRMedicalOrgPoliclinic().getId().getTerId()) == 0)
						break;
				}
				setSelectedMot((id < moTerList.size()) ? moTerList.get(id) : null);
				motSelected();
				
				for (id = 0; id < moMainList.size(); id++) {
					if (moMainList.get(id).getId().getLpuId().compareTo(getPatient().getRMedicalOrgPoliclinic().getId().getLpuId()) == 0)
						break;
				}
				setSelectedMom((id < moMainList.size()) ? moMainList.get(id) : null);
				momSelected();
				
				for (id = 0; id < moPoliclinicList.size(); id++) {
					if (moPoliclinicList.get(id).getId().getPolId().compareTo(getPatient().getRMedicalOrgPoliclinic().getId().getPolId()) == 0)
						break;
				}
				setSelectedMop((id < moPoliclinicList.size()) ? moPoliclinicList.get(id) : null);
			} else {
				setSelectedMor(null);
				setSelectedMot(null);
				setSelectedMom(null);
				setSelectedMop(null);
			}
			
			exam.loadExam(getPatient().getPatientId().getId(), isEditMode());
			setFiasValues();
		}
	}
	
	private void setFiasValues() {
		if (getPatient().getAddress() == null)
			getPatient().setAddress(new Address());
		
		Address addr = getPatient().getAddress();
		
		if (addr.getLivRegId() != null)
			fias.setRegion(fias.getElementById(addr.getLivRegId()));
		else
			fias.setRegion(new FiasElement("", addr.getLivReg()));
		if (addr.getLivCityId() != null)
			fias.setGorod(fias.getElementById(addr.getLivCityId()));
		else
			fias.setGorod(new FiasElement("", addr.getLivCity()));
		if (addr.getLivStreetId() != null)
			fias.setUlica(fias.getElementById(addr.getLivStreetId()));
		else
			fias.setUlica(new FiasElement("", addr.getLivStreet()));
		
		fias.setDom(addr.getLivHouse());
		fias.setKorp(addr.getLivFacility());
		fias.setStr(addr.getLivBuilding());
		fias.setKv(addr.getLivFlat());
	}
	
	public String getAddress() {
		return fias.getAddress();
	}

	public String savePatientAndRedirect() throws Exception {
		savePatient();
		
		return "listman.xhtml?faces-redirect=true";
	}
	
	public void savePatient() throws Exception {
		Integer patientId= patient.getId();
		
        try {
        	saveTransaction.begin();
			if (patient.getId() == null) {
				PatientId pid = new PatientId();
				em.persist(pid);
				
				patient.setPatientId(pid);
				patient.setVerCreationDate(new Date());
			} else {
				em.createNativeQuery("UPDATE patient SET _ver_active = FALSE WHERE _ver_parent_id = " + patient.getPatientId().getId()).executeUpdate();
				patient.setId(null);
			}
			patient.setVerActive(true);
			getFiasValues();
			
			if ((selectedMor != null) && (selectedMot != null) && (selectedMom != null) && (selectedMop != null)) {
				patient.setRMedicalOrgPoliclinic(new RMedicalOrgPoliclinic());
				patient.getRMedicalOrgPoliclinic().getRMedicalOrgMain().getRMedicalOrgTer().getRMedicalOrgRegion().setRegId(selectedMor.getRegId());
				patient.getRMedicalOrgPoliclinic().getRMedicalOrgMain().getRMedicalOrgTer().setId(selectedMot.getId());
				patient.getRMedicalOrgPoliclinic().getRMedicalOrgMain().setId(selectedMom.getId());
				patient.getRMedicalOrgPoliclinic().setId(selectedMop.getId());
			} else {
				patient.setRMedicalOrgPoliclinic(null);
			}
			
			patient.setUser(login.getAuthedUser());
			em.persist(patient);
			saveTransaction.commit();
			patientId=null;
        }
        catch (Exception e) {
        	saveTransaction.rollback();
        	patient.setId(patientId);
        	throw new Exception("Произошла ошибка при сохранении данных пациента");
		}
	}
	
	private void getFiasValues() {
		Address addr = getPatient().getAddress();
		
		if (!fias.getRegion().getAoid().isEmpty())
			addr.setLivRegId(UUID.fromString(fias.getRegion().getAoid()));
		else
			addr.setLivRegId(null);
		addr.setLivReg(fias.getRegion().getFormalname());
		if (!fias.getGorod().getAoid().isEmpty())
			addr.setLivCityId(UUID.fromString(fias.getGorod().getAoid()));
		else
			addr.setLivCityId(null);
		addr.setLivCity(fias.getGorod().getFormalname());
		if (!fias.getUlica().getAoid().isEmpty())
			addr.setLivStreetId(UUID.fromString(fias.getUlica().getAoid()));
		else
			addr.setLivStreetId(null);
		addr.setLivStreet(fias.getUlica().getFormalname());
		
		addr.setLivHouse(fias.getDom());
		addr.setLivFacility(fias.getKorp());
		addr.setLivBuilding(fias.getStr());
		addr.setLivFlat(fias.getKv());
	}
	
	public void printReport(int id, boolean pdf) throws IOException, ServletException {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext ec = ctx.getExternalContext();
		HttpServletRequest req = (HttpServletRequest) ec.getRequest();
		HttpSession ses = req.getSession();
		
		ses.setAttribute("id", id);
		ses.setAttribute("pdf", pdf);
		ses.setAttribute("patId", getPatient().getId());
		ec.redirect("report");
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<RGender> getSexList() {
		return sexList;
	}
	
	public void setSexList(List<RGender> sexList) {
		this.sexList = sexList;
		sexConverter.setList(sexList);
	}
	
	public ListConverter getSexConverter() {
		return sexConverter;
	}

	public void setSexConverter(ListConverter sexConverter) {
		this.sexConverter = sexConverter;
	}

	public List<RHabitat> getJitelList() {
		return jitelList;
	}
	
	public void setJitelList(List<RHabitat> jitelList) {
		this.jitelList = jitelList;
		jitConverter.setList(jitelList);
	}
	
	public ListConverter getJitConverter() {
		return jitConverter;
	}

	public void setJitConverter(ListConverter jitConverter) {
		this.jitConverter = jitConverter;
	}
	
	public List<RDecrGroup> getDecrList() {
		return decrList;
	}
	
	public void setDecrList(List<RDecrGroup> decrList) {
		this.decrList = decrList;
		dgConverter.setList(decrList);
	}
	
	public ListConverter getDgConverter() {
		return dgConverter;
	}

	public void setDgConverter(ListConverter dgConverter) {
		this.dgConverter = dgConverter;
	}
	
	public List<RMedGroup> getMedList() {
		return medList;
	}
	
	public void setMedList(List<RMedGroup> medList) {
		this.medList = medList;
		mgConverter.setList(medList);
	}
	
	public ListConverter getMgConverter() {
		return mgConverter;
	}

	public void setMgConverter(ListConverter mgConverter) {
		this.mgConverter = mgConverter;
	}
	
	public List<RSocGroup> getSocList() {
		return socList;
	}
	
	public void setSocList(List<RSocGroup> socList) {
		this.socList = socList;
		sgConverter.setList(socList);
	}
	
	public ListConverter getSgConverter() {
		return sgConverter;
	}

	public void setSgConverter(ListConverter sgConverter) {
		this.sgConverter = sgConverter;
	}

	public List<RCitizen> getCitizenList() {
		return citizenList;
	}

	public void setCitizenList(List<RCitizen> citizenList) {
		this.citizenList = citizenList;
		citizenConverter.setList(citizenList);
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

	public ListConverter getCitizenConverter() {
		return citizenConverter;
	}

	public void setCitizenConverter(ListConverter citizenConverter) {
		this.citizenConverter = citizenConverter;
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

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
}
