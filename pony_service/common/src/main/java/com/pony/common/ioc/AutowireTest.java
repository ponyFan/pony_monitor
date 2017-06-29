package com.pony.common.ioc;

/**
 * Created by zelei.fan on 2017/6/29.
 */
public class AutowireTest {

    private Person person;

    private Student student;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "AutowireTest{" +
                "person=" + person +
                ", student=" + student +
                '}';
    }
}
