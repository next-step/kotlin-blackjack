package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardDeckTest : FunSpec({

    context("randomCardDeck") {
        test("랜덤한 카드 52장을 받을 수 있다.") {
            val actual = CardDeck.randomCardDeck()
            actual.size() shouldBe 52
        }
    }
})
