package ru.miacn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import ru.miacn.fias.FiasEditor;
import ru.miacn.fias.FiasElement;
import ru.miacn.persistence.reference.ListConverter;
import ru.miacn.persistence.reference.RDecrGroup;
import ru.miacn.persistence.reference.RMedGroup;
import ru.miacn.persistence.reference.RMedicalOrgMain;
import ru.miacn.persistence.reference.RMedicalOrgPoliclinic;
import ru.miacn.persistence.reference.RMedicalOrgRegion;
import ru.miacn.persistence.reference.RMedicalOrgTer;
import ru.miacn.persistence.reference.RResultType;
import ru.miacn.persistence.reference.RSocGroup;

@Named
@SessionScoped
@Transactional
public class FilterBean implements Serializable{
	private static final long serialVersionUID = 2296258073300711800L;
	@PersistenceContext(unitName = "fluor-PU")
	private EntityManager em;
	@Inject
	private FiasEditor fias;

	private ListConverter dgConverter;
	private List<RDecrGroup> decrList;
	private RDecrGroup selectedDg;
	private ListConverter mgConverter;
	private List<RMedGroup> medList;
	private RMedGroup selectedMg;
	private ListConverter sgConverter;
	private List<RSocGroup> socList;
	private RSocGroup selectedSg;
	private ListConverter restypeConverter;
	private List<RResultType> restypeList;
	private RResultType selectedRezType;
	
	private ListConverter morConverter;
	private ListConverter mor2Converter;
	private List<RMedicalOrgRegion> moRegionList;
	private List<RMedicalOrgRegion> moRegionObsList;
	private RMedicalOrgRegion selectedMor;
	private RMedicalOrgRegion selectedRegObs;

	private ListConverter motConverter;
	private ListConverter mot2Converter;
	private List<RMedicalOrgTer> moTerList;
	private List<RMedicalOrgTer> moTerList2;
	private RMedicalOrgTer selectedMot;
	private RMedicalOrgTer selectedTerObs;

	private ListConverter momConverter;
	private ListConverter mom2Converter;
	private List<RMedicalOrgMain> moMainList;
	private List<RMedicalOrgMain> moMainList2;
	private RMedicalOrgMain selectedMom;
	private RMedicalOrgMain selectedLpuObs;
	
	private ListConverter mopConverter;
	private List<RMedicalOrgPoliclinic> moPoliclinicList;
	private RMedicalOrgPoliclinic selectedMop;
	private Date datStart;
	private Date datEnd;
	
	@PostConstruct
	private void init() {
		setDgConverter(new ListConverter());
		setMgConverter(new ListConverter());
		setSgConverter(new ListConverter());
		setRestypeConverter(new ListConverter());
		setMorConverter(new ListConverter());
		setMotConverter(new ListConverter());
		setMomConverter(new ListConverter());
		setMopConverter(new ListConverter());
		setMor2Converter(new ListConverter());
		setMot2Converter(new ListConverter());
		setMom2Converter(new ListConverter());

		setDecrList(em.createQuery("SELECT r FROM " + RDecrGroup.class.getName() + " r ORDER BY r.id", RDecrGroup.class).getResultList());
		setMedList(em.createQuery("SELECT r FROM " + RMedGroup.class.getName() + " r ORDER BY r.id", RMedGroup.class).getResultList());
		setSocList(em.createQuery("SELECT r FROM " + RSocGroup.class.getName() + " r ORDER BY r.id", RSocGroup.class).getResultList());
		setMoRegionList(em.createQuery("SELECT r FROM " + RMedicalOrgRegion.class.getName() + " r ORDER BY r.id", RMedicalOrgRegion.class).getResultList());
		setMoRegionObsList(em.createQuery("SELECT r FROM " + RMedicalOrgRegion.class.getName() + " r ORDER BY r.id", RMedicalOrgRegion.class).getResultList());
		setRestypeList(em.createQuery("SELECT r FROM " + RResultType.class.getName() + " r ORDER BY r.id", RResultType.class).getResultList());
	}

	public void throwNullPointerException() {
		throw new NullPointerException("A NullPointerException!");
	}
	
 	public void clearFilter(){
		fias.setRegion(new FiasElement("", null));
		fias.setGorod(new FiasElement("", null));
		fias.setUlica(new FiasElement("", null));
		fias.setDom(null);
		fias.setKorp(null);
		fias.setStr(null);
		fias.setKv(null);

		setDatStart(null);
		setDatEnd(null);
		setSelectedDg(null);
		setSelectedMg(null);
		setSelectedSg(null);
		setSelectedRezType(null);
		setMoTerList(new ArrayList<RMedicalOrgTer>());
		setMoMainList(new ArrayList<RMedicalOrgMain>());
		setMoPoliclinicList(new ArrayList<RMedicalOrgPoliclinic>());
		setMoTerList2(new ArrayList<RMedicalOrgTer>());
		setMoMainList2(new ArrayList<RMedicalOrgMain>());
		setSelectedMor(null);
		setSelectedRegObs(null);
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
		setSelectedMop(null);
		setMoMainList(new ArrayList<RMedicalOrgMain>());
		setMoPoliclinicList(new ArrayList<RMedicalOrgPoliclinic>());
	}

