package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드는 숫자와 문양으로 구성`() {
        val sut = Card.diamond(Number.SIX)

        assertThat(sut.number).isEqualTo(Number.SIX)
        assertThat(sut.shape).isEqualTo(Shape.DIAMOND)
    }
}
