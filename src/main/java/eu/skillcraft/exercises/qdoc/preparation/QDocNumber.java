package eu.skillcraft.exercises.qdoc.preparation;

import java.time.YearMonth;

public class QDocNumber {
	private final String value;

	public QDocNumber(int next, String systemType, YearMonth yearMonth, boolean isDemo, boolean isAuditor) {

		if (next <= 0 ){
			throw new RuntimeException("");
		}

		// walidacja struktury
		String number = next + "/" + systemType + "/" + yearMonth.getMonthValue() + "/" + yearMonth.getYear();

		if (isDemo) {
			number = "DEMO/" + number;
		}
		if (isAuditor) {
			number = number + "/AUDIT";
		}
		value = number;
	}

	public String value() {
		return value;
	}
}
