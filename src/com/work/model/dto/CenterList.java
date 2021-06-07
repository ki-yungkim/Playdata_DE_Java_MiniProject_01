package com.work.model.dto;

public class CenterList {

	private String centerName;
	
	private String facilityName;
	
	private String postcode;
	
	private String address;

	private String addressDetail;
	
	private String phoneNumber;
	
	/**
	 * 초기 생성자 
	 */
	public CenterList() {
	}

	
	/**
	 * 필수 생성자 
	 * @param centerName
	 * @param facilityName
	 * @param postcode
	 * @param address
	 * @param addressDetail
	 * @param phoneNumber
	 */
	public CenterList(String centerName, String facilityName, String postcode, String address, String addressDetail,
			String phoneNumber) {
		this.centerName = centerName;
		this.facilityName = facilityName;
		this.postcode = postcode;
		this.address = address;
		this.addressDetail = addressDetail;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the centerName
	 */
	public String getCenterName() {
		return centerName;
	}

	/**
	 * @param centerName the centerName to set
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
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
	 * @return the addressDetail
	 */
	public String getAddressDetail() {
		return addressDetail;
	}

	/**
	 * @param addressDetail the addressDetail to set
	 */
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
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

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((addressDetail == null) ? 0 : addressDetail.hashCode());
		result = prime * result + ((centerName == null) ? 0 : centerName.hashCode());
		result = prime * result + ((facilityName == null) ? 0 : facilityName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
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
		CenterList other = (CenterList) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (addressDetail == null) {
			if (other.addressDetail != null)
				return false;
		} else if (!addressDetail.equals(other.addressDetail))
			return false;
		if (centerName == null) {
			if (other.centerName != null)
				return false;
		} else if (!centerName.equals(other.centerName))
			return false;
		if (facilityName == null) {
			if (other.facilityName != null)
				return false;
		} else if (!facilityName.equals(other.facilityName))
			return false;
		
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(centerName);
		builder.append(", ");
		builder.append(facilityName);
		builder.append(", ");
		builder.append(postcode);
		builder.append(", ");
		builder.append(address);
		builder.append(", ");
		builder.append(addressDetail);
		builder.append(", ");
		builder.append(phoneNumber);
		builder.append(", ");
		
		return builder.toString();
	}
	

}
