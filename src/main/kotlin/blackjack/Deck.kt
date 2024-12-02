package blackjack

class Deck {
    fun getSingleCard(): Card? {
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
