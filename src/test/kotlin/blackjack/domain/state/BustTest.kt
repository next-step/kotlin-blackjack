package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BustTest {

    @Test
    fun `버스트 상태는 카드를 더 받을 수 없다`() {
        val bustCards = Cards(listOf(Card(Suit.SPADE, Denomination.JACK), Card(Suit.SPADE, Denomination.ACE)))
        val bust = Bust(bustCards)
        Assertions.assertThat(bust.isTakeable()).isFalse()
    }

    @Test
    fun `버스트 상태에서 카드를 뽑을 경우 예외를 반환한다`() {
        val bustCards = Cards(
            listOf(
                Card(Suit.SPADE, Denomination.JACK),
                Card(Suit.SPADE, Denomination.QUEEN),
                Card(Suit.SPADE, Denomination.KING)
            )
        )
        val bust = Bust(bustCards)
        val dummyCard = Card(Suit.SPADE, Denomination.TWO)
        val expected = "Bust 는 카드를 뽑을 수 없다."

        val result = assertThrows<UnsupportedOperationException> { bust.draw(dummyCard) }

        Assertions.assertThat(result.message).isEqualTo(expected)
    }

    @Test
    fun `버스트 상태에서 스테이를 하는 경우 예외를 반환한다`() {
        val bustCards = Cards(listOf(Card(Suit.SPADE, Denomination.JACK), Card(Suit.SPADE, Denomination.ACE)))
        val bust = Bust(bustCards)
        val expected = "Bust 상태에서 Stay 로 바꿀 수 없다."

        val result = assertThrows<UnsupportedOperationException> { bust.stay() }

        Assertions.assertThat(result.message).isEqualTo(expected)
    }
}
