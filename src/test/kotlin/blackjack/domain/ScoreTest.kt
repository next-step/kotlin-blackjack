package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `player가 받은 카드들의 점수를 계산한다`() {
        val cards = Cards(mutableListOf(Card(Denomination.TWO, Suit.CLUBS), Card(Denomination.FIVE, Suit.HEARTS)))
        val denominations = cards.cards.map { it.denomination }

        val score = Score(denominations)

        assertEquals(score.calculate(), 7)
    }
}
