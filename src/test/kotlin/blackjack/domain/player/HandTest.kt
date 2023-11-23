package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Character
import blackjack.domain.card.Suit
import blackjack.domain.cards.HandCards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions
import java.lang.IllegalArgumentException

class HandTest : StringSpec({
    "Hand 는 초기에 2 장의 카드로 이루어져야 한다" {
        Assertions.assertThatThrownBy {
            Hand(
                HandCards(
                    mutableListOf(
                        Card(Suit.Spade, Character.Jack),
                        Card(Suit.Clover, Character.Ace),
                        Card(Suit.Clover, Character.Ace),
                    )
                )
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Hand should be initialized with 2 cards")
    }

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

    "Hand 의 sum 이 21인 Player 는 blackjack 상태가 된다" {
        val hand = Hand(
            HandCards(
                mutableListOf(
                    Card(Suit.Spade, Character.Jack),
                    Card(Suit.Clover, Character.Ace),
                )
            )
        )
        val player = Player("aaa", hand)

        player.addCard(Card(Suit.Diamond, Character.Jack))

        hand.valueSum() shouldBe 21
        hand.isBlackjack() shouldBe true
        hand.isBust() shouldBe false
        player.state shouldBe PlayerState.Blackjack
    }

    "Hand 의 sum 이 21을 초과하는 Player 는 bust 상태가 된다" {
        val hand = Hand(
            HandCards(
                mutableListOf(
                    Card(Suit.Spade, Character.Two),
                    Card(Suit.Clover, Character.Jack),
                )
            )
        )

        val player = Player("aaa", hand)

        player.addCard(Card(Suit.Diamond, Character.Jack))

        hand.valueSum() shouldBe 22
        hand.isBlackjack() shouldBe false
        hand.isBust() shouldBe true
        player.state shouldBe PlayerState.Bust
    }
})
