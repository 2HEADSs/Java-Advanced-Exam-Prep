package shelter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Shelter {
    private int capacity;
    private List<Animal> data;

    public Shelter(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Animal animal) {
        if (data.size() < this.capacity) {
            this.data.add(animal);
        }
    }

    public boolean remove(String name) {
        Animal animal = this.data.stream().
                filter(a -> a.getName().equals(name)).
                findFirst()
                .orElse(null);
        return this.data.remove(animal);
    }

    public Animal getAnimal(String name, String caretaker) {
        return this.data.stream().
                filter(a -> a.getName().equals(name)
                        && a.getCaretaker().equals(caretaker)).
                findFirst().orElse(null);
    }

    public Animal getOldestAnimal() {
        List<Animal> sortedByAgeAsc = this.data.stream().
                sorted(Comparator.comparingInt(Animal::getAge))
                .collect(Collectors.toList());
        return sortedByAgeAsc.get(sortedByAgeAsc.size() - 1);
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {

        StringBuilder sb = new StringBuilder();
        sb.append("The shelter has the following animals:");
        this.data.forEach(animal -> {
            sb.append(System.lineSeparator());
            sb.append(String.format("%s %s", animal.getName(), animal.getCaretaker()));
        });

        return sb.toString();
    }

}