	@SuppressWarnings("unchecked")
	public void regobsSelected() {
		if (selectedRegObs != null) {
			Query query = em.createNativeQuery("SELECT * FROM r_medical_org_ter r WHERE r.reg_id = :r_id ORDER BY r.name", RMedicalOrgTer.class);
			query.setParameter("r_id", selectedRegObs.getRegId());
			setMoTerList2(query.getResultList());
		} else {
			setMoTerList2(new ArrayList<RMedicalOrgTer>());
		}
		setSelectedTerObs(null);
		setSelectedLpuObs(null);
		setMoMainList2(new ArrayList<RMedicalOrgMain>());
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
		setSelectedMop(null);
		setMoPoliclinicList(new ArrayList<RMedicalOrgPoliclinic>());
	}

	@SuppressWarnings("unchecked")
	public void motobsSelected() {
		if (selectedTerObs != null) {
			Query query = em.createNativeQuery("SELECT * FROM r_medical_org_main r WHERE r.ter_id = :t_id and r.reg_id = :r_id ORDER BY r.lpu_id", RMedicalOrgMain.class);
			query.setParameter("t_id", selectedTerObs.getId().getTerId());
			query.setParameter("r_id", selectedTerObs.getId().getRegId());
			setMoMainList2(query.getResultList());
		} else {
			setMoMainList2(new ArrayList<RMedicalOrgMain>());
		}
		setSelectedLpuObs(null);
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
		setSelectedMop(null);
	}

	public ListConverter getMgConverter() {
		return mgConverter;
	}

	public void setMgConverter(ListConverter mgConverter) {
		this.mgConverter = mgConverter;
	}

	public List<RMedGroup> getMedList() {
		return medList;
	}

	public void setMedList(List<RMedGroup> medList) {
		this.medList = medList;
		mgConverter.setList(medList);
	}

	public ListConverter getSgConverter() {
		return sgConverter;
	}

	public void setSgConverter(ListConverter sgConverter) {
		this.sgConverter = sgConverter;
	}

	public List<RSocGroup> getSocList() {
		return socList;
	}

	public void setSocList(List<RSocGroup> socList) {
		this.socList = socList;
		sgConverter.setList(socList);
	}

	public ListConverter getDgConverter() {
		return dgConverter;
	}

	public void setDgConverter(ListConverter dgConverter) {
		this.dgConverter = dgConverter;
	}
	
	public List<RDecrGroup> getDecrList() {
		return decrList;
	}

	public void setDecrList(List<RDecrGroup> decrList) {
		this.decrList = decrList;
		dgConverter.setList(decrList);
	}

	public ListConverter getMorConverter() {
		return morConverter;
	}

	public void setMorConverter(ListConverter morConverter) {
		this.morConverter = morConverter;
	}

	public ListConverter getMor2Converter() {
		return mor2Converter;
	}

	public void setMor2Converter(ListConverter mor2Converter) {
		this.mor2Converter = mor2Converter;
	}

	public List<RMedicalOrgRegion> getMoRegionList() {
		return moRegionList;
	}

	public void setMoRegionList(List<RMedicalOrgRegion> moRegionList) {
		this.moRegionList = moRegionList;
		morConverter.setList(moRegionList);
	}

	public List<RMedicalOrgRegion> getMoRegionObsList() {
		return moRegionObsList;
	}

	public void setMoRegionObsList(List<RMedicalOrgRegion> moRegionObsList) {
		this.moRegionObsList = moRegionObsList;
		mor2Converter.setList(moRegionObsList);
	}

	public RMedicalOrgRegion getSelectedMor() {
		return selectedMor;
	}

	public void setSelectedMor(RMedicalOrgRegion selectedMor) {
		this.selectedMor = selectedMor;
	}

	public ListConverter getMotConverter() {
		return motConverter;
	}

	public void setMotConverter(ListConverter motConverter) {
		this.motConverter = motConverter;
	}

	public ListConverter getMot2Converter() {
		return mot2Converter;
	}

	public void setMot2Converter(ListConverter mot2Converter) {
		this.mot2Converter = mot2Converter;
	}

