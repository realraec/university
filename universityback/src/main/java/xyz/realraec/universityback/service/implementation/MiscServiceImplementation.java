package xyz.realraec.universityback.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.enumeration.Diploma;
import xyz.realraec.universityback.enumeration.Gender;
import xyz.realraec.universityback.service.MiscService;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
// Prints out information to the console as it goes
@Slf4j
public class MiscServiceImplementation implements MiscService {

    @Override
    public Department[] getDepartments() {
        log.info("Fetching all departments");
        return Department.class.getEnumConstants();
    }

    @Override
    public Gender[] getGenders() {
        log.info("Fetching all genders");
        return Gender.class.getEnumConstants();
    }

    @Override
    public Diploma[] getDiplomas() {
        log.info("Fetching all diplomas");
        return Diploma.class.getEnumConstants();
    }
}
