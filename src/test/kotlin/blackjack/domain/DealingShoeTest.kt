package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize

class DealingShoeTest : FreeSpec({
    "참여자들에게 카드를 2장씩 나누어 준다" {
        val dealingShoe = DealingShoe()
        val participants = Participants.of(Dealer(), listOf(Gambler("pobi"), Gambler("jason")))

        dealingShoe.dealTwoCardsEach(participants)

        participants.elements.forEach { participant ->
            participant.cards shouldHaveSize 2
        }
    }

    "겜블러에게 카드를 1장 나누어 준다" {
        val dealingShoe = DealingShoe()
        val gambler = Gambler("pobi")

        dealingShoe.dealCard(gambler)

        gambler.cards shouldHaveSize 1
    }
})
