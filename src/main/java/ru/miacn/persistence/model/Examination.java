package ru.miacn.persistence.model;

import javax.persistence.*;

import ru.miacn.persistence.reference.RExamMethod;
import ru.miacn.persistence.reference.RExamType;
import ru.miacn.persistence.reference.RMedicalOrgMain;
import ru.miacn.persistence.reference.RMedicalOrgPoliclinic;
import ru.miacn.persistence.reference.RMedicalOrgRegion;
import ru.miacn.persistence.reference.RMedicalOrgTer;
import ru.miacn.persistence.reference.RResultType;

import java.util.Date;

@Entity
@NamedQuery(name="Examination.findAll", query="SELECT e FROM Examination e")
public class Examination  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date dat;

	@Column(name="follow_up")
	private Boolean followUp;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	private Patient patient;

	//bi-directional many-to-one association to RExamMethod
	@ManyToOne
	@JoinColumn(name="method_id")
	private RExamMethod rExamMethod;

	//bi-directional many-to-one association to RExamType
	@ManyToOne
	@JoinColumn(name="type_id")
	private RExamType rExamType;

	//bi-directional many-to-one association to RMedicalOrgMain
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="med_city_id", referencedColumnName="ter_id", insertable = false, updatable = false),
		@JoinColumn(name="med_lpu_id", referencedColumnName="lpu_id", insertable = false, updatable = false),
		@JoinColumn(name="med_reg_id", referencedColumnName="reg_id", insertable = false, updatable = false)
		})
	private RMedicalOrgMain rMedicalOrgMain;

	//bi-directional many-to-one association to RMedicalOrgPoliclinic
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="med_city_id", referencedColumnName="ter_id", insertable = false, updatable = false),
		@JoinColumn(name="med_lpu_id", referencedColumnName="lpu_id", insertable = false, updatable = false),
		@JoinColumn(name="med_pol_id", referencedColumnName="pol_id", insertable = false, updatable = false),
		@JoinColumn(name="med_reg_id", referencedColumnName="reg_id", insertable = false, updatable = false)
		})
	private RMedicalOrgPoliclinic rMedicalOrgPoliclinic;

	//bi-directional many-to-one association to RMedicalOrgRegion
	@ManyToOne
	@JoinColumn(name="med_reg_id", insertable = false, updatable = false)
	private RMedicalOrgRegion rMedicalOrgRegion;

	//bi-directional many-to-one association to RMedicalOrgTer
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="med_city_id", referencedColumnName="ter_id", insertable = false, updatable = false),
		@JoinColumn(name="med_reg_id", referencedColumnName="reg_id", insertable = false, updatable = false)
		})
	private RMedicalOrgTer rMedicalOrgTer;

	//bi-directional many-to-one association to RResultType
	@ManyToOne
	@JoinColumn(name="result_id")
	private RResultType rResultType;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDat() {
		return this.dat;
	}

	public void setDat(Date dat) {
		this.dat = dat;
	}

	public Boolean getFollowUp() {
		return this.followUp;
	}

	public void setFollowUp(Boolean followUp) {
		this.followUp = followUp;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public RExamMethod getRExamMethod() {
		return this.rExamMethod;
	}

	public void setRExamMethod(RExamMethod rExamMethod) {
		this.rExamMethod = rExamMethod;
	}

	public RExamType getRExamType() {
		return this.rExamType;
	}

	public void setRExamType(RExamType rExamType) {
		this.rExamType = rExamType;
	}

	public RMedicalOrgMain getRMedicalOrgMain() {
		return this.rMedicalOrgMain;
	}

	public void setRMedicalOrgMain(RMedicalOrgMain rMedicalOrgMain) {
		this.rMedicalOrgMain = rMedicalOrgMain;
	}

	public RMedicalOrgPoliclinic getRMedicalOrgPoliclinic() {
		return this.rMedicalOrgPoliclinic;
	}

	public void setRMedicalOrgPoliclinic(RMedicalOrgPoliclinic rMedicalOrgPoliclinic) {
		this.rMedicalOrgPoliclinic = rMedicalOrgPoliclinic;
	}

	public RMedicalOrgRegion getRMedicalOrgRegion() {
		return this.rMedicalOrgRegion;
	}

	public void setRMedicalOrgRegion(RMedicalOrgRegion rMedicalOrgRegion) {
		this.rMedicalOrgRegion = rMedicalOrgRegion;
	}

	public RMedicalOrgTer getRMedicalOrgTer() {
		return this.rMedicalOrgTer;
	}

	public void setRMedicalOrgTer(RMedicalOrgTer rMedicalOrgTer) {
		this.rMedicalOrgTer = rMedicalOrgTer;
	}

	public RResultType getRResultType() {
		return this.rResultType;
	}

	public void setRResultType(RResultType rResultType) {
		this.rResultType = rResultType;
	}

}