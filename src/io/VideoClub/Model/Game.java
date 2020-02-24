package io.VideoClub.Model;

import io.VideoClub.Model.Enums.GameCategory;

public class Game extends Product{
    private GameCategory category;

    public Game() {
    }
    
    public Game(String name, String description,double prize, GameCategory category){
        super(name,description,prize);
        this.category=category;
    }

    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Juego{" +super.toString()+ " category=" + category + '}';
    }
    
}
