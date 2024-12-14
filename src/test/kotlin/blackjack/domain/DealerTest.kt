package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize

class DealerTest : FreeSpec({
    "겜블러들에게 카드를 2장씩 나누어 준다" {
        val dealer = Dealer()
        val gamblers = Gamblers.from(listOf("pobi", "jason"))

        dealer.dealTwoCardsEach(gamblers)

        gamblers.forEach { gambler ->
            gambler.cards shouldHaveSize 2
        }
    }

    "겜블러에게 카드를 1장 나누어 준다" {
        val dealer = Dealer()
        val gambler = Gambler("pobi")

        dealer.dealCard(gambler)

        gambler.cards shouldHaveSize 1
    }
})
