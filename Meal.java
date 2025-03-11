// Author : John Whitmore
// Date Created : 02 / 05 / 25
// Date Modified : 02 / 12 / 25
// File Name : Meal.java
// Purpose : Provides class for Meal creation

import java.io.Serializable;
import java.util.LinkedList;

public class Meal implements Serializable, Comparable<Meal>{

    // fields
    private String name;
    private String recipe;
    private String flavor;
    private String texture;
    private String mealType;
    private boolean spicy = false;
    private int prepTime = 0;
    private LinkedList<Ingrediant> ingrediants;
    
    // constructor
    public Meal(){
        ingrediants = new LinkedList<Ingrediant>();
    }

    // getters
    public String getName(){
        return name;
    }

    public String getFlavor(){
        return flavor;
    }

    public String getTexture(){
        return texture;
    }

    public String getMealType(){
        return mealType;
    }

    public boolean getSpicy(){
        return spicy;
    }

    public int getPrepTime(){
        return prepTime;
    }

    public Ingrediant[] getIngrediants(){
        Ingrediant[] allIngrediants = new Ingrediant[ingrediants.size()];
        allIngrediants = ingrediants.toArray(allIngrediants);
        return allIngrediants;
    }

    public String toString(){
        return recipe;
    }

    // setters
    public void setName(String name){
        this.name = name;
    }

    public void setFlavor(String flavor){
        this.flavor = flavor;
    }

    public void setTexture(String texture){
        this.texture = texture;
    }

    public void setType(String mealType){
        this.mealType = mealType;
    }

    public void setSpicy(boolean spicy){
        this.spicy = spicy;
    }

    public void setPrepTime(int prepTime){
        this.prepTime = prepTime;
    }

    public void setRecipe(String recipe){
        this.recipe = recipe;
    }

    // ingrediant array changers
    public void addIngrediant(Ingrediant ing){
        ingrediants.add(ing);
    }

    public void removeIngrediant(Ingrediant ing){
        ingrediants.remove(ing);
    }

    // compareTo inherit
    public int compareTo(Meal other){
        return this.getName().compareTo(other.getName());
    }
}
