package com.example.productapi

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductapiApplicationTests {
	val underTest=Calculator()

	@Test
	fun isShouldAddNumbers() {

		//given
		val numberOne:Int=20
		val numberTwo:Int=30

		//when
		val result:Int=underTest.add(numberOne,numberTwo)
		val excpected=50
		//then
		assertThat(result).isEqualTo(excpected)
	}

	class Calculator{
		fun add(a: Int, b: Int): Int {
			return a + b
		}
	}

}
