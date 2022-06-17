package blackjack

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.enums.CardPoint
import blackjack.domain.enums.CardShape
import blackjack.fixture.FixedCardFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 카드를 줄 수 있다`() {
        assertThat(Dealer().give() is Card).isEqualTo(true)
    }

    @Test
    fun `딜러가 처음주는 카드는 2 장이다`() {
        assertThat(Dealer().shareCards().size).isEqualTo(2)
    }

    @Test
    fun `딜러가 카드를 소지할 수 있다`() {
        val dealer: Player = Dealer()
        dealer.takeCard(FixedCardFactory(CardShape.HEART, CardPoint.KING).create())
        val card = dealer.cards[0]

        assertThat(Pair(card.shape, card.point)).isEqualTo(Pair(CardShape.HEART, CardPoint.KING))
    }
}
