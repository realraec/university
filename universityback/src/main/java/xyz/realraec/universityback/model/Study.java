package xyz.realraec.universityback.model;

import xyz.realraec.universityback.enumeration.Department;

import javax.persistence.Column;
import java.time.Year;
import java.util.Random;

public class Study {

    protected String heading;
    protected String code;
    protected Department department;

    protected String generateCodeAndDpt() {
        Random random = new Random();
        String code = "";

        // Level
        if (this.heading.endsWith("100")) {
            code += "100";
        } else if (this.heading.endsWith("200")) {
            code += "200";
        } else if (this.heading.endsWith("300")) {
            code += "300";
        }

        // Year
        code += String.valueOf(Year.now().getValue()).substring(2);

        // Department
        if (this.heading.startsWith("Maths")
                || this.heading.startsWith("Physics")
                || this.heading.startsWith("Algebra")
                || this.heading.startsWith("Geometry")
                || this.heading.startsWith("Statistics")) {
            code += "01";
            this.department = Department.DEPARTMENT_HARD_SCIENCES;
        } else if (this.heading.startsWith("US Literature")
                || this.heading.startsWith("UK Literature")) {
            code += "02";
            this.department = Department.DEPARTMENT_ARTS;
        } else if (this.heading.startsWith("History")
                || this.heading.startsWith("US History")
                || this.heading.startsWith("UK History")
                || this.heading.startsWith("Indian History")
                || this.heading.startsWith("French History")
                || this.heading.startsWith("Linguistics")
                || this.heading.startsWith("Economics")) {
            code += "03";
            this.department = Department.DEPARTMENT_SOCIAL_SCIENCES;
        } else if (this.heading.startsWith("Languages")
                || this.heading.startsWith("English")
                || this.heading.startsWith("German")
                || this.heading.startsWith("French")) {
            code += "04";
            this.department = Department.DEPARTMENT_LANGUAGES;
        } else {
            code += "00";
            this.department = Department.DEPARTMENT_MISC;
        }

        code += String.valueOf(random.nextInt(1000 - 100) + 100);
        return code;
    }


    @Column(name = "heading", nullable = false)
    public String getHeading() {
        return heading;
    }

    public void setHeading(String name) {
        this.heading = name;
    }

    @Column(name = "code", nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "department", nullable = false)
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
