package io.VideoClub.Model;

import io.VideoClub.Model.Enums.GameCategory;
import io.VideoClub.Model.Enums.ProductsTypes;

public class Game extends Product{
    private GameCategory category;
    private int minAge;
    public Game() {
    }
    
    public Game(String name, String description,double prize, GameCategory category, int minAge){
        super(name,description,prize);
        this.type = ProductsTypes.Juegos;
        this.category=category;
        this.minAge = minAge;
    }

   
    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
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
        return "Juego" + super.toString()+ " categoria: " + category + " edad minima: " + minAge;
    }

   public Game clone()throws CloneNotSupportedException {
        Product clone = (Product) super.clone(); //To change body of generated methods, choose Tools | Templates.
        return (Game) clone;
   }
    
}
