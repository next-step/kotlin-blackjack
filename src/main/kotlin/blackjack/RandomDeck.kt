package blackjack

class RandomDeck : Deck {
    override fun draw(): Card? {
        CACHED_CARDS.shuffle()
        return CACHED_CARDS.removeLastOrNull()
    }

    companion object {
        private val CACHED_CARDS =
            CardNumber.entries.flatMap { number ->
                CardMark.entries.map { mark -> Card(number, mark) }
            }.toMutableList()
    }
}
