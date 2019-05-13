package com.revature.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "caves")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Cave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "cubic_meters_volume")
	private double cubicMetersVolume;

	@Column(name = "cave_type")
	private String caveType;
	private String location;

	@OneToMany(fetch=FetchType.LAZY, cascade= {CascadeType.MERGE, CascadeType.DETACH}) // @OneToMany - dictates that this relationship is one-to-many
				// where one is the Cave and Many are the Bears
	@JoinColumn(name = "cave_id") // Creates a JoinColumn for holding this relationship
	private List<Bear> occupants;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCubicMetersVolume() {
		return cubicMetersVolume;
	}

	public void setCubicMetersVolume(double cubicMetersVolume) {
		this.cubicMetersVolume = cubicMetersVolume;
	}

	public String getCaveType() {
		return caveType;
	}

	public void setCaveType(String caveType) {
		this.caveType = caveType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Bear> getOccupants() {
		return occupants;
	}

	public void setOccupants(List<Bear> occupants) {
		this.occupants = occupants;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caveType == null) ? 0 : caveType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cubicMetersVolume);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((occupants == null) ? 0 : occupants.hashCode());
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
		Cave other = (Cave) obj;
		if (caveType == null) {
			if (other.caveType != null)
				return false;
		} else if (!caveType.equals(other.caveType))
			return false;
		if (Double.doubleToLongBits(cubicMetersVolume) != Double.doubleToLongBits(other.cubicMetersVolume))
			return false;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (occupants == null) {
			if (other.occupants != null)
				return false;
		} else if (!occupants.equals(other.occupants))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cave [id=" + id + ", cubicMetersVolume=" + cubicMetersVolume + ", caveType=" + caveType + ", location="
				+ location + ", occupants=" + occupants + "]";
	}

	public Cave(int id, double cubicMetersVolume, String caveType, String location, List<Bear> occupants) {
		super();
		this.id = id;
		this.cubicMetersVolume = cubicMetersVolume;
		this.caveType = caveType;
		this.location = location;
		this.occupants = occupants;
	}

	public Cave() {
		super();
	}
}
