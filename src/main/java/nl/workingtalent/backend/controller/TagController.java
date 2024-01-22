package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.dto.TagDto;
import nl.workingtalent.backend.dto.TagSaveDto;
import nl.workingtalent.backend.entity.Tag;
import nl.workingtalent.backend.entity.User;
import nl.workingtalent.backend.mapper.DtoMapper;
import nl.workingtalent.backend.service.TagService;

@RestController
@RequestMapping(path = "tag")
@CrossOrigin
public class TagController {
	@Autowired
	TagService ts;
	
	@Autowired
	DtoMapper mapper;
	
	@GetMapping("all")
	public List<TagDto> getTags() {
		return ts.getTags().stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public Optional<TagDto> getTagById(@PathVariable("id") long id) {
		return ts.getTagById(id).map(mapper::toDto);
	}
	
	@PostMapping
	public void addTag(@RequestBody TagSaveDto tagDto) {
		ts.addTag(mapper.toEntity(tagDto));
	}
	
	@PutMapping("{id}")
	public void updateTag(@PathVariable("id") long id, @RequestBody TagSaveDto tagDto) {
		Tag tag = mapper.toEntity(tagDto);
		tag.setId(id);
		ts.updateTag(tag);
	}
	
	@DeleteMapping("{id}")
	public void deleteTag(@PathVariable("id") long id) {
		ts.deleteTag(id);
	}
}
