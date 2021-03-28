package blackjack.domain.card.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class HitTest {
    private val ten = Card(Suit.CLUB, Denomination.TEN)

    @Test
    fun `hit 는 점수가 21점 미만일 경우에만 생성 된다`() {
        assertDoesNotThrow { Hit(Cards(ten)) }
    }

    @Test
    fun `hit 는 점수가 21점 이상일 경우 생성이 불가능하다 21점 인 경우`() {
        val ace = Card(Suit.CLUB, Denomination.ACE)

        val cards = Cards(ten, ace)
        assertThrows<IllegalArgumentException> { Hit(cards) }
    }

    @Test
    fun `hit 는 카드를 받을 수 있다`() {
        val cards = Cards(ten)
        val hit = Hit(cards)

        Assertions.assertThat(hit.isFinished()).isFalse()
        assertDoesNotThrow { hit.draw(ten) }
    }

    @Test
    fun `hit 은 stay가 될 수 있다`() {
        val cards = Cards(ten)
        val hit = Hit(cards)

        assertDoesNotThrow { hit.stay() }
    }
}
