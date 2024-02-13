package December_17_2019;

import java.util.*;
import java.util.stream.Collectors;

public class SantasPresentFactory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String materialsInput = scanner.nextLine();
        String macigsInput = scanner.nextLine();

        Stack<Integer> materials = new Stack<>();
        Arrays.stream(materialsInput.split("\\s+"))
                .map(Integer::parseInt).forEach(materials::push);

        Queue<Integer> magics = Arrays.stream(macigsInput.split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        Map<String, Integer> presentsMap = new TreeMap<>();
        presentsMap.put("Doll", 0);
        presentsMap.put("Wooden train", 0);
        presentsMap.put("Teddy bear", 0);
        presentsMap.put("Bicycle", 0);

        while (!materials.isEmpty() && !magics.isEmpty()) {
            int material = materials.peek();
            int magic = magics.peek();

            int totalMagic = magic * material;
            if (totalMagic == 150) {
                materials.pop();
                magics.poll();
                presentsMap.put("Doll", presentsMap.get("Doll") + 1);
            } else if (totalMagic == 250) {
                materials.pop();
                magics.poll();
                presentsMap.put("Wooden train", presentsMap.get("Wooden train") + 1);
            } else if (totalMagic == 300) {
                materials.pop();
                magics.poll();
                presentsMap.put("Teddy bear", presentsMap.get("Teddy bear") + 1);
            } else if (totalMagic == 400) {
                materials.pop();
                magics.poll();
                presentsMap.put("Bicycle", presentsMap.get("Bicycle") + 1);
            } else if (totalMagic < 0) {
                int sum = material + magic;
                materials.pop();
                magics.poll();
                materials.push(sum);
            } else if (totalMagic > 0) {
                magics.poll();
                materials.push(materials.pop() + 15);
            } else {
                if (magic == 0) {
                    magics.poll();
                }
                if (material == 0) {
                    materials.pop();
                }
            }
        }
//•	On the first line - print whether you've succeeded in crafting the presents
//o	"The presents are crafted! Merry Christmas!"
//o	"No presents this Christmas!"
        if (presentsMap.get("Doll") > 0 && presentsMap.get("Wooden train") > 0 ||
                presentsMap.get("Teddy bear") > 0 && presentsMap.get("Bicycle") > 0) {
            System.out.println("The presents are crafted! Merry Christmas!");
        } else {
            System.out.println("No presents this Christmas!");
        }
//•	On the next two lines print the materials and magic that are left, if there are any, otherwise skip the line
//o	"Materials left: {material1}, {material2}, …"
//o	"Magic left: {magicValue1}, {magicValue2}, …
        if (!materials.isEmpty()) {
            Collections.reverse(materials);
            System.out.print("Materials left: ");
            System.out.println(materials.toString().replace("[","").replace("]",""));
        }
        if (!magics.isEmpty()) {
            Collections.reverse(materials);

            System.out.print("Magic left: ");
            System.out.println(magics.toString().replace("[","").replace("]",""));
        }

        //•	On the next lines print the presents you have crafted at least once, ordered alphabetically in the format:
        //"{toy name}: {amount}
        for (Map.Entry<String, Integer> entry : presentsMap.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }

    }
}
