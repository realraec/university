package xyz.realraec.universityback.service.implementation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Gender;
import xyz.realraec.universityback.model.Person;
import xyz.realraec.universityback.repository.ProfessorRepository;
import xyz.realraec.universityback.repository.StudentRepository;
import xyz.realraec.universityback.service.PersonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional
// Prints out information to the console as it goes
@Slf4j
public class PersonServiceImplementation implements PersonService {

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;


    private JpaRepository chooseCorrectRepository(String entityType) throws Exception {
        return switch (entityType) {
            case "S" -> studentRepository;
            case "P" -> professorRepository;
            default -> throw new Exception("Invalid entity type");
        };
    }


    @Override
    @Transactional
    public ArrayList<Integer> promote(Long[] personsIdList, String personType) throws Exception {
        log.info("Promoting persons with id: {}", Arrays.toString(personsIdList));

        if (personsIdList.length == 0) {
            throw new Exception("No person was provided to perform this action on.");
        }

        JpaRepository repository = chooseCorrectRepository(personType);

        ArrayList<Person> personsList = new ArrayList<>();
        ArrayList<Integer> levelsList = new ArrayList<>();


        for (int i = 0; i < personsIdList.length; i++) {
            Object person;
            try {
                person = repository.findById(personsIdList[i]).get();
            } catch (Exception e) {
                throw new Exception("The ID is incorrect.");
            }
            personsList.add((Person) person);

            if ((personType.equals("S") && ((Person) person).getLevel() == 7) || (personType.equals("P") && ((Person) person).getLevel() == 6)) {
                throw new Exception("The level is already the highest" + (personsIdList.length == 1 ? "" : " for at least one of the persons") + ".");
            } else {
                levelsList.add(((Person) person).getLevel() + 1);
            }
        }

        for (int i = 0; i < personsList.size(); i++) {
            personsList.get(i).setLevel(levelsList.get(i));
        }

        return levelsList;
    }


    @Override
    @Transactional
    public ArrayList<Integer> demote(Long[] personsIdList, String personType) throws Exception {
        log.info("Demoting persons with id: {}", Arrays.toString(personsIdList));

        if (personsIdList.length == 0) {
            throw new Exception("No person was provided to perform this action on.");
        }

        JpaRepository repository = chooseCorrectRepository(personType);

        ArrayList<Person> personsList = new ArrayList<>();
        ArrayList<Integer> levelsList = new ArrayList<>();


        for (int i = 0; i < personsIdList.length; i++) {
            Object person;
            try {
                person = repository.findById(personsIdList[i]).get();
            } catch (Exception e) {
                throw new Exception("The ID is incorrect.");
            }
            personsList.add((Person) person);

            if (((Person) person).getLevel() == 1) {
                throw new Exception("The level is already the lowest" + (personsIdList.length == 1 ? "" : " for at least one of the persons") + ".");
            } else {
                levelsList.add(((Person) person).getLevel() - 1);
            }
        }

        for (int i = 0; i < personsList.size(); i++) {
            personsList.get(i).setLevel(levelsList.get(i));
        }

        return levelsList;
    }


    @Override
    @Transactional
    public ArrayList<Integer> giveWarning(Long[] personsIdList, String personType) throws Exception {
        log.info("Giving a warning to persons with id: {}", Arrays.toString(personsIdList));

        if (personsIdList.length == 0) {
            throw new Exception("No person was provided to perform this action on.");
        }

        JpaRepository repository = chooseCorrectRepository(personType);

        ArrayList<Person> personsList = new ArrayList<>();
        ArrayList<Integer> warningsList = new ArrayList<>();


        for (int i = 0; i < personsIdList.length; i++) {
            Object person;
            try {
                person = repository.findById(personsIdList[i]).get();
            } catch (Exception e) {
                throw new Exception("The ID is incorrect.");
            }
            personsList.add((Person) person);

            if (((Person) person).getWarnings() == 3) {
                throw new Exception("The number of warnings is already the highest" + (personsIdList.length == 1 ? "" : " for at least one of the persons") + ".");
            } else {
                warningsList.add(((Person) person).getWarnings() + 1);
            }
        }

        for (int i = 0; i < personsList.size(); i++) {
            personsList.get(i).setWarnings(warningsList.get(i));
        }

        return warningsList;
    }


    @Override
    @Transactional
    public Integer kickOut(Long[] personsIdList, String personType) throws Exception {
        log.info("Kicking out persons with id: {}", Arrays.toString(personsIdList));

        if (personsIdList.length == 0) {
            throw new Exception("No person was provided to perform this action on.");
        }

        JpaRepository repository = chooseCorrectRepository(personType);

        ArrayList<Person> personsList = new ArrayList<>();
        int maxWarning = 3;

        for (int i = 0; i < personsIdList.length; i++) {
            Object person;
            try {
                person = repository.findById(personsIdList[i]).get();
            } catch (Exception e) {
                throw new Exception("The ID is incorrect.");
            }

            personsList.add((Person) person);

            if (((Person) person).getWarnings() == maxWarning) {
                throw new Exception((personsIdList.length == 1 ? "The person" : "At least one of the persons") + " has already been kicked out.");
            }
        }

        for (int i = 0; i < personsList.size(); i++) {
            personsList.get(i).setWarnings(maxWarning);
        }

        return maxWarning;
    }


    @Override
    @Transactional
    public Object edit(Long personId, String personType, String personLastName, String personFirstName, Gender personGender, LocalDate personBirthdate,
                       String personEmail, String personPhone) throws Exception {
        log.info("Editing person with id: {}", personId);

        JpaRepository repository = chooseCorrectRepository(personType);

        Object person;
        try {
            person = repository.findById(personId).get();
        } catch (Exception e) {
            throw new Exception("The ID is incorrect.");
        }

        if (personLastName != null && personLastName.length() > 0 && !Objects.equals(((Person) person).getLastName(), personLastName)) {
            ((Person) person).setLastName(personLastName);
        }

        if (personFirstName != null && personFirstName.length() > 0 && !Objects.equals(((Person) person).getFirstName(), personFirstName)) {
            ((Person) person).setFirstName(personFirstName);
        }

        if (personGender != null && personGender.getGender().length() > 0 && !Objects.equals(((Person) person).getGender(), personGender)) {
            ((Person) person).setGender(personGender);
        }

        if (personBirthdate != null && !Objects.equals(((Person) person).getBirthdate(), personBirthdate)) {
            ((Person) person).setBirthdate(personBirthdate);
        }

        if (personEmail != null && personEmail.length() > 0 && !Objects.equals(((Person) person).getEmail(), personEmail)) {
            ((Person) person).setEmail(personEmail);
        }

        if (personPhone != null && personPhone.length() > 0 && !Objects.equals(((Person) person).getPhone(), personPhone)) {
            ((Person) person).setPhone(personPhone);
        }

        return person;
    }

}
