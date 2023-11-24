package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Character
import blackjack.domain.card.Suit
import blackjack.domain.cards.HandCards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class HandTest : StringSpec({
    "Hand 의 sum 은 정확한 값을 반환하야 한다" {
        val hand = Hand(HandCards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight))))

        hand.valueSum() shouldBe 18
    }

    "Hand 의 sum 이 21이면 blackjack 이다" {
        val hand = Hand(
            HandCards(
                mutableListOf(
                    Card(Suit.Spade, Character.Jack),
                    Card(Suit.Clover, Character.Ace),
                )
            )
        )

        hand.addCard(Card(Suit.Diamond, Character.Jack))

        hand.valueSum() shouldBe 21
        hand.isBlackjack() shouldBe true
        hand.isBust() shouldBe false
    }

    "Hand 의 sum 이 21을 초과하면 bust 이다" {
        val hand = Hand(
            HandCards(
                mutableListOf(
                    Card(Suit.Spade, Character.Jack),
                    Card(Suit.Clover, Character.Jack),
                )
            )
        )
        hand.addCard(Card(Suit.Diamond, Character.Two))

        hand.valueSum() shouldBe 22
        hand.isBlackjack() shouldBe false
        hand.isBust() shouldBe true
    }
})
