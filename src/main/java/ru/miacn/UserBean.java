package ru.miacn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import ru.miacn.persistence.model.User;
import ru.miacn.persistence.reference.ListConverter;
import ru.miacn.persistence.reference.RMedicalOrgMain;
import ru.miacn.persistence.reference.RMedicalOrgPoliclinic;
import ru.miacn.persistence.reference.RMedicalOrgRegion;
import ru.miacn.persistence.reference.RMedicalOrgTer;
import ru.miacn.persistence.reference.RUserRole;

@Named
@SessionScoped
public class UserBean implements Serializable {
	private static final long serialVersionUID = 5018277285233472200L;
	@PersistenceContext(unitName = "fluor-PU")
	private EntityManager em;
	
	private User user;
	
	private ListConverter userRoleConverter;
	private List<RUserRole> userRoleList;
	
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
		setUser(new User());
		
		setMomConverter(new ListConverter());
		setMotConverter(new ListConverter());
		setMopConverter(new ListConverter());
		setMorConverter(new ListConverter());
		setUserRoleConverter(new ListConverter());
		
		setUserRoleList(em.createQuery("SELECT r FROM " + RUserRole.class.getName() + " r ORDER BY r.id", RUserRole.class).getResultList());
		setMoRegionList(em.createQuery("SELECT r FROM " + RMedicalOrgRegion.class.getName() + " r ORDER BY r.id", RMedicalOrgRegion.class).getResultList());
	}
	
	@Transactional
	public void saveUser() {
			if ((selectedMor != null) && (selectedMot != null) && (selectedMom != null)) {
				user.setrMedicalOrgMain(new RMedicalOrgMain());
				user.getrMedicalOrgMain().getRMedicalOrgTer().getRMedicalOrgRegion().setRegId(selectedMor.getRegId());
				user.getrMedicalOrgMain().getRMedicalOrgTer().setId(selectedMot.getId());
				user.getrMedicalOrgMain().setId(selectedMom.getId());
			} else {
				user.setrMedicalOrgMain(null);
			}
			em.persist(user);
	}
	
	public void loadPatient() {
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public void setMoRegionList(List<RMedicalOrgRegion> moRegionList) {
		this.moRegionList = moRegionList;
		morConverter.setList(moRegionList);
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

	public List<RMedicalOrgTer> getMoTerList() {
		return moTerList;
	}

	public void setMoTerList(List<RMedicalOrgTer> moTerList) {
		this.moTerList = moTerList;
		motConverter.setList(moTerList);
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

	public RMedicalOrgPoliclinic getSelectedMop() {
		return selectedMop;
	}

	public void setSelectedMop(RMedicalOrgPoliclinic selectedMop) {
		this.selectedMop = selectedMop;
	}

	public List<RMedicalOrgMain> getMoMainList() {
		return moMainList;
	}

	public void setMoMainList(List<RMedicalOrgMain> moMainList) {
		this.moMainList = moMainList;
		momConverter.setList(moMainList);
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

	public ListConverter getUserRoleConverter() {
		return userRoleConverter;
	}

	public void setUserRoleConverter(ListConverter userRoleConverter) {
		this.userRoleConverter = userRoleConverter;
	}

	public List<RUserRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<RUserRole> userRoleList) {
		this.userRoleList = userRoleList;
		userRoleConverter.setList(userRoleList);
	}

}
