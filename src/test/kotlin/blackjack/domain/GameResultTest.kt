package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `플레이어 4명중 딜러가 bust`() {
        val playerNames = listOf("molly", "jayce", "pug")
        val dealer = Dealer("딜러")
        val players = playerNames.map { Participant(it) }

        val gameResult = GameResult(dealer, players)

        gameResult.setDealerIsWin()

        assertThat(dealer.gameScore.win).isEqualTo(3)
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
