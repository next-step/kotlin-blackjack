package blackjack.player

import blackjack.card.Card
import blackjack.card.CardSymbol
import blackjack.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackScoreCalculatorTest {

    @Test
    fun `카드에 ace가 있고, 11점 이하면 ace를 11로 계산해서 합산한다`() {
        val cards = listOf(
            Card(Suit.CLUB, CardSymbol.ACE),
            Card(Suit.CLUB, CardSymbol.TEN),
        )
        val score = BlackjackScoreCalculator.getScore(cards)
        assertThat(score).isEqualTo(21)
    }

    @Test
    fun `카드에 ace가 있고, 합이 12점 이상이면 ace를 1점으로 계산한다`() {
        val cards = listOf(
            Card(Suit.CLUB, CardSymbol.ACE),
            Card(Suit.CLUB, CardSymbol.TWO),
            Card(Suit.CLUB, CardSymbol.NINE),
        )
        val score = BlackjackScoreCalculator.getScore(cards)
        assertThat(score).isEqualTo(12)
    }

    @Test
    fun `카드에 ace가 없으면 그냥 계산한다`() {
        val cards = listOf(
            Card(Suit.CLUB, CardSymbol.FIVE),
            Card(Suit.CLUB, CardSymbol.TWO),
            Card(Suit.CLUB, CardSymbol.NINE),
            Card(Suit.CLUB, CardSymbol.TEN),
        )
        val score = BlackjackScoreCalculator.getScore(cards)
        assertThat(score).isEqualTo(26)
    }
}
