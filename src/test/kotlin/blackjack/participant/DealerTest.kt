package blackjack.participant

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardSuit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "딜러는 카드 점수가 17점이 넘으면 카드를 받을 수 없다." {
        // Arrange:
        val dealer = Dealer()
        val card1 = Card(CardNumber.TEN, CardSuit.SPADES)
        val card2 = Card(CardNumber.TEN, CardSuit.HEARTS)
        dealer.take(listOf(card1, card2))

        // Act:
        val isMoreCard = dealer.isMoreCard()

        // Assert:
        isMoreCard shouldBe false
    }

    "딜러는 카드 점수가 16점이면 카드를 받을 수 있다." {
        // Arrange:
        val dealer = Dealer()
        val card1 = Card(CardNumber.TEN, CardSuit.SPADES)
        val card2 = Card(CardNumber.SIX, CardSuit.HEARTS)
        dealer.take(listOf(card1, card2))

        // Act:
        val isMoreCard = dealer.isMoreCard()

        // Assert:
        isMoreCard shouldBe true
    }
})
