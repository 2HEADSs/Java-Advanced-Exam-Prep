package December_14_2022;

import java.util.*;

public class ClimbThePeaks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> foodStack = new ArrayDeque<>();
        Deque<Integer> staminaQueue = new ArrayDeque<>();
        List<String> peaksNames = new ArrayList<>(Arrays.asList("Vihren", "Kutelo", "Banski Suhodol", "Polezhan", "Kamenitza"));
        Map<String, Integer> peaksForClimbed = new LinkedHashMap<>();
        peaksForClimbed.put("Vihren", 80);
        peaksForClimbed.put("Kutelo", 90);
        peaksForClimbed.put("Banski Suhodol", 100);
        peaksForClimbed.put("Polezhan", 60);
        peaksForClimbed.put("Kamenitza", 70);

        List<String> climbed = new LinkedList<>();


        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt).
                forEach(foodStack::push);

        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt).
                forEach(staminaQueue::offer);

        while (!foodStack.isEmpty() && !staminaQueue.isEmpty() && !peaksForClimbed.isEmpty()) {
            int dailyStamina = staminaQueue.poll();
            int dailyFood = foodStack.pop();
            String currentPeak = peaksNames.get(0);
            int dailyPoints = dailyFood + dailyStamina;

            if (dailyPoints >= peaksForClimbed.get(currentPeak)) {
                peaksNames.remove(currentPeak);
                peaksForClimbed.remove(currentPeak);
                climbed.add(currentPeak);
            }
        }
        if (peaksNames.isEmpty()) {
            System.out.println("Alex did it! He climbed all top five Pirin peaks in one week -> @FIVEinAWEEK");
        } else {
            System.out.println("Alex failed! He has to organize his journey better next time -> @PIRINWINS");
        }
        if (!climbed.isEmpty()) {
            System.out.println("Conquered peaks:");
            climbed.forEach(System.out::println);

        }
    }
}
