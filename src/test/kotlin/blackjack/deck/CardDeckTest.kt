package blackjack.deck

import blackjack.domain.deck.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {

    private lateinit var cardDeck: CardDeck

    @BeforeEach
    fun setUp() {
        this.cardDeck = CardDeck()
    }

    @Test
    fun `카드덱에서 카드 한장을 받는다`() {
        assertThat(cardDeck.draw()).isNotNull
    }

    @Test
    fun `카드덱에 카드가 없으면 RuntimeException이 발생하는걸 확인한다`() {
        (0 until BLACK_JACK_CARD_DECK_SIZE)
            .forEach { _ -> cardDeck.draw() }
        assertThrows<RuntimeException> { cardDeck.draw() }
    }

    companion object {
        private const val BLACK_JACK_CARD_DECK_SIZE = 52
    }
}
