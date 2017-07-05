package com.pony.common.ioc;

/**
 * Created by zelei.fan on 2017/6/29.
 */
public class AutowireTest {

    private Teacher teacher;

    private Student student;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
                "teacher=" + teacher +
                ", student=" + student +
                '}';
    }
}
