package blackjack

import blackjack.CardTextFixtures.spadeAceCard
import blackjack.CardTextFixtures.spadeEightCard
import blackjack.CardTextFixtures.spadeFiveCard
import blackjack.CardTextFixtures.spadeFourCard
import blackjack.CardTextFixtures.spadeNineCard
import blackjack.CardTextFixtures.spadeThreeCard
import blackjack.CardTextFixtures.spadeTwoCard
import blackjack.InitialCardsTestFixtures.blackjackCards
import blackjack.InitialCardsTestFixtures.initial16Cards
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({
    "Cards에 card를 넣어 새 Cards를 반환할 수 있다" {
        val sut = Cards(initial16Cards)

        val result = sut.add(spadeFiveCard)

        result.toList() shouldContainExactlyInAnyOrder initial16Cards + listOf(spadeFiveCard)
    }

    "Cards가 보유한 카드들의 합을 구할 수 있다" {
        val sut = Cards(blackjackCards)

        val result = sut.sum()

        result shouldBe 21
    }

    "Cards는 보유한 카드 갯수를 알려줄 수 있다" {
        val sut =
            Cards(
                listOf(
                    spadeAceCard,
                    spadeTwoCard,
                    spadeThreeCard,
                    spadeFourCard,
                    spadeFiveCard,
                ),
            )

        val result = sut.size()

        result shouldBe 5
    }

    "Cards는 버스트했는지 반환할 수 있다" {
        forAll(
            row(
                listOf(
                    spadeNineCard,
                    spadeEightCard,
                    spadeFourCard,
                ),
                false,
            ),
            row(
                listOf(
                    spadeNineCard,
                    spadeEightCard,
                    spadeFiveCard,
                ),
                true,
            ),
        ) { initialCards, expected ->
            val sut = Cards(initialCards)
            val result = sut.isBust()
            result shouldBe expected
        }
    }
})
