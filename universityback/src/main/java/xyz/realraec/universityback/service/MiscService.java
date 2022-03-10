package xyz.realraec.universityback.service;

import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.enumeration.Diploma;
import xyz.realraec.universityback.enumeration.Gender;

@Service
public interface MiscService {

    Department[] getDepartments() throws InterruptedException;

    Gender[] getGenders() throws InterruptedException;

    Diploma[] getDiplomas() throws InterruptedException;

}
