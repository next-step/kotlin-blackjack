package blackjack.domain.card

class Deck private constructor(val cards: MutableSet<Card>) {

    fun getOneCard(): Card {
        return cards.first().also { cards.remove(it) }
    }

    companion object {
        fun makeDeck(): Deck {
            val cards = Pattern.values().flatMap { pattern ->
                NumberShape.values().map { number ->
                    Card(number, pattern)
                }
            }.toMutableSet()
            return Deck(cards)
        }
    }
}
