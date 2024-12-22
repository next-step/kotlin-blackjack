package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class ParticipantsTest : FreeSpec({
    "참여자는 비어있을 수 없다" {
        shouldThrow<IllegalArgumentException> { Participants(listOf()) }
    }

    "딜러는 단 한명만 참여 가능하다" - {
        "딜러가 둘 이상인 경우 예외가 발생한다" {
            shouldThrow<IllegalArgumentException> { Participants(listOf(Dealer(), Dealer(), Gambler("kim"))) }
        }

        "딜러가 한명인 경우 예외가 발생하지 않는다" {
            shouldNotThrowAny { Participants(listOf(Dealer(), Gambler("kim"))) }
        }
    }

    "겜블러는 적어도 한 명 이상 있어야 한다" - {
        "겜블러가 없는 경우 예외가 발생한다" {
            shouldThrow<IllegalArgumentException> { Participants(listOf(Dealer())) }
        }

        "겜블러가 한명 이상인 경우 예외가 발생하지 않는다" {
            shouldNotThrowAny { Participants(listOf(Dealer(), Gambler("kim"))) }
        }
    }

    "참가자들이 카드를 두장씩 받는다" {
        val participants =
            Participants(
                listOf(
                    Gambler("kim"),
                    Gambler("lee"),
                    Gambler("park"),
                    Dealer(),
                ),
            )
        val deck = Deck()

        participants.receiveTwoCardsEach(deck)

        participants.elements.forEach { participant ->
            participant.cards shouldHaveSize 2
        }
    }

    "참가자들 중에서 겜블러들만 추출한다" {
        val participants =
            Participants(
                listOf(
                    Gambler("kim"),
                    Gambler("lee"),
                    Gambler("park"),
                    Dealer(),
                ),
            )

        val gamblers = participants.extractGamblers()

        gamblers shouldHaveSize 3
        gamblers.map { it.name } shouldBe listOf("kim", "lee", "park")
    }

    "참가자들의 이름을 추출한다" {
        val participants =
            Participants(
                listOf(
                    Gambler("kim"),
                    Gambler("lee"),
                    Gambler("park"),
                    Dealer(),
                ),
            )

        val names = participants.extractNames()

        names shouldHaveSize 4
        names shouldBe listOf("kim", "lee", "park", "딜러")
    }
})
