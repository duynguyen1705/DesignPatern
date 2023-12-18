package DesignPatern.Bai1;

public class Couple {
    private Person husband;
    private Person wife;

    public Couple(Person husband, Person wife) {
        this.husband = husband;
        this.wife = wife;
    }

    public Person getHusband() {
        return husband;
    }

    public void setHusband(Person husband) {
        this.husband = husband;
    }

    public Person getWife() {
        return wife;
    }

    public void setWife(Person wife) {
        this.wife = wife;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", husband.getName(), wife.getName());
    }
}