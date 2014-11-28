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
	public static final long serialVersionUID = -229673017810787765L;
    private String email;
    private String serviceLevel = "medium";
	private String firstName = "Фамилия";
    private String midlName = "Имя";
    private String lastName = "Отчество";
    private Date datBirthay = new Date();
    private String Region = "Кемеровская область";
    private String Nasp;
    private String Street;
    private String Dom;
    private String Korp;
    private String Str;
    private String Flat;
    private String Telefon;
    private int Jitel;
    private String Sex = "Unknown";
    private String Nationality = "Росия";
    private boolean selUmer;
    private boolean selMO;
    private String NaspMO;
    private String AdresMO;
    private String NameMO;
    private String ClinicMO;
    private int DekrGrp;
    private int MedGrp;
    private int RiskGrp;
    
    public EditmanBean() {}

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
		return Telefon;
	}

	public void setTelefon(String telefon) {
		Telefon = telefon;
	}

	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}

	public String getNasp() {
		return Nasp;
	}

	public void setNasp(String nasp) {
		Nasp = nasp;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getDom() {
		return Dom;
	}

	public void setDom(String dom) {
		Dom = dom;
	}

	public String getKorp() {
		return Korp;
	}

	public void setKorp(String korp) {
		Korp = korp;
	}

	public String getStr() {
		return Str;
	}

	public void setStr(String str) {
		Str = str;
	}

	public String getFlat() {
		return Flat;
	}

	public void setFlat(String flat) {
		Flat = flat;
	}

	public int getJitel() {
		return Jitel;
	}

	public void setJitel(int jitel) {
		Jitel = jitel;
	}

	public String getNationality() {
		return Nationality;
	}

	public void setNationality(String nationality) {
		Nationality = nationality;
	}

	public boolean isSelMO() {
		return selMO;
	}

	public void setSelMO(boolean selMO) {
		this.selMO = selMO;
	}

	public String getNaspMO() {
		return NaspMO;
	}

	public void setNaspMO(String naspMO) {
		NaspMO = naspMO;
	}

	public String getAdresMO() {
		return AdresMO;
	}

	public void setAdresMO(String adresMO) {
		AdresMO = adresMO;
	}

	public String getNameMO() {
		return NameMO;
	}

	public void setNameMO(String nameMO) {
		NameMO = nameMO;
	}

	public String getClinicMO() {
		return ClinicMO;
	}

	public void setClinicMO(String clinicMO) {
		ClinicMO = clinicMO;
	}

	public int getDekrGrp() {
		return DekrGrp;
	}

	public void setDekrGrp(int dekrGrp) {
		DekrGrp = dekrGrp;
	}

	public int getMedGrp() {
		return MedGrp;
	}

	public void setMedGrp(int medGrp) {
		MedGrp = medGrp;
	}

	public int getRiskGrp() {
		return RiskGrp;
	}

	public void setRiskGrp(int riskGrp) {
		RiskGrp = riskGrp;
	}
}

