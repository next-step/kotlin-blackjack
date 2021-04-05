package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BlackjackTest {

    @Test
    fun `블랙잭 상태는 카드를 더 받을 수 없다`() {
        val blackjackCards = Cards(listOf(Card(Suit.SPADE, Denomination.JACK), Card(Suit.SPADE, Denomination.ACE)))
        val blackjack = Blackjack(blackjackCards)
        assertThat(blackjack.isTakeable()).isFalse()
    }

    @Test
    fun `블랙잭 상태에서 카드를 뽑을 경우 예외를 반환한다`() {
        val blackjackCards = Cards(listOf(Card(Suit.SPADE, Denomination.JACK), Card(Suit.SPADE, Denomination.ACE)))
        val blackjack = Blackjack(blackjackCards)
        val dummyCard = Card(Suit.SPADE, Denomination.TWO)
        val expected = "Blackjack 은 카드를 뽑을 수 없다."

        val result = assertThrows<UnsupportedOperationException> { blackjack.draw(dummyCard) }

        assertThat(result.message).isEqualTo(expected)
    }

    @Test
    fun `블랙잭 상태에서 스테이를 하는 경우 예외를 반환한다`() {
        val blackjackCards = Cards(listOf(Card(Suit.SPADE, Denomination.JACK), Card(Suit.SPADE, Denomination.ACE)))
        val blackjack = Blackjack(blackjackCards)
        val expected = "Blackjack 상태에서 Stay 로 바꿀 수 없다."

        val result = assertThrows<UnsupportedOperationException> { blackjack.stay() }

        assertThat(result.message).isEqualTo(expected)
    }
}
