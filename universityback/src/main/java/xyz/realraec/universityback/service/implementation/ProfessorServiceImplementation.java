package xyz.realraec.universityback.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.enumeration.Gender;
import xyz.realraec.universityback.model.Course;
import xyz.realraec.universityback.model.Professor;
import xyz.realraec.universityback.repository.CourseRepository;
import xyz.realraec.universityback.repository.ProfessorRepository;
import xyz.realraec.universityback.service.ProfessorService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ProfessorServiceImplementation implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;


    @Override
    public Professor create(Professor professor) {
        // Prints the following
        log.info("Saving new professor: {}", professor.getCode());
        return professorRepository.save(professor);
    }


    @Override
    public Collection<Professor> list(int limit) {
        log.info("Fetching all professors");
        // From the first element up to the limit
        return professorRepository.findAll(PageRequest.of(0, limit)).toList();
    }


    @Override
    public Professor get(Long id) {
        log.info("Fetching professor with id: {}", id);
        // The get() method returns the actual server found by id
        return professorRepository.findById(id).get();
    }


    @Override
    @Transactional
    public Boolean update(Long id, String code, String lastName, String firstName, Gender gender, LocalDate birthdate, Integer level, String email, String phone, Double salary, Double balance, Integer warnings) {
        Professor professor = get(id);

        if (code != null && code.length() > 0 && !Objects.equals(professor.getCode(), code)) {
            professor.setCode(code);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(professor.getLastName(), lastName)) {
            professor.setLastName(lastName);
        }

        if (firstName != null && firstName.length() > 0 && !Objects.equals(professor.getFirstName(), firstName)) {
            professor.setFirstName(firstName);
        }

        if (gender != null && gender.getGender().length() > 0 && !Objects.equals(professor.getGender(), gender)) {
            professor.setGender(gender);
        }

        if (birthdate != null && !Objects.equals(professor.getBirthdate(), birthdate)) {
            professor.setBirthdate(birthdate);
        }

        if (level != null && professor.getLevel() != level) {
            try {
                professor.setLevel(level);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (email != null && email.length() > 0 && !Objects.equals(professor.getEmail(), email)) {
            professor.setEmail(email);
        }

        if (phone != null && phone.length() > 0 && !Objects.equals(professor.getPhone(), phone)) {
            professor.setPhone(phone);
        }

        if (salary != null && professor.getSalary() != salary) {
            professor.setSalary(salary);
        }

        if (balance != null && professor.getBalance() != balance) {
            professor.setBalance(balance);
        }

        if (warnings != null && professor.getWarnings() != warnings) {
            professor.setWarnings(warnings);
        }

        log.info("Updating professor with id: {}", professor.getId());
        return Boolean.TRUE;
    }


    @Override
    @Transactional
    public Boolean replace(Long id, Professor newProfessor) {
        Professor oldProfessor = get(id);

        String oldCode = oldProfessor.getCode();
        String newCode = newProfessor.getCode();
        if (oldCode != null && oldCode.length() > 0
                && newCode != null && newCode.length() > 0
                && !Objects.equals(oldCode, newCode)) {
            oldProfessor.setCode(newCode);
        }

        String oldLastName = oldProfessor.getLastName();
        String newLastName = newProfessor.getLastName();
        if (oldLastName != null && oldLastName.length() > 0
                && newLastName != null && newLastName.length() > 0
                && !Objects.equals(oldLastName, newLastName)) {
            oldProfessor.setLastName(newLastName);
        }

        String oldFirstName = oldProfessor.getFirstName();
        String newFirstName = newProfessor.getFirstName();
        if (oldFirstName != null && oldFirstName.length() > 0
                && newFirstName != null && newFirstName.length() > 0
                && !Objects.equals(oldFirstName, newFirstName)) {
            oldProfessor.setFirstName(newFirstName);
        }

        Gender oldGender = oldProfessor.getGender();
        Gender newGender = newProfessor.getGender();
        if (oldGender != null && oldGender.getGender().length() > 0
                && newGender != null && newGender.getGender().length() > 0
                && !Objects.equals(oldGender, newGender)) {
            oldProfessor.setGender(newGender);
        }

        String oldEmail = oldProfessor.getEmail();
        String newEmail = newProfessor.getEmail();
        if (oldEmail != null && oldEmail.length() > 0
                && newEmail != null && newEmail.length() > 0
                && !Objects.equals(oldEmail, newEmail)) {
            oldProfessor.setEmail(newEmail);
        }

        String oldPhone = oldProfessor.getPhone();
        String newPhone = newProfessor.getPhone();
        if (oldPhone != null && oldPhone.length() > 0
                && newPhone != null && newPhone.length() > 0
                && !Objects.equals(oldPhone, newPhone)) {
            oldProfessor.setPhone(newPhone);
        }

        double oldSalary = oldProfessor.getSalary();
        double newSalary = newProfessor.getSalary();
        if (oldSalary != newSalary) {
            oldProfessor.setSalary(newSalary);
        }

        double oldBalance = oldProfessor.getBalance();
        double newBalance = newProfessor.getBalance();
        if (oldBalance != newBalance) {
            oldProfessor.setBalance(newBalance);
        }

        int oldWarnings = oldProfessor.getWarnings();
        int newWarnings = newProfessor.getWarnings();
        if (oldWarnings != newWarnings) {
            oldProfessor.setWarnings(newWarnings);
        }

        log.info("Replacing professor with id: {}", id);
        return Boolean.TRUE;
    }


    @Override
    public Boolean delete(Long id) {
        log.info("Deleting professor with id: {}", id);
        professorRepository.deleteById(id);
        return Boolean.TRUE;
    }


    @Override
    public ArrayList<Set<Course>> getCoursesTaughtByProfessor(Long[] professorsIdList) throws Exception {
        log.info("Getting courses taught by professor with id: {}", Arrays.toString(professorsIdList));

        if (professorsIdList.length == 0) {
            throw new Exception("No student was provided to perform this action on.");
        }

        ArrayList<Set<Course>> coursesList = new ArrayList<>();
        for (int i = 0; i < professorsIdList.length; i++) {
            coursesList.add(courseRepository.findByProfessorId(professorsIdList[i]));
        }

        return coursesList;
    }


    @Override
    @Transactional
    public Boolean deleteProfessors(Long[] entitiesIdList) throws Exception {
        log.info("Deleting entities (professors) with id: {}", Arrays.toString(entitiesIdList));

        if (entitiesIdList.length == 0) {
            throw new Exception("No entity (professor) was provided to perform this action on.");
        }

        //ArrayList<Professor> professorsList = new ArrayList<>();

        for (int i = 0; i < entitiesIdList.length; i++) {
            if (!professorRepository.existsById(entitiesIdList[i])) {
                throw new Exception("The ID is incorrect" + (entitiesIdList.length == 1 ? "" : " for at least one of the entities") + ".");
            }
        }

        ArrayList<Set<Course>> coursesTaughtByProfessorsList = getCoursesTaughtByProfessor(entitiesIdList);

        for (int i = 0; i < coursesTaughtByProfessorsList.size(); i++) {
            Set<Course> temp = coursesTaughtByProfessorsList.get(i);
            for (Course course : temp) {
                course.setProfessor(null);
            }
            /*for (int j = 0; j < coursesTaughtByProfessorsList.get(i).size(); j++) {
                coursesTaughtByProfessorsList.get(i).get(j).setProfessor(null);
            }*/
        }

        for (int i = 0; i < entitiesIdList.length; i++) { //professorsList.size()
            //Professor professor = professorsList.get(i);
            professorRepository.deleteById(entitiesIdList[i]);
        }

        return Boolean.TRUE;
    }


    @Override
    public Integer getNumberProfessors() {
        log.info("Getting the number of professors");
        return professorRepository.queryNumberEntries();
    }


    @Override
    public ArrayList<Integer> getNumberProfessorsPerLevel() {
        log.info("Getting the number of professors per level");

        ArrayList<Integer> numberProfessorsPerLevel = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            numberProfessorsPerLevel.add(professorRepository.queryNumberEntriesPerLevel(i));
        }

        return numberProfessorsPerLevel;
    }


    @Override
    public Map<Gender, Integer> getNumberProfessorsPerGender() {
        log.info("Getting the number of professors per gender");

        Map<Gender, Integer> numberProfessorsPerGenderMap = new HashMap<>();
        Gender[] genders = Gender.class.getEnumConstants();
        for (int i = 0; i < genders.length; i++) {
            Gender temp = genders[i];
            numberProfessorsPerGenderMap.put(temp, professorRepository.queryNumberEntriesPerGender(temp));
        }

        return numberProfessorsPerGenderMap;
    }


    @Override
    public Map<String, Object> getMostOrLeastCoursesTaught(boolean mostOrLeast) {
        log.info("Getting the " + (mostOrLeast ? "highest" : "lowest") + " number of courses taught");

        Map<String, Object> numberCoursesAndProfessorMap = new HashMap<>();
        int numberProfessors = professorRepository.queryNumberEntries();
        long idProfessor = 1L;
        while (professorRepository.findById(idProfessor).isEmpty()) {
            idProfessor++;
        }
        int numberCoursesTaught = professorRepository.queryNumberCoursesTaught(idProfessor);
        for (long i = idProfessor + 1; i <= numberProfessors; i++) {
            int temp = professorRepository.queryNumberCoursesTaught(i);
            if ((mostOrLeast && temp > numberCoursesTaught)
                    || (!mostOrLeast && temp < numberCoursesTaught)) {
                numberCoursesTaught = temp;
                idProfessor = i;
            }
        }

        numberCoursesAndProfessorMap.put("person", professorRepository.findById(idProfessor).get());
        numberCoursesAndProfessorMap.put("number", numberCoursesTaught);

        return numberCoursesAndProfessorMap;
    }

}

