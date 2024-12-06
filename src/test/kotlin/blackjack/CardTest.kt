package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드는 숫자와 모양으로 구성된다." {
        // Arrange:
        val number = CardNumber.ONE
        val suit = CardSuit.SPADES

        // Act:
        val card = Card(number, suit)

        // Assert:
        card.number shouldBe CardNumber.ONE
        card.suit shouldBe CardSuit.SPADES
    }

    "카드가 Ace인지 확인할 수 있다." {
        // Arrange:
        val card = Card(CardNumber.ONE, CardSuit.SPADES)

        // Act:
        val result = card.isAce()

        // Assert:
        result shouldBe true
    }

    "카드의 문자열 표현을 확인할 수 있다." {
        // Arrange:
        val card = Card(CardNumber.ONE, CardSuit.SPADES)

        // Act:
        val result = card.toString()

        // Assert:
        result shouldBe "A스페이드"
    }
})
