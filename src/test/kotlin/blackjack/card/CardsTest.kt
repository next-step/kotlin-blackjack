package blackjack.card

import blackjack.card.Cards.Companion.createShuffledCardPack
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({
    "카드를 한 장도 받을 수 있다." {
        // Arrange:
        val cards = Cards()
        val card = Card(CardNumber.ACE, CardSuit.SPADES)

        // Act:
        cards.addAll(listOf(card))

        // Assert:
        cards.cards.size shouldBe 1
    }

    "카드를 여러장 추가할 수 있다." {
        // Arrange:
        val cards = Cards()
        val card1 = Card(CardNumber.ACE, CardSuit.SPADES)
        val card2 = Card(CardNumber.TWO, CardSuit.SPADES)

        // Act:
        cards.addAll(listOf(card1, card2))

        // Assert:
        cards.cards.size shouldBe 2
    }

    "ACE 2가지 경우에 수에 테스트가 되어야 한다. (ACE, 10 -> 21)" {
        // Arrange:
        val cards = Cards()
        val card1 = Card(CardNumber.ACE, CardSuit.SPADES)
        val card2 = Card(CardNumber.TEN, CardSuit.SPADES)
        cards.addAll(listOf(card1, card2))

        // Act:
        val bestScore = cards.bestScore()

        // Assert:
        bestScore shouldBe 21
    }

    "ACE 2가지 경우에 수에 테스트가 되어야 한다. (ACE, 10, 10 -> 21)" {
        // Arrange:
        val cards = Cards()
        val card1 = Card(CardNumber.ACE, CardSuit.SPADES)
        val card2 = Card(CardNumber.TEN, CardSuit.SPADES)
        val card3 = Card(CardNumber.TEN, CardSuit.SPADES)
        cards.addAll(listOf(card1, card2, card3))

        // Act:
        val bestScore = cards.bestScore()

        // Assert:
        bestScore shouldBe 21
    }

    "카드를 첫 장을 가져올 수 있다." {
        // Arrange:
        val cards = Cards()
        val card1 = Card(CardNumber.ACE, CardSuit.SPADES)
        val card2 = Card(CardNumber.TWO, CardSuit.SPADES)

        // Act:
        cards.addAll(listOf(card1, card2))

        // Assert:
        cards.cards[0] shouldBe card1
    }

    "기본 카드팩을 만들 수 있다." {
        // Arrange & Act:
        val cards = createShuffledCardPack()

        // Assert:
        cards.size shouldBe 52
    }
})
