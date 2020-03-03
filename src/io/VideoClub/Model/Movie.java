package io.VideoClub.Model;

import io.VideoClub.Model.Enums.MovieCategory;


public class Movie extends Product{
    private MovieCategory category;
    private int minAge;
    public Movie() {
    }
    
    public Movie(String name, String description,double prize, MovieCategory category, int minAge){
        super(name,description,prize);
        this.category=category;
        this.minAge = minAge;
    }

    public MovieCategory getCategory() {
        return category;
    }

    public void setCategory(MovieCategory category) {
        this.category = category;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }
    
    @Override
    public String toString() {
        return "Pelicula{" +super.toString()+ " category=" + category + " edad minima=" + minAge + '}';
    }
  
}