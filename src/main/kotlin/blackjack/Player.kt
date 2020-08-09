package blackjack

class Player(name: String) {

    private val _myReceivedDeck = mutableListOf<Deck>()
    val myReceivedDeck: List<Deck> get() = _myReceivedDeck

    fun requestDeck(deck: Deck) {
        _myReceivedDeck.add(deck)
    }
}
