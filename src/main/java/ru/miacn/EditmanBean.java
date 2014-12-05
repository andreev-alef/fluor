package ru.miacn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ru.miacn.persistence.reference.ListConverter;
import ru.miacn.persistence.reference.RDecrGroup;
import ru.miacn.persistence.reference.RExamMethod;
import ru.miacn.persistence.reference.RExamType;
import ru.miacn.persistence.reference.RHabitat;
import ru.miacn.persistence.reference.RMedGroup;
import ru.miacn.persistence.reference.RMedicalOrgMain;
import ru.miacn.persistence.reference.RMedicalOrgPoliclinic;
import ru.miacn.persistence.reference.RMedicalOrgTer;
import ru.miacn.persistence.reference.RResultType;
import ru.miacn.persistence.reference.RSocGroup;
import ru.miacn.persistence.reference.RCitizen;
import ru.miacn.persistence.reference.RGender;

@Named
@SessionScoped
public class EditmanBean implements Serializable {
	public static final long serialVersionUID = -229673017810787765L;
	@PersistenceContext
	private EntityManager em;

	private String firstName = "Фамилия";
    private String midlName = "Имя";
    private String lastName = "Отчество";
    private Date datBirthay = new Date();
    private String region = "Кемеровская область";
    private String nasp;
    private String street;
    private String dom;
    private String korp;
    private String str;
    private String flat;
    private String telefon;
    private int jitel;
    private String sex = "N";
    private String nationality = "Росия";
    private boolean selUmer;
    private boolean selMO;
    private String naspMO;
    private String adresMO;
    private String nameMO;
    private String clinicMO;
    private int dekrGrp;
    private int medGrp;
    private int riskGrp;

	private List<RGender> sexList;
	private List<RHabitat> jitelList;
	private List<RExamType> extList;
	private List<RDecrGroup> decrList;
	private List<RMedGroup> medList;
	private List<RSocGroup> socList;
	private List<RCitizen> citizenList;
	private List<RExamMethod> exmList;
	private List<RMedicalOrgMain> moMainList;
	private List<RMedicalOrgPoliclinic> moPoliclinicList;
	private List<RMedicalOrgTer> moTerList;
	private List<RResultType> restypeList;
	private RGender selectedSex;
	private ListConverter sexConverter;
	private RHabitat selectedJitel;
	private ListConverter jitConverter;
	private RExamType selectedExt;
	private ListConverter extConverter;
	private RDecrGroup selectedDg;
	private ListConverter dgConverter;
	private RMedGroup selectedMg;
	private ListConverter mgConverter;
	private RSocGroup selectedSg;
	private ListConverter sgConverter;
	private RCitizen selectedCitizen;
	private ListConverter citizenConverter;
	private RExamMethod selectedExm;
	private ListConverter exmConverter;
	private RMedicalOrgMain selectedMom;
	private ListConverter momConverter;
	private RMedicalOrgPoliclinic selectedMop;
	private ListConverter mopConverter;
	private RMedicalOrgTer selectedMot;
	private ListConverter motConverter;
	private RResultType selectedResType;
	private ListConverter restypeConverter;
	    
	@PostConstruct
	private void init() {
		setSexConverter(new ListConverter());
		setJitConverter(new ListConverter());
		setExtConverter(new ListConverter());
		setDgConverter(new ListConverter());
		setMgConverter(new ListConverter());
		setSgConverter(new ListConverter());
		setCitizenConverter(new ListConverter());
		setExmConverter(new ListConverter());
		setRestypeConverter(new ListConverter());
		setSexList(em.createQuery("SELECT r FROM " + RGender.class.getName() + " r ORDER BY r.id", RGender.class).getResultList());
		setJitelList(em.createQuery("SELECT r FROM " + RHabitat.class.getName() + " r ORDER BY r.id", RHabitat.class).getResultList());
		setExtList(em.createQuery("SELECT r FROM " + RExamType.class.getName() + " r ORDER BY r.id", RExamType.class).getResultList());
		setCitizenList(em.createQuery("SELECT r FROM " + RCitizen.class.getName() + " r ORDER BY r.id", RCitizen.class).getResultList());
		setExmList(em.createQuery("SELECT r FROM " + RExamMethod.class.getName() + " r ORDER BY r.id", RExamMethod.class).getResultList());
		setRestypeList(em.createQuery("SELECT r FROM " + RResultType.class.getName() + " r ORDER BY r.id", RResultType.class).getResultList());
		setDecrList(em.createQuery("SELECT r FROM " + RDecrGroup.class.getName() + " r ORDER BY r.id", RDecrGroup.class).getResultList());
		setMedList(em.createQuery("SELECT r FROM " + RMedGroup.class.getName() + " r ORDER BY r.id", RMedGroup.class).getResultList());
		setSocList(em.createQuery("SELECT r FROM " + RSocGroup.class.getName() + " r ORDER BY r.id", RSocGroup.class).getResultList());
	}

