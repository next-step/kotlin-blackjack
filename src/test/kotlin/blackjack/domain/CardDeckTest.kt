package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.lang.RuntimeException

class CardDeckTest {

    @Test
    fun `cardDeck create test`() {

        // WHEN
        val cardDeck = CardDeck()

        // THEN
        val cardTypeSize = CardType.values().size
        val cardNumberSize = CardNumber.values().size
        assertThat(cardDeck.count).isEqualTo(cardNumberSize * cardTypeSize)

        cardDeck.cards
            .groupBy { it.type }
            .forEach { entry -> assertThat(entry.value.size).isEqualTo(cardNumberSize) }

        cardDeck.cards
            .groupBy { it.number }
            .forEach { entry -> assertThat(entry.value.size).isEqualTo(cardTypeSize) }
        println(cardDeck.cards.toString())
    }

    @Test
    fun `cardDeck draw test`() {

        // GIVEN
        val cardDeck = CardDeck()

        // WHEN
        val drawCard = cardDeck.draw()
        println(drawCard)
        println(cardDeck.cards.toString())

        // THEN
        val cardTypeSize = CardType.values().size
        val cardNumberSize = CardNumber.values().size
        assertThat(cardDeck.count).isEqualTo(cardNumberSize * cardTypeSize - 1)
        assertThat(cardDeck.cards).doesNotContain(drawCard)
    }

    @Test
    fun `cardDeck draw all test`() {

        // GIVEN
        val cardDeck = CardDeck()

        // WHEN
        val cardTypeSize = CardType.values().size
        val cardNumberSize = CardNumber.values().size
        assertThatThrownBy {
            repeat(cardNumberSize * cardTypeSize + 1) {
                val drawCard = cardDeck.draw()
                println(drawCard)
            }
        }
            .isInstanceOf(RuntimeException::class.java)
            .hasMessageMatching("remain card is empty.")
    }
}
