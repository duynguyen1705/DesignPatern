package DesignPatern.Bai1;

import java.util.ArrayList;
import java.util.List;

public class Person implements Composite {
    private String name;
    private Date birthDate;
    private Gender gender;
    private List<Person> children;

    public Person(String name, Date birthDate, Gender gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void addChild(Person child) {
        this.children.add(child);
    }

    @Override
    public void print() {
        System.out.println(this.name);
        for (Person child : this.children) {
            child.print();
        }
    }

    public List<Person> findUnmarriedPeople() {
        List<Person> unmarriedPeople = new ArrayList<>();
        for (Person person : this.children) {
            if (!person.isMarried()) {
                unmarriedPeople.add(person);
            }

            unmarriedPeople.addAll(person.findUnmarriedPeople());
        }

        return unmarriedPeople;
    }

    public List<Couple> findCouplesWithTwoChildren() {
        List<Couple> couplesWithTwoChildren = new ArrayList<>();
        for (Person person : this.children) {
            if (person.getChildren().size() == 2) {
                Couple couple1 = new Couple(person, person.getChildren().get(0));
                couplesWithTwoChildren.add(couple1);
                Couple couple2 = new Couple(person, person.getChildren().get(1));
                couplesWithTwoChildren.add(couple2);
            } else if (!person.isGroup()) {
                couplesWithTwoChildren.addAll(person.findCouplesWithTwoChildren());
            }
        }
        return couplesWithTwoChildren;
    }
}
