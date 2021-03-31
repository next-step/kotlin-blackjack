package blackjack.domain.card.state

import blackjack.domain.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class BustTest {

    private val ten = Card(Suit.CLUB, Denomination.TEN)
    private val cards = Cards(ten, ten, ten)

    @Test
    fun `bust 는 점수가 21점 초과에만 생성 된다`() {
        assertDoesNotThrow { Bust(cards) }
    }

    @Test
    fun `bust 는 점수가 21점 초과되지 않으면 생성이 불가능하다`() {
        val cards = Cards(ten, ten)
        assertThrows<IllegalArgumentException> { Bust(cards) }
    }

    @Test
    fun `bust 는 더 이상 카드를 받을 수 없다`() {
        val bust = Bust(cards)

        assertThat(bust.isFinished()).isTrue()
        assertThrows<IllegalStateException> { bust.draw(ten) }
    }

    @Test
    fun `bust 은 stay가 될 수 없다`() {
        val bust = Bust(cards)

        assertThrows<IllegalStateException> { bust.stay() }
    }

    @Test
    fun `bust 의 수익금은 -1배 이다`() {
        val bust = Bust(cards)

        val money = Money(10000)
        assertThat(bust.profit(money)).isEqualTo(money * -1.0)
    }
}
