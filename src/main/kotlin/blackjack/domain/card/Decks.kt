package blackjack.domain.card

class Decks(decks: List<Deck>) {

    private val _decks: MutableList<Deck> = decks.toMutableList()

    fun getDeck(): Deck = _decks.removeFirst()
}
