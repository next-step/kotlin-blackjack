package blackjack.business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun testCards() {
        // given,when
        val cards = Cards()

        // then
        cards.cards.size shouldBe 52
        cards.cards.toSet().size shouldBe 52
    }
}
