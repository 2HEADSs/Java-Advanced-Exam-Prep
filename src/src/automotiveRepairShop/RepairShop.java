package automotiveRepairShop;

import java.util.ArrayList;
import java.util.List;

public class RepairShop {
    private  int capacity;
    private List<Vehicle> vehicles;

    public RepairShop(int capacity) {
        this.capacity = capacity;
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle){
        if(this.vehicles.size() < this.capacity){
            this.vehicles.add(vehicle);
        }
    }

    public boolean removeVehicle(String vin){
        for (Vehicle vehicle: this.vehicles){
            if(vehicle.getVIN().equals(vin)){
                this.vehicles.remove(vehicle);
                return true;
            }
        }
        return false;
    }

    public int getCount(){
        return this.vehicles.size();
    }

    public Vehicle getLowestMileage(){
        return this.vehicles.stream()
                .min((v1,v2)->Integer.compare(v1.getMileage(),v2.getMileage()))
                .get();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehicles in the preparatory:").append(System.lineSeparator());
        this.vehicles.forEach(v -> sb.append(v.toString()).append(System.lineSeparator()));
        return sb.toString();
    }
}
