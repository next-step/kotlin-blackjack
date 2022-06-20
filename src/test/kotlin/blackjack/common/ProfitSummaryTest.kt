package blackjack.common

import blackjack.domain.bet.Profits
import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerResult
import blackjack.domain.player.PlayerState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitSummaryTest {
    @Test
    fun `딜러와 플레이어 수만큼 베팅 수익 정보를 저장하고 있다`() {
        val profits = Profits.of(
            dealer = stand(),
            players = listOf(
                stand(),
                stand(),
                stand(),
                stand()
            )
        )

        assertThat(ProfitSummary(profits).values).hasSize(5)
    }
}

private fun stand() = PlayerResult(
    Player(listOf(Card.Queen(CardSuit.CLOVER), Card.Four(CardSuit.CLOVER))),
    PlayerState.Stand
)

private fun Player(cards: List<Card>) = Player("vivian", 1000.0, cards)
