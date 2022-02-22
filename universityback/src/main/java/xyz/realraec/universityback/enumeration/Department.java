package xyz.realraec.universityback.enumeration;

public enum Department {

    DEPARTMENT_HARD_SCIENCES("DEPARTMENT_HARD_SCIENCES"),
    DEPARTMENT_SOCIAL_SCIENCES("DEPARTMENT_SOCIAL_SCIENCES"),
    DEPARTMENT_LANGUAGES("DEPARTMENT_LANGUAGES"),
    DEPARTMENT_ARTS("DEPARTMENT_ARTS"),
    DEPARTMENT_MISC("DEPARTMENT_MISC");

    private final String department;

    Department(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}
