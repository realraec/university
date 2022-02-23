package xyz.realraec.universityback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import xyz.realraec.universityback.enumeration.Diploma;
import xyz.realraec.universityback.enumeration.Gender;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

/**
 * Student class
 */

@Entity(name = "Student")
@Table(name = "students")
// Creates a constructor using no attribute as argument
@NoArgsConstructor
// Creates a constructor using all attributes as arguments
//@AllArgsConstructor
public class Student extends Person {

    protected static final int CREDITS_PER_YEAR_COMPLETED = 60;
    protected static final int TUITION_FOR_BACHELORS_YEAR = 170;
    protected static final int TUITION_FOR_MASTERS_YEAR = 243;
    protected static final int TUITION_FOR_DOCTORATE_YEAR = 380;


    private long id;
    private int credits;
    private double totalTuition;
    private Degree majorDegree;
    private Degree minorDegree;
    private Diploma diploma = Diploma.DIPLOMA_DOCTORAL;
    private Set<Course> courses;


    /*public Student() throws Exception {
        this.lastName = "Stu";
        this.firstName = "Dent";
        this.sex = Sex.SEX_MALE;
        this.email = "Email";
        this.phone = "Phone";
        this.majorDegree = null;
        this.minorDegree = null;
        this.diploma = "";
        this.level = 1;
        calculateCredits();
        calculateTotalTuition();
        this.accountBalance -= totalTuition;
    }*/

    public Student(String lastName, String firstName, Gender gender) throws Exception {

        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.email = generateEmail();
        this.birthdate = generateBirthdate();
        this.phone = generatePhone();
        this.level = generateLevel(8);
        this.code = "S" + generateCode();
        this.warnings = generateWarnings();

        calculateCredits();
        calculateTotalTuition();
        calculateDiploma();
        this.balance -= totalTuition;
    }


