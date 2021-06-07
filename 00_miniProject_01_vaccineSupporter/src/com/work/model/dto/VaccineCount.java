package com.work.model.dto;

public class VaccineCount {

	private int idKey;
	
	private String day;
	
	private String region;
	
	private int yesterdayFirst;

	private int yesterdaySecond;

	private int totalFirst;

	private int totalSecond;



	public VaccineCount() {
	}



	/**
	 * @param idKey
	 * @param day
	 * @param region
	 * @param yesterdayFirst
	 * @param yesterdaySecond
	 * @param totalFirst
	 * @param totalSecond
	 */
	public VaccineCount(int idKey, String day, String region, int yesterdayFirst, int yesterdaySecond, int totalFirst,
			int totalSecond) {
		this.idKey = idKey;
		this.day = day;
		this.region = region;
		this.yesterdayFirst = yesterdayFirst;
		this.yesterdaySecond = yesterdaySecond;
		this.totalFirst = totalFirst;
		this.totalSecond = totalSecond;
	}



	
	
	/**
	 * @return the idKey
	 */
	public int getIdKey() {
		return idKey;
	}



	/**
	 * @param idKey the idKey to set
	 */
	public void setIdKey(int idKey) {
		this.idKey = idKey;
	}



	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}



	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}



	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}



	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}



	/**
	 * @return the yesterdayFirst
	 */
	public int getYesterdayFirst() {
		return yesterdayFirst;
	}



	/**
	 * @param yesterdayFirst the yesterdayFirst to set
	 */
	public void setYesterdayFirst(int yesterdayFirst) {
		this.yesterdayFirst = yesterdayFirst;
	}



	/**
	 * @return the yesterdaySecond
	 */
	public int getYesterdaySecond() {
		return yesterdaySecond;
	}



	/**
	 * @param yesterdaySecond the yesterdaySecond to set
	 */
	public void setYesterdaySecond(int yesterdaySecond) {
		this.yesterdaySecond = yesterdaySecond;
	}



	/**
	 * @return the totalFirst
	 */
	public int getTotalFirst() {
		return totalFirst;
	}



	/**
	 * @param totalFirst the totalFirst to set
	 */
	public void setTotalFirst(int totalFirst) {
		this.totalFirst = totalFirst;
	}



	/**
	 * @return the totalSecond
	 */
	public int getTotalSecond() {
		return totalSecond;
	}



	/**
	 * @param totalSecond the totalSecond to set
	 */
	public void setTotalSecond(int totalSecond) {
		this.totalSecond = totalSecond;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
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
		VaccineCount other = (VaccineCount) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		return true;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(day);
		builder.append(", ");
		builder.append(region);
		builder.append(", ");
		builder.append(yesterdayFirst);
		builder.append(", ");
		builder.append(yesterdaySecond);
		builder.append(", ");
		builder.append(totalFirst);
		builder.append(", ");
		builder.append(totalSecond);
		return builder.toString();
	}

	





	
}

