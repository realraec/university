package xyz.realraec.universityback.service;

import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Gender;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public interface PersonService {

    ArrayList<Integer> promote(Long[] personsIdList, String personType) throws Exception;

    ArrayList<Integer> demote(Long[] personsIdList, String personType) throws Exception;

    ArrayList<Integer> giveWarning(Long[] personsIdList, String personType) throws Exception;

    Integer kickOut(Long[] personsIdList, String personType) throws Exception;

    Object edit(Long personId, String personType, String lastName, String firstName, Gender gender, LocalDate birthdate,
                String email, String phone) throws Exception;

}
