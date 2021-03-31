package blackjack.domain.card.state

import blackjack.domain.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class StayTest {

    private val ten = Card(Suit.CLUB, Denomination.TEN)
    private val cards = Cards(ten)

    @Test
    fun `Stay 는 점수가 21점 이하일 경우에만 생성 된다`() {
        assertDoesNotThrow { Stay(cards) }
    }

    @Test
    fun `Stay 는 점수가 21점 초과일 경우 생성이 불가능하다 21점 인 경우`() {
        val two = Card(Suit.CLUB, Denomination.TWO)

        val cards = Cards(ten, ten, two)
        assertThrows<IllegalArgumentException> { Stay(cards) }
    }

    @Test
    fun `Stay 는 더 이상 카드를 받을 수 없다`() {
        val stay = Stay(cards)

        Assertions.assertThat(stay.isFinished()).isTrue()
        assertThrows<IllegalStateException> { stay.draw(ten) }
    }

    @Test
    fun `Stay 은 stay가 될 수 없다`() {
        val stay = Stay(cards)

        assertThrows<IllegalStateException> { stay.stay() }
    }

    @Test
    fun `Stay의 수익금은 1배 이다`() {
        val stay = Stay(cards)

        val money = Money(10000)
        assertThat(stay.profit(money)).isEqualTo(money * 1.0)
    }
}
