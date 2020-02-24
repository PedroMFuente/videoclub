package io.VideoClub.Model;

import io.VideoClub.Model.Enums.MovieCategory;

public class Movie extends Product{
    private MovieCategory category;
    
    public Movie() {
    }
    
    public Movie(String name, String description,double prize, MovieCategory category){
        super(name,description,prize);
        this.category=category;
    }

    public MovieCategory getCategory() {
        return category;
    }

    public void setCategory(MovieCategory category) {
        this.category = category;
    }
    
    @Override
    public String toString() {
        return "Pelicula{" +super.toString()+ " category=" + category + '}';
    }
    
}
