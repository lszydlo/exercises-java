package eu.skillcraft.exercises.qdoc.preparation;

public class PreparationService {

	private final QDocDraftRepo repo;
	private final NumberGenerator generator;

	public PreparationService(QDocDraftRepo repo, NumberGenerator generator) {
		this.repo = repo;
		this.generator = generator;
	}

	public void handle(DoCreateQDocDraft command) {

		String number = generator.generate();
		repo.save(new QDocDraft(number));
	}

	static class QDocDraft {
		public QDocDraft(String number) {
		}
	}
}
