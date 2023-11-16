package study

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Character
import blackjack.domain.card.Deck
import blackjack.domain.card.Suit
import blackjack.domain.player.Hand
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerState
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

class BlackjackTest : StringSpec({
    "Deck 은 52장의 중복 없는 카드로 이루어진다" {
        Assertions.assertThatThrownBy {
            Deck(Cards(mutableListOf(Card(Suit.Spade, Character.Ace))))
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Invalid initial Card count")

        Assertions.assertThatThrownBy {
            val cards = Cards.fullCards()
            val card = cards.drawTop()

            cards.add(Card(Suit.values().first { it != card.suit }, Character.Ace))
            Deck(cards)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Invalid suit count")

        Assertions.assertThatThrownBy {
            val cards = Cards.fullCards()
            val card = cards.drawTop()

            cards.add(Card(card.suit, Character.values().first { it != card.character }))
            Deck(cards)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Duplicate cards")
    }

    "Deck 에서 draw 하면 Deck 의 카드 수는 1 감소해야 한다" {
        val deck = Deck.fullDeck()
        val prevSize = deck.cardCount()
        deck.draw()
        val nowSize = deck.cardCount()
        prevSize shouldBe nowSize + 1
    }

    "Deck 에서 draw 한 카드는 Deck 에서 사라져야 한다" {
        val deck = Deck.fullDeck()
        val card = deck.draw()

        deck.cards.cardList.contains(card) shouldBe false
    }

    "Hand 의 sum 은 정확한 값을 반환하야 한다" {
        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight))))

        hand.valueSum() shouldBe 18
    }

    "Hand 의 sum 이 21이면 blackjack 이다" {
        val hand = Hand(
            Cards(
                mutableListOf(
                    Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight), Card(
                        Suit.Clover, Character.Three
                    )
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
                    Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight), Card(
                        Suit.Clover, Character.Five
                    )
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
                    Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight), Card(
                        Suit.Clover, Character.Three
                    )
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
                    Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight), Card(
                        Suit.Clover, Character.Five
                    )
                )
            )
        )
        val player = Player(hand)

        hand.valueSum() shouldBe 23
        hand.isBlackjack() shouldBe false
        hand.isBust() shouldBe true
        player.state shouldBe PlayerState.Bust
    }

    "Player 는 Idle 일 때 hit 할 수 있다" {
        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight))))
        val idlePlayer = Player(hand)

        idlePlayer.state shouldBe PlayerState.Idle
        idlePlayer.hit()
        idlePlayer.state shouldBe PlayerState.Hit
    }

    "Player 가 addCard 하면 Player 의 hand 의 카드 수는 증가해야 한다" {
        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight))))
        val player = Player(hand)

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
                    Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight), Card(
                        Suit.Clover, Character.Five
                    )
                )
            )
        )
        val bustPlayer = Player(bustHand)

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
                    Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight), Card(
                        Suit.Clover, Character.Three
                    )
                )
            )
        )
        val blackjackPlayer = Player(blackjackHand)

        blackjackHand.isBlackjack() shouldBe true
        blackjackPlayer.state shouldBe PlayerState.Blackjack

        Assertions.assertThatThrownBy {
            blackjackPlayer.hit()
        }.isInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("Invalid state transition")
    }

    "Player 는 stay 후엔 다시 hit 할 수 없다" {
        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight))))
        val stayPlayer = Player(hand)
        stayPlayer.stay()

        stayPlayer.state shouldBe PlayerState.Stay

        Assertions.assertThatThrownBy {
            stayPlayer.hit()
        }.isInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("Invalid state transition")
    }

    "Player 가 hit 상태가 되면 hit 할 수 없다" {
        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight))))
        val idlePlayer = Player(hand)

        idlePlayer.state shouldBe PlayerState.Idle
        idlePlayer.hit()
        idlePlayer.state shouldBe PlayerState.Hit

        Assertions.assertThatThrownBy {
            idlePlayer.hit()
        }.isInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("Invalid state transition")
    }
})
