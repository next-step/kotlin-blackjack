package blackjack.domain.score

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerResult
import blackjack.domain.player.PlayerState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoresTest {
    @Test
    fun `플레이어 수 만큼 스코어를 계산한다`() {
        val numberOfPlayers = 5

        assertThat(
            Scores.of(
                result(),
                (1..numberOfPlayers).map { result() }
            ).values
        ).hasSize(numberOfPlayers)
    }
}

private fun result() = PlayerResult(
    Player("vivian", listOf(Card.Queen(CardSuit.CLOVER), Card.Four(CardSuit.CLOVER))),
    PlayerState.Stand
)
