package ru.miacn.fias;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class FiasConverter implements Converter {
	private List<FiasElement> list;
	private FiasElement elem;
	
	public List<?> getList() {
		return list;
	}

	public void setList(List<FiasElement> list) {
		this.list = list;
	}

	public FiasElement getElem() {
		return elem;
	}

	public void setElem(FiasElement elem) {
		if (elem != null)
			this.elem = elem;
		else
			this.elem = new FiasElement("", "");
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if ((value != null) && !value.isEmpty() && (getList() != null)) {
			for (Object r : getList()) {
				if (r.toString().equals(value))
					return r;
			}
		}
		if ((value != null) && !value.isEmpty()) {
			if ((getElem() != null) && (getElem().toString().equals(value)))
				return getElem();
			else
				return new FiasElement("", value);
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null)
			return value.toString();
		else
			return null;
	}
}
