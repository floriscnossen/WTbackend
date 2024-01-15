package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.dto.CopyDto;
import nl.workingtalent.backend.entity.Copy;
import nl.workingtalent.backend.service.CopyService;

@RestController
@RequestMapping(path = "copy")
public class CopyController {
	@Autowired
	CopyService cs;
	
	@GetMapping("all")
	public List<CopyDto> getCopies() {
		return cs.getCopies().stream().map(c -> c.toDto()).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public Optional<CopyDto> getCopyById(@PathVariable("id") long id) {
		return cs.getCopyById(id).map(c -> c.toDto());
	}
}
