package blackjack.domain.bet

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerResult
import blackjack.domain.player.PlayerState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitsTest {
    @Test
    fun `플레이어 수만큼 베팅 수익을 계산한다`() {
        val numberOfPlayers = 5

        assertThat(
            Profits.of(
                result(),
                (1..numberOfPlayers).map { result() }
            ).players
        ).hasSize(numberOfPlayers)
    }
}

private fun result() = PlayerResult(
    Player("vivian", 1000.0, listOf(Card.Queen(CardSuit.CLOVER), Card.Four(CardSuit.CLOVER))),
    PlayerState.Stand
)
