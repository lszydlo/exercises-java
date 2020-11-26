package eu.skillcraft.exercises.qdoc.preparation;

import java.time.YearMonth;

public class NumberFactory {


	private final SequencePort seqPort;
	private final ConfigPort configPort;
	private final TimePort timePort;
	private final AuthPort authPort;

	public NumberFactory(SequencePort seqPort, ConfigPort configPort, TimePort timePort, AuthPort authPort) {
		this.seqPort = seqPort;
		this.configPort = configPort;
		this.timePort = timePort;
		this.authPort = authPort;
	}

	public QDocNumber create() {
		return new QDocNumber(seqPort.next(), configPort.systemType(),YearMonth.now(timePort.clock()),configPort.isDemo(),authPort.isAuditor());
	}
}
