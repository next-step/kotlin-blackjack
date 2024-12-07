package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "Card should have a rank and a suit" {
        val card = Card(Rank.TWO, Suit.HEART)
        card.rank shouldBe Rank.TWO
        card.suit shouldBe Suit.HEART
    }

    "Ace card should be calculated as 1 or 11" {
        val card = Card(Rank.ACE, Suit.DIAMOND)
        card.value(11) shouldBe 11
        card.value(1) shouldBe 1
    }

    "King, Queen, Jack cards should be calculated as 10" {
        listOf(Rank.KING, Rank.QUEEN, Rank.JACK).forEach { rank ->
            val card = Card(rank, Suit.CLUB)
            card.value() shouldBe 10
        }
    }

    "Number cards should be calculated as their respective numbers" {
        Rank.entries.filter { it.value in 2..10 }.forEach { rank ->
            val card = Card(rank, Suit.SPADE)
            card.value() shouldBe rank.value
        }
    }
})
