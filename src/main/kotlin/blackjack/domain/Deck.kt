package blackjack.domain

class Deck(private var cards: Set<Card>) {
    val size: Int
        get() = cards.size

    fun giveCard(): Card {
        val cardPicked = cards.first()
        return cardPicked
    }


    companion object {


        private val DEFAULT_DECK = Suit.values().flatMap { cardPackOfSuit(it) }.toSet()
        fun defaultDeck() =
            Deck(DEFAULT_DECK.shuffled().toSet())
        private fun cardPackOfSuit(suit: Suit): Set<Card> {
            return Denomination.values().map { denomination -> Card(denomination, suit) }.toSet()
        }
    }
}

fun main() {
    val deck = Deck.defaultDeck()
    println(deck.giveCard())
}
