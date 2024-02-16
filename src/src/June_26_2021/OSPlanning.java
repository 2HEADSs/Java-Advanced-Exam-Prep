package June_26_2021;

import java.util.*;
import java.util.stream.Collectors;

public class OSPlanning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Deque<Integer> taskStack = new ArrayDeque<>();
        Deque<Integer> threadsQueue = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt).forEach(taskStack::push);

        Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).forEach(threadsQueue::offer);

        int taskToKill = Integer.parseInt(scanner.nextLine());
        int killer = 0;

        boolean finish = false;
        while (!finish) {
            int currentTask = taskStack.peek();
            int currentThread = threadsQueue.peek();

            if (currentThread >= currentTask) {
                if (currentTask == taskToKill) {
                    killer = currentThread;
                    finish = true;
                    break;
                } else {
                    taskStack.pop();
                    threadsQueue.poll();
                }
            } else if (currentTask == taskToKill) {
                killer = currentThread;
                finish = true;
                taskStack.pop();
                break;
            } else {
                threadsQueue.poll();
            }

        }

        System.out.printf("Thread with value %s killed task %s", killer, taskToKill);
        System.out.println();

        String leftThreads = threadsQueue.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(leftThreads);

    }
}
