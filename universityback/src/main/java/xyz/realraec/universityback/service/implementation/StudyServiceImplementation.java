package xyz.realraec.universityback.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.model.Study;
import xyz.realraec.universityback.repository.CourseRepository;
import xyz.realraec.universityback.repository.DegreeRepository;
import xyz.realraec.universityback.service.StudyService;

import javax.transaction.Transactional;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class StudyServiceImplementation implements StudyService {

    private final CourseRepository courseRepository;
    private final DegreeRepository degreeRepository;

    private JpaRepository chooseCorrectRepository(String entityType) throws Exception {
        return switch (entityType) {
            case "C" -> courseRepository;
            case "D" -> degreeRepository;
            default -> throw new Exception("Invalid entity type");
        };
    }


    @Override
    @Transactional
    public Object edit(Long studyId, String studyType, String studyHeading, Department studyDepartment) throws Exception {
        log.info("Editing object of study with id: {}", studyId);

        JpaRepository repository = chooseCorrectRepository(studyType);

        Object study;
        try {
            study = repository.findById(studyId).get();
        } catch (Exception e) {
            throw new Exception("The ID is incorrect.");
        }

        if (studyHeading != null && studyHeading.length() > 0 && !Objects.equals(((Study) study).getHeading(), studyHeading)) {
            ((Study) study).setHeading(studyHeading);
        }

        if (studyDepartment != null && studyDepartment.getDepartment().length() > 0 && !Objects.equals(((Study) study).getDepartment(), studyDepartment)) {
            ((Study) study).setDepartment(studyDepartment);
        }

        return study;
    }

}
