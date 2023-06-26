package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardTest : FunSpec({
    context("덱에 들어 있는 카드는 모두 52장이다") {
        val deck = Deck()
        deck.cards.size shouldBe 52
    }
})
