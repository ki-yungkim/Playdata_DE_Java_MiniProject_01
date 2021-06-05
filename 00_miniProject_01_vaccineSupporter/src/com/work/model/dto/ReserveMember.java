package com.work.model.dto;

public class ReserveMember {

	private String name;
	
	private String phoneNumber;
	
	private String address;
	
	private String idNumber;
	
	private String vaccineName;
	
	private String firstVaccination;
	
	private String secondVaccDay;
	
	private String callDay;

	/**
	 * 
	 */
	public ReserveMember() {
	}

	/**
	 * @param name
	 * @param phoneNumber
	 * @param address
	 * @param idNumber
	 * @param vaccine
	 * @param firstVaccination
	 */
	public ReserveMember(String name, String phoneNumber, String address, String idNumber, String vaccine,
			String firstVaccination) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.idNumber = idNumber;
		this.vaccineName = vaccine;
		this.firstVaccination = firstVaccination;
	}

	/**
	 * @param name
	 * @param phoneNumber
	 * @param address
	 * @param idNumber
	 * @param vaccine
	 * @param firstVaccination
	 * @param secondVaccinationDay
	 * @param callDay
	 */
	public ReserveMember(String name, String phoneNumber, String address, String idNumber, String vaccine,
			String firstVaccination, String secondVaccinationDay, String callDay) {
		this(name,phoneNumber, address, idNumber, vaccine, firstVaccination);
		this.secondVaccDay = secondVaccinationDay;
		this.callDay = callDay;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return the vaccine
	 */
	public String getVaccine() {
		return vaccineName;
	}

	/**
	 * @param vaccine the vaccine to set
	 */
	public void setVaccine(String vaccine) {
		this.vaccineName = vaccine;
	}

	/**
	 * @return the firstVaccination
	 */
	public String getFirstVaccination() {
		return firstVaccination;
	}

	/**
	 * @param firstVaccination the firstVaccination to set
	 */
	public void setFirstVaccination(String firstVaccination) {
		this.firstVaccination = firstVaccination;
	}

	/**
	 * @return the secondVaccinationDay
	 */
	public String getSecondVaccinationDay() {
		return secondVaccDay;
	}

	/**
	 * @param secondVaccinationDay the secondVaccinationDay to set
	 */
	public void setSecondVaccinationDay(String secondVaccinationDay) {
		this.secondVaccDay = secondVaccinationDay;
	}

	/**
	 * @return the callDay
	 */
	public String getCallDay() {
		return callDay;
	}

	/**
	 * @param callDay the callDay to set
	 */
	public void setCallDay(String callDay) {
		this.callDay = callDay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((callDay == null) ? 0 : callDay.hashCode());
		result = prime * result + ((firstVaccination == null) ? 0 : firstVaccination.hashCode());
		result = prime * result + ((idNumber == null) ? 0 : idNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((secondVaccDay == null) ? 0 : secondVaccDay.hashCode());
		result = prime * result + ((vaccineName == null) ? 0 : vaccineName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReserveMember other = (ReserveMember) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (callDay == null) {
			if (other.callDay != null)
				return false;
		} else if (!callDay.equals(other.callDay))
			return false;
		if (firstVaccination == null) {
			if (other.firstVaccination != null)
				return false;
		} else if (!firstVaccination.equals(other.firstVaccination))
			return false;
		if (idNumber == null) {
			if (other.idNumber != null)
				return false;
		} else if (!idNumber.equals(other.idNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (secondVaccDay == null) {
			if (other.secondVaccDay != null)
				return false;
		} else if (!secondVaccDay.equals(other.secondVaccDay))
			return false;
		if (vaccineName == null) {
			if (other.vaccineName != null)
				return false;
		} else if (!vaccineName.equals(other.vaccineName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(", ");
		builder.append(phoneNumber);
		builder.append(", ");
		builder.append(address);
		builder.append(", ");
		builder.append(idNumber);
		builder.append(", ");
		builder.append(vaccineName);
		builder.append(", ");
		builder.append(firstVaccination);
		builder.append(", ");
		builder.append(secondVaccDay);
		builder.append(", ");
		builder.append(callDay);
		return builder.toString();
	}
	
	
	
}
