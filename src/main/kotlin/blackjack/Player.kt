package blackjack

class Player(name: String) {

    private val _myReceivedDeck = mutableListOf<Deck>()
    val myReceivedDeck: List<Deck> get() = _myReceivedDeck

    init {
        repeat(INIT_DECK_SIZE) {
            requestDeck()
        }
    }

    fun requestDeck() {
        _myReceivedDeck.add(Deck.pop())
    }

    companion object {
        private const val INIT_DECK_SIZE = 2
    }
}
