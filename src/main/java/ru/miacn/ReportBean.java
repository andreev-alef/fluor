package ru.miacn;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.miacn.persistence.reference.ListConverter;
import ru.miacn.persistence.reference.RMedicalOrgMain;
import ru.miacn.persistence.reference.RMedicalOrgPoliclinic;
import ru.miacn.persistence.reference.RMedicalOrgRegion;
import ru.miacn.persistence.reference.RMedicalOrgTer;

@Named
@SessionScoped
public class ReportBean  implements Serializable{
	private static final long serialVersionUID = 3234666418891232049L;

	@PersistenceContext(unitName = "fluor-PU")
	private EntityManager em;

	private ListConverter morConverter;
	private List<RMedicalOrgRegion> moRegionList;
	private RMedicalOrgRegion selectedMor;

	private ListConverter motConverter;
	private List<RMedicalOrgTer> moTerList;
	private RMedicalOrgTer selectedMot;

	private ListConverter momConverter;
	private List<RMedicalOrgMain> moMainList;
	private RMedicalOrgMain selectedMom;
	
	private ListConverter mopConverter;
	private List<RMedicalOrgPoliclinic> moPoliclinicList;
	private RMedicalOrgPoliclinic selectedMop;
	private Date datStart;
	private Date datEnd;
	
	@PostConstruct
	private void init() {
		setMorConverter(new ListConverter());
		setMotConverter(new ListConverter());
		setMomConverter(new ListConverter());
		setMopConverter(new ListConverter());
		setSelectedMor(null);

		setMoRegionList(em.createQuery("SELECT r FROM " + RMedicalOrgRegion.class.getName() + " r ORDER BY r.id", RMedicalOrgRegion.class).getResultList());
	}

	public void clearFilter(){
		setDatStart(null);
		setDatEnd(null);
		setMoTerList(new ArrayList<RMedicalOrgTer>());
		setMoMainList(new ArrayList<RMedicalOrgMain>());
		setMoPoliclinicList(new ArrayList<RMedicalOrgPoliclinic>());
		setSelectedMor(null);
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
//		setSelectedMot(null);
//		setSelectedMom(null);
//		setSelectedMop(null);
//		setMoMainList(new ArrayList<RMedicalOrgMain>());
//		setMoPoliclinicList(new ArrayList<RMedicalOrgPoliclinic>());
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
//		setSelectedMom(null);
//		setSelectedMop(null);
//		setMoPoliclinicList(new ArrayList<RMedicalOrgPoliclinic>());
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
//		setSelectedMop(null);
	}
	
	public void printReport(int id, boolean pdf) throws IOException, ServletException {
		if (!checkReportParams())
			return;
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext ec = ctx.getExternalContext();
		HttpServletRequest req = (HttpServletRequest) ec.getRequest();
		HttpSession ses = req.getSession();
		
		ses.setAttribute("id", id);
		ses.setAttribute("pdf", pdf);
		ses.setAttribute("morId", getSelectedMor().getRegId());
		ses.setAttribute("motId", getSelectedMot().getId().getTerId());
		if (getSelectedMom() != null)
			ses.setAttribute("momId", getSelectedMom().getId().getLpuId());
		else
			ses.removeAttribute("momId");
		ses.setAttribute("datStart", getDatStart());
		ses.setAttribute("datEnd", getDatEnd());
		ec.redirect("report");
	}
	
	private boolean checkReportParams() {
		String errorText = "";
		
		if ((getSelectedMor() == null) || (getSelectedMot() == null))
			errorText = "Не выбран регион и/или населенный пункт.";
		else if ((getDatStart() == null) || (getDatEnd() == null))
			errorText = "Не указан период формирования отчета.";
		else if (getDatEnd().before(getDatStart()))
			errorText = "Конец периода формирования отчета не может быть раньше начала.";
		else {
			Calendar calStart = Calendar.getInstance();
			Calendar calEnd = Calendar.getInstance();
			
			calStart.setTime(getDatStart());
			calEnd.setTime(getDatEnd());
			
			if (calStart.get(Calendar.YEAR) != calEnd.get(Calendar.YEAR))
				errorText = "Период формирования отчета должен укладываться в один год.";
		}
		
		if (!errorText.isEmpty()) {
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorText, null);
			
			fc.addMessage(null, msg);
			
			return false;
		} else {
			return true;
		}
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

	public List<RMedicalOrgTer> getMoTerList() {
		return moTerList;
	}

	public void setMoTerList(List<RMedicalOrgTer> moTerList) {
		this.moTerList = moTerList;
		motConverter.setList(moTerList);
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

	public List<RMedicalOrgMain> getMoMainList() {
		return moMainList;
	}

	public void setMoMainList(List<RMedicalOrgMain> moMainList) {
		this.moMainList = moMainList;
		momConverter.setList(moMainList);
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
}
