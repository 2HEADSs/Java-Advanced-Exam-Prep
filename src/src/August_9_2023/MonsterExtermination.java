package August_9_2023;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class MonsterExtermination {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> soldierStack = new ArrayDeque<>();
        Deque<Integer> monsterQueue = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(","))
                .map(Integer::parseInt)
                .forEach(monsterQueue::offer);

        Arrays.stream(scanner.nextLine()
                        .split(",")).map(Integer::parseInt)
                .forEach(soldierStack::push);

        int monstersKillled = 0;

        while (!soldierStack.isEmpty() && !monsterQueue.isEmpty()) {
            int currentMonsterArmour = monsterQueue.poll();
            int currentSoldierStike = soldierStack.pop();

            if (currentSoldierStike >= currentMonsterArmour) {
                int leftStrikePower = currentSoldierStike - currentMonsterArmour;
                if (leftStrikePower > 0 ) {
                    if(!soldierStack.isEmpty()){
                    int newLastPower = soldierStack.pop() + leftStrikePower;
                    soldierStack.push(newLastPower);
                    }else {
                        soldierStack.push(leftStrikePower);
                    }
                }
                monstersKillled++;
            } else {
                int leftArmour = currentMonsterArmour - currentSoldierStike;
                if (leftArmour > 0) {
                    monsterQueue.offer(leftArmour);
                }
            }
        }
        if (monsterQueue.isEmpty()) {
            System.out.println("All monsters have been killed!");
        }
        if (soldierStack.isEmpty()) {
            System.out.println("The soldier has been defeated.");
        }

        System.out.println("Total monsters killed: " + monstersKillled);

    }
}
