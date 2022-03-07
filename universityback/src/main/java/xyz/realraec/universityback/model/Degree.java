package xyz.realraec.universityback.model;

import com.fasterxml.jackson.annotation.*;
import lombok.NoArgsConstructor;
import xyz.realraec.universityback.enumeration.Department;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Degree class
 */
@Table(name = "degrees")
@Entity(name = "Degree")
@NoArgsConstructor
public class Degree extends Study {

    private long id;
    private Set<Course> courses = new HashSet<>();

    public Degree(String heading) {
        this.heading = heading;
        this.code = "D" + generateCodeAndDpt();
    }

    public Degree(String heading, Department department) {
        this.heading = heading;
        this.department = department;
        this.code = "D" + generateCodeAndDpt();
    }

    public Degree(String heading, HashSet<Course> courses) {
        this.heading = heading;
        this.code = "D" + generateCodeAndDpt();
        this.courses = courses;
    }

    @Id
    @SequenceGenerator(
            name = "degree_sequence",
            sequenceName = "degree_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "degree_sequence"
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


    // Shared attributes
    @Override
    public String getHeading() {
        return heading;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Department getDepartment() {
        return department;
    }


    // Degree-specific attributes
    //@JsonIgnore
    @JsonIgnoreProperties(
            {"department", "isExamMadeByProfessor", "isExamTakenByStudents", "professor", "students"})
    @OneToMany(
            //fetch = FetchType.EAGER
    )
    @JoinColumn(name = "part_of_degree_id")
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Transactional
    public void addCourse(Course course) {
        this.courses.add(course);
    }

    @Transactional
    public void removeCourse(Course course) {
        this.courses.remove(course);
    }



    /**
     * Overriding of the toString() method
     * to show all the attributes of the Degree object instead of its unique code
     *
     * @return Degree with all its attributes to String
     */
    @Override
    public String toString() {
        return "Degree{" +
                "id=" + id +
                ", heading='" + heading + '\'' +
                ", code='" + code + '\'' +
                ", department=" + department +
                ", courses=" + courses +
                '}';
    }

    /**
     * Overriding of the equals() method
     * to compare Degree objects based on their id and nothing else
     *
     * @param o other Object to compare with this object
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Degree degree = (Degree) o;
        return id == degree.id;
    }

    /**
     * Overriding of the hashCode() method
     * to base the identity of the Degree object on its id and nothing else
     *
     * @return a hash code based on the Degree's id
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
