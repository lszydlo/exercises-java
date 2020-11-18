package eu.skillcraft.exercises.qdoc.preparation;

import java.time.YearMonth;


public class QDocNumberFactory {

	private final SequencePort sequencePort;
	private final ConfigPort configPort;
	private final TimePort timePort;
	private final AuthPort authPort;

	private final NumberGenerator generator = new NumberGenerator();

	public QDocNumberFactory(SequencePort sequencePort, ConfigPort configPort, TimePort timePort, AuthPort authPort) {
		this.sequencePort = sequencePort;
		this.configPort = configPort;
		this.timePort = timePort;
		this.authPort = authPort;
	}

	public String create() {
		return generator.generate(
				sequencePort.next(),
				configPort.systemType(),
				YearMonth.now(timePort.clock()),
				configPort.isDemo(),
				authPort.isAuditor());
	}
}
