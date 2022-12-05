package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱은 총 52장의 카드이다`() {
        Deck().cards.count() shouldBe 52
    }
}
