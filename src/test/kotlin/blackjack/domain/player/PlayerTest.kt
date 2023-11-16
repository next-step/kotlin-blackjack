package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.model.Card
import blackjack.model.Character
import blackjack.model.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions
import java.lang.RuntimeException

class PlayerTest : StringSpec({
    "Player 는 Idle 일 때 hit 할 수 있다" {
        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight))))
        val idlePlayer = Player("aaa", hand)

        idlePlayer.state shouldBe PlayerState.Idle
        idlePlayer.hit()
        idlePlayer.state shouldBe PlayerState.Hit
    }

    "Player 가 addCard 하면 Player 의 hand 의 카드 수는 증가해야 한다" {
        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight))))
        val player = Player("aaa", hand)

        hand.valueSum() shouldBe 18

        player.hit()
        val prevCardCount = player.hand.cards.size
        player.addCard(Card(Suit.Heart, Character.Seven))
        player.hand.cards.size shouldBe prevCardCount + 1
    }

    "Player 가 bust 상태가 되면 hit 할 수 없다" {
        val bustHand = Hand(
            Cards(
                mutableListOf(
                    Card(Suit.Spade, Character.Jack),
                    Card(Suit.Clover, Character.Eight),
                    Card(Suit.Clover, Character.Five),
                )
            )
        )
        val bustPlayer = Player("bust", bustHand)

        bustHand.isBust() shouldBe true
        bustPlayer.state shouldBe PlayerState.Bust

        Assertions.assertThatThrownBy {
            bustPlayer.hit()
        }.isInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("Invalid state transition")
    }

    "Player 가 blackjack 상태가 되면 hit 할 수 없다" {
        val blackjackHand = Hand(
            Cards(
                mutableListOf(
                    Card(Suit.Spade, Character.Jack),
                    Card(Suit.Clover, Character.Eight),
                    Card(Suit.Clover, Character.Three),
                )
            )
        )
        val blackjackPlayer = Player("bj", blackjackHand)

        blackjackHand.isBlackjack() shouldBe true
        blackjackPlayer.state shouldBe PlayerState.Blackjack

        Assertions.assertThatThrownBy {
            blackjackPlayer.hit()
        }.isInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("Invalid state transition")
    }

    "Player 는 stay 후엔 다시 hit 할 수 없다" {
        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight))))
        val stayPlayer = Player("aaa", hand)
        stayPlayer.stay()

        stayPlayer.state shouldBe PlayerState.Stay

        Assertions.assertThatThrownBy {
            stayPlayer.hit()
        }.isInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("Invalid state transition")
    }

    "Player 가 hit 상태가 되면 hit 할 수 없다" {
        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight))))
        val idlePlayer = Player("aaa", hand)

        idlePlayer.state shouldBe PlayerState.Idle
        idlePlayer.hit()
        idlePlayer.state shouldBe PlayerState.Hit

        Assertions.assertThatThrownBy {
            idlePlayer.hit()
        }.isInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("Invalid state transition")
    }
})
