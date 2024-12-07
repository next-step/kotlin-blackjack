package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({
    "Cards should add and calculate total value" {
        val cards = Cards()
        cards.add(Card(Rank.ACE, Suit.CLUB))
        cards.add(Card(Rank.KING, Suit.CLUB))
        cards.getTotalValue() shouldBe 21
    }

    "Cards should calculate Ace as 1 if 11 exceeds 21" {
        val cards = Cards()
        cards.add(Card(Rank.ACE, Suit.HEART))
        cards.add(Card(Rank.KING, Suit.DIAMOND))
        cards.add(Card(Rank.TWO, Suit.SPADE))
        cards.getTotalValue() shouldBe 13
    }
})
