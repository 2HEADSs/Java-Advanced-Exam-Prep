package December_15_2021;

import java.util.*;
import java.util.stream.Collectors;

public class Meeting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String malesInput = scanner.nextLine();
        String femaleInput = scanner.nextLine();
        int matches = 0;
        Deque<Integer>malesStack = new ArrayDeque<>();
        Deque<Integer>femalesQueue = new ArrayDeque<>();


        Arrays.stream(malesInput.split("\\s+"))
                .map(Integer::parseInt).forEach(malesStack::push);

         Arrays.stream(femaleInput.split("\\s+"))
                .map(Integer::parseInt).forEach(femalesQueue::offer);

        while (!malesStack.isEmpty() && !femalesQueue.isEmpty()) {
            if (malesStack.peek() <= 0) {
                malesStack.pop();
            } else if (femalesQueue.peek() <= 0) {
                femalesQueue.poll();
            } else {
                //male % 25
                if (malesStack.peek() % 25 == 0) {
                    malesStack.pop();
                    if (!malesStack.isEmpty()) {
                        malesStack.pop();
                    }
                    //female % 25
                } else if (femalesQueue.peek() % 25 == 0) {
                    femalesQueue.poll();
                    if (!femalesQueue.isEmpty()) {
                        femalesQueue.poll();
                    }
                } else {
                    int male = malesStack.peek();
                    int female = femalesQueue.peek();

                    if (male == female) {
                        malesStack.pop();
                        femalesQueue.poll();
                        matches++;
                    } else {
                        malesStack.pop();
                        male -= 2;
                        malesStack.push(male);
                        femalesQueue.poll();
                    }
                }
            }
        }
        System.out.println("Matches: " + matches);
        if (malesStack.isEmpty()) {
            System.out.println("Males left: none");
        } else {
            System.out.print("Males left: ");
            System.out.println(malesStack.toString().replace("[", "").replace("]", ""));
        }
        if (femalesQueue.isEmpty()) {
            System.out.println("Females left: none");
        } else {
            System.out.print("Females left: ");
            System.out.println(femalesQueue.toString().replace("[", "").replace("]", ""));
        }
    }
}
