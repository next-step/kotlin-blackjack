package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ProfitCalculatorTest {
    @Test
    fun `게임에 이겼을 경우 베팅 금액을 수익으로 가진다`() {
        // given
        val dealer = Dealer()
        val player = Player("Tester")

        dealer.receiveCard(Card(CardNumber.TWO, CardShape.SPADES))
        dealer.receiveCard(Card(CardNumber.THREE, CardShape.HEARTS))

        // when
        player.receiveCard(Card(CardNumber.JACK, CardShape.SPADES))
        player.receiveCard(Card(CardNumber.NINE, CardShape.HEARTS))

        player.updateBettingMoney(100)

        // then
        assertThat(ProfitCalculator().run(dealer, player)).isEqualTo(100)
    }

    @Test
    fun `게임에 졌을 경우 베팅 금액만큼 수익을 잃는다`() {
        // given
        val dealer = Dealer()
        val player = Player("Tester")

        dealer.receiveCard(Card(CardNumber.TEN, CardShape.SPADES))
        dealer.receiveCard(Card(CardNumber.EIGHT, CardShape.HEARTS))

        // when
        player.receiveCard(Card(CardNumber.JACK, CardShape.SPADES))

        player.updateBettingMoney(100)

        // then
        assertThat(ProfitCalculator().run(dealer, player)).isEqualTo(-100)
    }

    @Test
    fun `게임에 비겼을 경우 수익은 0이다`() {
        // given
        val dealer = Dealer()
        val player = Player("Tester")

        dealer.receiveCard(Card(CardNumber.TEN, CardShape.SPADES))

        // when
        player.receiveCard(Card(CardNumber.KING, CardShape.SPADES))

        player.updateBettingMoney(10000)

        // then
        assertThat(ProfitCalculator().run(dealer, player)).isEqualTo(0)
    }

    @Test
    fun `처음 두 장의 카드 합이 21로 블랙잭일 경우 수익은 베팅 금액의 150퍼센트이다`() {
        // given
        val dealer = Dealer()
        val player = Player("Tester")

        dealer.receiveCard(Card(CardNumber.TEN, CardShape.SPADES))

        // when
        player.receiveCard(Card(CardNumber.JACK, CardShape.SPADES))
        player.receiveCard(Card(CardNumber.ACE, CardShape.SPADES))

        player.updateBettingMoney(1000)

        // then
        assertThat(ProfitCalculator().run(dealer, player)).isEqualTo(1500)
    }
}
