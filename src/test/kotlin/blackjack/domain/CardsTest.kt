package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardsTest {

    object FakeCards {
        val cards = Cards(
            listOf(
                Card.of(CardNumber.of("K"), CardShape.HEART),
                Card.of(CardNumber.of("4"), CardShape.DIAMOND),
                Card.of(CardNumber.of("7"), CardShape.SPADE)
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

    @Test
    fun `카드의 점수를 카운팅한다`() {
        val cards = FakeCards.cards
        cards.countingCard() shouldBe 21
    }

    @Test
    fun `Ace 카드는 카드의 점수가 21이 넘으면 1로 21이 넘지 않으면 11로 계산된다`() {
        val cards = Cards(listOf(Card.of(CardNumber.ACE, CardShape.DIAMOND)))
        cards.countingCard() shouldBe 11
        cards.add(Card.of(CardNumber.ACE, CardShape.HEART))
        cards.countingCard() shouldBe 12
        cards.add(Card.of(CardNumber.ACE, CardShape.SPADE))
        cards.countingCard() shouldBe 13
    }
}
