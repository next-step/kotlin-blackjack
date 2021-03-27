package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class StayTest {

    @Test
    fun `스테이 상태에서 카드를 뽑을 경우 예외를 반환한다`() {
        val stayCards = Cards(listOf(Card(Suit.SPADE, Denomination.JACK), Card(Suit.SPADE, Denomination.QUEEN)))
        val stay = Stay(stayCards)
        val dummyCard = Card(Suit.SPADE, Denomination.TWO)
        val expected = "Stay 는 카드를 뽑을 수 없다."

        val result = assertThrows<UnsupportedOperationException> { stay.draw(dummyCard) }

        Assertions.assertThat(result.message).isEqualTo(expected)
    }

    @Test
    fun `스테이 상태에서 스테이를 하는 경우 예외를 반환한다`() {
        val stayCards = Cards(listOf(Card(Suit.SPADE, Denomination.JACK), Card(Suit.SPADE, Denomination.ACE)))
        val stay = Stay(stayCards)
        val expected = "Stay 상태에서 Stay 로 바꿀 수 없다."

        val result = assertThrows<UnsupportedOperationException> { stay.stay() }

        Assertions.assertThat(result.message).isEqualTo(expected)
    }
}
