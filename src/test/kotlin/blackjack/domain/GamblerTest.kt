package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class GamblerTest : FreeSpec({
    "카드를 한장 받을 수 있다" {
        val gambler = Gambler("타짜")
        val card = Card(Suit.CLUBS, Rank.SIX)

        gambler.receive(card)

        gambler.cards shouldHaveSize 1
        gambler.cards[0] shouldBe card
    }

    "카드를 여러장 받을 수 있다" {
        val gambler = Gambler("타짜")
        val cards =
            arrayOf(
                Card(Suit.HEARTS, Rank.SIX),
                Card(Suit.SPADES, Rank.SIX),
                Card(Suit.DIAMONDS, Rank.SIX),
                Card(Suit.CLUBS, Rank.SIX),
            )

        gambler.receive(*cards)

        gambler.cards shouldHaveSize 4
        gambler.cards shouldBe cards
    }

    "총합이 21 이상이면 카드를 받을 수 없다" - {
        "카드를 받을 수 있는 경우" {
            val gambler = Gambler("타짜")
            gambler.receive(
                Card(Suit.HEARTS, Rank.NINE),
                Card(Suit.SPADES, Rank.ACE),
            )

            gambler.canNotReceiveCard() shouldBe false
        }

        "카드를 받을 수 없는 경우" {
            val gambler = Gambler("타짜")
            gambler.receive(
                Card(Suit.HEARTS, Rank.TEN),
                Card(Suit.SPADES, Rank.ACE),
            )

            gambler.canNotReceiveCard() shouldBe true
        }
    }
})
