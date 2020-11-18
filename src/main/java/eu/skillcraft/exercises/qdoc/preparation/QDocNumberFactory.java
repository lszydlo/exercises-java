package eu.skillcraft.exercises.qdoc.preparation;

import java.time.YearMonth;


public class QDocNumberFactory {

	private final SequencePort sequencePort;
	private final ConfigPort configPort;
	private final TimePort timePort;
	private final AuthPort authPort;


	public QDocNumberFactory(SequencePort sequencePort, ConfigPort configPort, TimePort timePort, AuthPort authPort) {
		this.sequencePort = sequencePort;
		this.configPort = configPort;
		this.timePort = timePort;
		this.authPort = authPort;
	}

	public QDocNumber create() {

		boolean isDemo = configPort.isDemo();

		return new QDocNumber(
				sequencePort.next(),
				configPort.systemType(),
				YearMonth.now(timePort.clock()),
				isDemo,
				authPort.isAuditor());
	}

	static class QDocNumber {

		private final String value;
		private final int next;
		private final String systemType;
		private final YearMonth yearMonth;
		private final boolean isDemo;
		private final boolean isAuditor;

		QDocNumber(int next, String systemType, YearMonth yearMonth, boolean isDemo, boolean isAuditor) {
			this.next = next;
			this.systemType = systemType;
			this.yearMonth = yearMonth;
			this.isDemo = isDemo;
			this.isAuditor = isAuditor;
			String number = next + "/" + systemType + "/" + yearMonth.getMonthValue() + "/" + yearMonth.getYear();

			if (isDemo) {
				number = "DEMO/" + number;
			}
			if (isAuditor) {
				number = number + "/AUDIT";
			}
			value = number;
		}
	}
}
