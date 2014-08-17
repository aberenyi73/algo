package com.aberenyi.algo2.assign1;

/**
 * Represents a job that is to be executed. It has a certain length and weight.
 * 
 * @author aberenyi
 * 
 */
public class Job implements Comparable<Job> {

    protected int weight;
    protected int length;
    boolean isDivide = false;

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }
    
    /**
     * @return the isDivide
     */
    public boolean isDivide() {
        return isDivide;
    }

    /**
     * @param isDivide the isDivide to set
     */
    public void setDivide(boolean isDivide) {
        this.isDivide = isDivide;
    }

    /**
     * @param weight
     * @param length
     */
    public Job(int weight, int length) {
        this.weight = weight;
        this.length = length;
    }

    @Override
    public int compareTo(Job o) {
        if (getWeightedSum() > o.getWeightedSum())
            return -1;
        if (getWeightedSum() < o.getWeightedSum())
            return 1;
        if (getWeightedSum() == o.getWeightedSum())
            if (getWeight() > o.getWeight())
                return -1;
            else if (getWeight() < o.getWeight())
                return 1;
            else
                return 0;
        return 0;
    }

    public double getWeightedSum() {
        if (isDivide)
            return ((double) weight) / length;
        else
            return weight - length;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Job [weight=");
        builder.append(weight);
        builder.append(", length=");
        builder.append(length);
        builder.append(", weightedSum=");
        builder.append(getWeightedSum());
        builder.append("]");
        return builder.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (isDivide ? 1231 : 1237);
        result = prime * result + length;
        result = prime * result + weight;
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Job other = (Job) obj;
        if (isDivide != other.isDivide)
            return false;
        if (length != other.length)
            return false;
        if (weight != other.weight)
            return false;
        return true;
    }

    
    

}
