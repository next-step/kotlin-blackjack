package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardTest {
    private lateinit var ACE_SPADE: Card
    private lateinit var KING_SPADE: Card

    @BeforeEach
    fun setUp() {
        ACE_SPADE = Card(CardScore.ACE, Suit.SPADE)
        KING_SPADE = Card(CardScore.KING, Suit.SPADE)
    }

    @Test
    fun `점수 확인`() {
        // then
        assertThat(ACE_SPADE.score).isEqualTo(1)
        assertThat(KING_SPADE.score).isEqualTo(10)
    }

    @Test
    fun `Ace인지 확인`() {
        assertThat(ACE_SPADE.isAce()).isTrue()
        assertThat(KING_SPADE.isAce()).isFalse()
    }
}
