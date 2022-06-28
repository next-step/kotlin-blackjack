package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardSymbol
import blackjack.domain.card.PlayerDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.UserRole
import blackjack.state.Stand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackjackJudgementTest {

    @Test
    fun `딜러보다 점수가 낮을 때 judgements 값을 확인한다`() {
        val blackjackJudgement = BlackjackJudgement(getTestPlayersWithDealer())
        val updateGameJudgement = blackjackJudgement.updateGameJudgement()
        val player = updateGameJudgement[0]

        assertThat(player.gameStatus.judgements[0].toString()).isEqualTo("패")
    }

    private fun getTestPlayersWithDealer(): List<UserRole> {
        val dealerStatus = GameStatus(
            state = Stand(
                PlayerDeck(
                    listOf(
                        Card(CardShape.SPADE, CardSymbol.ACE),
                        Card(CardShape.SPADE, CardSymbol.KING),
                    )
                )
            )
        )

        val playerStatus = GameStatus(
            state = Stand(
                PlayerDeck(
                    listOf(
                        Card(CardShape.HEART, CardSymbol.JACK),
                        Card(CardShape.HEART, CardSymbol.KING),
                    )
                )
            )
        )

        return listOf(
            Player("성주", gameStatus = playerStatus),
            Dealer(gameStatus = dealerStatus)
        )
    }
}
