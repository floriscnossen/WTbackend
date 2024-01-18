package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.dto.TagDto;
import nl.workingtalent.backend.entity.Tag;
import nl.workingtalent.backend.service.TagService;

@RestController
@RequestMapping(path = "tag")
public class TagController {
	@Autowired
	TagService ts;
	
	@GetMapping("all")
	public List<TagDto> getTags() {
		return ts.getTags().stream().map(t -> t.toDto()).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public Optional<TagDto> getTagById(@PathVariable("id") long id) {
		return ts.getTagById(id).map(t -> t.toDto());
	}
	
	@PostMapping
	public void addTag(@RequestBody Tag t) {
		ts.addTag(t);
	}
	
	@PutMapping("{id}")
	public void updateTag(@PathVariable("id") long id, @RequestBody Tag t) {
		t.setId(id);
		ts.updateTag(t);
	}
	
	@DeleteMapping("{id}")
	public void deleteTag(@PathVariable("id") long id) {
		ts.deleteTag(id);
	}
}
