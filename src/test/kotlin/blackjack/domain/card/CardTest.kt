package blackjack.domain.card

import blackjack.domain.card.Card
import blackjack.domain.card.Character
import blackjack.domain.card.Shape
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    internal fun `shape과 character로 카드를 생성할 수 있다`() {
        val card = Card(Shape.CLOVER, Character.EIGHT)
        card.shape shouldBe Shape.CLOVER
        card.character shouldBe Character.EIGHT
    }

    @Test
    internal fun `카드가 에이스카드인지 여부를 확인할 수 있다`() {
        val notAceCard = Card(Shape.CLOVER, Character.EIGHT)
        val aceCard = Card(Shape.DIAMOND, Character.A)

        notAceCard.isAce() shouldBe false
        aceCard.isAce() shouldBe true
    }
}
