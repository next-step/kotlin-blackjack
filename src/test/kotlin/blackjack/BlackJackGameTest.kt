package blackjack

import blackjack.domain.BlackJackCardNumber
import blackjack.domain.BlackJackCardShape
import blackjack.domain.BlackJackCards
import blackjack.domain.BlackJackCardsMap
import blackjack.domain.BlackJackGame
import blackjack.domain.BlackJackPlayer
import blackjack.domain.BlackJackPlayers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {
    @Test
    fun `플레이어 중 카드의 수의 합이 21에 가까운 플레이어가 승리한다`() {
        val blackJackCardsMap = BlackJackCardsMap()
        val blackJackPlayer1 =
            BlackJackPlayer(
                "사람1",
                BlackJackCards(
                    mutableListOf(
                        blackJackCardsMap.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                        blackJackCardsMap.get(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    ),
                ),
            )
        val blackJackPlayer2 =
            BlackJackPlayer(
                "사람2",
                BlackJackCards(
                    mutableListOf(
                        blackJackCardsMap.get(BlackJackCardShape.HEART, BlackJackCardNumber.QUEEN),
                        blackJackCardsMap.get(BlackJackCardShape.HEART, BlackJackCardNumber.KING),
                    ),
                ),
            )
        val blackJackGame =
            BlackJackGame(BlackJackPlayers(listOf(blackJackPlayer1, blackJackPlayer2)), blackJackCardsMap)

        assertThat(blackJackGame.getWinPlayers().get(0).name).isEqualTo("사람1")
    }
}
