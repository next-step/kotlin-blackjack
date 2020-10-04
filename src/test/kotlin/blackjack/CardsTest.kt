package blackjack

import domain.Card
import domain.Cards
import domain.Denomination
import domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드 뭉치들 점수 확인`() {
        val sampleCard1 = Card(Pair(Suit.CLUB, Denomination.FIVE))
        val sampleCard2 = Card(Pair(Suit.DIAMOND, Denomination.FOUR))
        val sampleCards = Cards(mutableListOf(sampleCard1, sampleCard2))
        assertThat(sampleCards.getTotalScore()).isEqualTo(9)
    }
}
