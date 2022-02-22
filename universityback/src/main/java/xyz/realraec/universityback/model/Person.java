package xyz.realraec.universityback.model;

import xyz.realraec.universityback.enumeration.Gender;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.Year;
import java.util.Random;

/**
 * Person class for the attributes and methods shared by both Student and Professor objects
 */
public class Person {

    protected String lastName;
    protected String firstName;
    protected String code;
    protected Gender gender;
    protected LocalDate birthdate;
    protected String email;
    protected String phone;
    protected int level ;
    protected int warnings ;
    protected double balance = 0;

    protected int generateLevel(int maxLevel) {
        Random random = new Random();
        return this.level = random.nextInt(maxLevel - 1) + 1;
    }

    protected String generateCode() {
        Random random = new Random();
        String code = String.valueOf(Year.now().getValue() - this.level).substring(2);
        if (this.getGender() == Gender.GENDER_MALE) {
            code += "01";
        } else if (this.getGender() == Gender.GENDER_FEMALE) {
            code += "02";
        } else {
            code += "00";
        }
        code += String.valueOf(random.nextInt(10000 - 1000) + 1000);
        return code;
    }

    protected LocalDate generateBirthdate() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(1980, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2005, 1, 1).toEpochDay();
        long randomDay = random.nextInt(maxDay - minDay) + minDay;
        return LocalDate.ofEpochDay(randomDay);
    }

    protected String generateEmail() {
        return firstName.toLowerCase() + "." + lastName.toLowerCase() + "@gmail.com";
    }

    protected String generatePhone() {
        Random random = new Random();
        return "06" + (random.nextInt(99999999 - 10000000) + 10000000);
    }

    protected int generateWarnings() {
        Random random = new Random();
        return random.nextInt(4);
    }




    @Column(name = "lastname", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "firstname", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "code", nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "gender", nullable = false)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "birthdate", nullable = false)
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone", nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "warnings", nullable = false)
    public int getWarnings() {
        return warnings;
    }

    public void setWarnings(int warnings) {
        this.warnings = warnings;
    }

    @Column(name = "balance", nullable = false)
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Column(name = "level", nullable = false)
    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) throws Exception {
        this.level = level;
    }

}
