package nl.workingtalent.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.workingtalent.backend.entity.Copy;
import nl.workingtalent.backend.service.CopyService;

@RestController
@RequestMapping(path = "copy")
@CrossOrigin
public class CopyController {
	@Autowired
	CopyService cs;
	
	@GetMapping("all")
	public List<Copy> getCopies() {
		return cs.getCopies();
	}
	
	@GetMapping("{id}")
	public Optional<Copy> getCopyById(@PathVariable("id") long id) {
		return cs.getCopyById(id);
	}
}
