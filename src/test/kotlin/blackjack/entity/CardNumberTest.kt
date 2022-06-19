package blackjack.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CardNumberTest {

  @Test
  fun `ACE는 1 또는 11을 의미한다`() {
    // given
    val expectedList = listOf<Int>(1, 11)

    // when
    val testCardNumber = CardNumber.ACE.value

    // then
    Assertions.assertThat(expectedList).contains(testCardNumber)
  }

  @Test
  fun `TWO는 2을 의미한다`() {
    // when
    val testCardNumber = CardNumber.TWO.value

    // then
    Assertions.assertThat(testCardNumber).isEqualTo(2)
  }

  @Test
  fun `THREE는 3을 의미한다`() {
    // when
    val testCardNumber = CardNumber.THREE.value

    // then
    Assertions.assertThat(testCardNumber).isEqualTo(3)
  }

  @Test
  fun `JACK은 10을 의미한다`() {
    // when
    val testCardNumber = CardNumber.JACK.value

    // then
    Assertions.assertThat(testCardNumber).isEqualTo(10)
  }

  @Test
  fun `QUEEN은 10을 의미한다`() {
    // when
    val testCardNumber = CardNumber.QUEEN.value

    // then
    Assertions.assertThat(testCardNumber).isEqualTo(10)
  }

  @Test
  fun `KING은 10을 의미한다`() {
    // when
    val testCardNumber = CardNumber.KING.value

    // then
    Assertions.assertThat(testCardNumber).isEqualTo(10)
  }
}