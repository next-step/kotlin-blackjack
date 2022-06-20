package blackjack.common

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerResult
import blackjack.domain.player.PlayerState
import blackjack.domain.score.Scores
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreSummaryTest {
    @Test
    fun `플레이어의 스코어가 주어질 경우 딜러의 스코어 정보도 계산할 수 있다`() {
        val scores = Scores.of(
            dealer = `stand with 15 total`(),
            players = listOf(
                `stand with 14 total`(),
                `stand with 15 total`(),
                `stand with 16 total`(),
                `stand with 16 total`()
            )
        )

        assertThat(ScoreSummary(scores).values[0])
            .isEqualTo("딜러" to "1승 1무 2패")
    }
}

private fun `stand with 14 total`() = PlayerResult(
    Player(listOf(Card.Queen(CardSuit.CLOVER), Card.Four(CardSuit.CLOVER))),
    PlayerState.Stand
)

private fun `stand with 15 total`() = PlayerResult(
    Player(listOf(Card.Queen(CardSuit.CLOVER), Card.Five(CardSuit.CLOVER))),
    PlayerState.Stand
)

private fun `stand with 16 total`() = PlayerResult(
    Player(listOf(Card.Queen(CardSuit.CLOVER), Card.Six(CardSuit.CLOVER))),
    PlayerState.Stand
)

private fun Player(cards: List<Card>) = Player("vivian", 1000.0, cards)
