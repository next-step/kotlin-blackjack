package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardSymbol
import blackjack.domain.card.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ScoreTest {
    @Test
    fun `score가 21미만 인 경우`() {
        val cards = listOf(
            Card(CardShape.CLOVER, CardSymbol.ACE),
            Card(CardShape.CLOVER, CardSymbol.TWO),
            Card(CardShape.CLOVER, CardSymbol.SEVEN)
        )

        val score = Score(cards)
        assertThat(score.getScore()).isEqualTo(20)
    }

    @Test
    fun `카드는 3장이고 score가 21 인 경우`() {
        val cards = listOf(
            Card(CardShape.CLOVER, CardSymbol.ACE),
            Card(CardShape.CLOVER, CardSymbol.TWO),
            Card(CardShape.CLOVER, CardSymbol.EIGHT)
        )

        val score = Score(cards)
        assertThat(score.getScore()).isEqualTo(21)
    }

    @Test
    fun `블랙잭인 경우 (카드가 2장이고 Score 가 21)`() {
        val cards = listOf(
            Card(CardShape.CLOVER, CardSymbol.ACE),
            Card(CardShape.CLOVER, CardSymbol.JACK)
        )

        val score = Score(cards)
        assertThat(score.getScore()).isEqualTo(21)
    }

    @Test
    fun `Ace 가 여러장인 경우`() {
        val cards = listOf(
            Card(CardShape.CLOVER, CardSymbol.ACE),
            Card(CardShape.HEART, CardSymbol.ACE),
            Card(CardShape.DIAMOND, CardSymbol.ACE),
            Card(CardShape.SPADE, CardSymbol.ACE)
        )

        val score = Score(cards)
        assertThat(score.getScore()).isEqualTo(14)
    }
}
