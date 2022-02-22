package xyz.realraec.universityback.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Diploma;
import xyz.realraec.universityback.enumeration.Gender;
import xyz.realraec.universityback.model.Degree;
import xyz.realraec.universityback.model.Student;
import xyz.realraec.universityback.repository.StudentRepository;
import xyz.realraec.universityback.service.StudentService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;


// Creates a constructor using the one attribute as argument
@RequiredArgsConstructor
@Service
@Transactional
// Prints out information to the console as it goes
@Slf4j
public class StudentServiceImplementation implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public Student create(Student student) {
        // Prints the following
        log.info("Saving new student: {}", student.getCode());
        return studentRepository.save(student);
    }

    @Override
    public Collection<Student> list(int limit) {
        log.info("Fetching all students");
        // From the first element up to the limit
        return studentRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Student get(Long id) {
        log.info("Fetching student with id: {}", id);
        // The get() method returns the actual server found by id
        return studentRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Student update(Long id, String code, String lastName, String firstName, Gender gender, LocalDate birthdate, Integer level, Degree majorDegree, Degree minorDegree, String email, String phone, Double tuition, Double balance, Integer credits, Diploma diploma, Integer warnings) throws Exception {
        Student student = get(id);

        if (code != null && code.length() > 0 && !Objects.equals(student.getCode(), code)) {
            student.setCode(code);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(student.getLastName(), lastName)) {
            student.setLastName(lastName);
        }

        if (firstName != null && firstName.length() > 0 && !Objects.equals(student.getFirstName(), firstName)) {
            student.setFirstName(firstName);
        }

        if (gender != null && gender.getGender().length() > 0 && !Objects.equals(student.getGender(), gender)) {
            student.setGender(gender);
        }

        if (birthdate != null && !Objects.equals(student.getBirthdate(), birthdate)) {
            student.setBirthdate(birthdate);
        }


        if (level != null && student.getLevel() != level) {
            try {
                student.setLevel(level);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (majorDegree != null && majorDegree.getCode().length() > 0 && !Objects.equals(student.getMajorDegree(), majorDegree)) {
            student.setMajorDegree(majorDegree);
        }

        if (minorDegree != null && minorDegree.getCode().length() > 0 && !Objects.equals(student.getMinorDegree(), minorDegree)) {
            student.setMinorDegree(minorDegree);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            student.setEmail(email);
        }

        if (phone != null && phone.length() > 0 && !Objects.equals(student.getPhone(), phone)) {
            student.setPhone(phone);
        }

        if (tuition != null && student.getTotalTuition() != tuition) {
            student.setTotalTuition(tuition);
        }

        if (balance != null && student.getBalance() != balance) {
            student.setBalance(balance);
        }

        if (credits != null && student.getCredits() != credits) {
            student.setCredits(credits);
        }

        if (diploma != null && diploma.getDiploma().length() > 0 && !Objects.equals(student.getDiploma(), diploma)) {
            student.setDiploma(diploma);
        }

        if (warnings != null && student.getWarnings() != warnings) {
            student.setWarnings(warnings);
        }

        log.info("Updating student with id: {}", student.getId());
        return student;
    }

    @Override
    @Transactional
    public Boolean replace(Long id, Student newStudent) throws Exception {
        Student oldStudent = get(id);

        String oldCode = oldStudent.getCode();
        String newCode = newStudent.getCode();
        if (oldCode != null && oldCode.length() > 0
                && newCode != null && newCode.length() > 0
                && !Objects.equals(oldCode, newCode)) {
            oldStudent.setCode(newCode);
        }

        String oldLastName = oldStudent.getLastName();
        String newLastName = newStudent.getLastName();
        if (oldLastName != null && oldLastName.length() > 0
                && newLastName != null && newLastName.length() > 0
                && !Objects.equals(oldLastName, newLastName)) {
            oldStudent.setLastName(newLastName);
        }

        String oldFirstName = oldStudent.getFirstName();
        String newFirstName = newStudent.getFirstName();
        if (oldFirstName != null && oldFirstName.length() > 0
                && newFirstName != null && newFirstName.length() > 0
                && !Objects.equals(oldFirstName, newFirstName)) {
            oldStudent.setFirstName(newFirstName);
        }

        Gender oldGender = oldStudent.getGender();
        Gender newGender = newStudent.getGender();
        if (oldGender != null && oldGender.getGender().length() > 0
                && newGender != null && newGender.getGender().length() > 0
                && !Objects.equals(oldGender, newGender)) {
            oldStudent.setGender(newGender);
        }

        Degree oldMajorDegree = oldStudent.getMajorDegree();
        Degree newMajorDegree = oldStudent.getMajorDegree();
        if (oldMajorDegree != null && oldMajorDegree.getCode().length() > 0
                && newMajorDegree != null && newMajorDegree.getCode().length() > 0
                && !Objects.equals(oldMajorDegree, newMajorDegree)) {
            oldStudent.setMajorDegree(newMajorDegree);
        }

        Degree oldMinorDegree = oldStudent.getMinorDegree();
        Degree newMinorDegree = oldStudent.getMinorDegree();
        if (oldMinorDegree != null && oldMinorDegree.getCode().length() > 0
                && newMinorDegree != null && newMinorDegree.getCode().length() > 0
                && !Objects.equals(oldMinorDegree, newMinorDegree)) {
            oldStudent.setMinorDegree(newMinorDegree);
        }

        String oldEmail = oldStudent.getEmail();
        String newEmail = newStudent.getEmail();
        if (oldEmail != null && oldEmail.length() > 0
                && newEmail != null && newEmail.length() > 0
                && !Objects.equals(oldEmail, newEmail)) {
            oldStudent.setEmail(newEmail);
        }

        String oldPhone = oldStudent.getPhone();
        String newPhone = newStudent.getPhone();
        if (oldPhone != null && oldPhone.length() > 0
                && newPhone != null && newPhone.length() > 0
                && !Objects.equals(oldPhone, newPhone)) {
            oldStudent.setPhone(newPhone);
        }

        double oldTuition = oldStudent.getTotalTuition();
        double newTuition = newStudent.getTotalTuition();
        if (oldTuition != newTuition) {
            oldStudent.setTotalTuition(newTuition);
        }

        double oldBalance = oldStudent.getBalance();
        double newBalance = newStudent.getBalance();
        if (oldBalance != newBalance) {
            oldStudent.setBalance(newBalance);
        }

        int oldCredits = oldStudent.getCredits();
        int newCredits = newStudent.getCredits();
        if (oldCredits != newCredits) {
            oldStudent.setCredits(newCredits);
        }

        Diploma oldDiploma = oldStudent.getDiploma();
        Diploma newDiploma = oldStudent.getDiploma();
        if (
                oldDiploma != null && oldDiploma.getDiploma().length() > 0
                        && newDiploma != null && newDiploma.getDiploma().length() > 0
                        && !Objects.equals(oldDiploma, newDiploma)) {
            oldStudent.setDiploma(newDiploma);
        }

        int oldWarnings = oldStudent.getWarnings();
        int newWarnings = newStudent.getWarnings();
        if (oldWarnings != newWarnings) {
            oldStudent.setWarnings(newWarnings);
        }

        log.info("Replacing student with id: {}", id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting student with id: {}", id);
        studentRepository.deleteById(id);
        return Boolean.TRUE;
    }
}

