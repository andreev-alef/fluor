package ru.miacn;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@SessionScoped
public class EditmanBean implements Serializable {
    protected String email;
    protected String serviceLevel = "medium";
	private static final long serialVersionUID = -229673017810787765L;
	protected String firstName = "Фамилия";
    protected String midlName = "Имя";
    protected String lastName = "Отчество";
    protected Date datBirthay = new Date();
    protected String Region = "Кемеровская область";
    protected String Nasp;
    protected String Street;
    protected String Dom;
    protected String Korp;
    protected String Str;
    protected String Flat;
    protected String Telefon;
    protected int Jitel;
    protected String Sex = "Unknown";
    protected String Nationality = "Росия";
    protected boolean selUmer;
    protected boolean selMO;
    protected String NaspMO;
    protected String AdresMO;
    protected String NameMO;
    protected String ClinicMO;
    protected int DekrGrp;
    protected int MedGrp;
    protected int RiskGrp;
    
    public EditmanBean() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMidlName() {
        return lastName;
    }

    public void setMidlName(String midlName) {
        this.midlName = midlName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        this.Sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }
    
    public void validateEmail(FacesContext context, UIComponent toValidate,
            Object value) throws ValidatorException {
        String emailStr = (String) value;
        if (-1 == emailStr.indexOf("@")) {
            FacesMessage message = new FacesMessage("Invalid email address");
            throw new ValidatorException(message);
        }
    }

    public String addConfirmedUser() {
        // This method would call a database or other service and add the 
        // confirmed user information.
        // For now, we just place an informative message in request scope
        FacesMessage doneMessage = 
                new FacesMessage("Successfully added new user");
        FacesContext.getCurrentInstance().addMessage(null, doneMessage);
        return "done";
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
}

