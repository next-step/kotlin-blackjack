package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.model.Card
import blackjack.model.Character
import blackjack.model.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class HandTest : StringSpec({
    "Hand 의 sum 은 정확한 값을 반환하야 한다" {
        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight))))

        hand.valueSum() shouldBe 18
    }

    "Hand 의 sum 이 21이면 blackjack 이다" {
        val hand = Hand(
            Cards(
                mutableListOf(
                    Card(Suit.Spade, Character.Jack),
                    Card(Suit.Clover, Character.Eight),
                    Card(Suit.Clover, Character.Three),
                )
            )
        )

        hand.valueSum() shouldBe 21
        hand.isBlackjack() shouldBe true
        hand.isBust() shouldBe false
    }

    "Hand 의 sum 이 21을 초과하면 bust 이다" {
        val hand = Hand(
            Cards(
                mutableListOf(
                    Card(Suit.Spade, Character.Jack),
                    Card(Suit.Clover, Character.Eight),
                    Card(Suit.Clover, Character.Five),
                )
            )
        )

        hand.valueSum() shouldBe 23
        hand.isBlackjack() shouldBe false
        hand.isBust() shouldBe true
    }

    "Hand 의 sum 이 21인 Player 는 blackjack 상태가 된다" {
        val hand = Hand(
            Cards(
                mutableListOf(
                    Card(Suit.Spade, Character.Jack),
                    Card(Suit.Clover, Character.Eight),
                    Card(Suit.Clover, Character.Three),
                )
            )
        )
        val player = Player(hand)

        hand.valueSum() shouldBe 21
        hand.isBlackjack() shouldBe true
        hand.isBust() shouldBe false
        player.state shouldBe PlayerState.Blackjack
    }

    "Hand 의 sum 이 21을 초과하는 Player 는 bust 상태가 된다" {
        val hand = Hand(
            Cards(
                mutableListOf(
                    Card(Suit.Spade, Character.Jack),
                    Card(Suit.Clover, Character.Eight),
                    Card(Suit.Clover, Character.Five),
                )
            )
        )
        val player = Player(hand)

        hand.valueSum() shouldBe 23
        hand.isBlackjack() shouldBe false
        hand.isBust() shouldBe true
        player.state shouldBe PlayerState.Bust
    }
})
