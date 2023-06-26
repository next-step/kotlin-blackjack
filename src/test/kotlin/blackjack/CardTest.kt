package blackjack

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Player
import blackjack.domain.Shape
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({

    "카드에서 Ace는 1 또는 11로 계산할 수 있다" {
        val aceCard = Card(Denomination.ACE, Shape.HEARTS)
        val sumUnderTen = 5
        Denomination.from(aceCard.denomination, sumUnderTen) shouldBe 11

        val sumUpperTen = 15
        Denomination.from(aceCard.denomination, sumUpperTen) shouldBe 1
    }

    "카드에서 2 ~ 10 까지는 그대로 계산한다" {
        val playerCards = mutableListOf(
            Card(Denomination.TWO, Shape.HEARTS),
            Card(Denomination.THREE, Shape.CLUBS),
            Card(Denomination.FOUR, Shape.SPADED)
        )
        val player = Player("Park", playerCards)

        player.getTotalScore() shouldBe 9
    }
})
