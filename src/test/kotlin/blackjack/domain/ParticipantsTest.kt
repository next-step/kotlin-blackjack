package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class ParticipantsTest : FreeSpec({
    "참가자들이 카드를 두장씩 받는다" {
        val participants = Participants(
            listOf(
                Gambler("kim"),
                Gambler("lee"),
                Gambler("park"),
                Dealer(),
            )
        )
        val deck = Deck()

        participants.receiveTwoCardsEach(deck)

        participants.elements.forEach { participant ->
            participant.cards shouldHaveSize 2
        }
    }

    "참가자들 중에서 겜블러들만 추출한다" {
        val participants = Participants(
            listOf(
                Gambler("kim"),
                Gambler("lee"),
                Gambler("park"),
                Dealer(),
            )
        )

        val gamblers = participants.extractGamblers()

        gamblers shouldHaveSize 3
        gamblers.map { it.name } shouldBe listOf("kim", "lee", "park")
    }

    "참가자들의 이름을 추출한다" {
        val participants = Participants(
            listOf(
                Gambler("kim"),
                Gambler("lee"),
                Gambler("park"),
                Dealer(),
            )
        )

        val names = participants.extractNames()

        names shouldHaveSize 4
        names shouldBe listOf("kim", "lee", "park", "딜러")
    }
})
