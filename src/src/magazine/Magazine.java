package magazine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Magazine {
    private String type;
    private int capacity;
    private List<Cloth> data;

    public Magazine(String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void addCloth(Cloth cloth) {
        if (this.data.size() < this.capacity) {
            this.data.add(cloth);
        }
    }

    public boolean removeCloth(String color) {
        for (Cloth cloth : this.data) {
            if (cloth.getColor().equals(color)) {
                return this.data.remove(cloth);

            }
        }
        return false;
    }

    public Cloth getSmallestCloth() {
        List<Cloth> sortedCloth = this.data.stream()
                .sorted(Comparator.comparingInt(Cloth::getSize))
                .collect(Collectors.toList());

        return sortedCloth.get(0);
    }

    public Cloth getCloth(String color) {
        for (Cloth cloth : this.data) {
            if (cloth.getColor().equals(color)) {
                return cloth;
            }
        }
        return null;
    }

    public int getCount() {
        return this.data.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.type).append(" magazine contains:").append(System.lineSeparator());
        this.data.forEach(c -> sb.append(c.toString()).append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
