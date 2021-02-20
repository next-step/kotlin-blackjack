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
        val player = Player("van")
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TEN, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(GameResult.getPlayerResult(dealer, player)).isFalse()
    }

    @Test
    fun `플레이어가 버스트안되고 딜러 버스트면 플레이어 승리`() {
        val dealer = Dealer()
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.TEN, Suit.CLOVER))
        dealer.addCard(Card(Denomination.FIVE, Suit.CLOVER))
        val player = Player("van")
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(GameResult.getPlayerResult(dealer, player)).isTrue()
    }

    @Test
    fun `버스트가 없으면 점수 큰사람이 승리`() {
        val dealer = Dealer() //19
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.FOUR, Suit.CLOVER))
        dealer.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player = Player("van") //17
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player2 = Player("van2") //20
        player2.addCard(Card(Denomination.TEN, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(GameResult.getPlayerResult(dealer, player)).isFalse()
        assertThat(GameResult.getPlayerResult(dealer, player2)).isTrue()
    }

    @Test
    fun `딜러의 승리 횟수를 알 수 있다`() {
        val dealer = Dealer() //19
        dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        dealer.addCard(Card(Denomination.FOUR, Suit.CLOVER))
        dealer.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player = Player("van") //17
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player2 = Player("van2") //20
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

        val player = Player("van") //17
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        val player2 = Player("van2") //20
        player2.addCard(Card(Denomination.TEN, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.CLOVER))

        assertThat(GameResult.getDealerLoseCounts(dealer, listOf(player, player2))).isEqualTo(1)
    }
}
