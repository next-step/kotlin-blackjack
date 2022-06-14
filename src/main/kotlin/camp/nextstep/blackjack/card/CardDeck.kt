package camp.nextstep.blackjack.card

class CardDeck private constructor(private val _cards: MutableList<Card>) {

    val cards get() = _cards.toList()

    val isNotEmpty get() = _cards.isNotEmpty()

    val size get() = _cards.size

    fun draw(): Card {
        return _cards.removeFirst()
    }

    companion object {
        fun new(): CardDeck {
            return CardDeck(Cards.ofCombinations().toMutableList())
        }

        fun of(cards: List<Card>): CardDeck {
            return CardDeck(cards.toMutableList())
        }
    }
}
