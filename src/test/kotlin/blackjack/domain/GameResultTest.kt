package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `딜러가 bust`() {
        val dealer = Dealer("딜러")
        val players = Participant("molly")

        val gameResult = GameResult(dealer, listOf(players))

        gameResult.dealerIsBust(players)

        assertThat(dealer.gameScore.win).isEqualTo(0)
        assertThat(dealer.gameScore.lose).isEqualTo(1)
    }

    @Test
    internal fun `플레이어가 bust`() {
        val dealer = Dealer("딜러")
        val players = Participant("molly")

        val gameResult = GameResult(dealer, listOf(players))

        gameResult.playerIsBust(players)

        assertThat(dealer.gameScore.win).isEqualTo(1)
        assertThat(dealer.gameScore.lose).isEqualTo(0)
    }

    @Test
    internal fun `플레이어가 12점, 딜러가 18점`() {
        val dealer = Dealer("딜러")
        val players = Participant("molly")

        val blackjack =
            BlackJackGame.of(dealer, listOf(players), MockCardDeck(Card(Card.CardPattern.CLUBS, Card.Denomination.SIX)))
        blackjack.firstCardDistribution()
        blackjack.drawTo("molly")

        val gameResult = GameResult(dealer, listOf(players))

        gameResult.decideWinner(players)

        assertThat(dealer.gameScore.win).isEqualTo(0)
        assertThat(dealer.gameScore.lose).isEqualTo(1)
    }

    @Test
    fun `플레이어 3명 모두 동점일 때`() {
        val playerNames = listOf("molly", "jayce")
        val dealer = Dealer("딜러")
        val players = playerNames.map { Participant(it) }

        val blackJackGame =
            BlackJackGame.of(dealer, players, MockCardDeck(Card(Card.CardPattern.CLUBS, Card.Denomination.EIGHT)))

        blackJackGame.firstCardDistribution()

        val gameResult = GameResult(dealer, players)

        players.forEach {
            gameResult.decideWinner(it)
        }

        assertThat(dealer.gameScore.draw).isEqualTo(2)
    }
}
