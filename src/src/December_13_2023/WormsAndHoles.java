package December_13_2023;

import java.util.*;

public class WormsAndHoles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> wormsStack = new ArrayDeque<>();
        Deque<Integer> holesQueue = new ArrayDeque<>();
        int matches = 0;
        boolean suitable = false;


        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).
                forEach(wormsStack::push);

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).
                forEach(holesQueue::offer);
        while (!wormsStack.isEmpty() && !holesQueue.isEmpty()) {
            int currentWorm = wormsStack.pop();
            int currentHole = holesQueue.poll();
            if (currentWorm == currentHole) {
                suitable=true;
                matches++;
            } else if (currentWorm > currentHole) {
                suitable=false;
                currentWorm -= 3;
                if (currentWorm > 0) {
                    wormsStack.push(currentWorm);
                }
            }

        }
        if (matches > 0) {
            System.out.println("Matches: " + matches);
        } else {
            System.out.println("There are no matches.");
        }
        if(suitable){
            System.out.println("Every worm found a suitable hole!");
        }
        if(wormsStack.isEmpty() && !suitable){
            System.out.println("Worms left: none");
        }
        if(!wormsStack.isEmpty()){
            System.out.print("Worms left: ");
            while (!wormsStack.isEmpty()){
            System.out.print(wormsStack.pollLast());
                if(!wormsStack.isEmpty()){
                    System.out.print(", ");
                }else {
                    System.out.println();
                }
            }
        }
        if(holesQueue.isEmpty()){
            System.out.println("Holes left: none");
        }else {
            System.out.print("Holes left: ");
            while (!holesQueue.isEmpty()){
                System.out.print(holesQueue.poll());
                if(!holesQueue.isEmpty()){
                    System.out.print(", ");
                }else {
                    System.out.println();
                }
            }
        }

    }
}
