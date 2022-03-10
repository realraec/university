package xyz.realraec.universityback.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Gender;
import xyz.realraec.universityback.model.Professor;
import xyz.realraec.universityback.repository.ProfessorRepository;
import xyz.realraec.universityback.service.ProfessorService;

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
public class ProfessorServiceImplementation implements ProfessorService {

    private final ProfessorRepository professorRepository;


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

        /*@Override
        public Server ping(String ipAddress) throws IOException {
            log.info("Pinging server IP: {}", ipAddress);
            Server server = serverRepository.findByIpAddress(ipAddress);
            // First get an internet address for the server
            InetAddress address = InetAddress.getByName(ipAddress);
            // Then check if it is reachable, and set status accordingly
            server.setStatus(address.isReachable(2000) ? Status.SERVER_UP : Status.SERVER_DOWN);
            serverRepository.save(server);
            return server;
        }*/


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

}

