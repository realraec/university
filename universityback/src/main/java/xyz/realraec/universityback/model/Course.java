package xyz.realraec.universityback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import xyz.realraec.universityback.enumeration.Department;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 * Course class
 */
@Entity(name = "Course")
@Table(name = "courses")
@NoArgsConstructor
public class Course extends Study {

    protected static final int CREDITS_BASE = 5;

    private long id;
    private boolean isExamMadeByProfessor = false;
    private boolean isExamTakenByStudents = false;
    private Professor professor;
    private Set<Student> students = new HashSet<>();


    public Course(String heading) {
        this.heading = heading;
        this.code = "C" + generateCodeAndDpt();

        Random random = new Random();
        isExamMadeByProfessor = ((random.nextInt(2)) == 1);
        if (isExamMadeByProfessor) {
            isExamTakenByStudents = ((random.nextInt(2)) == 0);
        } else {
            isExamTakenByStudents = false;
        }
    }

    public Course(String heading, Department department) {
        this.heading = heading;
        this.department = department;
        this.code = "C" + generateCodeAndDpt();

        isExamMadeByProfessor = false;
        isExamTakenByStudents = false;
    }

    /*public Course(String heading, Professor professor, Set<Student> students) {
        Random random = new Random();
        isExamMadeByProfessor = ((random.nextInt(2)) == 1);
        isExamTakenByStudents = ((random.nextInt(2)) == 0);

        this.heading = heading;
        this.code = "C" + generateCodeAndDpt();

        this.professor = professor;
        this.students = students;
    }*/

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @Column(name = "id",
            updatable = false
    )
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getHeading() {
        return super.getHeading();
    }

    @Override
    public String getCode() {
        return super.getCode();
    }

    @Override
    public Department getDepartment() {
        return super.getDepartment();
    }


    // Course-specific attributes
    //@JsonIgnore
    @JsonIgnoreProperties(
            {"gender", "birthdate", "email", "phone", "level", "warnings", "balance", "salary"})
    @ManyToOne(cascade = {CascadeType.ALL}
            //fetch = FetchType.LAZY
    )
    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Column(name = "is_exam_made_by_professor", nullable = false)
    public boolean isIsExamMadeByProfessor() {
        return isExamMadeByProfessor;
    }

    public void setIsExamMadeByProfessor(boolean examMadeByProfessor) {
        this.isExamMadeByProfessor = examMadeByProfessor;
    }

    @Column(name = "is_exam_taken_by_students", nullable = false)
    public boolean isIsExamTakenByStudents() {
        return isExamTakenByStudents;
    }

    public void setIsExamTakenByStudents(boolean examTakenByStudents) {
        this.isExamTakenByStudents = examTakenByStudents;
    }

    //@JsonBackReference
    /*@JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "code")*/
    //@JsonIgnore
    @JsonIgnoreProperties(
            {"gender", "birthdate", "email", "phone", "level", "warnings", "balance", "credits",
                    "totalTuition", "diploma", "majorDegree", "minorDegree", "courses"})
    @ManyToMany(
            //fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "courses_students_m2m",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
        //student.getCourses().add(this);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
        //student.getCourses().remove(this);
    }


    /**
     * Overriding of the toString() method
     * to show all the attributes of the Course object instead of its unique code
     *
     * @return Course with all its attributes to String
     */
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", heading='" + heading + '\'' +
                ", code='" + code + '\'' +
                ", department=" + department +
                ", isExamMadeByProfessor=" + isExamMadeByProfessor +
                ", isExamTakenByStudent=" + isExamTakenByStudents +
                ", professor=" + professor +
                ", students=" + students +
                '}';
    }


    /**
     * Overriding of the equals() method
     * to compare Course objects based on their id and nothing else
     *
     * @param o other Object to compare with this object
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id;
    }

    /**
     * Overriding of the hashCode() method
     * to base the identity of the Course object on its id and nothing else
     *
     * @return a hash code based on the Course's id
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
