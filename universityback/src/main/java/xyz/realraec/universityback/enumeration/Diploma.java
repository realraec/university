package xyz.realraec.universityback.enumeration;

public enum Diploma {

    DIPLOMA_HIGH_SCHOOL("DIPLOMA_HIGH_SCHOOL"),
    DIPLOMA_BACHELORS("DIPLOMA_BACHELORS"),
    DIPLOMA_MASTERS("DIPLOMA_MASTERS"),
    DIPLOMA_DOCTORAL("DIPLOMA_PHD");

    private final String diploma;

    Diploma(String diploma) {
        this.diploma = diploma;
    }

    public String getDiploma() {
        return diploma;
    }
}
