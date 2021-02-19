package blackjack.domain.rate

import blackjack.domain.deck.Card
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import org.assertj.core.api.Assertions
import org.assertj.core.data.Offset
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PlayerLostRateTest {
    @Test
    internal fun `플레이어가 버스트면 -100%`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        val dealer = Dealer()

        Assertions.assertThat(PlayerLostRate.isAssignable(dealer, player)).isTrue()
        Assertions.assertThat(PlayerLostRate.getEarningRate(dealer, player)).isEqualTo(-1.0, Offset.offset(0.01))
    }

    @Test
    internal fun `딜러가 버스트가 아니고 플레이어보다 점수가 크면 -100%`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        val dealer = Dealer()
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))


        Assertions.assertThat(PlayerLostRate.isAssignable(dealer, player)).isTrue()
        Assertions.assertThat(PlayerLostRate.getEarningRate(dealer, player)).isEqualTo(-1.0, Offset.offset(0.01))
    }

    @Test
    internal fun `플레이어가 버스트거나, 딜러가 버스트가 아닌데 플레이어보다 점수가 큰 경우를 제외한 모든 케이스는 에러`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        val dealer = Dealer()

        assertThrows<IllegalArgumentException> {
            PlayerLostRate.getEarningRate(dealer, player)
        }
    }

    @Test
    internal fun `플레이어가 버스트거나, 딜러가 버스트가 아닌데 플레이어보다 점수가 큰 경우를 제외한 모든 케이스는 에러2`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        val dealer = Dealer()
        player.addCard(Card(Denomination.NINE, Suit.HEART))

        assertThrows<IllegalArgumentException> {
            PlayerLostRate.getEarningRate(dealer, player)
        }
    }
}
