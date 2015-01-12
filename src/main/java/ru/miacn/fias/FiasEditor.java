package ru.miacn.fias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named
@SessionScoped
public class FiasEditor implements Serializable {
	@PersistenceContext(unitName = "FiasPU")
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
		TypedQuery<FiasElement> q = em.createQuery("SELECT r FROM " + FiasElement.class.getName() + " r WHERE (r.formalname LIKE ?1) AND (r.aolevel = 1) AND (r.actstatus = 1) ORDER BY r.formalname", FiasElement.class);
		
		q.setParameter(1, cond + "%");
		setRegionList(q.getResultList());
		
		return getRegionList();
	}
	
	public List<FiasElement> filterGorod(String cond) {
		if ((getRegion() != null) && (!getRegion().getAoid().isEmpty())) {
			TypedQuery<FiasElement> q = em.createQuery("SELECT r FROM " + FiasElement.class.getName() + " r WHERE (r.formalname LIKE ?1) AND (r.regioncode = ?2) AND (r.aolevel BETWEEN 4 AND 6) AND (r.actstatus = 1) ORDER BY r.formalname", FiasElement.class);
			
			q.setParameter(1, cond + "%");
			q.setParameter(2, getRegion().getRegioncode());
			setGorodList(q.getResultList());
		} else {
			setGorodList(new ArrayList<FiasElement>());
		}
		
		return getGorodList();
	}
	
	public List<FiasElement> filterUlica(String cond) {
		if ((getRegion() != null) && (!getRegion().getAoid().isEmpty()) &&
				(getGorod() != null) && (!getGorod().getAoid().isEmpty())) {
			TypedQuery<FiasElement> q = em.createQuery("SELECT r FROM " + FiasElement.class.getName() + " r WHERE (r.formalname LIKE ?1) AND (r.regioncode = ?2) AND (r.citycode = ?3) AND (r.placecode = ?4) AND (r.aolevel = 7) AND (r.actstatus = 1) ORDER BY r.formalname", FiasElement.class);
			
			q.setParameter(1, cond + "%");
			q.setParameter(2, getRegion().getRegioncode());
			q.setParameter(3, getGorod().getCitycode());
			q.setParameter(4, getGorod().getPlacecode());
			setUlicaList(q.getResultList());
		} else {
			setUlicaList(new ArrayList<FiasElement>());
		}
		
		return getUlicaList();
	}
	
	public FiasElement getElementById(UUID id) {
		return em.find(FiasElement.class, id.toString());
	}
	
	public FiasElement getRegion() {
		return region;
	}

	public void setRegion(FiasElement region) {
		regionConverter.setElem(region);
		this.region = region;
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
		this.gorod = gorod;
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
		this.ulica = ulica;
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
