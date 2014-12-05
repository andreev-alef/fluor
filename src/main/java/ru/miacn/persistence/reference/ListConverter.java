package ru.miacn.persistence.reference;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ListConverter implements Converter {
	private List<?> list;
	
	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if ((value != null) && !value.isEmpty() && (getList() != null)) {
			for (Object r : getList()) {
				if (r.toString().equals(value))
					return r;
			}
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
