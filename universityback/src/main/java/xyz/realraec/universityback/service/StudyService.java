package xyz.realraec.universityback.service;

import org.springframework.stereotype.Service;
import xyz.realraec.universityback.enumeration.Department;

@Service
public interface StudyService {

    Object edit(Long studyId, String studyType, String studyHeading, Department studyDepartment) throws Exception;

}
