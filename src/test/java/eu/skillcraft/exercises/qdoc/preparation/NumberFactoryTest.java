package eu.skillcraft.exercises.qdoc.preparation;

import org.junit.jupiter.api.Test;

import java.time.YearMonth;

import static org.assertj.core.api.Assertions.assertThat;

class NumberFactoryTest {
	@Test
	void should_generate_iso_number() {

		QDocNumber number = new QDocNumber(1, "ISO", YearMonth.of(2017,12), false, false);
		assertThat(number.value()).isEqualTo("1/ISO/12/2017");
	}

	@Test
	void should_generate_iso_number_for_auditor() {

		QDocNumber number = new QDocNumber(44, "ISO", YearMonth.of(2017,12), false, true);
		assertThat(number.value()).isEqualTo("44/ISO/12/2017/AUDIT");
	}

	@Test
	void should_generate_qep_number_in_demo() {
		QDocNumber number = new QDocNumber(5, "QEP", YearMonth.of(2017,12), true, false);
		assertThat(number.value()).isEqualTo("DEMO/5/QEP/12/2017");
	}
	@Test
	void should_generate_qep_number_in_demo_for_auditor() {
		QDocNumber number = new QDocNumber(31, "QEP", YearMonth.of(2017,12), true, true);
		assertThat(number.value()).isEqualTo("DEMO/31/QEP/12/2017/AUDIT");
	}
}