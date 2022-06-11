package camp.nextstep.blackjack

class CardDeck private constructor(val cards: MutableList<Card>) {

    val isNotEmpty get() = cards.isNotEmpty()

    fun draw(): Card {
        return cards.removeFirst()
    }

    companion object {
        fun new(): CardDeck {
            return CardDeck(Card.ofCombinations().toMutableList())
        }
    }
}
