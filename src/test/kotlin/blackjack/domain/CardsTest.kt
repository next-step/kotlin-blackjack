package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardsTest {

    object FakeCards {
        val cards = Cards(
            listOf(
                Card(CardNumber.of("K"), CardShape.HEART),
                Card(CardNumber.of("4"), CardShape.DIAMOND),
                Card(CardNumber.of("7"), CardShape.SPADE)
            )
        )
    }

    @Test
    fun `카드를 추가할 수 있다`() {
        val cards = Cards()
        cards.count() shouldBe 0
        FakeCards.cards.list.map { cards.add(it) }
        cards.count() shouldBe FakeCards.cards.count()
    }
}
