package blackjack

import blackjack.CardTextFixtures.spadeAceCard
import blackjack.CardTextFixtures.spadeEightCard
import blackjack.CardTextFixtures.spadeFiveCard
import blackjack.CardTextFixtures.spadeJackCard
import blackjack.CardTextFixtures.spadeKingCard
import blackjack.CardTextFixtures.spadeNineCard
import blackjack.CardTextFixtures.spadeQueenCard
import blackjack.CardTextFixtures.spadeSevenCard
import blackjack.CardTextFixtures.spadeSixCard
import blackjack.CardTextFixtures.spadeThreeCard
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "딜러는 생성 시에 카드 2장이 필수다" {
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
                Dealer(initialCards = initialCards)
            }
        }
    }

    "딜러는 카드 1장을 건네받아 손패에 추가할 수 있다" {
        val initialCards =
            listOf(
                spadeJackCard,
                spadeSixCard,
            )
        val sut = Dealer(initialCards = initialCards)
        val newCard = spadeThreeCard

        sut.receive(newCard)

        sut.hand shouldContainExactlyInAnyOrder
            listOf(
                spadeJackCard,
                spadeSixCard,
                spadeThreeCard,
            )
    }

    "딜러는 자신이 가진 카드의 숫자 합을 알 수 있다" {
        val initialCards =
            listOf(
                spadeNineCard,
                spadeEightCard,
            )

        val sut = Dealer(initialCards = initialCards)

        val result = sut.sumOfHand()
        result shouldBe 17
    }

    "딜러가 버스트했는지 알 수 있다" {
        val initialCards =
            listOf(
                spadeNineCard,
                spadeSevenCard,
            )

        forAll(
            row(spadeSixCard, true),
            row(spadeFiveCard, false),
        ) { card, expected ->
            val sut = Dealer(initialCards = initialCards)
            sut.receive(card)

            val result = sut.isBust()
            result shouldBe expected
        }
    }

    "딜러는 손패의 합이 16점 이하면 카드를 더 받아야 하는 상태이다" {
        val initialCards =
            listOf(
                spadeJackCard,
                spadeSixCard,
            )
        val sut = Dealer(initialCards = initialCards)

        val result: Boolean = sut.isUnderOver()

        result shouldBe true
    }

    "딜러는 손패 2장의 합이 17점 이상임에도 불구하고 카드를 뽑으려고 하면(히트) 예외를 던진다" {
        val initialCards =
            listOf(
                spadeJackCard,
                spadeSevenCard,
            )
        val sut = Dealer(initialCards = initialCards)

        sut.isUnderOver() shouldBe false

        val newCard = spadeAceCard

        shouldThrow<IllegalStateException> {
            sut.receive(newCard)
        }
    }
})
