package blackjack

import domain.card.Card
import domain.card.CardDeckImpl
import domain.card.CardType
import domain.card.Denomination
import io.kotest.assertions.throwables.shouldThrow
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.Test
import java.util.EmptyStackException

class CardDeckTest {
    @Test
    fun `카드 덱 생성 테스트`() {
        val spades = Denomination.values().map { Card.spade(it) }.toMutableList()
        val hearts = Denomination.values().map { Card.heart(it) }.toMutableList()
        val diamonds = Denomination.values().map { Card.diamond(it) }.toMutableList()
        val clovers = Denomination.values().map { Card.clover(it) }.toMutableList()

        val cardDeck = CardDeckImpl()

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
