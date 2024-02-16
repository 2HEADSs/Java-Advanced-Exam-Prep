package April_12_2023;

import java.util.*;

public class RubberDuckDebuggers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> timeQueue = new ArrayDeque<>();
        Deque<Integer> numberOfTasksStack = new ArrayDeque<>();

        Map<String, Integer> ducks = new LinkedHashMap<>();
        ducks.put("Darth Vader Ducky", 0);
        ducks.put("Thor Ducky", 0);
        ducks.put("Big Blue Rubber Ducky", 0);
        ducks.put("Small Yellow Rubber Ducky", 0);

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).
                forEach(timeQueue::offer);

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).
                forEach(numberOfTasksStack::push);

        while (!timeQueue.isEmpty() && !numberOfTasksStack.isEmpty()) {
            int currentTime = timeQueue.poll();
            int currentNumberOfTask = numberOfTasksStack.pop();

            int currentMultiply = currentTime * currentNumberOfTask;
            if (currentMultiply >= 0 && currentMultiply <= 60) {
                ducks.put("Darth Vader Ducky", ducks.get("Darth Vader Ducky") + 1);
            } else if (currentMultiply >= 61 && currentMultiply <= 120) {
                ducks.put("Thor Ducky", ducks.get("Thor Ducky") + 1);
            } else if (currentMultiply >= 121 && currentMultiply <= 180) {
                ducks.put("Big Blue Rubber Ducky", ducks.get("Big Blue Rubber Ducky") + 1);
            } else if (currentMultiply >= 181 && currentMultiply <= 240) {
                ducks.put("Small Yellow Rubber Ducky", ducks.get("Small Yellow Rubber Ducky") + 1);
            } else if (currentMultiply > 240) {
                currentNumberOfTask -= 2;
                timeQueue.offer(currentTime);
                numberOfTasksStack.push(currentNumberOfTask);
            }
        }
        System.out.println("Congratulations, all tasks have been completed! Rubber ducks rewarded:");
        System.out.println("Darth Vader Ducky: " +ducks.get("Darth Vader Ducky"));
        System.out.println("Thor Ducky: " +ducks.get("Thor Ducky"));
        System.out.println("Big Blue Rubber Ducky: " +ducks.get("Big Blue Rubber Ducky"));
        System.out.println("Small Yellow Rubber Ducky: " +ducks.get("Small Yellow Rubber Ducky"));
    }
}
