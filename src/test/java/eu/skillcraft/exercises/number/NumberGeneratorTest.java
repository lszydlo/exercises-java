package eu.skillcraft.exercises.number;


import org.junit.jupiter.api.Test;

import java.time.YearMonth;

import static org.assertj.core.api.Assertions.assertThat;

class NumberGeneratorTest {


	@Test
	void should_generate_iso_number() {

		NumberGenerator numberGenerator = new NumberGenerator();
		String number = numberGenerator.generate(1, "ISO", YearMonth.of(2017,12), false, false);
		assertThat(number).isEqualTo("1/ISO/12/2017");
	}

	@Test
	void should_generate_iso_number_for_auditor() {
		NumberGenerator numberGenerator = new NumberGenerator();
		String number = numberGenerator.generate(44, "ISO", YearMonth.of(2017,12), false, true);
		assertThat(number).isEqualTo("44/ISO/12/2017/AUDIT");
	}

	@Test
	void should_generate_qep_number_in_demo() {
		NumberGenerator numberGenerator = new NumberGenerator();
		String number = numberGenerator.generate(5, "QEP", YearMonth.of(2017,12), true, false);
		assertThat(number).isEqualTo("DEMO/5/QEP/12/2017");
	}
	@Test
	void should_generate_qep_number_in_demo_for_auditor() {
		NumberGenerator numberGenerator = new NumberGenerator();
		String number = numberGenerator.generate(31, "QEP", YearMonth.of(2017,12), true, true);
		assertThat(number).isEqualTo("DEMO/31/QEP/12/2017/AUDIT");
	}

}