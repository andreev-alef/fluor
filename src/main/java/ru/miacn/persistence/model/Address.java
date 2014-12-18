package ru.miacn.persistence.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

//TODO Убрать зависимость от hibernate как только java-тип uuid начнет нормально маппиться по дефолту
@Embeddable
public class Address {
	@Column(name = "liv_building")
	private String livBuilding;
	@Type(type = "pg-uuid")
	@Column(name = "liv_building_id")
	private UUID livBuildingId;
	@Column(name = "liv_city")
	private String livCity;
	@Column(name = "liv_city_id")
	private UUID livCityId;
	@Column(name = "liv_facility")
	private String livFacility;
	@Column(name = "liv_facility_id")
	private UUID livFacilityId;
	@Column(name = "liv_flat")
	private String livFlat;
	@Column(name = "liv_flat_id")
	private UUID livFlatId;
	@Column(name = "liv_house")
	private String livHouse;
	@Column(name = "liv_house_id")
	private UUID livHouseId;
	@Column(name = "liv_reg")
	private String livReg;
	@Column(name = "liv_reg_id")
	private UUID livRegId;
	@Column(name = "liv_street")
	private String livStreet;
	@Column(name = "liv_street_id")
	private UUID livStreetId;

	public String getLivBuilding() {
		return this.livBuilding;
	}

	public void setLivBuilding(String livBuilding) {
		this.livBuilding = livBuilding;
	}

	public UUID getLivBuildingId() {
		return this.livBuildingId;
	}

	public void setLivBuildingId(UUID livBuildingId) {
		this.livBuildingId = livBuildingId;
	}

	public String getLivCity() {
		return this.livCity;
	}

	public void setLivCity(String livCity) {
		this.livCity = livCity;
	}

	public UUID getLivCityId() {
		return this.livCityId;
	}

	public void setLivCityId(UUID livCityId) {
		this.livCityId = livCityId;
	}

	public String getLivFacility() {
		return this.livFacility;
	}

	public void setLivFacility(String livFacility) {
		this.livFacility = livFacility;
	}

	public UUID getLivFacilityId() {
		return this.livFacilityId;
	}

	public void setLivFacilityId(UUID livFacilityId) {
		this.livFacilityId = livFacilityId;
	}

	public String getLivFlat() {
		return this.livFlat;
	}

	public void setLivFlat(String livFlat) {
		this.livFlat = livFlat;
	}

	public UUID getLivFlatId() {
		return this.livFlatId;
	}

	public void setLivFlatId(UUID livFlatId) {
		this.livFlatId = livFlatId;
	}

	public String getLivHouse() {
		return this.livHouse;
	}

	public void setLivHouse(String livHouse) {
		this.livHouse = livHouse;
	}

	public UUID getLivHouseId() {
		return this.livHouseId;
	}

	public void setLivHouseId(UUID livHouseId) {
		this.livHouseId = livHouseId;
	}

	public String getLivReg() {
		return this.livReg;
	}

	public void setLivReg(String livReg) {
		this.livReg = livReg;
	}

	public UUID getLivRegId() {
		return this.livRegId;
	}

	public void setLivRegId(UUID livRegId) {
		this.livRegId = livRegId;
	}

	public String getLivStreet() {
		return this.livStreet;
	}

	public void setLivStreet(String livStreet) {
		this.livStreet = livStreet;
	}

	public UUID getLivStreetId() {
		return this.livStreetId;
	}

	public void setLivStreetId(UUID livStreetId) {
		this.livStreetId = livStreetId;
	}
}
