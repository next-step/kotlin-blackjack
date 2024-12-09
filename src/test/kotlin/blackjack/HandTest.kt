package blackjack

import blackjack.CardTextFixtures.spadeAceCard
import blackjack.CardTextFixtures.spadeEightCard
import blackjack.CardTextFixtures.spadeFiveCard
import blackjack.CardTextFixtures.spadeFourCard
import blackjack.CardTextFixtures.spadeKingCard
import blackjack.CardTextFixtures.spadeNineCard
import blackjack.CardTextFixtures.spadeTwoCard
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class HandTest : StringSpec({
    "손패는 카드를 추가할 수 있다" {
        val sut = Hand()

        sut.add(spadeKingCard)

        sut.cards.size shouldBe 1
        sut.cards[0] shouldBe spadeKingCard
    }

    "손패는 가진 카드의 숫자 합을 구할 수 있다(Ace 0개)" {
        forAll(*BlackJackCardTestFixtures.ace0Rows.toTypedArray()) { initialCards, expected ->
            val sut = Hand(initialCards)
            val result = sut.sumOfHand()
            result shouldBe expected
        }
    }

    "손패는 가진 카드의 숫자 합을 21에 최대한 가깝게 구할 수 있다(Ace 1개)" {
        forAll(*BlackJackCardTestFixtures.ace1Rows.toTypedArray()) { initialCards, expected ->
            val sut = Hand(initialCards)
            val result = sut.sumOfHand()
            result shouldBe expected
        }
    }

    "손패는 가진 카드의 숫자 합을 21에 최대한 가깝게 구할 수 있다(Ace 2개)" {
        forAll(*BlackJackCardTestFixtures.ace2Rows.toTypedArray()) { initialCards, expected ->
            val sut = Hand(initialCards)
            val result = sut.sumOfHand()
            result shouldBe expected
        }
    }

    "손패는 가진 카드의 숫자 합을 21에 최대한 가깝게 구할 수 있다(Ace 3개)" {
        forAll(*BlackJackCardTestFixtures.ace3Rows.toTypedArray()) { initialCards, expected ->
            val sut = Hand(initialCards)
            val result = sut.sumOfHand()
            result shouldBe expected
        }
    }

    "손패는 가진 카드의 숫자 합을 21에 최대한 가깝게 구할 수 있다(Ace 4개)" {
        forAll(*BlackJackCardTestFixtures.ace4Rows.toTypedArray()) { initialCards, expected ->
            val sut = Hand(initialCards)
            val result = sut.sumOfHand()
            result shouldBe expected
        }
    }

    "손패는 스스로 파산했는지 체크할 수 있다" {
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
            val sut = Hand(initialCards)
            val result = sut.isBust()
            result shouldBe expected
        }
    }

    "손패는 블랙잭인지 체크할 수 있다" {
        forAll(
            row(
                listOf(
                    spadeAceCard,
                    spadeKingCard,
                ),
                true,
            ),
            row(
                listOf(
                    spadeKingCard,
                    spadeNineCard,
                    spadeTwoCard,
                ),
                false,
            ),
        ) { initialCards, expected ->
            val sut = Hand(initialCards)

            sut.isBlackJack() shouldBe expected
        }
    }
})
