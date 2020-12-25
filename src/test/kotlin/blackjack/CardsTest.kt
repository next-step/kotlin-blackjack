package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CardsTest {

    private val sampleCard1 = Card(Pair(Denomination.EIGHT, Suit.CLUB))
    private val sampleCard2 = Card(Pair(Denomination.SEVEN, Suit.DIAMOND))
    private val sampleCards = Cards(mutableSetOf(sampleCard1, sampleCard2))


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

    @Test
    fun `get string cards`() {
        assertThat(sampleCards.toString()).isEqualTo("[8클로버, 7다이아몬드]")
    }

    @Test
    fun `check total score over max score or not`() {
        val sampleCard3 = Card(Pair(Denomination.FIVE, Suit.HEART))
        sampleCards.addCard(sampleCard3)
        val sampleCard4 = Card(Pair(Denomination.FIVE, Suit.SPADE))
        sampleCards.addCard(sampleCard4)
        assertTrue(sampleCards.hasMoreThanOver())
    }
}
