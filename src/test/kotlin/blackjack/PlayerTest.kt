package blackjack

import blackjack.CardTextFixtures.spadeAceCard
import blackjack.CardTextFixtures.spadeEightCard
import blackjack.CardTextFixtures.spadeFiveCard
import blackjack.CardTextFixtures.spadeFourCard
import blackjack.CardTextFixtures.spadeJackCard
import blackjack.CardTextFixtures.spadeKingCard
import blackjack.CardTextFixtures.spadeNineCard
import blackjack.CardTextFixtures.spadeQueenCard
import blackjack.CardTextFixtures.spadeThreeCard
import blackjack.CardTextFixtures.spadeTwoCard
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어는 이름이 빈 문자열일 수 없다" {
        val name = " "
        val initialCards =
            listOf(
                spadeKingCard,
                spadeQueenCard,
            )

        shouldThrow<IllegalArgumentException> {
            Player(name = name, initialCards = initialCards)
        }
    }

    "플레이어는 생성 시에 카드 2장이 필수다" {
        val name = "jason"

        listOf(
            listOf(
                spadeKingCard,
            ),
            listOf(
                spadeJackCard,
                spadeQueenCard,
                spadeKingCard,
            ),
        ).forEach { initialCards ->
            shouldThrow<IllegalArgumentException> {
                Player(name = name, initialCards = initialCards)
            }
        }
    }

    "플레이어는 카드 1장을 건네받아 손패에 추가할 수 있다" {
        val name = "jason"
        val initialCards =
            listOf(
                spadeAceCard,
                spadeTwoCard,
            )
        val sut = Player(name = name, initialCards = initialCards)
        val newCard = spadeThreeCard

        sut.receive(newCard)

        sut.hand shouldContainExactlyInAnyOrder
            listOf(
                spadeAceCard,
                spadeTwoCard,
                spadeThreeCard,
            )
    }

    "플레이어는 자신이 가진 카드의 숫자 합을 알 수 있다" {
        val name = "jason"
        val initialCards =
            listOf(
                spadeNineCard,
                spadeEightCard,
            )

        val sut = Player(name = name, initialCards = initialCards)

        val result = sut.sumOfHand()
        result shouldBe 17
    }

    "플레이어가 버스트했는지 알 수 있다" {
        val name = "jason"
        val initialCards =
            listOf(
                spadeNineCard,
                spadeEightCard,
            )

        forAll(
            row(spadeFiveCard, true),
            row(spadeFourCard, false),
        ) { card, expected ->
            val sut = Player(name = name, initialCards = initialCards)
            sut.receive(card)

            val result = sut.isBust()
            result shouldBe expected
        }
    }

    "플레이어가 시작 상태(손에 2장 소유)인지 알 수 있다" {
        val name = "jason"
        val initialCards =
            listOf(
                spadeNineCard,
                spadeEightCard,
            )

        val sut = Player(name = name, initialCards = initialCards)

        sut.isInitialState() shouldBe true

        sut.receive(spadeQueenCard)

        sut.isInitialState() shouldBe false
    }
})
