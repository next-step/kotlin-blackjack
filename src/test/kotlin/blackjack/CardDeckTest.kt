package blackjack

import domain.card.CardDeck
import domain.card.Clover
import domain.card.Denomination
import domain.card.Diamond
import domain.card.Heart
import domain.card.Spade
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `카드 덱 생성 테스트`() {
        val spades = Spade.createDeck().toMutableList()
        val hearts = Heart.createDeck().toMutableList()
        val diamonds = Diamond.createDeck().toMutableList()
        val clovers = Clover.createDeck().toMutableList()

        val cardDeck = CardDeck()

        for (i in 0 until Denomination.values().size * 4) {
            when (val poppedCard = cardDeck.pop()) {
                is Spade -> spades.remove(poppedCard)
                is Heart -> hearts.remove(poppedCard)
                is Diamond -> diamonds.remove(poppedCard)
                is Clover -> clovers.remove(poppedCard)
                else -> fail("Invalid card type")
            }
        }

        assertThat(spades).isEmpty()
        assertThat(hearts).isEmpty()
        assertThat(diamonds).isEmpty()
        assertThat(clovers).isEmpty()
        assertThat(cardDeck.pop()).isNull()
    }
}
