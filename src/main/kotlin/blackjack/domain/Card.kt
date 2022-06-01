package blackjack.domain

enum class Behavior {
    HIT, STAY
}

class Player(
    val name: String,
    val cards: MutableList<Card> = mutableListOf()
) {
    val point: Int
        get() = cards.fold(0) { point, card ->
            point + card.number.value
        }

    fun hit(card: Card) {
        cards.add(card)
    }

    fun stay() {
    }
}

class Deck private constructor(
    private val cards: MutableList<Card>
) {

    fun shuffle() {
        cards.shuffle()
    }

    fun draw(): Card {
        check(cards.isNotEmpty())
        return cards.removeLast()
    }

    companion object {
        fun init(): Deck {
            val cards = mutableListOf<Card>()
            Suit.values().forEach { suit ->
                CardNumber.values().forEach { cardNumber ->
                    cards.add(Card(cardNumber, suit))
                }
            }
            return Deck(cards)
        }
    }
}

data class Card(
    val number: CardNumber,
    val suit: Suit
) {

    override fun toString(): String {
        return "${number.displayedName}${suit.type}"
    }
}

enum class Suit(
    val type: String
) {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아")
}

enum class CardNumber(
    val value: Int,
    val displayedName: String
) {
    ACE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");
}
