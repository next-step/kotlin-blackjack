package blackjack.domain.game

import blackjack.domain.deck.Card
import blackjack.domain.deck.Pip
import blackjack.domain.deck.Suit
import blackjack.domain.player.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ScoreCalculatorTest {

    @DisplayName("카드의 총 합계 점수를 계산한다.")
    @Test
    fun calculateScore() {
        val hand = Hand().apply {
            addNew(Card(Pip.TWO, Suit.HEART))
            addNew(Card(Pip.KING, Suit.HEART))
        }
        assertThat(ScoreCalculator.calculate(hand)).isEqualTo(12)
    }

    @DisplayName("총 합이 21 이하면 Ace는 11로 계산한다")
    @Test
    fun calculateMaxAce() {
        val hand = Hand().apply {
            addNew(Card(Pip.ACE, Suit.HEART))
            addNew(Card(Pip.KING, Suit.HEART))
        }
        assertThat(ScoreCalculator.calculate(hand)).isEqualTo(21)
    }

    @DisplayName("총 합이 21을 초과하면 Ace는 1로 계산한다")
    @Test
    fun calculateMinAce() {
        val hand = Hand().apply {
            addNew(Card(Pip.KING, Suit.HEART))
            addNew(Card(Pip.KING, Suit.HEART))
            addNew(Card(Pip.ACE, Suit.HEART))
        }
        assertThat(ScoreCalculator.calculate(hand)).isEqualTo(21)
    }
}
