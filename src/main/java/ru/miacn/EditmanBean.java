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

import ru.miacn.persistence.ListConverter;
import ru.miacn.persistence.RefDecrGroup;
import ru.miacn.persistence.RefExamType;
import ru.miacn.persistence.RefGender;
import ru.miacn.persistence.RefHabitat;
import ru.miacn.persistence.RefMedGroup;
import ru.miacn.persistence.RefSocGroup;

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

	private List<RefGender> sexList;
	private List<RefHabitat> jitelList;
	private List<RefExamType> extList;
	private List<RefDecrGroup> decrList;
	private List<RefMedGroup> medList;
	private List<RefSocGroup> socList;
	private RefGender selectedSex;
	private ListConverter sexConverter;
	private RefHabitat selectedJitel;
	private ListConverter jitConverter;
	private RefExamType selectedExt;
	private ListConverter extConverter;
	private RefDecrGroup selectedDg;
	private ListConverter dgConverter;
	private RefMedGroup selectedMg;
	private ListConverter mgConverter;
	private RefSocGroup selectedSg;
	private ListConverter sgConverter;
    
	@PostConstruct
	private void init() {
		setSexConverter(new ListConverter());
		setJitConverter(new ListConverter());
		setExtConverter(new ListConverter());
		setDgConverter(new ListConverter());
		setMgConverter(new ListConverter());
		setSgConverter(new ListConverter());
		setSexList(em.createQuery("SELECT r FROM " + RefGender.class.getName() + " r ORDER BY r.name", RefGender.class).getResultList());
		setJitelList(em.createQuery("SELECT r FROM " + RefHabitat.class.getName() + " r ORDER BY r.name", RefHabitat.class).getResultList());
		setExtList(em.createQuery("SELECT r FROM " + RefExamType.class.getName() + " r ORDER BY r.name", RefExamType.class).getResultList());
		setDecrList(em.createQuery("SELECT r FROM " + RefDecrGroup.class.getName() + " r ORDER BY r.name", RefDecrGroup.class).getResultList());
		setMedList(em.createQuery("SELECT r FROM " + RefMedGroup.class.getName() + " r ORDER BY r.name", RefMedGroup.class).getResultList());
		setSocList(em.createQuery("SELECT r FROM " + RefSocGroup.class.getName() + " r ORDER BY r.name", RefSocGroup.class).getResultList());
	}

	public void sexSelected() {
		if (selectedSex != null) {
			TypedQuery<RefGender> query = em.createQuery("SELECT r FROM " + RefGender.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RefGender.class);
			
			query.setParameter("id", selectedSex.getId());
			setSexList(query.getResultList());
		} else {
			setSexList(new ArrayList<RefGender>());
		}
	}
	
	public List<RefGender> getSexList() {
		return sexList;
	}
	
	public void setSexList(List<RefGender> sexList) {
		this.sexList = sexList;
		sexConverter.setList(sexList);
	}
	
	public RefGender getSelectedSex() {
		return selectedSex;
	}
	
	public void setSelectedSex(RefGender selectedSex) {
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
			TypedQuery<RefHabitat> query = em.createQuery("SELECT r FROM " + RefHabitat.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RefHabitat.class);
			
			query.setParameter("id", selectedJitel.getId());
			setJitelList(query.getResultList());
		} else {
			setJitelList(new ArrayList<RefHabitat>());
		}
	}
	
	public List<RefHabitat> getJitelList() {
		return jitelList;
	}
	
	public void setJitelList(List<RefHabitat> jitelList) {
		this.jitelList = jitelList;
		jitConverter.setList(jitelList);
	}
	
	public RefHabitat getSelectedJitel() {
		return selectedJitel;
	}
	
	public void setSelectedJitel(RefHabitat selectedJitel) {
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
			TypedQuery<RefDecrGroup> query = em.createQuery("SELECT r FROM " + RefDecrGroup.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RefDecrGroup.class);
			
			query.setParameter("id", selectedDg.getId());
			setDecrList(query.getResultList());
		} else {
			setDecrList(new ArrayList<RefDecrGroup>());
		}
	}
	
	public List<RefDecrGroup> getDecrList() {
		return decrList;
	}
	
	public void setDecrList(List<RefDecrGroup> decrList) {
		this.decrList = decrList;
		dgConverter.setList(decrList);
	}
	
	public RefDecrGroup getSelectedDg() {
		return selectedDg;
	}
	
	public void setSelectedDg(RefDecrGroup selectedDg) {
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
			TypedQuery<RefMedGroup> query = em.createQuery("SELECT r FROM " + RefMedGroup.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RefMedGroup.class);
			
			query.setParameter("id", selectedMg.getId());
			setMedList(query.getResultList());
		} else {
			setMedList(new ArrayList<RefMedGroup>());
		}
	}
	
	public List<RefMedGroup> getMedList() {
		return medList;
	}
	
	public void setMedList(List<RefMedGroup> medList) {
		this.medList = medList;
		mgConverter.setList(medList);
	}
	
	public RefMedGroup getSelectedMg() {
		return selectedMg;
	}
	
	public void setSelectedMg(RefMedGroup selectedMg) {
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
			TypedQuery<RefSocGroup> query = em.createQuery("SELECT r FROM " + RefSocGroup.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RefSocGroup.class);
			
			query.setParameter("id", selectedSg.getId());
			setSocList(query.getResultList());
		} else {
			setSocList(new ArrayList<RefSocGroup>());
		}
	}
	
	public List<RefSocGroup> getSocList() {
		return socList;
	}
	
	public void setSocList(List<RefSocGroup> socList) {
		this.socList = socList;
		sgConverter.setList(socList);
	}
	
	public RefSocGroup getSelectedSg() {
		return selectedSg;
	}
	
	public void setSelectedSg(RefSocGroup selectedSg) {
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
			TypedQuery<RefExamType> query = em.createQuery("SELECT r FROM " + RefExamType.class.getName() + " r WHERE r.id = :id ORDER BY r.name", RefExamType.class);
			
			query.setParameter("id", selectedExt.getId());
			setExtList(query.getResultList());
		} else {
			setExtList(new ArrayList<RefExamType>());
		}
	}
	
	public List<RefExamType> getExtList() {
		return extList;
	}
	
	public void setExtList(List<RefExamType> extList) {
		this.extList = extList;
		extConverter.setList(extList);
	}
	
	public RefExamType getSelectedExt() {
		return selectedExt;
	}
	
	public void setSelectedExt(RefExamType selectedExt) {
		this.selectedExt = selectedExt;
	}

	public ListConverter getExtConverter() {
		return extConverter;
	}

	public void setExtConverter(ListConverter extConverter) {
		this.extConverter = extConverter;
	}

	
}

