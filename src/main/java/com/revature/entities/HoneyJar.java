package com.revature.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="honey_jars")
public class HoneyJar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double liters;
	
	@OneToOne(mappedBy = "honeyJar")
	@JsonIgnore
	Bear owner;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLiters() {
		return liters;
	}

	public void setLiters(double liters) {
		this.liters = liters;
	}

	public Bear getOwner() {
		return owner;
	}

	public void setOwner(Bear owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(liters);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		HoneyJar other = (HoneyJar) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(liters) != Double.doubleToLongBits(other.liters))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HoneyJar [id=" + id + ", liters=" + liters + ", owner=" + owner + "]";
	}

	public HoneyJar(int id, double liters, Bear owner) {
		super();
		this.id = id;
		this.liters = liters;
		this.owner = owner;
	}

	public HoneyJar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
