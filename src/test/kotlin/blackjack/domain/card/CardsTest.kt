package blackjack.domain.card

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Character
import blackjack.domain.card.GameCardGenerator
import blackjack.domain.card.GameCards
import blackjack.domain.card.Shape
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CardsTest {
    @Test
    internal fun `카드를 추가하면 카드가 추가된다`() {
        val cards = Cards()
        cards.addCard(GameCards(GameCardGenerator.crete()).draw())
        cards.values.size shouldBe 1
    }

    @Test
    internal fun `가지고 있는 카드의 점수가 합산되어 계산된다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.EIGHT),
                Card(Shape.CLOVER, Character.NINE),
                Card(Shape.CLOVER, Character.FIVE)
            )
        )
        cards.score() shouldBe 22
    }

    @Test
    internal fun `나머지의 점수가 20이면 A는 1로 계산된다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.J),
                Card(Shape.CLOVER, Character.TEN),
                Card(Shape.CLOVER, Character.A)
            )
        )
        cards.score() shouldBe 21
    }

    @Test
    internal fun `나머지의 점수가 10이면 A는 11으로 계산된다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.EIGHT),
                Card(Shape.CLOVER, Character.TWO),
                Card(Shape.CLOVER, Character.A)
            )
        )
        cards.score() shouldBe 21
    }
}
