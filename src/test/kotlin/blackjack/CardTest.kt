package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeIn

class CardTest : StringSpec({

    "카드 모양은 Spade, Hart, Diamond, Clover 4개의 모양이 있다" {
        val card = Card()

        println(card.cardShape.name)
        card.cardShape shouldBeIn CARD_SHAPE.values()
    }
})
