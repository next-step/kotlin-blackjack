package blackjack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드는 중복 없이 52장으로 구성된다`() {
        val cards = Cards()
        cards.items.size shouldBe 52
        cards.items.toSet().size shouldBe 52
    }
}