   /* @Override
    protected String generateEmail() {
        return this.firstName + "." + this.lastName + "@gmail.com";
        //        return firstName.toLowerCase() + "." + lastName.toLowerCase() + "@gmail.com";
    }*/


    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(name = "id",
            updatable = false
    )
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }


    // Shared attributes
    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public String getCode() {
        return super.getCode();
    }

    @Override
    public Gender getGender() {
        return super.getGender();
    }

    @Override
    public LocalDate getBirthdate() {
        return super.getBirthdate();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public int getWarnings() {
        return super.getWarnings();
    }

    @Override
    public double getBalance() {
        return super.getBalance();
    }

    @Override
    public int getLevel() {
        return super.getLevel();
    }

    @Override
    public void setLevel(int level) throws Exception {
        if ((level >= 1) && (level <= 7)) {
            this.level = level;
            calculateCredits();
            calculateTotalTuition();
        } else if (level == 0) {
            this.level = level;
        } else {
            throw new Exception("Invalid level for a student.");
        }
    }


    // Student-specific attributes
    @Column(name = "total_tuition", nullable = false)
    public double getTotalTuition() {
        return totalTuition;
    }

    public void setTotalTuition(double tuitionLeftToPay) {
        this.totalTuition = tuitionLeftToPay;
    }

    @Column(name = "credits", nullable = false)
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Column(name = "diploma")
    public Diploma getDiploma() {
        return diploma;
    }

    public void setDiploma(Diploma diploma) {
        this.diploma = diploma;
    }


    @JsonIgnore
    //@JsonIgnoreProperties(
    //        {"department", "isExamMadeByProfessor", "isExamTakenByStudents", "professor", "students"})
    @ManyToMany(
            //fetch = FetchType.EAGER,
            mappedBy = "students"
    )
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }



    //@JsonIgnore
    @JsonIgnoreProperties(value = {"department", "courses"})
    @ManyToOne(
            //fetch = FetchType.LAZY
    )
    public Degree getMajorDegree() {
        return majorDegree;
    }

    @Transactional
    public void setMajorDegree(Degree majorDegree) throws Exception {
        this.majorDegree = majorDegree;
        if (majorDegree != null) {
            for (Course course : this.majorDegree.getCourses()) {
                course.addStudent(this);
            }
        } else {
            throw new Exception("The degree mustn't be null.");
        }
    }

    //@JsonIgnore
    @JsonIgnoreProperties(value = {"department", "courses"})
    @ManyToOne(
            //fetch = FetchType.LAZY
    )
    public Degree getMinorDegree() {
        return minorDegree;
    }

    @Transactional
    public void setMinorDegree(Degree minorDegree) throws Exception {
        this.minorDegree = minorDegree;
        if (minorDegree != null) {
            for (Course course : this.minorDegree.getCourses()) {
                course.addStudent(this);
            }
        } else {
            throw new Exception("The degree mustn't be null.");
        }
    }


    /**
     * Method to calculate the credits obtained by a Student based on their level
     *
     * @return the amount of credits obtained
     * @throws Exception "The credits could not be calculated."
     */
    public boolean calculateCredits() throws Exception {
        if (level < 1 || level > 7) {
            throw new Exception("The credits could not be calculated.");
        } else {
            switch (level) {
                case 2:
                    credits = CREDITS_PER_YEAR_COMPLETED * 1;
                    break;
                case 3:
                    credits = CREDITS_PER_YEAR_COMPLETED * 2;
                    break;
                case 4:
                    credits = CREDITS_PER_YEAR_COMPLETED * 3;
                    break;
                case 5:
                    credits = CREDITS_PER_YEAR_COMPLETED * 4;
                    break;
                case 6:
                    credits = CREDITS_PER_YEAR_COMPLETED * 5;
                    break;
                case 7:
                    credits = CREDITS_PER_YEAR_COMPLETED * 6;
                    break;
                default:
                    credits = CREDITS_PER_YEAR_COMPLETED * 0;
            }
            return true;
        }
    }

    /**
     * Method to calculate the total tuition a Student has to pay off based on their level
     *
     * @return the total tuition
     * @throws Exception "The tuition could not be calculated."
     */
    public boolean calculateTotalTuition() throws Exception {
        if (level < 1 || level > 7) {
            throw new Exception("The tuition could not be calculated.");
        } else {
            switch (level) {
                case 4:
                case 5:
                    totalTuition += TUITION_FOR_MASTERS_YEAR;
                    break;
                case 6:
                case 7:
                    totalTuition += TUITION_FOR_DOCTORATE_YEAR;
                    break;
                case 2:
                case 3:
                default:
                    totalTuition += TUITION_FOR_BACHELORS_YEAR;
            }
            return true;
        }
    }


    public boolean calculateDiploma() throws Exception {
        if (level < 1 || level > 8) {
            throw new Exception("The tuition could not be calculated.");
        } else {
            if (level <= 3) {
                this.diploma = Diploma.DIPLOMA_HIGH_SCHOOL;
            } else if (level <= 5) {
                this.diploma = Diploma.DIPLOMA_BACHELORS;
            } else if (level <= 7) {
                this.diploma = Diploma.DIPLOMA_MASTERS;
            } else {
                this.diploma = Diploma.DIPLOMA_DOCTORAL;
            }
            return true;
        }
    }

    /**
     * Method to easily retrieve a set of all the Courses
     * the Student is enrolled in based on their two degrees
     *
     * @return a set of all the Courses the Student is enrolled in
     */
    /*public Set<Course> enrolledInCourses() {
        HashSet<Course> coursesSet = new HashSet<>();
        coursesSet.addAll(majorDegree.getCourses());
        coursesSet.addAll(minorDegree.getCourses());
        return coursesSet;
    }*/

    /**
     * Method for a Student to take an exam for a given Course
     *
     * @param course the Student takes the exam for
     * @return true if successful
     * @throws Exception "The student could not take the exam."
     */
    /*public boolean takeExam(Course course) throws Exception {
        if (!coursesCompleted.contains(course)
                && course.isExamMadeByProfessor()
                && enrolledInCourses().contains(course)) {
            course.setExamTakenByStudent(true);
            DALQueries.update(course);
            return true;
        } else {
            throw new Exception("The student could not take the exam.");
        }
    }*/


    /**
     * Method for a Student to put money on their account to pay for their tuition
     *
     * @param amount of money to put on the account
     * @return true if successful
     * @throws Exception "Could not put this amount of money on the account."
     */
    /*public boolean putMoneyOnAccount(double amount) throws Exception {
        System.out.println(accountBalance);
        if (accountBalance + amount <= 500) {
            accountBalance += amount;
            return true;
        } else {
            throw new Exception("Could not put this amount of money on the account.");
        }
    }*/


    /**
     * Overriding of the toString() method
     * to show all the attributes of the Student object instead of its unique code
     *
     * @return Student with all its attributes to String
     */
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", sex='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", level=" + level +
                ", warnings=" + warnings +
                ", credits=" + credits +
                ", diploma='" + diploma + '\'' +
                ", totalTuition=" + totalTuition +
                //", tuitionLeftToPay=" + tuitionLeftToPay +
                '}';
    }

    /**
     * Overriding of the equals() method
     * to compare Student objects based on their id and nothing else
     *
     * @param o other Object to compare with this object
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    /**
     * Overriding of the hashCode() method
     * to base the identity of the Student object on its id and nothing else
     *
     * @return a hash code based on the Student's id
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
