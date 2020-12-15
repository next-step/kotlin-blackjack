package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    private val sampleCard = Card(Pair(Denomination.JACK, Suit.DIAMOND))

    @Test
    fun `get card score`() {
        assertThat(sampleCard.getScore()).isEqualTo(10)
    }

    @Test
    fun `get string card information`() {
        assertThat(sampleCard.toString()).isEqualTo("JACK다이아몬드")
    }
}