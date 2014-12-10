package ru.miacn;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ru.miacn.persistence.model.Patient;


@Named
@SessionScoped
public class ListmanBean implements Serializable {
	public static final long serialVersionUID = -229673017810787765L;
	private String userFam = "inЛист";
	private String userIm = "inБин";
	private String userOt = "inЖава";
	private Date userDr;
	private String firstName = "Лист";
    private String midlName = "Бин";
    private String lastName = "Жава";
    private Date datBirthay = new Date();
    private String sex = "М";
    private String nasp;
    private String street;
	private List<Patient> patientList;

    @PostConstruct
    public void init() {
    	setFirstName("Иванов");
    	setMidlName("Петр");
    	setLastName("Семенович");
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

	public Date getDatBirthay() {
		return datBirthay;
	}

	public void setDatBirthay(Date datBirthay) {
		this.datBirthay = datBirthay;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNasp() {
		return nasp;
	}

	public void setNasp(String nasp) {
		this.nasp = nasp;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserFam() {
		return userFam;
	}

	public void setUserFam(String userFam) {
		this.userFam = userFam;
	}

	public String getUserIm() {
		return userIm;
	}

	public void setUserIm(String userIm) {
		this.userIm = userIm;
	}

	public String getUserOt() {
		return userOt;
	}

	public void setUserOt(String userOt) {
		this.userOt = userOt;
	}

	public Date getUserDr() {
		return userDr;
	}

	public void setUserDr(Date userDr) {
		this.userDr = userDr;
	}

}

