package fishingPond;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Pond {

    private int capacity;

    private List<Fish> fishes;

    public Pond(int capacity) {
        this.capacity = capacity;
        this.fishes = new ArrayList<>();
    }

    public void addFish(Fish fish) {
        if (fishes.size() < this.capacity) {
            this.fishes.add(fish);
        }
    }

    public boolean removeFish(String species) {
        Fish fish = this.fishes.stream().
                filter(a -> a.getSpecies().equals(species)).
                findFirst().orElse(null);

        return this.fishes.remove(fish);
    }

    public Fish getOldestFish() {
        List<Fish> sortedByAge = this.fishes.stream().
                sorted(Comparator.comparingInt(Fish::getAge)).
                collect(Collectors.toList());
        return sortedByAge.get(sortedByAge.size() - 1);

    }

    public Fish getFish(String species) {
        return this.fishes.stream().
                filter(a -> a.getSpecies().equals(species)).
                findFirst().orElse(null);
    }

    public int getCount() {
        return this.fishes.size();
    }

    public int getVacancies() {
        return this.capacity - this.fishes.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fishes in the pond:");
        this.fishes.forEach(f -> {
            sb.append(System.lineSeparator());
            sb.append(String.format("This %s is %d years old and reproduces through %s.",
                    f.getSpecies(), f.getAge(), f.getMatingSeason()));
        });
        return sb.toString();
    }
}
