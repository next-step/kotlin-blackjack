package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `모든 카드를 draw 후 draw를 시도하면 예외가 발생한다`() {
        val cardDeck = CardDeck()

        repeat(52) { cardDeck.draw() }

        shouldThrow<IllegalArgumentException> { cardDeck.draw() }
    }
}