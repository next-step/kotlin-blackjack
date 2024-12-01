package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackResultTest {
    @Test
    fun `플레이어 중 카드의 수의 합이 21에 가까운 플레이어가 승리한다`() {
        val blackJackCards =
            BlackJackCards(
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
                        blackJackCards.draw(),
                        blackJackCards.draw(),
                    ),
                ),
            )
        val blackJackPlayer2 =
            BlackJackPlayer(
                "사람2",
                BlackJackPlayerCards(
                    mutableListOf(
                        blackJackCards.draw(),
                        blackJackCards.draw(),
                    ),
                ),
            )
        val blackJackResult =
            BlackJackResult(BlackJackPlayers(listOf(blackJackPlayer1, blackJackPlayer2)))

        assertThat(blackJackResult.getWinPlayers().get(0).name).isEqualTo("사람2")
    }
}
