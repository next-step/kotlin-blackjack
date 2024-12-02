package blackjack

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
                Card(CardNumber.King, Suit.SPADES),
            ),
            listOf(
                Card(CardNumber.Jack, Suit.SPADES),
                Card(CardNumber.Queen, Suit.SPADES),
                Card(CardNumber.King, Suit.SPADES),
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
                Card(CardNumber.Jack, Suit.SPADES),
                Card(Number(6), Suit.SPADES),
            )
        val sut = Dealer(initialCards = initialCards)
        val newCard = Card(Number(3), Suit.SPADES)

        sut.receive(newCard)

        sut.hand shouldContainExactlyInAnyOrder
            listOf(
                Card(CardNumber.Jack, Suit.SPADES),
                Card(Number(6), Suit.SPADES),
                Card(Number(3), Suit.SPADES),
            )
    }

    "딜러는 자신이 가진 카드의 숫자 합을 알 수 있다" {
        val initialCards =
            listOf(
                Card(Number(9), Suit.SPADES),
                Card(Number(8), Suit.HEARTS),
            )

        val sut = Dealer(initialCards = initialCards)

        val result = sut.sumOfHand()
        result shouldBe 17
    }

    "딜러가 버스트했는지 알 수 있다" {
        val initialCards =
            listOf(
                Card(Number(9), Suit.SPADES),
                Card(Number(7), Suit.HEARTS),
            )

        forAll(
            row(Card(Number(6), Suit.SPADES), true),
            row(Card(Number(5), Suit.SPADES), false),
        ) { card, expected ->
            val sut = Dealer(initialCards = initialCards)
            sut.receive(card)

            val result = sut.isBust()
            result shouldBe expected
        }
    }
})
