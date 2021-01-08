package blackjack

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CardTest {
    private val sampleCard = Card(Pair(Denomination.JACK, Suit.DIAMOND))

    @Test
    fun `get card score`() {
        assertThat(sampleCard.getScore()).isEqualTo(10)
    }

    @Test
    fun `get string card information`() {
        assertThat(sampleCard.toString()).isEqualTo("J다이아몬드")
    }

    @Test
    fun `check card have a Ace card`() {
        val sampleAceCard = Card(Pair(Denomination.ACE, Suit.CLUB))
        assertTrue(sampleAceCard.checkAce())
    }
}
