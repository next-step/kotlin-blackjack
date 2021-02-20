package blackjack.domain.game

import blackjack.domain.deck.Card
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `플레이어가 버스트됐다면 플레이어 패배`() {
        val dealer = Dealer()
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TEN, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(GameResult.getPlayerResult(dealer, player)).isFalse()
    }

    @Test
    fun `플레이어가 버스트됐다면 플레이어 배팅금 모두 잃음`() {
        val dealer = Dealer()
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TEN, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(GameResult.getPlayerProfit(dealer, player, 1.0)).isEqualTo(-10000)
    }

    @Test
    fun `플레이어가 버스트안되고 딜러 버스트면 플레이어 승리`() {
        val dealer = Dealer()
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.TEN, Suit.CLOVER))
        dealer.addCard(Card(Denomination.FIVE, Suit.CLOVER))
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(GameResult.getPlayerResult(dealer, player)).isTrue()
    }


    @Test
    fun `플레이어가 버스트안되고 딜러 버스트면 배팅금 만큼 얻음`() {
        val dealer = Dealer()
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.TEN, Suit.CLOVER))
        dealer.addCard(Card(Denomination.FIVE, Suit.CLOVER))
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(GameResult.getPlayerProfit(dealer, player, 1.0)).isEqualTo(10000)
    }

    @Test
    fun `버스트가 없으면 점수 큰사람이 승리`() {
        val dealer = Dealer() //19
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.FOUR, Suit.CLOVER))
        dealer.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player = Player("van", 10000) //17
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player2 = Player("van2", 10000) //20
        player2.addCard(Card(Denomination.TEN, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(GameResult.getPlayerResult(dealer, player)).isFalse()
        assertThat(GameResult.getPlayerResult(dealer, player2)).isTrue()
    }

    @Test
    fun `버스트가 없으면 점수 큰사람이 승리해서 계산 됨`() {
        val dealer = Dealer() //19
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.FOUR, Suit.CLOVER))
        dealer.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player = Player("van", 10000) //17
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player2 = Player("van2", 10000) //20
        player2.addCard(Card(Denomination.TEN, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(GameResult.getPlayerProfit(dealer, player, 1.0)).isEqualTo(-10000)
        assertThat(GameResult.getPlayerProfit(dealer, player2, 1.0)).isEqualTo(10000)
    }

    @Test
    fun `딜러의 승리 횟수를 알 수 있다`() {
        val dealer = Dealer() //19
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.FOUR, Suit.CLOVER))
        dealer.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player = Player("van", 10000) //17
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player2 = Player("van2", 10000) //20
        player2.addCard(Card(Denomination.TEN, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(GameResult.getDealerWinCounts(dealer, listOf(player, player2))).isEqualTo(1)
    }

    @Test
    fun `딜러의 패배 횟수를 알 수 있다`() {
        val dealer = Dealer() //19
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.FOUR, Suit.CLOVER))
        dealer.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player = Player("van", 10000) //17
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player2 = Player("van2", 10000) //20
        player2.addCard(Card(Denomination.TEN, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(GameResult.getDealerLoseCounts(dealer, listOf(player, player2))).isEqualTo(1)
    }

    @Test
    fun `딜러의 수익금은 모든 플레이어 수익금 -하면 됨`() {
        val dealer = Dealer() //19
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.FOUR, Suit.CLOVER))
        dealer.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player = Player("van", 10000) //17
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player2 = Player("van2", 10000) //20
        player2.addCard(Card(Denomination.TEN, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(GameResult.getDealerProfit(dealer, listOf(player, player2))).isEqualTo(0)
    }
}
