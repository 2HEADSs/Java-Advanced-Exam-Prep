package Feb_22_2020;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Lootbox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> firstBoxQueue = new ArrayDeque<>();
        Deque<Integer> secondBoxStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(firstBoxQueue::offer);

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(secondBoxStack::push);
        boolean firstBoxIsEmpty = false;
        boolean secondBoxIsEmpty = false;
        int sum = 0;

        while (!firstBoxQueue.isEmpty() && !secondBoxStack.isEmpty()) {
            int currentFromFirstBox = firstBoxQueue.peek();
            int currentFromSecondBox = secondBoxStack.peek();
            int sumOfBoth = currentFromFirstBox + currentFromSecondBox;
            if (sumOfBoth % 2 == 0) {
                sum += sumOfBoth;
                firstBoxQueue.poll();
                secondBoxStack.pop();
            } else {
                int lastItemFormSecond = secondBoxStack.pop();
                if (secondBoxStack.isEmpty()) {
                    secondBoxIsEmpty = true;
                    break;
                } else {
                    firstBoxQueue.offer(lastItemFormSecond);
                }
            }
        }

        if (firstBoxQueue.isEmpty()) {
            System.out.println("First lootbox is empty");
        }
        if (secondBoxStack.isEmpty()) {
            System.out.println("Second lootbox is empty");
        }
        if (sum >= 100) {
            System.out.println("Your loot was epic! Value: " + sum);
        } else {
            System.out.println("Your loot was poor... Value: " + sum);
        }

    }
}
