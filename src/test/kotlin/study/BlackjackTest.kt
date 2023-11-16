package study

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
        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight), Card(Suit.Clover, Character.Three))))

        hand.valueSum() shouldBe 21
        hand.isBlackjack() shouldBe true
        hand.isBust() shouldBe false
    }

    "Hand 의 sum 이 21을 초과하면 bust 이다" {
        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight), Card(Suit.Clover, Character.Five))))

        hand.valueSum() shouldBe 23
        hand.isBlackjack() shouldBe false
        hand.isBust() shouldBe true
    }

    "Hand 의 sum 이 21인 Player 는 blackjack 상태가 된다" {
        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight), Card(Suit.Clover, Character.Three))))
        val player = Player(hand)

        hand.valueSum() shouldBe 21
        hand.isBlackjack() shouldBe true
        hand.isBust() shouldBe false
        player.state shouldBe PlayerState.Blackjack
    }

    "Hand 의 sum 이 21을 초과하는 Player 는 bust 상태가 된다" {
        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight), Card(Suit.Clover, Character.Five))))
        val player = Player(hand)

        hand.valueSum() shouldBe 23
        hand.isBlackjack() shouldBe false
        hand.isBust() shouldBe true
        player.state shouldBe PlayerState.Bust
    }

    "Player 는 Idle 일 때만 hit 할 수 있다" {
        val bustHand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight), Card(Suit.Clover, Character.Five))))
        val bustPlayer = Player(bustHand)

        bustHand.isBust() shouldBe true
        bustPlayer.state shouldBe PlayerState.Bust

        Assertions.assertThatThrownBy {
            bustPlayer.hit()
        }.isInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("Invalid state transition")

        val blackjackHand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight), Card(Suit.Clover, Character.Three))))
        val blackjackPlayer = Player(blackjackHand)

        blackjackHand.isBlackjack() shouldBe true
        blackjackPlayer.state shouldBe PlayerState.Blackjack

        Assertions.assertThatThrownBy {
            blackjackPlayer.hit()
        }.isInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("Invalid state transition")

        val hand = Hand(Cards(mutableListOf(Card(Suit.Spade, Character.Jack), Card(Suit.Clover, Character.Eight))))
        val stayPlayer = Player(hand)
        stayPlayer.stay()

        stayPlayer.state shouldBe PlayerState.Stay

        Assertions.assertThatThrownBy {
            stayPlayer.hit()
        }.isInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("Invalid state transition")

        val idlePlayer = Player(hand)

        idlePlayer.state shouldBe PlayerState.Idle
        idlePlayer.hit()
        idlePlayer.state shouldBe PlayerState.Hit

        Assertions.assertThatThrownBy {
            idlePlayer.hit()
        }.isInstanceOf(RuntimeException::class.java)
            .hasMessageContaining("Invalid state transition")
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
})

class Deck(val cards: Cards) {
    init {
        require(cards.size == initialCardCount) { "Invalid initial Card count ${cards.size}" }
        require(cards.cardList.count { it.suit == Suit.Spade } == Character.values().size) { "Invalid suit count ${Suit.Spade}" }
        require(cards.cardList.count { it.suit == Suit.Diamond } == Character.values().size) { "Invalid suit count ${Suit.Diamond}" }
        require(cards.cardList.count { it.suit == Suit.Heart } == Character.values().size) { "Invalid suit count ${Suit.Heart}" }
        require(cards.cardList.count { it.suit == Suit.Clover } == Character.values().size) { "Invalid suit count ${Suit.Clover}" }
        require(cards.cardList.toSet().size == initialCardCount) { "Duplicate cards ${cards.cardList}" }
    }

    fun draw(): Card = cards.drawTop()

    fun shuffle() {
        cards.shuffle()
    }

    fun cardCount(): Int = cards.size

    companion object {
        val initialCardCount = Suit.values().size * Character.values().size

        fun fullDeck(): Deck {
            return Deck(Cards.fullCards())
        }
    }
}

data class Cards(private val _cardList: MutableList<Card>) {
    val cardList get() = _cardList.toList()
    val size get() = _cardList.size

    fun shuffle() {
        _cardList.shuffle()
    }

    fun drawTop(): Card = _cardList.removeAt(0)

    fun add(card: Card) {
        _cardList.add(card)
    }

    companion object {
        fun fullCards(): Cards {
            val mutableList = mutableListOf<Card>()
            Suit.values().forEach { suit ->
                Character.values().forEach { character ->
                    mutableList.add(Card(suit, character))
                }
            }
            return Cards(mutableList)
        }
    }
}

data class Card(
    val suit: Suit,
    val character: Character
)

enum class Suit {
    Spade, Diamond, Heart, Clover
}

enum class Character(val value: Int) {
    Ace(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Ten(10),
    Jack(10),
    Queen(10),
    King(10),
}

enum class PlayerState {
    Idle, Hit, Stay, Bust, Blackjack
}

data class Hand(val cards: Cards) {
    fun addCard(card: Card) {
        cards.add(card)
    }
    fun valueSum() : Int = cards.cardList.sumOf { it.character.value }

    fun isBlackjack() = valueSum() == blackjack

    fun isBust() = valueSum() > blackjack

    companion object {
        const val blackjack = 21
    }
}

data class Player(
    val hand: Hand,
    var state: PlayerState = PlayerState.Idle
) {
    init {
        if (hand.isBlackjack()) {
            state = PlayerState.Blackjack
        } else if (hand.isBust()) {
            state = PlayerState.Bust
        }
    }
    fun hit() {
        require(state == PlayerState.Idle) {
            RuntimeException("Invalid state transition: $state -> ${PlayerState.Hit}")
        }
        state = PlayerState.Hit
    }

    fun stay() {
        require(state == PlayerState.Idle)
        state = PlayerState.Stay
    }

    fun addCard(card: Card) {
        require(state == PlayerState.Hit) {
            RuntimeException("Invalid state transition: $state -> ${PlayerState.Hit}")
        }
        hand.addCard(card)
        if (hand.isBust()) {
            state = PlayerState.Bust
        }

        if (hand.isBlackjack()) {
            state = PlayerState.Blackjack
        }
    }
}
