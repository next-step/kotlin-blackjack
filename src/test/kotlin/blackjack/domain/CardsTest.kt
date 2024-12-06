package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({
    "Cards should add and calculate total value" {
        val cards = Cards()
        cards.add(Card("Ace", Suit.CLUB))
        cards.add(Card("King", Suit.CLUB))
        cards.getTotalValue() shouldBe 21
    }

    "Cards should calculate Ace as 1 if 11 exceeds 21" {
        val cards = Cards()
        cards.add(Card("Ace", Suit.HEART))
        cards.add(Card("King", Suit.DIAMOND))
        cards.add(Card("2", Suit.SPADE))
        cards.getTotalValue() shouldBe 13
    }
})
