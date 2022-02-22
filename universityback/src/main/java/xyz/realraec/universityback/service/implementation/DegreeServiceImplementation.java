package xyz.realraec.universityback.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Department;
import xyz.realraec.universityback.model.Course;
import xyz.realraec.universityback.model.Degree;
import xyz.realraec.universityback.repository.DegreeRepository;
import xyz.realraec.universityback.service.DegreeService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


// Creates a constructor using the one attribute as argument
@RequiredArgsConstructor
@Service
@Transactional
// Prints out information to the console as it goes
@Slf4j
public class DegreeServiceImplementation implements DegreeService {

    private final DegreeRepository degreeRepository;


    @Override
    public Degree create(Degree degree) {
        // Prints the following
        log.info("Saving new degree: {}", degree.getHeading());
        return degreeRepository.save(degree);
    }

    @Override
    public Collection<Degree> list(int limit) {
        log.info("Fetching all degrees");
        // From the first element up to the limit
        return degreeRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Degree get(Long id) {
        log.info("Fetching degree with id: {}", id);
        // The get() method returns the actual degree found by id
        return degreeRepository.findById(id).get();
    }


    @Override
    @Transactional
    public Boolean update(Long id, String code, String heading, Department department, Set<Course> courses) {
        Degree degree = get(id);

        if (code != null && code.length() > 0 && !Objects.equals(degree.getCode(), code)) {
            degree.setCode(code);
        }

        if (heading != null && heading.length() > 0 && !Objects.equals(degree.getHeading(), heading)) {
            degree.setHeading(heading);
        }

        if (department != null && department.getDepartment().length() > 0 && !Objects.equals(degree.getDepartment(), department)) {
            degree.setDepartment(department);
        }

        if (courses != null && courses.size() > 0 && !Objects.equals(degree.getCourses(), courses)) {
            degree.setCourses(courses);
        }

        log.info("Updating degree with id: {}", degree.getId());
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean replace(Long id, Degree newDegree) {
        Degree oldDegree = get(id);

        String oldCode = oldDegree.getCode();
        String newCode = newDegree.getCode();
        if (oldCode != null && oldCode.length() > 0
                && newCode != null && newCode.length() > 0
                && !Objects.equals(oldCode, newCode)) {
            oldDegree.setCode(newCode);
        }

        String oldHeading = oldDegree.getHeading();
        String newHeading = newDegree.getHeading();
        if (oldHeading != null && oldHeading.length() > 0
                && newHeading != null && newHeading.length() > 0
                && !Objects.equals(oldHeading, newHeading)) {
            oldDegree.setHeading(newHeading);
        }

        Department oldDepartment = oldDegree.getDepartment();
        Department newDepartment = newDegree.getDepartment();
        if (
                oldDepartment != null && oldDepartment.getDepartment().length() > 0
                        && newDepartment != null && newDepartment.getDepartment().length() > 0
                        && !Objects.equals(oldDepartment, newDepartment)) {
            oldDegree.setDepartment(newDepartment);
        }

        Set<Course> oldCourses = oldDegree.getCourses();
        Set<Course> newCourses = newDegree.getCourses();
        if (oldCourses != null && oldCourses.size() > 0
                && newCourses != null && newCourses.size() > 0
                && !Objects.equals(oldCourses, newCourses)) {
            oldDegree.setCourses(newCourses);
        }

        log.info("Replacing degree with id: {}", id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting degree with id: {}", id);
        degreeRepository.deleteById(id);
        return Boolean.TRUE;
    }

}

