package blackjack.domain.deck

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Suit
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class DeckTest {

    private lateinit var cards: List<Card>

    @BeforeEach
    fun setUp() {
        cards = Suit.values()
            .flatMap { suit ->
                Denomination.values()
                    .map {
                        Card(suit, it)
                    }
            }
    }

    @Test
    fun `서로 다른 52장의 카드로 덱을 만든다`() {
        assertDoesNotThrow { Deck(cards.toSet()) }
    }

    @Test
    fun `서로 다른 51장의 카드로 덱을 만들 경우 예외처리`() {
        val elements = cards.toMutableList()
        elements.removeAt(0)
        assertThrows<IllegalArgumentException> { Deck(elements.toSet()) }
    }

    @Test
    fun `덱에 카드가 없는 경우 예외처리`() {
        val deck = DeckFactory.create()
        repeat(52) { deck.draw() }
        assertThrows<IllegalStateException> { deck.draw() }
    }
}
