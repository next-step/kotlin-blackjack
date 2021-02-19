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

internal class PlayerWinRateTest {
    @Test
    internal fun `플레이어가 버스트가 아니고, 딜러가 버스트면 100%`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        val dealer = Dealer()
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))

        Assertions.assertThat(PlayerWinRate.isAssignable(dealer, player)).isTrue()
        Assertions.assertThat(PlayerWinRate.getEarningRate(dealer, player)).isEqualTo(1.0, Offset.offset(0.01))
    }

    @Test
    internal fun `플레이어와 딜러가 두가 버스트가 아닌 경우 플레이어 점수가 더 크면 100%`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        val dealer = Dealer()
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))


        Assertions.assertThat(PlayerWinRate.isAssignable(dealer, player)).isTrue()
        Assertions.assertThat(PlayerWinRate.getEarningRate(dealer, player)).isEqualTo(1.0, Offset.offset(0.01))
    }


    @Test
    internal fun `플레이어와 딜러가 두가 블랙잭이면 100%`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.ACE, Suit.HEART))
        val dealer = Dealer()
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.ACE, Suit.HEART))


        Assertions.assertThat(PlayerWinRate.isAssignable(dealer, player)).isTrue()
        Assertions.assertThat(PlayerWinRate.getEarningRate(dealer, player)).isEqualTo(1.0, Offset.offset(0.01))
    }

    @Test
    internal fun `딜러만 버스트, 둘 다 버스트가 아닌데 플레어어가 점수가 큰 경우를 제외한 모든 케이스는 에러`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        val dealer = Dealer()
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))

        assertThrows<IllegalArgumentException> {
            PlayerWinRate.getEarningRate(dealer, player)
        }
    }

    @Test
    internal fun `딜러만 버스트, 둘 다 버스트가 아닌데 플레어어가 점수가 큰 경우를 제외한 모든 케이스는 에러2`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        val dealer = Dealer()
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))

        assertThrows<IllegalArgumentException> {
            PlayerWinRate.getEarningRate(dealer, player)
        }
    }
}
