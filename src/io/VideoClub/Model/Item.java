package io.VideoClub.Model;


public abstract class Item  {
     protected String name;
     protected String description;
     protected double prize;
    
    public Item(){}
    public Item(String name, String description, double prize) {
        this.name = name;
        this.description = description;
        this.prize = prize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "name=" + name + ", description=" + description + ", prize=" + prize + '}';
    }
    
}
