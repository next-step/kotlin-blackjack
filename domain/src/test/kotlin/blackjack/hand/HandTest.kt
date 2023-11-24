package blackjack.hand

import blackjack.card.Card
import blackjack.card.CardRank
import blackjack.card.CardSuit
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class HandTest : FunSpec({
    test("손패가 Ace, 10으로 이뤄져 있다면 21로 계산한다.") {
        val hand = StandardHand(
            cards = setOf(
                Card(suit = CardSuit.CLUBS, rank = CardRank.ACE),
                Card(suit = CardSuit.HEARTS, rank = CardRank.TEN)
            )
        )

        hand.calculateBestValue() shouldBe 21
    }

    test("손패가 Ace, 10, 2으로 이뤄져 있다면 13으로 계산한다.") {
        val hand = StandardHand(
            cards = setOf(
                Card(suit = CardSuit.CLUBS, rank = CardRank.ACE),
                Card(suit = CardSuit.HEARTS, rank = CardRank.TEN),
                Card(suit = CardSuit.HEARTS, rank = CardRank.TWO),
            )
        )

        hand.calculateBestValue() shouldBe 13
    }
})
