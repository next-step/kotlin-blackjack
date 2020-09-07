package blackjack

import blackjack.model.Card
import blackjack.model.Cards
import blackjack.model.Dealer
import blackjack.model.Denomination
import blackjack.model.Player
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    private val cards = Cards(
        listOf(
            Card(Suit.CLUBS, Denomination.ACE),
            Card(Suit.SPADES, Denomination.TEN)
        )
    )
    private val dealer = Dealer(cards)

    @Test
    fun `딜러 클래스 상속 테스트`() {
        assertThat(dealer is Player).isEqualTo(true)
        assertThat(dealer is Dealer).isEqualTo(true)
    }

    @Test
    fun `딜러 17점 이상 테스트`() {
        assertThat(dealer.isOver17()).isTrue()
    }

    @Test
    fun `딜러 점수 테스트`() {
        assertThat(dealer.score()).isEqualTo(21)
    }
}
