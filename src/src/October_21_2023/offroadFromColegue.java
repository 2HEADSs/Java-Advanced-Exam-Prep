package October_21_2023;

import java.util.*;

public class offroadFromColegue {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<String> altitudеs = new ArrayDeque<>(List.of("Altitude 1", "Altitude 2", "Altitude 3", "Altitude 4"));


        Deque<Integer> fuelStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(fuelStack::push);

        Deque<Integer> consIndexQueue = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(consIndexQueue::offer);

        Deque<Integer> neededFuelQueue = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(neededFuelQueue::offer);

        Map<String, Integer> altitudesWithValue = new LinkedHashMap<>();

        altitudеs.forEach(key -> {
            if (!neededFuelQueue.isEmpty()) {
                int val = neededFuelQueue.poll();
                altitudesWithValue.put(key, val);
            }
        });

        List<String> reached = new ArrayList<>();
        while (!fuelStack.isEmpty() && !consIndexQueue.isEmpty() && !altitudesWithValue.isEmpty()) {

            String currentAltitude = altitudеs.peek();

            int fuel = fuelStack.pop();
            int consumption = consIndexQueue.poll();
            int difference = fuel - consumption;

            if (difference >= altitudesWithValue.get(currentAltitude)) {
                reached.add(currentAltitude);
                altitudesWithValue.remove(currentAltitude);
                altitudеs.remove(currentAltitude);
                System.out.println("John has reached: " + currentAltitude);

            } else {

                System.out.println("John did not reach: " + currentAltitude);
                break;
            }
        }
        if (altitudеs.isEmpty()) {
            System.out.println("John has reached all the altitudes and managed to reach the top!");
        } else {
            System.out.println("John failed to reach the top.");
            if (!reached.isEmpty()) {
                System.out.print("Reached altitudes: ");
            } else {
                System.out.println("John didn't reach any altitude.");
            }

            System.out.print(String.join(", ", reached));
        }
    }

}
