package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.entity.Tag;
import nl.workingtalent.backend.service.TagService;

@RestController
@RequestMapping(path = "tag")
public class TagController {
	@Autowired
	TagService ts;
	
	@GetMapping("all")
	public List<Tag> getTags() {
		return ts.getTags();
	}
	
	@GetMapping("{id}")
	public Optional<Tag> getTagById(@PathVariable("id") long id) {
		return ts.getTagById(id);
	}
}
