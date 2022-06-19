package blackjack.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `DIAMOND 와3을 넣고 Card를 생성하면 Shape는 DIAMON이다`() {
        // given
        val testShape = Shape.DIAMOND
        val testNumber = CardNumber.NINE

        // when
        val testCard = Card(Shape.DIAMOND, testNumber)

        // then
        Assertions.assertThat(testCard.shape).hasSameClassAs(testShape)
    }

    @Test
    fun `DIAMOND 와 3을 넣고 Card를 생성하면 number는 3이다`() {
        // given
        val testShape = Shape.DIAMOND

        // when
        val testCard = Card(testShape, CardNumber.NINE)

        // then
        Assertions.assertThat(testCard.number.value).isEqualTo(9)
    }
}
