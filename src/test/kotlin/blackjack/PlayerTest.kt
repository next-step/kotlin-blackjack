package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class PlayerTest : StringSpec({
    "플레이어는 이름이 빈 문자열일 수 없다" {
        val name = " "
        val initialCards =
            listOf(
                Card(CardNumber.QUEEN, Suit.SPADES),
                Card(CardNumber.KING, Suit.SPADES),
            )

        shouldThrow<IllegalArgumentException> {
            Player(name = name, initialCards = initialCards)
        }
    }

    "플레이어는 생성 시에 카드 2장이 필수다" {
        val name = "jason"

        listOf(
            listOf(
                Card(CardNumber.KING, Suit.SPADES),
            ),
            listOf(
                Card(CardNumber.JACK, Suit.SPADES),
                Card(CardNumber.QUEEN, Suit.SPADES),
                Card(CardNumber.KING, Suit.SPADES),
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
                Card(CardNumber.ACE, Suit.SPADES),
                Card(CardNumber.TWO, Suit.SPADES),
            )
        val sut = Player(name = name, initialCards = initialCards)
        val newCard = Card(CardNumber.THREE, Suit.SPADES)

        sut.receive(newCard)

        sut.hand shouldContainExactlyInAnyOrder
            listOf(
                Card(CardNumber.ACE, Suit.SPADES),
                Card(CardNumber.TWO, Suit.SPADES),
                Card(CardNumber.THREE, Suit.SPADES),
            )
    }
})
