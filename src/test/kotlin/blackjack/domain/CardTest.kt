package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CardTest : FunSpec({
    context("덱에 들어 있는 카드는 모두 52장이다") {
        val deck = Deck()
        deck.cards.size shouldBe 52
    }

    context("카드의 종류는 총 13가지이다.") {
        val deck = Deck()
        val cards = deck.cards
        val denominations = cards.map { it.denomination }
        denominations.distinct().size shouldBe 13
        denominations.containsAll(Denomination.values().toList()) shouldBe true
    }

    context("카드를 문자열로 표현하면 숫자와 무늬가 표시된다.") {
        withData(
            Card(Suit.SPADE, Denomination.ACE) to "A스페이드",
            Card(Suit.HEART, Denomination.TWO) to "2하트",
        ) { (card, expected) ->
            card.toString() shouldBe expected
        }
    }
})
