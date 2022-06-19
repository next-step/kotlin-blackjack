package blackjack.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ShapeTest{
  @Test
  fun `SPADE는 스페이드를 의미한다`() {
    // when
    val testShape = Shape.SPADE.value

    // then
    Assertions.assertThat(testShape).isEqualTo("스페이드")
  }

  @Test
  fun `DIAMOND는 다이아몬드를 의미한다`() {
    // when
    val testShape = Shape.DIAMOND.value

    // then
    Assertions.assertThat(testShape).isEqualTo("다이아몬드")
  }

  @Test
  fun `HEART는 하트를 의미한다`() {
    // when
    val testShape = Shape.HEART.value

    // then
    Assertions.assertThat(testShape).isEqualTo("하트")
  }

  @Test
  fun `CLOVER는 클로버를 의미한다`() {
    // when
    val testShape = Shape.CLOVER.value

    // then
    Assertions.assertThat(testShape).isEqualTo("클로버")
  }
}