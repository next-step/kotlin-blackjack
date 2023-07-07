package blackjack

import domain.card.Card
import domain.card.CardType
import domain.card.Denomination
import domain.card.ShuffledCardDeck
import io.kotest.assertions.throwables.shouldThrow
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.Test
import java.util.EmptyStackException

class ShuffledCardDeckTest {
    @Test
    fun `카드 덱 생성 테스트`() {
        val spades = Denomination.values().map { Card.of(it, CardType.SPADE) }.toMutableList()
        val hearts = Denomination.values().map { Card.of(it, CardType.HEART) }.toMutableList()
        val diamonds = Denomination.values().map { Card.of(it, CardType.DIAMOND) }.toMutableList()
        val clovers = Denomination.values().map { Card.of(it, CardType.CLOVER) }.toMutableList()

        val cardDeck = ShuffledCardDeck.createNew()

        repeat(Denomination.values().size * CardType.values().size) {
            val poppedCard = cardDeck.pop()
            when (poppedCard.cardType) {
                CardType.SPADE -> spades.remove(poppedCard)
                CardType.HEART -> hearts.remove(poppedCard)
                CardType.DIAMOND -> diamonds.remove(poppedCard)
                CardType.CLOVER -> clovers.remove(poppedCard)
                else -> fail("Invalid card type")
            }
        }

        assertThat(spades).isEmpty()
        assertThat(hearts).isEmpty()
        assertThat(diamonds).isEmpty()
        assertThat(clovers).isEmpty()
        shouldThrow<EmptyStackException> {
            cardDeck.pop()
        }
    }
}
