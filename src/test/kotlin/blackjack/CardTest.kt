package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.ints.shouldBeInRange

class CardTest : StringSpec({

    "카드 모양은 Spade, Hart, Diamond, Clover 4개의 모양이 있다" {
        val card = Card()

        println(card.cardShape.name)
        card.cardShape shouldBeIn CARD_SHAPE.values()
    }

    "카드의 숫자는 1-13까지 부여 받을 수 있다" {
        val card = Card()

        println(card.cardShape.name)
        card.number.value shouldBeInRange (1..13)
    }
})
