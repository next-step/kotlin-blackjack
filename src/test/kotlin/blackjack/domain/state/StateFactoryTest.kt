package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class StateFactoryTest {
    @Test
    fun `서로 다른 두 장의 카드로 상태를 만든다`() {
        val card1 = Card(Suit.SPADE, Denomination.SIX)
        val card2 = Card(Suit.SPADE, Denomination.SEVEN)
        val result = StateFactory.init(card1, card2)
        Assertions.assertThat(result).isNotNull
    }

    @Test
    fun `동일한 두 장의 카드로 상태를 만들 경우 예외를 반환한다`() {
        val card1 = Card(Suit.SPADE, Denomination.SIX)
        val card2 = Card(Suit.SPADE, Denomination.SIX)
        val expected = "동일한 카드로는 생성할 수 없습니다. first: $card1, second: $card2"

        val result =
            org.junit.jupiter.api.assertThrows<IllegalArgumentException> { StateFactory.init(card1, card2) }

        Assertions.assertThat(result.message).isEqualTo(expected)
    }
}
