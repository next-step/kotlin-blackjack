package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "Player should have a name and manage cards through Cards" {
        val player = Player("pobi")
        player.name shouldBe "pobi"
        player.getCards().isEmpty() shouldBe true
    }

    "Player should add cards to their collection" {
        val player = Player("pobi")
        val card = Card("Ace", Suit.HEART)
        player.addCard(card)

        player.getCards().size shouldBe 1
        player.getCards()[0] shouldBe card
    }

    "Player should calculate the total value of their cards correctly" {
        val player = Player("pobi")
        player.addCard(Card("Ace", Suit.HEART))
        player.addCard(Card("King", Suit.CLUB))

        player.getTotalValue() shouldBe 21
    }

    "Player should consider Ace as 1 if 11 causes the total to exceed 21" {
        val player = Player("pobi")
        player.addCard(Card("Ace", Suit.HEART))
        player.addCard(Card("King", Suit.CLUB))
        player.addCard(Card("2", Suit.DIAMOND))

        player.getTotalValue() shouldBe 13
    }
})
