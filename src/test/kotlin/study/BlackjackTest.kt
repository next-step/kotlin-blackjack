package study

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions
import java.lang.IllegalArgumentException

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
    fun hit() {
        require(state != PlayerState.Idle) {
            "Invalid state transition: $state -> ${PlayerState.Hit}"
        }
        state = PlayerState.Hit
    }

    fun stay() {
        require(state == PlayerState.Idle)
        state = PlayerState.Stay
    }

    fun addCard(card: Card) {
        require(state == PlayerState.Hit) {
            "Invalid state transition: $state -> ${PlayerState.Hit}"
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
