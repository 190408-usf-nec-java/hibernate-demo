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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
@Entity // Entity annotation tells ORMs that instances of this class are ORM-manageable entities
@Table(name="bears") // Optional table configuration options
public class Bear {
	
	@Id // Indicates a primary key field
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Creates fields as SERIAL in pg
										// and allows the table to provide values itself
	private int id;
	
	@Column(length = 20, nullable=false) // Optional annotation for providing column level configuration
	private String breed;
	
	@Transient // Tells ORM not to store this data, 
				//		no column will be created, data will not be persisted.
	private double kilograms;
	private String location;
	private String favoriteFood;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cave_id")
	private Cave cave;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="honey_jar_id")
	private HoneyJar honeyJar;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="bear_cubs",
				joinColumns= {@JoinColumn(name="parent_id")},
				inverseJoinColumns= {@JoinColumn(name="cub_id")})
	List<Bear> cubs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public double getKilograms() {
		return kilograms;
	}

	public void setKilograms(double kilograms) {
		this.kilograms = kilograms;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFavoriteFood() {
		return favoriteFood;
	}

	public void setFavoriteFood(String favoriteFood) {
		this.favoriteFood = favoriteFood;
	}

	public Cave getCave() {
		return cave;
	}

	public void setCave(Cave cave) {
		this.cave = cave;
	}

	public HoneyJar getHoneyJar() {
		return honeyJar;
	}

	public void setHoneyJar(HoneyJar honeyJar) {
		this.honeyJar = honeyJar;
	}

	public List<Bear> getCubs() {
		return cubs;
	}

	public void setCubs(List<Bear> cubs) {
		this.cubs = cubs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + ((cave == null) ? 0 : cave.hashCode());
		result = prime * result + ((cubs == null) ? 0 : cubs.hashCode());
		result = prime * result + ((favoriteFood == null) ? 0 : favoriteFood.hashCode());
		result = prime * result + ((honeyJar == null) ? 0 : honeyJar.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(kilograms);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((location == null) ? 0 : location.hashCode());
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
		Bear other = (Bear) obj;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (cave == null) {
			if (other.cave != null)
				return false;
		} else if (!cave.equals(other.cave))
			return false;
		if (cubs == null) {
			if (other.cubs != null)
				return false;
		} else if (!cubs.equals(other.cubs))
			return false;
		if (favoriteFood == null) {
			if (other.favoriteFood != null)
				return false;
		} else if (!favoriteFood.equals(other.favoriteFood))
			return false;
		if (honeyJar == null) {
			if (other.honeyJar != null)
				return false;
		} else if (!honeyJar.equals(other.honeyJar))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(kilograms) != Double.doubleToLongBits(other.kilograms))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bear [id=" + id + ", breed=" + breed + ", kilograms=" + kilograms + ", location=" + location
				+ ", favoriteFood=" + favoriteFood + "]";
	}

	public Bear(int id, String breed, double kilograms, String location, String favoriteFood, Cave cave,
			HoneyJar honeyJar, List<Bear> cubs) {
		super();
		this.id = id;
		this.breed = breed;
		this.kilograms = kilograms;
		this.location = location;
		this.favoriteFood = favoriteFood;
		this.cave = cave;
		this.honeyJar = honeyJar;
		this.cubs = cubs;
	}

	public Bear() {
		super();
	}
	
	
	
}