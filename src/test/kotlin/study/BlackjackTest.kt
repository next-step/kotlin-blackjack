package study

import io.kotest.core.spec.style.StringSpec
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

        private fun fullDeck(): Deck {
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
