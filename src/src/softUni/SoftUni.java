package softUni;


import java.util.ArrayList;
import java.util.List;

public class SoftUni {
    private int capacity;
    private List<Student> data;

    public SoftUni(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getCount() {
        return this.data.size();
    }

    public String insert(Student student) {
        String output = "";
        if (this.data.size() < this.capacity) {
            if (!this.data.contains(student)) {
                this.data.add(student);
                output = String.format("Added student %s %s.", student.getFirstName(), student.getLastName());
            } else {
                output = "Student is already in the hall.";

            }
        }else {
            output = "The hall is full.";
        }
        return output;
    }

    public String remove(Student student){
        String output = "";
        if(this.data.contains(student)){
            this.data.remove(student);
            output = String.format("Removed student %s %s.", student.getFirstName(), student.getLastName());
        }else {
            output = "Student not found.";
        }
    return output;
    }

    public Student getStudent(String firstName, String lastName){
        for (Student student: this.data){
            if(student.getFirstName().equals(firstName)&&student.getLastName().equals(lastName)){
                return student;
            }
        }
        return null;
    }

    public  String getStatistics(){
        StringBuilder sb = new StringBuilder();
        sb.append("Hall size: ").append(this.data.size()).append(System.lineSeparator());
        for (Student s : this.data) {
            sb.append(String.format("Student: %s %s, Best Course = %s%n", s.getFirstName(), s.getLastName(), s.getBestCourse()));
        } return sb.toString();
    };

}
