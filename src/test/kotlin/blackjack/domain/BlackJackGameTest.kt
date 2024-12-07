package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {
    @Test
    fun `딜러보다 숫자가 높은 플레이어는 승리 낮은 플레이어는 패배`() {
        val blackJackDeck =
            BlackJackDeck(
                mutableListOf(
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.QUEEN),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.KING),
                    BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.KING),
                    BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.QUEEN),
                ),
            )
        val blackJackDealer =
            BlackJackDealer(
                blackJackPlayerCards = BlackJackPlayerCards.byDeck(blackJackDeck),
            )

        val blackJackNormalPlayer1 =
            BlackJackNormalPlayer(
                "사람1",
                BlackJackPlayerCards.byDeck(blackJackDeck),
            )
        val blackJackNormalPlayer2 =
            BlackJackNormalPlayer(
                "사람2",
                BlackJackPlayerCards.byDeck(blackJackDeck),
            )
        val blackJackGame =
            BlackJackGame(listOf(blackJackNormalPlayer1, blackJackNormalPlayer2), blackJackDealer, blackJackDeck)
        val gameResult = blackJackGame.getGameResult()
        assertThat(gameResult.playerResults.first { it.name == "사람1" }.result).isEqualTo(BlackJackPlayResult.DRAW)
        assertThat(gameResult.playerResults.first { it.name == "사람2" }.result).isEqualTo(BlackJackPlayResult.WIN)
        assertThat(gameResult.dealerResult.winCount).isEqualTo(0)
        assertThat(gameResult.dealerResult.lossCount).isEqualTo(1)
    }

    @Test
    fun `승리한 플레이어에게 이익을 판돈만큼 증가시킨다`() {
        val blackJackDeck =
            BlackJackDeck(
                mutableListOf(
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.SEVEN),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.KING),
                    BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.KING),
                    BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.QUEEN),
                ),
            )
        val blackJackDealer =
            BlackJackDealer(
                blackJackPlayerCards = BlackJackPlayerCards.byDeck(blackJackDeck),
            )

        val blackJackNormalPlayer1 =
            BlackJackNormalPlayer(
                "사람1",
                BlackJackPlayerCards.byDeck(blackJackDeck),
                bettingMoney = 1000,
            )
        val blackJackNormalPlayer2 =
            BlackJackNormalPlayer(
                "사람2",
                BlackJackPlayerCards.byDeck(blackJackDeck),
                bettingMoney = 2000,
            )
        val blackJackGame =
            BlackJackGame(listOf(blackJackNormalPlayer1, blackJackNormalPlayer2), blackJackDealer, blackJackDeck)

        val gameResult = blackJackGame.getGameResult()

        blackJackGame.blackJackGamePlayers.calculateProfit(gameResult.playerResults)
        assertThat(blackJackGame.getNormalPlayers().get(0).profit).isEqualTo(-1000)
        assertThat(blackJackGame.getNormalPlayers().get(1).profit).isEqualTo(3000)
        assertThat(blackJackGame.blackJackGamePlayers.dealer.profit).isEqualTo(-2000)
    }

    @Test
    fun dealerDrawTest() {
        val blackJackDeck =
            BlackJackDeck(
                mutableListOf(
                    BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.KING),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.FIVE),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.KING),
                    BlackJackCard.get(BlackJackCardShape.DIAMOND, BlackJackCardNumber.KING),
                    BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.JACK),
                    BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.QUEEN),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.SEVEN),
                    BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.TWO),
                ),
            )
        val blackJackDealer =
            BlackJackDealer(
                blackJackPlayerCards = BlackJackPlayerCards.byDeck(blackJackDeck),
            )

        val blackJackNormalPlayer1 =
            BlackJackNormalPlayer(
                "사람1",
                BlackJackPlayerCards.byDeck(blackJackDeck),
                bettingMoney = 1000,
            )
        val blackJackNormalPlayer2 =
            BlackJackNormalPlayer(
                "사람2",
                BlackJackPlayerCards.byDeck(blackJackDeck),
                bettingMoney = 2000,
            )
        val blackJackGame =
            BlackJackGame(listOf(blackJackNormalPlayer1, blackJackNormalPlayer2), blackJackDealer, blackJackDeck)

        blackJackGame.dealerDraw()
        assertThat(blackJackGame.blackJackGamePlayers.dealer.blackJackPlayerCards.cards.size).isEqualTo(4)
        assertThat(blackJackGame.blackJackGamePlayers.dealer.blackJackPlayerCards.cards).containsExactly(
            BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.TWO),
            BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.SEVEN),
            BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.FIVE),
            BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.KING),
        )
    }
}
