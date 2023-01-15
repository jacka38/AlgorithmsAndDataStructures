package oy.tol.tra;

public class Person implements Comparable<Person>{
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    // TODO: Implement equals(), hashCode() and Comparable interface.

    @Override
    public boolean equals(Object person) {
        
        if (this == person){
            return true;
        } 

        if (person == null || getClass() != person.getClass()){
            return false;
        }
        Person o = (Person) person;

        return firstName.equals(o.firstName) && lastName.equals(o.lastName);
    }

    public int hashCode() {
        
        int result = 5381;

        result = (result << 5) + result + lastName.hashCode();
        result = (result << 5) + result + firstName.hashCode();
        
        return result;
    }
    
    @Override
    public int compareTo(Person person) {
        int lastNameCmp = lastName.compareTo(person.lastName);
        return lastNameCmp != 0 ? lastNameCmp : firstName.compareTo(person.firstName);
    }
}
