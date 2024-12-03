package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {
    @Test
    fun `딜러보다 숫자가 높은 플레이어는 승리 낮은 플레이어는 패배`() {
        val blackJackDeck =
            BlackJackDeck(
                mutableListOf(
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.QUEEN),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.KING),
                    BlackJackCard(BlackJackCardShape.SPADE, BlackJackCardNumber.KING),
                    BlackJackCard(BlackJackCardShape.SPADE, BlackJackCardNumber.QUEEN),
                ),
            )
        val blackJackDealer =
            BlackJackDealer(
                blackJackPlayerCards = BlackJackPlayerCards.byDeck(blackJackDeck),
            )

        val blackJackPlayer1 =
            BlackJackPlayer(
                "사람1",
                BlackJackPlayerCards.byDeck(blackJackDeck),
            )
        val blackJackPlayer2 =
            BlackJackPlayer(
                "사람2",
                BlackJackPlayerCards.byDeck(blackJackDeck),
            )
        val blackJackGame =
            BlackJackGame(listOf(blackJackPlayer1, blackJackPlayer2), blackJackDealer)
        val gameResult = blackJackGame.getGameResult()
        assertThat(gameResult.playerResults.first { it.name == "사람1" }.result).isEqualTo(BlackJackPlayResult.DRAW)
        assertThat(gameResult.playerResults.first { it.name == "사람2" }.result).isEqualTo(BlackJackPlayResult.WIN)
        assertThat(gameResult.dealerResult.winCount).isEqualTo(1)
        assertThat(gameResult.dealerResult.lossCount).isEqualTo(0)
    }
}
