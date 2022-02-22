package xyz.realraec.universityback.enumeration;

public enum Gender {

    GENDER_MALE("GENDER_MALE"),
    GENDER_FEMALE("GENDER_FEMALE"),
    GENDER_OTHER("GENDER_OTHER");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
