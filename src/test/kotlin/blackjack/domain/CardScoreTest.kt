package blackjack.domain

import blackjack.domain.CardScore.Companion.scoreOfCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CardScoreTest {

    @Test
    fun `card score`() {
        assertAll()
        assertTrue(scoreOfCard(CardScore.ACE) == 1)
        assertTrue(scoreOfCard(CardScore.TWO) == 2)
        assertTrue(scoreOfCard(CardScore.THREE) == 3)
        assertTrue(scoreOfCard(CardScore.FOUR) == 4)
        assertTrue(scoreOfCard(CardScore.FIVE) == 5)
        assertTrue(scoreOfCard(CardScore.SIX) == 6)
        assertTrue(scoreOfCard(CardScore.SEVEN) == 7)
        assertTrue(scoreOfCard(CardScore.EIGHT) == 8)
        assertTrue(scoreOfCard(CardScore.NINE) == 9)
        assertTrue(scoreOfCard(CardScore.TEN) == 10)
        assertTrue(scoreOfCard(CardScore.JACK) == 10)
        assertTrue(scoreOfCard(CardScore.QUEEN) == 10)
        assertTrue(scoreOfCard(CardScore.KING) == 10)
    }

    @Test
    fun `initial of card`() {
        // when
        val initialOfAce = CardScore.initialOfCard(CardScore.ACE)
        val initialOfTen = CardScore.initialOfCard(CardScore.TEN)

        // then
        assertThat(initialOfAce).isEqualTo("A")
        assertThat(initialOfTen).isEqualTo("10")
    }

    @Test
    fun `sum with ace`() {
        // when
        val sumWithAce = CardScore.sumWithAce(10, hasAce = true)
        val sumWithoutAce = CardScore.sumWithAce(10, hasAce = false)

        // then
        assertThat(sumWithAce).isEqualTo(20)
        assertThat(sumWithoutAce).isEqualTo(10)
    }
}
