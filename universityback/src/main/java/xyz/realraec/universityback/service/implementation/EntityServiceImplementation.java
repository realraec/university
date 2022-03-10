package xyz.realraec.universityback.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import xyz.realraec.universityback.repository.CourseRepository;
import xyz.realraec.universityback.repository.DegreeRepository;
import xyz.realraec.universityback.repository.ProfessorRepository;
import xyz.realraec.universityback.repository.StudentRepository;
import xyz.realraec.universityback.service.EntityService;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service
@Transactional
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
                throw new Exception("The ID is incorrect " + (entitiesIdList.length == 1 ? "" : " for at least one of the entities") + ".");
            }

            entitiesList.add(entity);
        }

        return entitiesList;
    }

    @Override
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
                entity = repository.getById(entitiesIdList[i]);
            } catch (Exception e) {
                throw new Exception("The ID is incorrect " + (entitiesIdList.length == 1 ? "" : " for at least one of the entities") + ".");
            }

            entitiesList.add(entity);
        }

        for (int i = 0; i < entitiesList.size(); i++) {
            repository.deleteById(entitiesIdList[i]);
        }

        return Boolean.TRUE;
    }
}
