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

import nl.workingtalent.backend.dto.CopyDto;
import nl.workingtalent.backend.dto.CopySaveDto;
import nl.workingtalent.backend.entity.Book;
import nl.workingtalent.backend.entity.Copy;
import nl.workingtalent.backend.entity.User;
import nl.workingtalent.backend.mapper.DtoMapper;
import nl.workingtalent.backend.service.CopyService;

@RestController
@RequestMapping(path = "copy")
public class CopyController {
	@Autowired
	CopyService cs;
	
	@Autowired
	DtoMapper mapper;
	
	@GetMapping("all")
	public List<CopyDto> getCopies() {
		return cs.getCopies().stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public Optional<CopyDto> getCopyById(@PathVariable("id") long id) {
		return cs.getCopyById(id).map(mapper::toDto);
	}
	
	@PostMapping
	public void addCopy(@RequestBody CopySaveDto copyDto) {
		cs.addCopy(mapper.toEntity(copyDto));
	}
	
	@PutMapping("{id}")
	public void updateCopy(@PathVariable("id") long id, @RequestBody CopySaveDto copyDto) {
		Copy copy = mapper.toEntity(copyDto);
		copy.setId(id);
		cs.updateCopy(copy);
	}
	
	@DeleteMapping("{id}")
	public void deleteCopy(@PathVariable("id") long id) {
		cs.deleteCopy(id);
	}
}
