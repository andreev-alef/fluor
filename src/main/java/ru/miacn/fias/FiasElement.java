package ru.miacn.fias;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addrobj")
public class FiasElement {
	@Id
	private String aoid;
	private int aolevel;
	private String regioncode;
	private String autocode;
	private String areacode;
	private String citycode;
	private String ctarcode;
	private String placecode;
	private String streetcode;
	private String formalname;
	private int actstatus;

	public FiasElement() {
		
	}
	
	public FiasElement(String aoid, String formalname) {
		setAoid(aoid);
		setFormalname(formalname);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FiasElement)
			return ((FiasElement) obj).getAoid().equals(getAoid());
		else
			return false;
	}

	public String getAoid() {
		return aoid;
	}

	public void setAoid(String aoid) {
		this.aoid = aoid;
	}

	public int getAolevel() {
		return aolevel;
	}

	public void setAolevel(int aolevel) {
		this.aolevel = aolevel;
	}

	public String getRegioncode() {
		return regioncode;
	}

	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}

	public String getAutocode() {
		return autocode;
	}

	public void setAutocode(String autocode) {
		this.autocode = autocode;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCtarcode() {
		return ctarcode;
	}

	public void setCtarcode(String ctarcode) {
		this.ctarcode = ctarcode;
	}

	public String getPlacecode() {
		return placecode;
	}

	public void setPlacecode(String placecode) {
		this.placecode = placecode;
	}

	public String getStreetcode() {
		return streetcode;
	}

	public void setStreetcode(String streetcode) {
		this.streetcode = streetcode;
	}

	public String getFormalname() {
		return formalname;
	}

	public void setFormalname(String formalname) {
		this.formalname = formalname;
	}

	public int getActstatus() {
		return actstatus;
	}

	public void setActstatus(int actstatus) {
		this.actstatus = actstatus;
	}
}
