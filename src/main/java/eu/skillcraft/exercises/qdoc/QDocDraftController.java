package eu.skillcraft.exercises.qdoc;

import eu.skillcraft.exercises.qdoc.preparation.DoCreateQDocDraft;
import eu.skillcraft.exercises.qdoc.preparation.PreparationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QDocDraftController {

	PreparationService service;

	@PostMapping
	String create(@Validated DoCreateQDocDraft command) {
		service.handle(command);
		return "";
	}
}
