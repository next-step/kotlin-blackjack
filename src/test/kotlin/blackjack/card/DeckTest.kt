package blackjack.card

import domain.card.Deck
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `덱은 312개의 카드를 가진다`() {
        val deck = Deck()
        deck.cardCount shouldBe 312
    }
}