	public List<RMedicalOrgTer> getMoTerList() {
		return moTerList;
	}

	public void setMoTerList(List<RMedicalOrgTer> moTerList) {
		this.moTerList = moTerList;
		motConverter.setList(moTerList);
	}

	public List<RMedicalOrgTer> getMoTerList2() {
		return moTerList2;
	}

	public void setMoTerList2(List<RMedicalOrgTer> moTerList2) {
		this.moTerList2 = moTerList2;
		mot2Converter.setList(moTerList2);
	}

	public RMedicalOrgTer getSelectedMot() {
		return selectedMot;
	}

	public void setSelectedMot(RMedicalOrgTer selectedMot) {
		this.selectedMot = selectedMot;
	}

	public ListConverter getMomConverter() {
		return momConverter;
	}

	public void setMomConverter(ListConverter momConverter) {
		this.momConverter = momConverter;
	}

	public ListConverter getMom2Converter() {
		return mom2Converter;
	}

	public void setMom2Converter(ListConverter mom2Converter) {
		this.mom2Converter = mom2Converter;
	}

	public List<RMedicalOrgMain> getMoMainList() {
		return moMainList;
	}

	public void setMoMainList(List<RMedicalOrgMain> moMainList) {
		this.moMainList = moMainList;
		momConverter.setList(moMainList);
	}

	public List<RMedicalOrgMain> getMoMainList2() {
		return moMainList2;
	}

	public void setMoMainList2(List<RMedicalOrgMain> moMainList2) {
		this.moMainList2 = moMainList2;
		mom2Converter.setList(moMainList2);
	}

	public RMedicalOrgMain getSelectedMom() {
		return selectedMom;
	}

	public void setSelectedMom(RMedicalOrgMain selectedMom) {
		this.selectedMom = selectedMom;
	}

	public ListConverter getMopConverter() {
		return mopConverter;
	}

	public void setMopConverter(ListConverter mopConverter) {
		this.mopConverter = mopConverter;
	}

	public List<RMedicalOrgPoliclinic> getMoPoliclinicList() {
		return moPoliclinicList;
	}

	public void setMoPoliclinicList(List<RMedicalOrgPoliclinic> moPoliclinicList) {
		this.moPoliclinicList = moPoliclinicList;
		mopConverter.setList(moPoliclinicList);
	}

	public RMedicalOrgPoliclinic getSelectedMop() {
		return selectedMop;
	}

	public void setSelectedMop(RMedicalOrgPoliclinic selectedMop) {
		this.selectedMop = selectedMop;
	}

	public ListConverter getRestypeConverter() {
		return restypeConverter;
	}

	public void setRestypeConverter(ListConverter restypeConverter) {
		this.restypeConverter = restypeConverter;
	}

	public List<RResultType> getRestypeList() {
		return restypeList;
	}

	public void setRestypeList(List<RResultType> restypeList) {
		this.restypeList = restypeList;
		restypeConverter.setList(restypeList);
	}

	public Date getDatStart() {
		return datStart;
	}

	public void setDatStart(Date datStart) {
		this.datStart = datStart;
	}

	public Date getDatEnd() {
		return datEnd;
	}

	public void setDatEnd(Date datEnd) {
		this.datEnd = datEnd;
	}

	public RDecrGroup getSelectedDg() {
		return selectedDg;
	}

	public void setSelectedDg(RDecrGroup selectedDg) {
		this.selectedDg = selectedDg;
	}

	public RMedGroup getSelectedMg() {
		return selectedMg;
	}

	public void setSelectedMg(RMedGroup selectedMg) {
		this.selectedMg = selectedMg;
	}

	public RSocGroup getSelectedSg() {
		return selectedSg;
	}

	public void setSelectedSg(RSocGroup selectedSg) {
		this.selectedSg = selectedSg;
	}

	public RResultType getSelectedRezType() {
		return selectedRezType;
	}

	public void setSelectedRezType(RResultType selectedRezType) {
		this.selectedRezType = selectedRezType;
	}

	public RMedicalOrgRegion getSelectedRegObs() {
		return selectedRegObs;
	}

	public void setSelectedRegObs(RMedicalOrgRegion selectedRegObs) {
		this.selectedRegObs = selectedRegObs;
	}

	public RMedicalOrgTer getSelectedTerObs() {
		return selectedTerObs;
	}

	public void setSelectedTerObs(RMedicalOrgTer selectedTerObs) {
		this.selectedTerObs = selectedTerObs;
	}

	public RMedicalOrgMain getSelectedLpuObs() {
		return selectedLpuObs;
	}

	public void setSelectedLpuObs(RMedicalOrgMain selectedLpuObs) {
		this.selectedLpuObs = selectedLpuObs;
	}

}
