package blackjack.domain.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CardsTest {
    @Test
    internal fun `카드를 추가하면 카드가 추가된다`() {
        val cards = Cards()
        cards.addCard(GameCardGenerator.crete().draw())
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

    @Test
    internal fun `카드가 2개이면서 21이면 블랙잭이다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.K),
                Card(Shape.CLOVER, Character.A)
            )
        )
        cards.isBlackJack() shouldBe true
    }

    @Test
    internal fun `카드가 3개이면서 21이면 블랙잭이아니다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.EIGHT),
                Card(Shape.CLOVER, Character.TWO),
                Card(Shape.CLOVER, Character.A)
            )
        )
        cards.isBlackJack() shouldBe false
    }

    @Test
    internal fun `카드가 2개여도 21이 아니면 블랙잭이아니다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.EIGHT),
                Card(Shape.CLOVER, Character.TWO)
            )
        )
        cards.isBlackJack() shouldBe false
    }
}
