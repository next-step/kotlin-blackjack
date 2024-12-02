package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {
    @Test
    fun `플레이어 중 카드의 수의 합이 21에 가까운 플레이어가 승리한다`() {
        val blackJackDeck =
            BlackJackDeck(
                mutableListOf(
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.QUEEN),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.KING),
                ),
            )
        val blackJackPlayer1 =
            BlackJackPlayer(
                "사람1",
                BlackJackPlayerCards(
                    mutableListOf(
                        blackJackDeck.draw(),
                        blackJackDeck.draw(),
                    ),
                ),
            )
        val blackJackPlayer2 =
            BlackJackPlayer(
                "사람2",
                BlackJackPlayerCards(
                    mutableListOf(
                        blackJackDeck.draw(),
                        blackJackDeck.draw(),
                    ),
                ),
            )
        val blackJackGame =
            BlackJackGame(listOf(blackJackPlayer1, blackJackPlayer2), null)

        assertThat(blackJackGame.getWinPlayer().get(0).name).isEqualTo("사람2")
    }
}
