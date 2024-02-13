package December_13_2023;

import java.util.*;

public class WormsAndHoles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] wormsInput = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] holesInput = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Deque<Integer> wormsStack = new ArrayDeque<>();
        Deque<Integer> holessQueue = new ArrayDeque<>();

        for (int i : wormsInput) {
            wormsStack.push(i);
        }
        for (int i : holesInput) {
            holessQueue.offer(i);
        }

        int matches = 0;

        while (!wormsStack.isEmpty() && !holessQueue.isEmpty()) {
            int currentWorm = wormsStack.peek();
            int currentHole = holessQueue.peek();
            if (currentWorm == currentHole) {
                wormsStack.pop();
                holessQueue.poll();
                matches++;
            } else {
                holessQueue.poll();
                currentWorm -= 3;
                if (currentWorm > 0) {
                    wormsStack.pop();
                    wormsStack.push(currentWorm);
                } else {
                    wormsStack.pop();
                }
            }
        }
        if (matches == 0) {
            System.out.printf("There are no matches.%n");
        } else {
            System.out.printf("Matches: %d%n", matches);
        }
        if (wormsStack.isEmpty()) {
            if (matches == wormsInput.length) {
                System.out.printf("Every worm found a suitable hole!%n");
            } else {
                System.out.printf("Worms left: none%n");
            }
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            List<Integer> leftWorms = new ArrayList<>();
            while (!wormsStack.isEmpty()) {
                leftWorms.add(0, wormsStack.pop());
            }
            for (int i = 0; i < leftWorms.size() - 1; i++) {
                stringBuilder.append(leftWorms.get(i));
                stringBuilder.append(", ");
            }
            stringBuilder.append(leftWorms.get(leftWorms.size() - 1));
            System.out.printf("Worms left: %s%n", stringBuilder);
        }
        if (holessQueue.isEmpty()) {
            System.out.printf("Holes left: none%n");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            while (!holessQueue.isEmpty()) {
                stringBuilder.append(holessQueue.poll());
                if (!holessQueue.isEmpty()) {
                    stringBuilder.append(", ");
                }
            }
            System.out.printf("Holes left: %s%n", stringBuilder);
            
        }
    }
}
