package eu.skillcraft.exercises.qdoc.preparation;

public class PreparationService {

	private final QDocDraftRepo repo;
	private final QDocNumberFactory numberFactory;

	public PreparationService(QDocDraftRepo repo, QDocNumberFactory numberFactory) {
		this.repo = repo;
		this.numberFactory = numberFactory;
	}

	public void handle(DoCreateQDocDraft command) {

		String number = numberFactory.create();
		repo.save(new QDocDraft(number,command.title,command.type));
	}

	static class QDocDraft {
		private final String number;
		private final String title;
		private final String type;

		public QDocDraft(String number, String title, String type) {
			this.number = number;
			this.title = title;
			this.type = type;
		}
	}
}
