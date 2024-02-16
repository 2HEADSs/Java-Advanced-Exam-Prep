package June_17_2023;

import java.util.*;
import java.util.stream.Collectors;

public class TempleOfDoom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> toolsQueue = new ArrayDeque<>();
        Deque<Integer> substanceStack = new ArrayDeque<>();
        List<Integer> chalenges = new ArrayList<>();


        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(toolsQueue::offer);

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(substanceStack::push);

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(chalenges::add);

        while (!toolsQueue.isEmpty() && !substanceStack.isEmpty()) {

            int currentTool = toolsQueue.poll();
            int currentSubstance = substanceStack.pop();

            int multiplyResult = currentTool * currentSubstance;

            if (chalenges.contains(multiplyResult)) {
                chalenges.remove(chalenges.indexOf(multiplyResult));
            } else {
                currentTool += 1;
                toolsQueue.offer(currentTool);
                currentSubstance -= 1;
                if (currentSubstance > 0) {
                    substanceStack.push(currentSubstance);
                }
            }
        }

        if (!chalenges.isEmpty()) {
            System.out.println("Harry is lost in the temple. Oblivion awaits him.");
        } else {
            System.out.println("Harry found an ostracon, which is dated to the 6th century BCE.");
        }
        if (!toolsQueue.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Tools: ");
            String leftTools = toolsQueue.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            sb.append(leftTools);
            System.out.println(sb);
        }
        if (!substanceStack.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Substances: ");
            String leftSubstances = substanceStack.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            sb.append(leftSubstances);
            System.out.println(sb);
        }
        if (!chalenges.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Challenges: ");
            String leftChalenges = chalenges.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            sb.append(leftChalenges);
            System.out.println(sb);
        }

    }
}
