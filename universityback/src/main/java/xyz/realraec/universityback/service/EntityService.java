package xyz.realraec.universityback.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public interface EntityService {

    ArrayList<Optional> refreshEntities(Long[] entitiesIdList, String entityType) throws Exception;

    Boolean deleteEntities(Long[] entitiesIdList, String entityType) throws Exception;

    ArrayList<String> contactPersonsRelatedToEntities(Long[] entitiesIdList, String entityType) throws Exception;

}
