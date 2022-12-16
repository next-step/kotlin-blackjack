package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱은 최초 총 52장의 카드이다`() {
        Deck().count() shouldBe 52
    }

    @Test
    fun `덱에서 한장 혹은 최초 카드 수 만큼 나눌 수 있다`() {
        val deck = Deck()
        deck.count() shouldBe 52
        deck.drawInitCards()
        deck.count() shouldBe 50
        deck.draw()
        deck.count() shouldBe 49
    }
}
