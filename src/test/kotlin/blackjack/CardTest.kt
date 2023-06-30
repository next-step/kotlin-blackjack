package blackjack

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Player
import blackjack.domain.Shape
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({

    "카드에서 Ace는 1 또는 11로 계산할 수 있다" {
        val aceCard = Card(Denomination.ACE, Shape.HEARTS)
        val player1Cards = Cards().apply {
            add(aceCard)
        }
        player1Cards.score shouldBe 11

        val player2Cards = Cards().apply {
            add(Card(Denomination.FIVE, Shape.HEARTS))
            add(Card(Denomination.JACK, Shape.HEARTS))
            add(Card(Denomination.ACE, Shape.HEARTS))
        }
        player2Cards.score shouldBe 16
    }

    "카드에서 2 ~ 10 까지는 그대로 계산한다" {
        val playerCards = Cards().apply {
            add(Card(Denomination.TWO, Shape.HEARTS))
            add(Card(Denomination.THREE, Shape.CLUBS))
            add(Card(Denomination.FOUR, Shape.SPADED))
        }
        val player = Player("Park", playerCards)

        player.getTotalScore() shouldBe 9
    }
})
