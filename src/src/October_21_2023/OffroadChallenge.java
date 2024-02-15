package October_21_2023;

import java.util.*;

public class OffroadChallenge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> fuelStack = new ArrayDeque<>();
        Deque<Integer> consumptionQueue = new ArrayDeque<>();
        List<Integer> altitude = new ArrayList<>();
        List<Integer> reachedAltitudes = new ArrayList<>();
        int reached = 0;
        List<Integer> notReached = new ArrayList<>(Arrays.asList(1, 2, 3, 4));


        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).
                forEach(fuelStack::push);

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).
                forEach(consumptionQueue::offer);

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).
                forEach(altitude::add);

        for (int i = 0; i < altitude.size(); i++) {
            int currentFule = fuelStack.pop();
            int currentConsumption = consumptionQueue.poll();
            int currentAttitude = altitude.get(i);
            if ((currentFule - currentConsumption) >= currentAttitude) {
                reachedAltitudes.add(i + 1);
                notReached.remove(0);
                reached++;
                System.out.println("John has reached: Altitude " + reachedAltitudes.get(reachedAltitudes.size()-1));
            } else {
                System.out.println("John did not reach: Altitude " + notReached.get(0));
                break;
            }
        }

        if (!reachedAltitudes.isEmpty() && !notReached.isEmpty()) {
            System.out.println("John failed to reach the top.");
            StringBuilder sb = new StringBuilder();
            sb.append("Reached altitudes: ");
            for (int i = 0; i < reachedAltitudes.size(); i++) {
                if (i < reachedAltitudes.size() - 1) {
                    sb.append("Altitude ").append(reachedAltitudes.get(i)).append(", ");
                } else {
                    sb.append("Altitude ").append(reachedAltitudes.get(i));
                }
            }
            System.out.println(sb);
        }
        if (reachedAltitudes.isEmpty()) {
            System.out.print("John failed to reach the top. John didn't reach any altitude.");
        }
        if (reachedAltitudes.size() == 4) {
            System.out.print("John has reached all the altitudes and managed to reach the top!");
        }
    }
}
