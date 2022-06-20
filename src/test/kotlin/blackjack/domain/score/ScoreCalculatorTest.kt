package blackjack.domain.score

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerResult
import blackjack.domain.player.PlayerState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ScoreCalculatorTest {
    @Test
    fun `딜러가 블랙잭이고 플레이어가 블랙잭일 경우 무승부이다`() {
        assertThat(ScoreCalculator.calculate(blackjack(), blackjack())).isEqualTo(ScoreType.DRAW)
    }

    @Test
    fun `딜러가 블랙잭이고 플레이어가 블랙잭이 아닐 경우 패한다`() {
        assertAll(
            { assertThat(ScoreCalculator.calculate(blackjack(), busted())).isEqualTo(ScoreType.LOSE) },
            { assertThat(ScoreCalculator.calculate(blackjack(), `stand with 14 total`())).isEqualTo(ScoreType.LOSE) }
        )
    }

    @Test
    fun `딜러의 합이 21을 초과할 경우 그 때까지 남아있는 모든 플레이어들은 승리한다`() {
        assertAll(
            { assertThat(ScoreCalculator.calculate(busted(), busted())).isEqualTo(ScoreType.LOSE) },
            { assertThat(ScoreCalculator.calculate(busted(), `stand with 14 total`())).isEqualTo(ScoreType.WIN) }
        )
    }

    @Test
    fun `딜러의 합의 21이하일 경우 플레이어 합이 딜러보다 낮으면 패한다`() {
        assertThat(ScoreCalculator.calculate(`stand with 15 total`(), `stand with 14 total`())).isEqualTo(ScoreType.LOSE)
    }

    @Test
    fun `딜러의 합이 21이하일 경우 플레이어 합이 딜러보다 높으면 승리한다`() {
        assertThat(ScoreCalculator.calculate(`stand with 15 total`(), `stand with 16 total`())).isEqualTo(ScoreType.WIN)
    }

    @Test
    fun `딜러의 합이 21이하일 경우 플레이어 합이 딜러와 같으면 무승무이다`() {
        assertThat(ScoreCalculator.calculate(`stand with 15 total`(), `stand with 15 total`())).isEqualTo(ScoreType.DRAW)
    }

    @Test
    fun `딜러의 합이 21이하이고 플레이어 합이 21을 초과할 경우 패한다`() {
        assertThat(ScoreCalculator.calculate(`stand with 15 total`(), busted())).isEqualTo(ScoreType.LOSE)
    }
}

private fun blackjack() = PlayerResult(
    Player("vivian", listOf(Card.Ace(CardSuit.CLOVER), Card.King(CardSuit.CLOVER))),
    PlayerState.Blackjack
)

private fun busted() = PlayerResult(
    Player("vivian", listOf(Card.Ace(CardSuit.CLOVER), Card.King(CardSuit.CLOVER))),
    PlayerState.Busted
)

private fun `stand with 14 total`() = PlayerResult(
    Player("vivian", listOf(Card.Queen(CardSuit.CLOVER), Card.Four(CardSuit.CLOVER))),
    PlayerState.Stand
)

private fun `stand with 15 total`() = PlayerResult(
    Player("vivian", listOf(Card.Queen(CardSuit.CLOVER), Card.Five(CardSuit.CLOVER))),
    PlayerState.Stand
)

private fun `stand with 16 total`() = PlayerResult(
    Player("vivian", listOf(Card.Queen(CardSuit.CLOVER), Card.Six(CardSuit.CLOVER))),
    PlayerState.Stand
)
