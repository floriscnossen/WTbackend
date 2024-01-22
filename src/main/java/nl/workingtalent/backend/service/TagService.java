package nl.workingtalent.backend.service;

import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.entity.Tag;
import nl.workingtalent.backend.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired    //dependency injection
    TagRepository tr;
    
    public List<Tag> getTags() {
		return tr.findAll();
	}
    
    public Optional<Tag> getTagById(long id) {
		return tr.findById(id);
	}
    
    public Tag addTag(Tag tag) {
    	return tr.save(tag);
    }
    
    public Tag findOraddTagByName(String name) {
    	return tr.findFirstByName(name).orElseGet(() -> tr.save(new Tag(name)));
    }
    
    public Tag updateTag(Tag tag) {
    	return tr.save(tag);
    }
    
    public void deleteTag(long id) {
    	tr.deleteById(id);
    }
}
