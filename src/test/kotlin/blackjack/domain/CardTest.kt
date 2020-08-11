package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardTest {
    private lateinit var aceCard: Card
    private lateinit var aceCardCopy: Card
    private lateinit var kingCard: Card

    @BeforeEach
    fun `set up`() {
        aceCard = Card(Pair(CardScore.ACE, Suit.HEART))
        aceCardCopy = Card(Pair(CardScore.ACE, Suit.HEART))
        kingCard = Card(Pair(CardScore.KING, Suit.DIAMOND))
    }

    @Test
    fun `same object`() {
        assertTrue(aceCard == aceCardCopy)
        assertFalse(aceCard == kingCard)
    }

    @Test
    fun score() {
        // when
        val aceCardScore = aceCard.score()
        val kingCardScore = kingCard.score()

        assertThat(aceCardScore).isEqualTo(1)
        assertThat(kingCardScore).isEqualTo(10)
    }
}
