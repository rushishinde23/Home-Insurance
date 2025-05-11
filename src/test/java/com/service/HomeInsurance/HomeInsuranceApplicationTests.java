package com.service.HomeInsurance;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class HomeInsuranceApplicationTests {

	@Test
	void testMainMethod() {
		try (MockedStatic<SpringApplication> mockedStatic = mockStatic(SpringApplication.class)) {
			HomeInsuranceApplication.main(new String[]{});
			mockedStatic.verify(() -> SpringApplication.run(HomeInsuranceApplication.class, new String[]{}));
		}
	}

}



