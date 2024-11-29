package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class HandTest : StringSpec({
    "손패는 카드를 추가할 수 있다" {
        val sut = Hand()

        sut.add(Card(CardNumber.KING, Suit.SPADES))

        sut.cards.size shouldBe 1
        sut.cards[0] shouldBe Card(CardNumber.KING, Suit.SPADES)
    }

    "손패는 가진 카드의 숫자 합을 구할 수 있다" {
        val initialCards =
            listOf(
                Card(CardNumber.NINE, Suit.SPADES),
                Card(CardNumber.EIGHT, Suit.HEARTS),
            )
        val sut = Hand(initialCards)

        val result = sut.sumOfHand()

        result shouldBe 17
    }
})
