package ru.miacn.fias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class FiasEditor implements Serializable {
	private static final long serialVersionUID = 5089683425177113868L;

	@PersistenceContext(unitName = "Fias-PU")
	private EntityManager em;
	
	private FiasElement region;
	private List<FiasElement> regionList;
	private FiasConverter regionConverter;
	private FiasElement gorod;
	private List<FiasElement> gorodList;
	private FiasConverter gorodConverter;
	private FiasElement ulica;
	private List<FiasElement> ulicaList;
	private FiasConverter ulicaConverter;
	private String dom;
	private String korp;
	private String str;
	private String kv;

	@PostConstruct
	private void init() {
		setRegionConverter(new FiasConverter());
		setGorodConverter(new FiasConverter());
		setUlicaConverter(new FiasConverter());
	}

	public List<FiasElement> filterRegion(String cond) {
		TypedQuery<FiasElement> q = em.createQuery("SELECT r FROM " + FiasElement.class.getName() + " r WHERE (LOWER(r.formalname) LIKE ?1) AND (r.aolevel = 1) AND (r.actstatus = 1) ORDER BY r.formalname", FiasElement.class);
		
		cond = cond.replaceAll("^\\s+", "");
		
		if (!cond.isEmpty()) {	
			q.setParameter(1, cond.toLowerCase() + "%");
			setRegionList(q.getResultList());
		}
		
		return getRegionList();
	}
	
	public List<FiasElement> filterGorod(String cond) {
		if ((getRegion() != null) && (!getRegion().getAoid().isEmpty())) {
			TypedQuery<FiasElement> q = em.createQuery("SELECT r FROM " + FiasElement.class.getName() + " r WHERE (LOWER(r.formalname) LIKE ?1) AND (r.regioncode = ?2) AND (r.aolevel BETWEEN 4 AND 6) AND (r.actstatus = 1) ORDER BY r.formalname", FiasElement.class);
			
			cond = cond.replaceAll("^\\s+", "");
			
			if (!cond.isEmpty()) {
				q.setParameter(1, cond.toLowerCase() + "%");
				q.setParameter(2, getRegion().getRegioncode());
				setGorodList(q.getResultList());
			}
			
		} else {
			setGorodList(new ArrayList<FiasElement>());
		}
		
		return getGorodList();
	}
	
	public List<FiasElement> filterUlica(String cond) {
		if ((getRegion() != null) && (!getRegion().getAoid().isEmpty()) &&
				(getGorod() != null) && (!getGorod().getAoid().isEmpty())) {
			TypedQuery<FiasElement> q = em.createQuery("SELECT r FROM " + FiasElement.class.getName() + " r WHERE (LOWER(r.formalname) LIKE ?1) AND (r.regioncode = ?2) AND (r.citycode = ?3) AND (r.placecode = ?4) AND (r.aolevel = 7) AND (r.actstatus = 1) ORDER BY r.formalname", FiasElement.class);
			
			cond = cond.replaceAll("^\\s+", "");
			
			if (!cond.isEmpty()) {
				q.setParameter(1, cond.toLowerCase() + "%");
				q.setParameter(2, getRegion().getRegioncode());
				q.setParameter(3, getGorod().getCitycode());
				q.setParameter(4, getGorod().getPlacecode());
				setUlicaList(q.getResultList());
			}
			
		} else {
			setUlicaList(new ArrayList<FiasElement>());
		}
		
		return getUlicaList();
	}
	
	public FiasElement getElementById(UUID id) {
		return em.find(FiasElement.class, id.toString());
	}
	
	public String getAddress() {
		String addr = "";
		
		if ((getRegion() != null) && (!getRegion().getAoid().isEmpty()))
			addr += getRegion().getFormalname() + " " + getRegion().getShortname() + ", ";
		else if ((getRegion() != null) && (getRegion().getFormalname() != null) && (!getRegion().getFormalname().isEmpty()))
			addr += getRegion().getFormalname() + ", ";
		if ((getGorod() != null) && (!getGorod().getAoid().isEmpty()))
			addr += getGorod().getShortname() + " " + getGorod().getFormalname() + ", ";
		else if ((getGorod() != null) && (getGorod().getFormalname() != null) && (!getGorod().getFormalname().isEmpty()))
			addr += getGorod().getFormalname() + ", ";
		if ((getUlica() != null) && (!getUlica().getAoid().isEmpty()))
			addr += getUlica().getShortname() + " " + getUlica().getFormalname() + ", ";
		else if ((getUlica() != null) && (getUlica().getFormalname() != null) && (!getUlica().getFormalname().isEmpty()))
			addr += getUlica().getFormalname() + ", ";
		if ((getDom() != null) && (!getDom().isEmpty()))
			addr += "дом " + getDom() + ", ";
		if ((getKorp() != null) && (!getKorp().isEmpty()))
			addr += "корп " + getKorp() + ", ";
		if ((getKv() != null) && (!getKv().isEmpty()))
			addr += "кв " + getKv() + ", ";
		
		if (addr.length() > 0)
			addr = addr.substring(0, addr.length() - 2);
		
		return addr;
	}
	
	public FiasElement getRegion() {
		return region;
	}

	public void setRegion(FiasElement region) {
		regionConverter.setElem(region);
		this.region = regionConverter.getElem();
	}

	public List<FiasElement> getRegionList() {
		return regionList;
	}

	public void setRegionList(List<FiasElement> regionList) {
		regionConverter.setList(regionList);
		this.regionList = regionList;
	}

	public FiasConverter getRegionConverter() {
		return regionConverter;
	}

	public void setRegionConverter(FiasConverter regionConverter) {
		this.regionConverter = regionConverter;
	}

	public FiasElement getGorod() {
		return gorod;
	}

	public void setGorod(FiasElement gorod) {
		gorodConverter.setElem(gorod);
		this.gorod = gorodConverter.getElem();
	}

	public List<FiasElement> getGorodList() {
		return gorodList;
	}

	public void setGorodList(List<FiasElement> gorodList) {
		gorodConverter.setList(gorodList);
		this.gorodList = gorodList;
	}

	public FiasConverter getGorodConverter() {
		return gorodConverter;
	}

	public void setGorodConverter(FiasConverter gorodConverter) {
		this.gorodConverter = gorodConverter;
	}

	public FiasElement getUlica() {
		return ulica;
	}

	public void setUlica(FiasElement ulica) {
		ulicaConverter.setElem(ulica);
		this.ulica = ulicaConverter.getElem();
	}

	public List<FiasElement> getUlicaList() {
		return ulicaList;
	}

	public void setUlicaList(List<FiasElement> ulicaList) {
		ulicaConverter.setList(ulicaList);
		this.ulicaList = ulicaList;
	}

	public FiasConverter getUlicaConverter() {
		return ulicaConverter;
	}

	public void setUlicaConverter(FiasConverter ulicaConverter) {
		this.ulicaConverter = ulicaConverter;
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

	public String getKv() {
		return kv;
	}

	public void setKv(String kv) {
		this.kv = kv;
	}
}