	public void sexSelected() {
		if (selectedSex != null) {
			TypedQuery<RGender> query = em.createQuery("SELECT r FROM " + RGender.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RGender.class);
			query.setParameter("id", selectedSex.getId());
			setSexList(query.getResultList());
		} else {
			setSexList(new ArrayList<RGender>());
		}
	}
	
	public void citizenSelected() {
		if (selectedCitizen != null) {
			TypedQuery<RCitizen> query = em.createQuery("SELECT r FROM " + RCitizen.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RCitizen.class);
			query.setParameter("id", selectedCitizen.getId());
			setCitizenList(query.getResultList());
		} else {
			setCitizenList(new ArrayList<RCitizen>());
		}
	}
	
	public void exmSelected() {
		if (selectedExm != null) {
			TypedQuery<RExamMethod> query = em.createQuery("SELECT r FROM " + RExamMethod.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RExamMethod.class);
			query.setParameter("id", selectedExm.getId());
			setExmList(query.getResultList());
		} else {
			setExmList(new ArrayList<RExamMethod>());
		}
	}
	
	public void reztypeSelected() {
		if (selectedResType != null) {
			TypedQuery<RResultType> query = em.createQuery("SELECT r FROM " + RResultType.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RResultType.class);
			query.setParameter("id", selectedResType.getId());
			setRestypeList(query.getResultList());
		} else {
			setRestypeList(new ArrayList<RResultType>());
		}
	}
	
	public List<RGender> getSexList() {
		return sexList;
	}
	
	public void setSexList(List<RGender> sexList) {
		this.sexList = sexList;
		sexConverter.setList(sexList);
	}
	
	public RGender getSelectedSex() {
		return selectedSex;
	}
	
	public void setSelectedSex(RGender selectedSex) {
		this.selectedSex = selectedSex;
	}

	public ListConverter getSexConverter() {
		return sexConverter;
	}

	public void setSexConverter(ListConverter sexConverter) {
		this.sexConverter = sexConverter;
	}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidlName() {
		return midlName;
	}

	public void setMidlName(String midlName) {
		this.midlName = midlName;
	}

	public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

	public Date getDatBirthay() {
		return datBirthay;
	}

	public void setDatBirthay(Date datBirthay) {
		this.datBirthay = datBirthay;
	}

	public boolean isSelUmer() {
		return selUmer;
	}

