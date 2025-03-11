// Author : John Whitmore
// Date Created : 02 / 04 / 25
// Date Modified : 02 / 12 / 25
// File Name : Ingrediant.java
// Purpose : Provides class for Ingrediant creation

import java.io.Serializable;
import java.util.LinkedList;

public class Ingrediant implements Serializable, Comparable<Ingrediant>{

    // fields
    private String name;
    private String category;
    private String subCategory;
    private double quantity;
    private LinkedList<Meal> meals;

    // constructor
    public Ingrediant(){
        meals = new LinkedList<Meal>();
    }

    // getters
    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    public String getSubCategory(){
        return subCategory;
    }

    public double getQuantity(){
        return quantity;
    }

    public Meal[] getMeals(){
        Meal[] allMeals = new Meal[meals.size()];
        allMeals = meals.toArray(allMeals);
        return allMeals;
    }

    // setters
    public void setName(String name){
        this.name = name;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setSubCategory(String subCategory){
        this.subCategory = subCategory;
    }

    public void setQuantity(double quantity){
        this.quantity = quantity;
    }

    // meal array changers
    public void addMeal(Meal meal){
        meals.add(meal);
    }

    public void removeMeal(Meal meal){
        meals.remove(meal);
    }

    // compareTo inherit
    public int compareTo(Ingrediant other){
        if(this.getCategory().compareTo(other.getCategory()) != 0){
            return this.getCategory().compareTo(other.getCategory());
        } else if(this.getSubCategory().compareTo(other.getSubCategory()) != 0){
            return this.getCategory().compareTo(other.getSubCategory());
        } else {
            return this.getName().compareTo(other.getName());
        }
    }
}