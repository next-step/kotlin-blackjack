package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CardsFactoryTest {

    @Test
    fun `서로 다른 두 장의 카드로 Cards를 만든다`() {
        val card1 = Card(Suit.SPADE, Denomination.SIX)
        val card2 = Card(Suit.SPADE, Denomination.SEVEN)
        val result = CardsFactory.makeCards(card1 to card2)
        assertThat(result).isNotNull
    }

    @Test
    fun `동일한 두 장의 카드로 Cards를 만들 경우 예외를 반환한다`() {
        val card1 = Card(Suit.SPADE, Denomination.SIX)
        val card2 = Card(Suit.SPADE, Denomination.SIX)
        val expected = "동일한 카드로는 생성할 수 없습니다. cards.first: $card2, cards.second: $card2"

        val result = assertThrows<IllegalArgumentException> { CardsFactory.makeCards(card1 to card2) }

        assertThat(result.message).isEqualTo(expected)
    }
}
