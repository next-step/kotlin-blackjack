package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardTest : FunSpec({
    context("덱에 들어 있는 카드는 모두 52장이다") {
        val deck = Deck()
        deck.cards.size shouldBe 52
    }

    context("카드의 종류는 총 13가지이다.") {
        val deck = Deck()
        val cards = deck.cards
        val numbers = cards.map { it.number }
        numbers.distinct().size shouldBe 13
        numbers.containsAll((1..13).toList()) shouldBe true
    }
})
