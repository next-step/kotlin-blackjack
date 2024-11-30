package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackResultTest {
    @Test
    fun `플레이어 중 카드의 수의 합이 21에 가까운 플레이어가 승리한다`() {
        BlackJackCardsSetter { cards ->
            cards.clear()
            cards.add(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE))
            cards.add(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.JACK))
            cards.add(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.QUEEN))
            cards.add(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.KING))
        }
        val blackJackCardsMap = BlackJackCardsMap()
        val blackJackPlayer1 =
            BlackJackPlayer(
                "사람1",
                BlackJackCards(
                    mutableListOf(
                        blackJackCardsMap.get(),
                        blackJackCardsMap.get(),
                    ),
                ),
            )
        val blackJackPlayer2 =
            BlackJackPlayer(
                "사람2",
                BlackJackCards(
                    mutableListOf(
                        blackJackCardsMap.get(),
                        blackJackCardsMap.get(),
                    ),
                ),
            )
        val blackJackResult =
            BlackJackResult(BlackJackPlayers(listOf(blackJackPlayer1, blackJackPlayer2)))

        assertThat(blackJackResult.getWinPlayers().get(0).name).isEqualTo("사람1")
    }
}
