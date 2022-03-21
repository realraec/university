package xyz.realraec.universityback.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import xyz.realraec.universityback.model.Course;
import xyz.realraec.universityback.model.Degree;
import xyz.realraec.universityback.model.Professor;
import xyz.realraec.universityback.model.Student;
import xyz.realraec.universityback.repository.CourseRepository;
import xyz.realraec.universityback.repository.DegreeRepository;
import xyz.realraec.universityback.repository.ProfessorRepository;
import xyz.realraec.universityback.repository.StudentRepository;
import xyz.realraec.universityback.service.EntityService;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class EntityServiceImplementation implements EntityService {

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;
    private final DegreeRepository degreeRepository;

    private JpaRepository chooseCorrectRepository(String entityType) throws Exception {
        return switch (entityType) {
            case "S" -> studentRepository;
            case "P" -> professorRepository;
            case "C" -> courseRepository;
            case "D" -> degreeRepository;
            default -> throw new Exception("Invalid entity type");
        };
    }

    @Override
    public ArrayList<Optional> refreshEntities(Long[] entitiesIdList, String entityType) throws Exception {
        log.info("Refreshing entities with id: {}", Arrays.toString(entitiesIdList));

        JpaRepository repository = chooseCorrectRepository(entityType);

        if (entitiesIdList.length == 0) {
            throw new Exception("No entity was provided to perform this action on.");
        }

        ArrayList<Optional> entitiesList = new ArrayList<>();

        for (int i = 0; i < entitiesIdList.length; i++) {

            Optional entity;
            try {
                entity = repository.findById(entitiesIdList[i]);
            } catch (Exception e) {
                throw new Exception("The ID is incorrect" + (entitiesIdList.length == 1 ? "" : " for at least one of the entities") + ".");
            }

            entitiesList.add(entity);
        }

        return entitiesList;
    }

    @Override
    @Transactional
    public Boolean deleteEntities(Long[] entitiesIdList, String entityType) throws Exception {
        log.info("Deleting entities with id: {}", Arrays.toString(entitiesIdList));

        JpaRepository repository = chooseCorrectRepository(entityType);

        if (entitiesIdList.length == 0) {
            throw new Exception("No entity was provided to perform this action on.");
        }

        ArrayList<Object> entitiesList = new ArrayList<>();

        for (int i = 0; i < entitiesIdList.length; i++) {

            Object entity;
            try {
                entity = repository.findById(entitiesIdList[i]).get();
            } catch (Exception e) {
                throw new Exception("The ID is incorrect" + (entitiesIdList.length == 1 ? "" : " for at least one of the entities") + ".");
            }

            entitiesList.add(entity);
        }

        for (int i = 0; i < entitiesList.size(); i++) {
            repository.deleteById(entitiesIdList[i]);
        }

        return Boolean.TRUE;
    }


    @Override
    public ArrayList<String> contactPersonsRelatedToEntities(Long[] entitiesIdList, String entityType) throws Exception {
        log.info("Contacting persons related to entities with id: {}", Arrays.toString(entitiesIdList));
        Exception customException = new Exception("The ID is incorrect" + (entitiesIdList.length == 1 ? "" : " for at least one of the entities") + ".");

        //JpaRepository repository = chooseCorrectRepository(entityType);

        if (entitiesIdList.length == 0) {
            throw new Exception("No entity was provided to perform this action on.");
        }

        ArrayList<String> emailsList = new ArrayList<>();

        switch (entityType) {
            case "S":
                for (int i = 0; i < entitiesIdList.length; i++) {
                    if (!studentRepository.existsById(entitiesIdList[i]))
                        throw customException;
                    Student student = studentRepository.getById(entitiesIdList[i]);
                    String temp = student.getEmail();
                    if (!emailsList.contains(temp))
                        emailsList.add(temp);
                }
                break;
            case "P":
                for (int i = 0; i < entitiesIdList.length; i++) {
                    if (!professorRepository.existsById(entitiesIdList[i]))
                        throw customException;
                    Professor professor = professorRepository.getById(entitiesIdList[i]);
                    String temp = professor.getEmail();
                    if (!emailsList.contains(temp))
                        emailsList.add(temp);
                }
                break;
            case "C":
                for (int i = 0; i < entitiesIdList.length; i++) {
                    if (!courseRepository.existsById(entitiesIdList[i]))
                        throw customException;
                    Course course = courseRepository.findById(entitiesIdList[i]).get();

                    // Professor
                    emailsList.add(course.getProfessor().getEmail());

                    // Students
                    Object[] students = course.getStudents().toArray();
                    for (int j = 0; j < students.length; j++) {
                        String temp = ((Student) students[j]).getEmail();
                        if (!emailsList.contains(temp))
                            emailsList.add(temp);
                    }
                }
                break;
            default:
                for (int i = 0; i < entitiesIdList.length; i++) {
                    if (!degreeRepository.existsById(entitiesIdList[i])) {
                        throw customException;
                    } else {
                        // Professors
                        ArrayList<String> emailsProfessors = professorRepository.getEmailByDegreeId(entitiesIdList[i]);
                        for (int j = 0; j < emailsProfessors.size(); j++) {
                            String temp = emailsProfessors.get(j);
                            if (!emailsList.contains(temp)) {
                                emailsList.add(temp);
                            }
                        }

                        // Students
                        ArrayList<String> emailsStudents = studentRepository.getEmailByDegreeId(entitiesIdList[i]);
                        for (int j = 0; j < emailsStudents.size(); j++) {
                            String temp = emailsStudents.get(j);
                            if (!emailsList.contains(temp)) {
                                emailsList.add(temp);
                            }
                        }
                    }
                }
        }

        return emailsList;
    }
}
