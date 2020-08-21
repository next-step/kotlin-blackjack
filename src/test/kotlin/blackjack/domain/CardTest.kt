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
    fun setUp() {
        aceCard = Card(CardScore.ACE, Suit.HEART)
        aceCardCopy = Card(CardScore.ACE, Suit.HEART)
        kingCard = Card(CardScore.KING, Suit.DIAMOND)
    }

    @Test
    fun `같은 객체`() {
        assertThat(aceCard).isEqualTo(aceCardCopy)
        assertThat(aceCard).isNotEqualTo(kingCard)
    }

    @Test
    fun `점수 확인`() {
        // when
        val aceCardScore = aceCard.score()
        val kingCardScore = kingCard.score()

        // then
        assertThat(aceCardScore).isEqualTo(1)
        assertThat(kingCardScore).isEqualTo(10)
    }

    @Test
    fun `is Ace`() {
        assertTrue(aceCard.isAce())
        assertFalse(kingCard.isAce())
    }
}
