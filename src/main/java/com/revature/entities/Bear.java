package com.revature.entities;

public class Bear {
	private int id;
	private String breed;
	private double kilograms;
	private String location;
	private String favoriteFood;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + ((favoriteFood == null) ? 0 : favoriteFood.hashCode());
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
		if (favoriteFood == null) {
			if (other.favoriteFood != null)
				return false;
		} else if (!favoriteFood.equals(other.favoriteFood))
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

	public Bear(int id, String breed, double kilograms, String location, String favoriteFood) {
		super();
		this.id = id;
		this.breed = breed;
		this.kilograms = kilograms;
		this.location = location;
		this.favoriteFood = favoriteFood;
	}

	public Bear() {
		super();
		// TODO Auto-generated constructor stub
	}

}
