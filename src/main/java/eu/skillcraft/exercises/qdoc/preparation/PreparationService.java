package eu.skillcraft.exercises.qdoc.preparation;

import java.time.YearMonth;

public class PreparationService {

	private final QDocDraftRepo repo;
	private final NumberGenerator generator;
	private final SequencePort seqPort;
	private final ConfigPort configPort;
	private final TimePort timePort;
	private final AuthPort authPort;

	public PreparationService(QDocDraftRepo repo, NumberGenerator generator, SequencePort seqPort, ConfigPort configPort, TimePort timePort, AuthPort authPort) {
		this.repo = repo;
		this.generator = generator;
		this.seqPort = seqPort;
		this.configPort = configPort;
		this.timePort = timePort;
		this.authPort = authPort;
	}

	public void handle(DoCreateQDocDraft command) {

		String number = generator.generate(seqPort.next(),configPort.systemType(), YearMonth.now(timePort.clock()), configPort.isDemo(), authPort.isAuditor());
		repo.save(new QDocDraft(number, command.title, command.docType));
	}

	static class QDocDraft {
		private final String number;
		private final String title;
		private final String docType;

		public QDocDraft(String number, String title, String docType) {
			this.number = number;
			this.title = title;
			this.docType = docType;
		}
	}
}
