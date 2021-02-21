package blackjack.domain.rate

import blackjack.domain.deck.Card
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BlackJackRateTest {
    @Test
    internal fun `플레이어는 블랙잭이고 딜러가 블랙잭이 아닌 경우 블랙잭 비율을 받는다`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.ACE, Suit.CLOVER))
        val dealer = Dealer()

        assertThat(BlackJackRate.isAssignable(dealer, player)).isTrue()
        assertThat(BlackJackRate.getEarningRate(dealer, player)).isEqualTo(1.5, Offset.offset(0.01))
    }

    @Test
    internal fun `플레이어는 블랙잭이고 딜러가 블랙잭이 아닌 경우 블랙잭 비율을 요청하면 에러`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        val dealer = Dealer()

        assertThrows<IllegalArgumentException> {
            BlackJackRate.getEarningRate(dealer, player)
        }
    }
}
