package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "Card should have a rank and a suit" {
        val card = Card("2", Suit.HEART)
        card.rank shouldBe "2"
        card.suit shouldBe Suit.HEART
    }

    "Ace card should be calculated as 1 or 11" {
        val card = Card("Ace", Suit.DIAMOND)
        card.getValue(11) shouldBe 11
        card.getValue(1) shouldBe 1
    }

    "King, Queen, Jack cards should be calculated as 10" {
        listOf("King", "Queen", "Jack").forEach {
                rank ->
            val card = Card(rank, Suit.CLUB)
            card.getValue() shouldBe 10
        }
    }

    "Number cards should be calculated as their respective numbers" {
        (2..10).forEach {
                rank ->
            val card = Card(rank.toString(), Suit.SPADE)
            card.getValue() shouldBe rank
        }
    }
})
