package io.VideoClub.Model;

import io.VideoClub.Model.Enums.MovieCategory;

public class Pelicula extends Product{
    private MovieCategory category;
    
    public Pelicula() {
    }
    
    public Pelicula(String name, String description,double prize, MovieCategory category){
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
