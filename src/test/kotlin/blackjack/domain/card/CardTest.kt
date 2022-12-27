package blackjack.domain.card

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CardTest : FunSpec({
    context("카드는 숫자 1~10, 모양 Heart, Diamond, Spade, Club을 가진다.") {
        withData(
            CardNumber.values()
                .filterNot { it == CardNumber.ACE }
                .flatMap { cardNumber ->
                    CardShape.values()
                        .map { cardNumber to it }
                }
        ) { (cardNumber, cardShape) ->
            cardNumber.number shouldBeInRange (1..10)
            cardShape.toString() shouldBeIn CardShape.values().map { it.toString() }

            val card = Card(cardNumber, cardShape)

            card shouldNotBe null
        }
    }

    test("모든 카드는 총 56장이다.") {
        val expectedCount = CardNumber.values().size * CardShape.values().size

        expectedCount shouldBe 56
        Card.ALL_CARD_LIST shouldHaveSize expectedCount
        Card.ALL_CARD_LIST.toSet() shouldHaveSize expectedCount
    }
})
