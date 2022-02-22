package xyz.realraec.universityback.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import xyz.realraec.universityback.enumeration.Gender;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

/**
 * Professor class
 */
@Entity(name = "Professor")
@Table(name = "professors")
// Creates a constructor using no attribute as argument
@NoArgsConstructor
// Creates a constructor using all attributes as arguments
//@AllArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Professor extends Person {

    protected static final double MINIMUM_WAGE = 1539.42;

    private long id;
    private double salary;


    public Professor(String lastName, String firstName, Gender gender) throws Exception {
        Random random = new Random();

        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.email = generateEmail();
        this.birthdate = generateBirthdate();
        this.phone = generatePhone();
        this.level = generateLevel(7);
        this.code = "P" + generateCode();
        this.warnings = generateWarnings();

        calculateSalary();
        this.balance += (random.nextInt(3) * salary);
    }



    @Id
    @SequenceGenerator(
            name = "professor_sequence",
            sequenceName = "professor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "professor_sequence"
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

    /**
     * Overriding of the Person setLevel(int level) method
     * to make sure the level is correct before changing it
     *
     * @param level to give to the Professor object
     * @throws Exception "Invalid level for a professor."
     */
    @Override
    public void setLevel(int level) throws Exception {
        if ((level >= 1) && (level <= 6)) {
            super.setLevel(level);
            calculateSalary();
        } else if (level == 0) {
            super.setLevel(level);
            salary = 0;
        } else {
            throw new Exception("Invalid level for a professor.");
        }
    }


    // Professor-specific attributes
    @Column(name = "salary", nullable = false)
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Method to calculate the salary a Professor is entitled to based on their level
     *
     * @return the salary
     * @throws Exception "The salary could not be calculated."
     */
    public boolean calculateSalary() throws Exception {
        if (level >= 1 && level <= 6) {
            switch (level) {
                case 2:
                    salary = MINIMUM_WAGE * 1.2;
                    break;
                case 3:
                    salary = MINIMUM_WAGE * 1.4;
                    break;
                case 4:
                    salary = MINIMUM_WAGE * 1.6;
                    break;
                case 5:
                    salary = MINIMUM_WAGE * 1.8;
                    break;
                case 6:
                    salary = MINIMUM_WAGE * 2;
                    break;
                default:
                    salary = MINIMUM_WAGE;
            }
            return true;
        } else {
            throw new Exception("The salary could not be calculated.");
        }
    }


    /**
     * Overriding of the toString() method
     * to show all the attributes of the Professor object instead of its unique code
     *
     * @return Professor with all its attributes to String
     */
    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", sex=" + gender +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", level=" + level +
                ", warnings=" + warnings +
                ", accountBalance=" + balance +
                ", salary=" + salary +
                '}';
    }

    /**
     * Overriding of the equals() method
     * to compare Professor objects based on their id and nothing else
     *
     * @param o other Object to compare with this object
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return id == professor.id;
    }

    /**
     * Overriding of the hashCode() method
     * to base the identity of the Professor object on its id and nothing else
     *
     * @return a hash code based on the Student's id
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
