package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱은 최초 총 52장의 카드이다`() {
        Deck().count() shouldBe 52

        val deck = Deck()
        println(deck.divideInitialCards())
        deck.count()
    }
}
