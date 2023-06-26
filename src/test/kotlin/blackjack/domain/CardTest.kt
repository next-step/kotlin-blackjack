package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    internal fun `카드는 트럼프카드 안에 존재하는 카드만 생성된다`() {
        val card = Card.draw()
        Shape.values().contains(card.shape) shouldBe true
        Character.values().contains(card.character) shouldBe true
    }
}
