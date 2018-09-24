package com.dto;

public class Target {

    private String year;
    private String month;
    private String name;
    private double target;
    private double achieved;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getAchieved() {
        return achieved;
    }

    public void setAchieved(double achieved) {
        this.achieved = achieved;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(achieved);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(target);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Target other = (Target) obj;
		if (Double.doubleToLongBits(achieved) != Double.doubleToLongBits(other.achieved))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(target) != Double.doubleToLongBits(other.target))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Target [year=" + year + ", month=" + month + ", name=" + name + ", target=" + target + ", achieved="
				+ achieved + "]";
	}    
}