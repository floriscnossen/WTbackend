package nl.workingtalent.backend.service;

import nl.workingtalent.backend.entity.Tag;
import nl.workingtalent.backend.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired    //dependency injection
    TagRepository tr;

}
