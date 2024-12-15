package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize

class DealingShoeTest : FreeSpec({
    "겜블러들에게 카드를 2장씩 나누어 준다" {
        val dealingShoe = DealingShoe()
        val gamblers = Gamblers.from(listOf("pobi", "jason"))

        dealingShoe.dealTwoCardsEach(gamblers)

        gamblers.forEach { gambler ->
            gambler.cards shouldHaveSize 2
        }
    }

    "겜블러에게 카드를 1장 나누어 준다" {
        val dealingShoe = DealingShoe()
        val gambler = Gambler("pobi")

        dealingShoe.dealCard(gambler)

        gambler.cards shouldHaveSize 1
    }
})
