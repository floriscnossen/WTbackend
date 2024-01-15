package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