	public void setSelUmer(boolean selUmer) {
		this.selUmer = selUmer;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getNasp() {
		return nasp;
	}

	public void setNasp(String nasp) {
		this.nasp = nasp;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDom() {
		return dom;
	}

	public void setDom(String dom) {
		this.dom = dom;
	}

	public String getKorp() {
		return korp;
	}

	public void setKorp(String korp) {
		this.korp = korp;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

	public int getJitel() {
		return jitel;
	}

	public void setJitel(int jitel) {
		this.jitel = jitel;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public boolean isSelMO() {
		return selMO;
	}

	public void setSelMO(boolean selMO) {
		this.selMO = selMO;
	}

	public String getNaspMO() {
		return naspMO;
	}

	public void setNaspMO(String naspMO) {
		this.naspMO = naspMO;
	}

	public String getAdresMO() {
		return adresMO;
	}

	public void setAdresMO(String adresMO) {
		this.adresMO = adresMO;
	}

	public String getNameMO() {
		return nameMO;
	}

	public void setNameMO(String nameMO) {
		this.nameMO = nameMO;
	}

	public String getClinicMO() {
		return clinicMO;
	}

	public void setClinicMO(String clinicMO) {
		this.clinicMO = clinicMO;
	}

	public int getDekrGrp() {
		return dekrGrp;
	}

	public void setDekrGrp(int dekrGrp) {
		this.dekrGrp = dekrGrp;
	}

	public int getMedGrp() {
		return medGrp;
	}

	public void setMedGrp(int medGrp) {
		this.medGrp = medGrp;
	}

	public int getRiskGrp() {
		return riskGrp;
	}

	public void setRiskGrp(int riskGrp) {
		this.riskGrp = riskGrp;
	}

	public void jitelSelected() {
		if (selectedSex != null) {
			TypedQuery<RHabitat> query = em.createQuery("SELECT r FROM " + RHabitat.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RHabitat.class);
			
			query.setParameter("id", selectedJitel.getId());
			setJitelList(query.getResultList());
		} else {
			setJitelList(new ArrayList<RHabitat>());
		}
	}
	
	public List<RHabitat> getJitelList() {
		return jitelList;
	}
	
	public void setJitelList(List<RHabitat> jitelList) {
		this.jitelList = jitelList;
		jitConverter.setList(jitelList);
	}
	
	public RHabitat getSelectedJitel() {
		return selectedJitel;
	}
	
	public void setSelectedJitel(RHabitat selectedJitel) {
		this.selectedJitel = selectedJitel;
	}

	public ListConverter getJitConverter() {
		return jitConverter;
	}

	public void setJitConverter(ListConverter jitConverter) {
		this.jitConverter = jitConverter;
	}
//
	public void dgSelected() {
		if (selectedDg != null) {
			TypedQuery<RDecrGroup> query = em.createQuery("SELECT r FROM " + RDecrGroup.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RDecrGroup.class);
			
			query.setParameter("id", selectedDg.getId());
			setDecrList(query.getResultList());
		} else {
			setDecrList(new ArrayList<RDecrGroup>());
		}
	}
	
	public List<RDecrGroup> getDecrList() {
		return decrList;
	}
	
	public void setDecrList(List<RDecrGroup> decrList) {
		this.decrList = decrList;
		dgConverter.setList(decrList);
	}
	
	public RDecrGroup getSelectedDg() {
		return selectedDg;
	}
	
	public void setSelectedDg(RDecrGroup selectedDg) {
		this.selectedDg = selectedDg;
	}

	public ListConverter getDgConverter() {
		return dgConverter;
	}

	public void setDgConverter(ListConverter dgConverter) {
		this.dgConverter = dgConverter;
	}
	//
	public void mgSelected() {
		if (selectedMg != null) {
			TypedQuery<RMedGroup> query = em.createQuery("SELECT r FROM " + RMedGroup.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RMedGroup.class);
			
			query.setParameter("id", selectedMg.getId());
			setMedList(query.getResultList());
		} else {
			setMedList(new ArrayList<RMedGroup>());
		}
	}
	
	public List<RMedGroup> getMedList() {
		return medList;
	}
	
	public void setMedList(List<RMedGroup> medList) {
		this.medList = medList;
		mgConverter.setList(medList);
	}
	
	public RMedGroup getSelectedMg() {
		return selectedMg;
	}
	
	public void setSelectedMg(RMedGroup selectedMg) {
		this.selectedMg = selectedMg;
	}

	public ListConverter getMgConverter() {
		return mgConverter;
	}

	public void setMgConverter(ListConverter mgConverter) {
		this.mgConverter = mgConverter;
	}
	//
	public void sgSelected() {
		if (selectedSg != null) {
			TypedQuery<RSocGroup> query = em.createQuery("SELECT r FROM " + RSocGroup.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RSocGroup.class);
			
			query.setParameter("id", selectedSg.getId());
			setSocList(query.getResultList());
		} else {
			setSocList(new ArrayList<RSocGroup>());
		}
	}
	
	public List<RSocGroup> getSocList() {
		return socList;
	}
	
	public void setSocList(List<RSocGroup> socList) {
		this.socList = socList;
		sgConverter.setList(socList);
	}
	
	public RSocGroup getSelectedSg() {
		return selectedSg;
	}
	
	public void setSelectedSg(RSocGroup selectedSg) {
		this.selectedSg = selectedSg;
	}

	public ListConverter getSgConverter() {
		return sgConverter;
	}

	public void setSgConverter(ListConverter sgConverter) {
		this.sgConverter = sgConverter;
	}
	//
	public void extSelected() {
		if (selectedExt != null) {
			TypedQuery<RExamType> query = em.createQuery("SELECT r FROM " + RExamType.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RExamType.class);
			
			query.setParameter("id", selectedExt.getId());
			setExtList(query.getResultList());
		} else {
			setExtList(new ArrayList<RExamType>());
		}
	}
	
	public List<RExamType> getExtList() {
		return extList;
	}
	
	public void setExtList(List<RExamType> extList) {
		this.extList = extList;
		extConverter.setList(extList);
	}
	
	public RExamType getSelectedExt() {
		return selectedExt;
	}
	
	public void setSelectedExt(RExamType selectedExt) {
		this.selectedExt = selectedExt;
	}

	public ListConverter getExtConverter() {
		return extConverter;
	}

	public void setExtConverter(ListConverter extConverter) {
		this.extConverter = extConverter;
	}

	public List<RCitizen> getCitizenList() {
		return citizenList;
	}

	public void setCitizenList(List<RCitizen> citizenList) {
		this.citizenList = citizenList;
		citizenConverter.setList(citizenList);
	}

	public List<RExamMethod> getExmList() {
		return exmList;
	}

	public void setExmList(List<RExamMethod> exmList) {
		this.exmList = exmList;
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

	public void setMoTerList(List<RMedicalOrgTer> moTerList) {
		this.moTerList = moTerList;
		motConverter.setList(moTerList);
	}

	public List<RResultType> getRestypeList() {
		return restypeList;
	}

	public void setRestypeList(List<RResultType> restypeList) {
		this.restypeList = restypeList;
		restypeConverter.setList(restypeList);
	}

	public RCitizen getSelectedCitizen() {
		return selectedCitizen;
	}

	public void setSelectedCitizen(RCitizen selectedCitizen) {
		this.selectedCitizen = selectedCitizen;
	}

	public ListConverter getCitizenConverter() {
		return citizenConverter;
	}

	public void setCitizenConverter(ListConverter citizenConverter) {
		this.citizenConverter = citizenConverter;
	}

	public RExamMethod getSelectedExm() {
		return selectedExm;
	}

	public void setSelectedExm(RExamMethod selectedExm) {
		this.selectedExm = selectedExm;
	}

	public ListConverter getExmConverter() {
		return exmConverter;
	}

	public void setExmConverter(ListConverter exmConverter) {
		this.exmConverter = exmConverter;
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

	public RResultType getSelectedResType() {
		return selectedResType;
	}

	public void setSelectedResType(RResultType selectedResType) {
		this.selectedResType = selectedResType;
	}

//	
//	public List<RResultType> getMedList() {
//		return medList;
//	}
//
//	public void setMedList(List<RResultType> medList) {
//		this.medList = medList;
//		mgConverter.setList(medList);
//	}
//	
//	public RResultType getSelectedMg() {
//		return selectedMg;
//	}
//	
//	public void setSelectedMg(RResultType selectedMg) {
//		this.selectedMg = selectedMg;
//	}
//	
//	public ListConverter getMgConverter() {
//		return mgConverter;
//	}
//	
	private void setRestypeConverter(ListConverter restypeConverter) {
		this.restypeConverter = restypeConverter;
	}
	public ListConverter getRestypeConverter() {
		return restypeConverter;
	}

	
}

