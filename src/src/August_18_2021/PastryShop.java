package August_18_2021;

import java.util.*;
import java.util.stream.Collectors;

public class PastryShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> liquidsQueue = new ArrayDeque<>();
        Deque<Integer> ingredientsStack = new ArrayDeque<>();

        Map<Integer, String> foodForCooking = new LinkedHashMap<>();
        foodForCooking.put(25, "Biscuit");
        foodForCooking.put(50, "Cake");
        foodForCooking.put(75, "Pastry");
        foodForCooking.put(100, "Pie");

        Map<String, Integer> cookedFood = new LinkedHashMap<>();
        cookedFood.put("Biscuit", 0);
        cookedFood.put("Cake", 0);
        cookedFood.put("Pastry", 0);
        cookedFood.put("Pie", 0);

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).
                forEach(liquidsQueue::offer);

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).
                forEach(ingredientsStack::push);

        boolean allFoodIsCooked = false;

        while (!liquidsQueue.isEmpty() && !ingredientsStack.isEmpty()) {
            int currentLiquid = liquidsQueue.poll();
            int currentIngredient = ingredientsStack.pop();
            int currentMix = currentIngredient + currentLiquid;
            if (foodForCooking.containsKey(currentMix)) {
                String currentDish = foodForCooking.get(currentMix);
                cookedFood.put(currentDish, cookedFood.get(currentDish) + 1);
            }else {
                ///to check is not empty
                currentIngredient +=3;
                ingredientsStack.push(currentIngredient);
            }

        }

        for (String s : cookedFood.keySet()) {
            if(cookedFood.get(s)>0){
                allFoodIsCooked = true;
            }else {
                allFoodIsCooked = false;
                break;
            }
        }

        if(allFoodIsCooked){
            System.out.println("Great! You succeeded in cooking all the food!");
        }else {
            System.out.println("What a pity! You didn't have enough materials to cook everything.");
        }
        //Liquids
        if(liquidsQueue.isEmpty()){
            System.out.println("Liquids left: none");
        }else {
            StringBuilder sb = new StringBuilder();
            sb.append("Liquids left: ");
            String leftLiquids = liquidsQueue.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            sb.append(leftLiquids);
            System.out.println(sb);
        }

        //Ingredients
        if(ingredientsStack.isEmpty()){
            System.out.println("Ingredients left: none");
        }else {
            StringBuilder sb = new StringBuilder();
            sb.append("Ingredients left: ");
            String leftIngredients = ingredientsStack.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            sb.append(leftIngredients);
            System.out.println(sb);
        }

        System.out.println("Biscuit: " +cookedFood.get("Biscuit"));
        System.out.println("Cake: " +cookedFood.get("Cake"));
        System.out.println("Pie: " +cookedFood.get("Pie"));
        System.out.println("Pastry: " +cookedFood.get("Pastry"));
    }
}

