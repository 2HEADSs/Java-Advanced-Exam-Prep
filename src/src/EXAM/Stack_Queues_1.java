package EXAM;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Stack_Queues_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String moneyInput = scanner.nextLine();
        String priceInput = scanner.nextLine();
        Deque<Integer> amountMoneyStack = new ArrayDeque<>();
        Deque<Integer> foodPriceQueue = new ArrayDeque<>();

        int countEatenFood = 0;

        Arrays.stream(moneyInput.split("\\s+"))
                .map(Integer::parseInt).forEach(amountMoneyStack::push);

        Arrays.stream(priceInput.split("\\s+"))
                .map(Integer::parseInt).forEach(foodPriceQueue::offer);

        while (!amountMoneyStack.isEmpty() && !foodPriceQueue.isEmpty()) {
            int currentFoodPrice = foodPriceQueue.poll();
            int currentMoney = amountMoneyStack.pop();

            if (currentMoney == currentFoodPrice) {
                countEatenFood++;
            } else if (currentMoney > currentFoodPrice) {
                countEatenFood++;
                int change = currentMoney - currentFoodPrice;
                if (!amountMoneyStack.isEmpty()) {
                    int nextMoney = amountMoneyStack.pop();
                    int newLastMoney = change + nextMoney;
                    amountMoneyStack.push(newLastMoney);
                }
            }
        }
        if (countEatenFood >= 4) {
            System.out.println("Gluttony of the day! Henry ate " + countEatenFood + " foods.");
        }
        if (countEatenFood > 1 && countEatenFood < 4) {
            System.out.println("Henry ate: " + countEatenFood + " foods.");
        }
        if(countEatenFood==1){
            System.out.println("Henry ate: "+countEatenFood+" food.");
        }
        if(countEatenFood==0){
            System.out.println("Henry remained hungry. He will try next weekend again.");
        }
    }
}
