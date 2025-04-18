// Author : John Whitmore
// Date Created : 02 / 12 / 2025
// Date Modified : 03 / 07 / 2025
// File Name : DealersMeal.java
// Purpose : Driver class for Dealer's Meal program

import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedList;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileInputStream;

public class DealersMeal {

    // fields
    private static String[] flavors = {"Other", "Bitter", "Salty", "Sweet", "Sour", "Umami", "Acidic", "Buttery", "Smoky", "Tart","American", "Asian", "European", "Latin", "Mediterranean"};
    private static String[] textures = {"Other", "Airy", "Brittle", "Chewy", "Chunky", "Creamy", "Crisp", "Crunchy", "Crumbly", "Dry", "Flaky", "Fluffy", "Foamy", "Gooey", "Grainy", "Greasy", "Hard", "Heavy", "Light", "Moist", "Mushy", "Oily", "Rubbery", "Soft", "Spongy", "Sticky", "Stringy", "Tender", "Thick", "Tough", "Watery"};
    private static String[] types = {"Other", "Breakfast", "Brunch", "Lunch", "Dinner", "Supper", "Dessert", "Snack", "Family Meal", "Meal-for-one", "Fried", "Grilled", "Microwaved", "Oven-baked"};
    private static TreeMap<String, Meal> mTree = new TreeMap<>();
    private static TreeMap<String, Ingrediant> iTree;

    public static void main(String args[]){
        //loadIngrediantTree();
        loadMealTree();

        SearchWindow window = new SearchWindow();
        window.setVisible(true);
    }

    public static String[] getFlavors(){
        return flavors;
    }

    public static String[] getTextures(){
        return textures;
    }

    public static String[] getTypes(){
        return types;
    }

    public static void addMeal(Meal meal, String name){
        if(mTree.isEmpty()){
            mTree.put(meal.getName(), meal);
        } else if(mTree.get(name) == null){
            mTree.put(meal.getName(), meal);
        } else {
            mTree.remove(name);
            mTree.put(meal.getName(), meal);
        }
        saveMealTree();
    }

    public static Meal[] search(Meal temp){
        LinkedList<Meal> matches = new LinkedList<>();

        for(Map.Entry<String, Meal> m : mTree.entrySet()){
            Meal found = m.getValue();
            if((found.getName().equals(temp.getName())) == false && temp.getName().equals("") == false) continue;
            if((found.getFlavor().equals(temp.getFlavor())) == false && temp.getFlavor().equals("Other") == false) continue;
            if((found.getTexture().equals(temp.getTexture())) == false && temp.getTexture().equals("Other") == false) continue;
            if((found.getMealType().equals(temp.getMealType())) == false && temp.getMealType().equals("Other") == false) continue;
            if(((found.getPrepTime() > temp.getPrepTime()) && temp.getPrepTime() > 0)) continue;
            if(found.getSpicy() != temp.getSpicy()) continue;

            matches.add(found);
        }
        
        return matches.toArray(new Meal[matches.size()]);
    }

    public static Meal getMeal(String name){
        return mTree.get(name);
    }

    private static void saveIngrediantTree(){
        try (FileOutputStream outStream = new FileOutputStream("ingrediants.dat"); ObjectOutputStream objStream = new ObjectOutputStream(outStream);){
            for(Map.Entry<String, Ingrediant> entry : iTree.entrySet()){
                Ingrediant i = entry.getValue();
                objStream.writeObject(i);
            }
        } catch (IOException e){
            System.out.println("File Not Found");
        }
    }

    private static void loadIngrediantTree(){
        boolean endOfFile = false;

        try (FileInputStream inStream = new FileInputStream("ingrediants.dat"); ObjectInputStream objInput = new ObjectInputStream(inStream)){
            while(!endOfFile){
                try {
                    Ingrediant i = (Ingrediant)objInput.readObject();
                    iTree.put(i.getName(), i);
                } catch (EOFException eof){
                    endOfFile = true;
                }
            }
        } catch(IOException e){
            System.out.println("File Not Found");
        } catch (ClassNotFoundException e){
            System.out.println("Class Not Found");
        }
    }

    private static void saveMealTree(){
        try (FileOutputStream outStream = new FileOutputStream("meals.dat"); ObjectOutputStream objStream = new ObjectOutputStream(outStream);){
            for(Map.Entry<String, Meal> entry : mTree.entrySet()){
                Meal food = entry.getValue();
                objStream.writeObject(food);
            }
        } catch (IOException e){
            System.out.println("File Not Found");
        }
    }

    private static void loadMealTree(){
        boolean endOfFile = false;

        try (FileInputStream inStream = new FileInputStream("meals.dat"); ObjectInputStream objInput = new ObjectInputStream(inStream)){
            while(!endOfFile){
                try {
                    Meal food = (Meal)objInput.readObject();
                    mTree.put(food.getName(), food);
                } catch (EOFException eof){
                    endOfFile = true;
                }
            }
        } catch(IOException e){
            System.out.println("File Not Found");
        } catch (ClassNotFoundException e){
            System.out.println("Class Not Found");
        }
    }
}
