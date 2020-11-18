package eu.skillcraft.exercises.qdoc.preparation;

public class PreparationService {

	QDocDraftRepo repo;

	public void handle(DoCreateQDocDraft command) {
		//

		repo.save(new QDocDraft());
	}

	static class QDocDraft {
	}
}
