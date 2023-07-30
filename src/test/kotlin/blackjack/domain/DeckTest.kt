package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class DeckTest {
    @Test
    internal fun `덱에 카드를 추가할 수 있다`() {
        val deck = Deck()
        deck.size shouldBe 0

        val newDeck = deck + SPADES_TEN
        newDeck.size shouldBe 1
    }

    @Test
    internal fun `덱에 있는 카드의 점수 합계를 알 수 있다`() {
        val deck = Deck()
        val deck2 = deck + SPADES_ACE + SPADES_TEN
        deck2.score().toInt() shouldBe 21
    }
}
