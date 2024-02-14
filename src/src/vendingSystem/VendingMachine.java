package vendingSystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private int buttonCapacity;
    private List<Drink> drinks;

    public VendingMachine(int buttonCapacity) {
        this.buttonCapacity = buttonCapacity;
        this.drinks = new ArrayList<>();
    }

    public int getCount(){
        return this.drinks.size();
    }

    public void addDrink(Drink drink){
        if(this.drinks.size()<this.buttonCapacity){
            this.drinks.add(drink);
        }
    }

    public boolean removeDrink(String name){
        for (Drink drink: this.drinks){
            if(drink.getName().equals(name)){
                this.drinks.remove(drink);
                return true;
            }
        }
        return false;
    }

    public Drink getLongest(){
        return this.drinks.stream()
                .max((d1, d2) -> Integer.compare(d1.getVolume(), d2.getVolume()))
                .orElse(null);
    }

    public Drink getCheapest(){
        return this.drinks.stream()
                .min((d1, d2) -> d1.getPrice().compareTo(d2.getPrice()))
                .orElse(null);
    }
    public String buyDrink(String name){
        for (Drink drink: this.drinks){
            if(drink.getName().equals(name)){
                return drink.toString();
            }
        }
        return null;
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append("Drinks available:").append(System.lineSeparator());
        this.drinks.forEach(e -> sb.append(e.toString()).append(System.lineSeparator()));
        return sb.toString();
    }
}
