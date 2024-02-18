package sharkHaunt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Basin {
    private String name;
    private int capacity;

    private List<Shark> sharks;

    public Basin(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.sharks = new ArrayList<>();
    }

    public void addShark(Shark shark) {
        if (this.sharks.size() < this.capacity) {
            this.sharks.add(shark);
        } else {
            System.out.println("This basin is at full capacity!");
        }
    }

    public boolean removeShark(String kind) {
        for (Shark shark : this.sharks) {
            if (shark.getKind().equals(kind)) {
                return this.sharks.remove(shark);
            }
        }
        return false;
    }

    public Shark getLargestShark() {
        List<Shark> sortedByLengthAsc = this.sharks.stream()
                .sorted(Comparator.comparingInt(Shark::getLength))
                .collect(Collectors.toList());
        return sortedByLengthAsc.get(sortedByLengthAsc.size() - 1);
    }

    public Shark getShark(String kind) {
        for (Shark shark : this.sharks) {
            if (shark.getKind().equals(kind)) {
                return shark;
            }
        }
        return null;
    }

    public int getCount() {
        return this.sharks.size();
    }

    public int getAverageLength() {
        int count = this.sharks.size();
        int sum = 0;
        for (Shark shark : this.sharks) {
            sum += shark.getLength();
        }
        return sum / count;
    }

    public String report(){
        StringBuilder sb = new StringBuilder();
        sb.append("Sharks in ").append(this.name).append(":").append(System.lineSeparator());
        for (Shark shark:this.sharks){
            sb.append("The ").append(shark.getKind()).append(" is ").append(shark.getLength())
                    .append(" centimeters long, eats ").append(shark.getFood())
                    .append(" and inhabits ").append(shark.getHabitation()).append(".")
                    .append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
