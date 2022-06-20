package blackjack.domain.bet

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerResult
import blackjack.domain.player.PlayerState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ProfitCalculatorTest {
    @Test
    fun `플레이어와 딜러가 모두 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다`() {
        assertThat(ProfitCalculator.calculate(blackjack(), blackjack()).value).isEqualTo(0.0)
    }

    @Test
    fun `플레이어가 블랙잭, 딜러가 아닐 경우 플레이어는 베팅한 금액의 1,5 배를 추가로 받는다`() {
        assertAll(
            { assertThat(ProfitCalculator.calculate(blackjack(), busted()).value).isEqualTo(1.5) },
            { assertThat(ProfitCalculator.calculate(blackjack(), `stand with 14 total`()).value).isEqualTo(1.5) }
        )
    }

    @Test
    fun `플레이어가 버스트일 경우 딜러 결과에 상관 없이 베팅한 금액을 잃는다`() {
        assertAll(
            { assertThat(ProfitCalculator.calculate(busted(), busted()).value).isEqualTo(-1.0) },
            { assertThat(ProfitCalculator.calculate(busted(), `stand with 14 total`()).value).isEqualTo(-1.0) },
            { assertThat(ProfitCalculator.calculate(busted(), blackjack()).value).isEqualTo(-1.0) }
        )
    }

    @Test
    fun `플레이어는 21이하, 딜러는 버스트일 경우 베팅한 금액만큼 추가로 받는다`() {
        assertThat(ProfitCalculator.calculate(`stand with 14 total`(), busted()).value).isEqualTo(1.0)
    }

    @Test
    fun `플레이어는 21이하, 딜러는 블랙잭일 경우 베팅한 금액을 잃는다`() {
        assertThat(ProfitCalculator.calculate(`stand with 14 total`(), blackjack()).value).isEqualTo(-1.0)
    }

    @Test
    fun `플레이어가 딜러보다 높을 경우 베팅한 금액만큼 추가로 받는다`() {
        assertThat(ProfitCalculator.calculate(`stand with 15 total`(), `stand with 14 total`()).value).isEqualTo(1.0)
    }

    @Test
    fun `플레이어가 딜러와 같을 경우 베팅한 금액을 돌려받는다`() {
        assertThat(ProfitCalculator.calculate(`stand with 15 total`(), `stand with 15 total`()).value).isEqualTo(0.0)
    }

    @Test
    fun `플레이어가 딜러보다 낮을 경우 베팅한 금액을 잃는다`() {
        assertThat(ProfitCalculator.calculate(`stand with 15 total`(), `stand with 16 total`()).value).isEqualTo(-1.0)
    }
}

private fun blackjack() = PlayerResult(
    Player(listOf(Card.Ace(CardSuit.CLOVER), Card.King(CardSuit.CLOVER))),
    PlayerState.Blackjack
)

private fun busted() = PlayerResult(
    Player(listOf(Card.Ace(CardSuit.CLOVER), Card.King(CardSuit.CLOVER))),
    PlayerState.Busted
)

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

private fun Player(cards: List<Card>) = Player("vivian", 1.0, cards)
