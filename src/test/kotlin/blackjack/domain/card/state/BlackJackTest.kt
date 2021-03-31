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

internal class BlackJackTest {

    private val ten = Card(Suit.CLUB, Denomination.TEN)
    private val ace = Card(Suit.CLUB, Denomination.ACE)
    private val two = Card(Suit.CLUB, Denomination.TWO)
    private val cards = Cards(ten, ace)

    @Test
    fun `BlackJack 는 점수가 21점일 경우만 생성 된다`() {
        assertDoesNotThrow { BlackJack(cards) }
    }

    @Test
    fun `BlackJack 는 점수가 21점이 아니라면 생성이 불가능하다 10 + 10 = 20`() {
        val cards = Cards(ten, ten)
        assertThrows<IllegalArgumentException> { BlackJack(cards) }
    }

    @Test
    fun `BlackJack 는 점수가 21점이 아니라면 생성이 불가능하다 10 + 10 + 2 = 22`() {
        val cards = Cards(ten, ten, two)
        assertThrows<IllegalArgumentException> { BlackJack(cards) }
    }

    @Test
    fun `BlackJack 는 더 이상 카드를 받을 수 없다`() {
        val blackJack = BlackJack(cards)

        Assertions.assertThat(blackJack.isFinished()).isTrue()
        assertThrows<IllegalStateException> { blackJack.draw(ten) }
    }

    @Test
    fun `BlackJack 은 stay가 될 수 없다`() {
        val blackJack = BlackJack(cards)

        assertThrows<IllegalStateException> { blackJack.stay() }
    }

    @Test
    fun `blackJack의 수익금은 1,5배 이다`() {
        val blackJack = BlackJack(cards)

        val money = Money(10000)
        assertThat(blackJack.profit(money)).isEqualTo(money * 1.5)
    }
}
