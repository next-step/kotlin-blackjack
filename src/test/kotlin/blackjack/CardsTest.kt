package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {

    private val sampleCard1 = Card(Pair(Denomination.EIGHT, Suit.CLUB))
    private val sampleCard2 = Card(Pair(Denomination.SEVEN, Suit.DIAMOND))
    private val sampleCards = Cards(mutableListOf(sampleCard1, sampleCard2))

    @Test
    fun `get tototal score from cards`() {
        assertThat(sampleCards.getTotalScore()).isEqualTo(15)
    }

    @Test
    fun `add card`() {
        val sampleCard3 = Card(Pair(Denomination.FIVE, Suit.HEART))
        sampleCards.addCard(sampleCard3)
        assertThat(sampleCards.getTotalScore()).isEqualTo(20)
    }
}