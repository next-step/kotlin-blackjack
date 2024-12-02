package blackjack

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
                Card(CardNumber.Queen, Suit.SPADES),
                Card(CardNumber.King, Suit.SPADES),
            )

        shouldThrow<IllegalArgumentException> {
            Player(name = name, initialCards = initialCards)
        }
    }

    "플레이어는 생성 시에 카드 2장이 필수다" {
        val name = "jason"

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
                Player(name = name, initialCards = initialCards)
            }
        }
    }

    "플레이어는 카드 1장을 건네받아 손패에 추가할 수 있다" {
        val name = "jason"
        val initialCards =
            listOf(
                Card(CardNumber.Ace, Suit.SPADES),
                Card(Number(2), Suit.SPADES),
            )
        val sut = Player(name = name, initialCards = initialCards)
        val newCard = Card(Number(3), Suit.SPADES)

        sut.receive(newCard)

        sut.hand shouldContainExactlyInAnyOrder
            listOf(
                Card(CardNumber.Ace, Suit.SPADES),
                Card(Number(2), Suit.SPADES),
                Card(Number(3), Suit.SPADES),
            )
    }

    "플레이어는 자신이 가진 카드의 숫자 합을 알 수 있다" {
        val name = "jason"
        val initialCards =
            listOf(
                Card(Number(9), Suit.SPADES),
                Card(Number(8), Suit.HEARTS),
            )

        val sut = Player(name = name, initialCards = initialCards)

        val result = sut.sumOfHand()
        result shouldBe 17
    }

    "플레이어가 버스트했는지 알 수 있다" {
        val name = "jason"
        val initialCards =
            listOf(
                Card(Number(9), Suit.SPADES),
                Card(Number(8), Suit.HEARTS),
            )

        forAll(
            row(Card(Number(5), Suit.SPADES), true),
            row(Card(Number(4), Suit.SPADES), false),
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
                Card(Number(9), Suit.SPADES),
                Card(Number(8), Suit.HEARTS),
            )

        val sut = Player(name = name, initialCards = initialCards)

        sut.isInitialState() shouldBe true

        sut.receive(Card(CardNumber.Queen, Suit.SPADES))

        sut.isInitialState() shouldBe false
    }
})
