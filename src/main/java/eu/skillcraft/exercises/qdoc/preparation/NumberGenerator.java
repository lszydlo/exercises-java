package eu.skillcraft.exercises.qdoc.preparation;

import java.time.YearMonth;

public class NumberGenerator {

	public String generate(int next, String systemType, YearMonth yearMonth, boolean isDemo, boolean isAuditor) {
		String number = next + "/" + systemType + "/" + yearMonth.getMonthValue() + "/" + yearMonth.getYear();

		if (isDemo) {
			number = "DEMO/" + number;
		}
		if (isAuditor) {
			number = number + "/AUDIT";
		}

		return number;
	}
}
